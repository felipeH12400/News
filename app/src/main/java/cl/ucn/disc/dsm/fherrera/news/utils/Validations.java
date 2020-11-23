package cl.ucn.disc.dsm.fherrera.news.utils;

public final class Validations {

    /**
     *
     * @param title
     * @param MinSize
     * @param message
     */
    public static void MinSize(String title, int MinSize, String message) {
        //null?
        notNull(title, message);
        if (title.length() < MinSize) {
            throw new IllegalArgumentException(" Wrong size " + message);
        }

    }

    /**
     *
     * @param value
     * @param message
     */
    public static void notNull(Object value, String message){
        if (value == null) {
            throw new IllegalArgumentException(" Title was null ");
        }

    }
}
