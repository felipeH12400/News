package cl.ucn.disc.dsm.fherrera.news.services;

import com.kwabenaberko.newsapilib.models.Article;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import cl.ucn.disc.dsm.fherrera.news.model.News;
import cl.ucn.disc.dsm.fherrera.news.utils.Validations;

public final class ContractsImplNewsApi implements Contracts {

    private static final Logger log = LoggerFactory.getLogger(ContractsImplNewsApi.class);
    private final NewsApiService newsApiService;

    public ContractsImplNewsApi(final String theApiKey){
        Validations.MinSize(theApiKey, 10 ,  "ApiKey !!");
        this.newsApiService = new NewsApiService(theApiKey);

    }
    private static News toNews(final Article article) {
        Validations.notNull(article, "Article null !?!");

        boolean needFix = false;

        if (article.getAuthor() == null || article.getAuthor().length() == 0) {
            article.setAuthor("No author*");
            needFix = true;
        }

        if (article.getDescription() == null || article.getDescription().length() == 0) {
            article.setDescription("No description*");
            needFix = true;
        }

        if (needFix) {
            log.warn("Article with invalid restrictions: {}.", ToStringBuilder.reflectionToString(
                    article, ToStringStyle.MULTI_LINE_STYLE
            ));
        }

        ZonedDateTime publishedAt = ZonedDateTime
                .parse(article.getPublishedAt())
                .withZoneSameInstant(ZoneId.of("-3"));

        return new News(article.getTitle(),
                article.getSource().getName(),
                article.getAuthor(),
                article.getUrl(),
                article.getUrlToImage(),
                article.getDescription(),
                article.getDescription(),
                publishedAt
        );
    }
    private static <T> Predicate<T> distintByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;

    }

    @Override
    public List<News> retrieveNews(final Integer size) {
        try   {
            List<Article> articles = newsApiService.getTopHeadlines(
                    "technology", size);
            List<News> news = new ArrayList<>();
            for(Article article : articles) {
            news.add(toNews(article));
            }
            return news.stream()
                    .filter(distintByKey(News::getId))
                    .sorted((k1, k2) -> k2.getPublishedAt().compareTo(k1.getPublishedAt()))
                    .collect(Collectors.toList());




        } catch (IOException ex) {
            log.error("Error", ex);
            return null;
        }


    }

    @Override
    public void saveNews(final News news) {
        throw new NotImplementedException("Can't save in NewsAPI");

    }
}
