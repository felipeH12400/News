package cl.ucn.disc.dsm.fherrera.news.services;


import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

import cl.ucn.disc.dsm.fherrera.news.model.News;

public final class TestNews {

    private static final Logger log = LoggerFactory.getLogger(TestNews.class);
    @Test
    public void testConstructor() {
        log.debug("Testing ..");
        News news = new News(
                "The Title",
                "The Source",
                "The Author",
                "The URL",
                "The URL Image",
                "The Description",
                "The Content",
                ZonedDateTime.now(ZoneId.of("-3"))
        );

        log.debug("The id: {}.", news.getId());
        Assertions.assertEquals(1182003507361219134L, news.getId(), "Wrong id !");
        log.debug("Title null ..");
        Assertions.assertEquals(1182003507361219134L, news.getId(), "Wrong id !");
        log.debug("Title null ..");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new News(null,
                    "The Source",
                    "The Author",
                    "The URL",
                    "The URL Image",
                    "The Description",
                    "The Content",
                    ZonedDateTime.now(ZoneId.of("-3"))
            );

        });

        log.debug("Source null ..");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new News("the title",
                    null,
                    "the autor",
                    " the url ",
                    "url image",
                    "descripcion",
                    "content",
                    ZonedDateTime.now(ZoneId.of("-3"))
            );
        });

        log.debug("Author null ..");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new News("the title",
                    "source",
                    null,
                    " the url ",
                    "url image",
                    "descripcion",
                    "content",
                    ZonedDateTime.now(ZoneId.of("-3"))
            );
        });

        log.debug("descripcion null ..");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new News("the title",
                    "source",
                    "the autor",
                    " the url ",
                    "url image",
                    null,
                    "content",
                    ZonedDateTime.now(ZoneId.of("-3"))
            );
        });

        log.debug("Content null ..");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new News("the title",
                    "source",
                    "the autor",
                    " the url ",
                    "url image",
                    "descripcion",
                    null,
                    ZonedDateTime.now(ZoneId.of("-3"))
            );
        });

        log.debug("publishedAt null ..");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new News("the title",
                    "source",
                    "the autor",
                    " the url ",
                    "url image",
                    "descripcion",
                    "content",
                    null
            );
        });

        log.debug(".. Done!");
    }


















}
