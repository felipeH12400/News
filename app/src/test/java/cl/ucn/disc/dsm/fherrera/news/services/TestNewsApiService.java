package cl.ucn.disc.dsm.fherrera.news.services;

import com.kwabenaberko.newsapilib.models.Article;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public final class TestNewsApiService {

    private static final Logger log = LoggerFactory.getLogger(TestNewsApiService.class);

    @Test
    public void wrongApi() throws IOException {
        log.debug("Testing ..");
        Assertions.assertThrows(IllegalArgumentException.class, () ->{
            NewsApiService newsApiService = new   NewsApiService(null);
        });

        log.debug("Wrong key ..");
        Assertions.assertThrows(RuntimeException.class, () -> {
            NewsApiService newsApiService = new NewsApiService("This is my wrong key");
            List<Article> articles = newsApiService.getTopHeadlines("general",  10 );
            log.debug("Articles: {}.", articles);
        });

        log.debug("Good key ..");
        {
            NewsApiService newsApiService = new NewsApiService("<USE REAL APIKEY>");
            List<Article> articles = newsApiService.getTopHeadlines("general", 10);
            log.debug("Articles: {}.", articles);
            log.debug("Articles size: {}.", articles.size());
        }
        {
            //Agregar mas testing
        }
        log.debug(".. Done.");

    }

}
