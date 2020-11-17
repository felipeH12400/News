package cl.ucn.disc.dsm.fherrera.news.services;

import java.util.List;

import cl.ucn.disc.dsm.fherrera.news.model.News;


public interface Contracts {


    List<News> retrieveNews(Integer size);


    void saveNews(News news);

}
