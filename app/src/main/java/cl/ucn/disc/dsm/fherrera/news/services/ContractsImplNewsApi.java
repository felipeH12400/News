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

        if (article.getAuthor() == null) {
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

    @Override
    public List<News> retrieveNews(Integer size) {
        try   {
            List<Article> articles = newsApiService.getTopHeadlines(
                    "technology", size);
            List<News> news = new ArrayList<>();
            for(Article article : articles) {
            news.add(toNews(article));
            }
            return news;
        } catch (IOException ex) {
            log.error("Error", ex);
            return null;
        }


    }

    @Override
    public void saveNews(News news) {
        throw new NotImplementedException("Can't save in NewsAPI");

    }
}
