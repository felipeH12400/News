package cl.ucn.disc.dsm.fherrera.news.services;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import cl.ucn.disc.dsm.fherrera.news.model.News;

public final class TestContractsImplNewsApi {

    private static final Logger log = LoggerFactory.getLogger(TestContractsImplNewsApi.class);
    @Test
    public void testRetrieveNews(){
        log.debug("Testing ..");
        //Cambiar apikey
        Contracts contracts = new   ContractsImplNewsApi("641505b3411341d9b8376a84ff42dc20");
        int  size = 20;
        List<News> news = contracts.retrieveNews(size);
        Assertions.assertNotNull(news, "List null !!");
        Assertions.assertEquals(size, news.size(), "Wrong size!");
        for   (News n : news){
            log.debug("News: {}.", ToStringBuilder.reflectionToString(n, ToStringStyle.MULTI_LINE_STYLE));

        }
        log.debug(".. end.");
    }

}
