package cl.ucn.disc.dsm.fherrera.news.services;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import cl.ucn.disc.dsm.fherrera.news.model.News;


public final class TestContractsImplFaker {


    private static final Logger log = LoggerFactory.getLogger(TestContractsImplFaker.class);


    @Test
    public void testRetrieveNews() {

        log.debug("Testing ..");


        Contracts contracts = new ContractsImplFaker();


        List<News> news = contracts.retrieveNews(5);


        Assertions.assertNotNull(news, "List was null ");


        Assertions.assertFalse(news.isEmpty(), "Empty list? ");


        Assertions.assertEquals(5, news.size(), "List size != 5 ");


        for (News n : news) {
            log.debug("News: {}", ToStringBuilder.reflectionToString(n, ToStringStyle.MULTI_LINE_STYLE));
        }


        Assertions.assertEquals(0, contracts.retrieveNews(0).size(), "List != 0");


        Assertions.assertEquals(3, contracts.retrieveNews(3).size(), "List != 3");


        Assertions.assertTrue(contracts.retrieveNews(10).size() <= 10, "List != 10");

        log.debug("Done.");
    }


    @Test
    public void testSaveNews() {

        log.debug("Testing ..");

        // The concrete implementation
        Contracts contracts = new ContractsImplFaker();

        log.debug("Done.");

    }

}
