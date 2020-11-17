package cl.ucn.disc.dsm.fherrera.news.services;

import com.github.javafaker.Faker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cl.ucn.disc.dsm.fherrera.news.model.News;


public final class ContractsImplFaker implements Contracts {


    private static final Logger log = LoggerFactory.getLogger(ContractsImplFaker.class);


    private final List<News> theNews = new ArrayList<>();


    public ContractsImplFaker() {

        // The faker to use
        final Faker faker = Faker.instance();

        for (int i = 0; i < 5; i++) {

            this.theNews.add(new News(
                    Integer.toUnsignedLong(i),
                    faker.book().title(),
                    faker.name().username(),
                    faker.name().fullName(),
                    faker.internet().url(),
                    faker.internet().avatar(),
                    faker.harryPotter().quote(),
                    faker.lorem().paragraph(3),
                    ZonedDateTime.now(ZoneId.of("-3"))
            ));

        }

    }


    @Override
    public List<News> retrieveNews(final Integer size) {

        // Return all the data
        if (size > theNews.size()) {
            return Collections.unmodifiableList(this.theNews);
        }

        // The last "size" elements.
        return Collections.unmodifiableList(
                theNews.subList(theNews.size() - size, theNews.size())
        );
    }

    @Override
    public void saveNews(final News news) {
        this.theNews.add(news);
    }

}

