package cl.ucn.disc.dsm.fherrera.news.model;

import net.openhft.hashing.LongHashFunction;

import org.threeten.bp.ZonedDateTime;

import cl.ucn.disc.dsm.fherrera.news.utils.Validations;


public final class News {

    /**
     * Unique id
     */
    private final Long id;

    /**
     * The Title
     */
    private final String title;

    /**
     * The Source
     */
    private final String source;

    /**
     * The Author
     */
    private final String author;

    /**
     * The URL
     */
    private final String url;

    /**
     * The URL of image
     */
    private final String urlImage;

    /**
     * The Description
     */
    private final String description;

    /**
     * The Content
     */
    private final String content;

    /**
     * The Date of publish
     */
    private final ZonedDateTime publishedAt;

    /**
     * The Constructor
     * @param title
     * @param source
     * @param author
     * @param url
     * @param urlImage
     * @param description
     * @param content
     * @param publishedAt
     */
    public News(String title, String source, String author, String url, String urlImage, String description, String content, ZonedDateTime publishedAt) {
        //Validation
        Validations.MinSize(title,2,"title");
        Validations.MinSize(source,2,"source");
        Validations.MinSize(author,2,"author");
        Validations.notNull(content, "Content");
        Validations.notNull(publishedAt, "PublishedAt");
        //xxhashing
        this.id = LongHashFunction.xx().hashChars(title + "|" + source + "|" +  author);
        this.title = title;
        this.source = source;
        this.author = author;
        this.url = url;
        this.urlImage = urlImage;
        this.description = description;
        this.content = content;
        this.publishedAt = publishedAt;
    }

    /**
     * @return the Id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the sources
     */
    public String getSource() {
        return source;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return the image
     */
    public String getUrlImage() {
        return urlImage;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @return the date of publish
     */
    public ZonedDateTime getPublishedAt() {
        return publishedAt;
    }

}
