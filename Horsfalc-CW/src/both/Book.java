package both;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Book implements Serializable {

    private int bookId; // primary key
    private String title;
    private String authors;
    private int average_rating;
    private double isbn;
    private double isbn13;
    private String language_code;
    private int num_pages;
    private int rating_count;
    private int text_review_count;
    private int quantity;

    public Book(int bookId, String title, String authors, int average_rating, double isbn, double isbn13, String language_code, int num_pages, int rating_count, int text_review_count, int quantity ) {

        this.bookId = bookId;
        this.title = title;
        this.authors = authors;
        this.average_rating = average_rating;
        this.isbn = isbn;
        this.isbn13 = isbn13;
        this.language_code = language_code;
        this.num_pages = num_pages;
        this.rating_count = rating_count;
        this.text_review_count = text_review_count;
        this.quantity = quantity;
    }

    public static Book newBookFromResultSet(ResultSet resultSet) throws SQLException {
        return new  both.Book(
                resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getInt(4),
                resultSet.getDouble(5),
                resultSet.getDouble(6),
                resultSet.getString(7),
                resultSet.getInt(8),
                resultSet.getInt(9),
                resultSet.getInt(10),
                resultSet.getInt(11));

    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public int getAverage_rating() {
        return average_rating;
    }

    public void setAverage_rating(int average_rating) {
        this.average_rating = average_rating;
    }

    public double getIsbn() {
        return isbn;
    }

    public void setIsbn(double isbn) {
        this.isbn = isbn;
    }

    public double getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(double isbn13) {
        this.isbn13 = isbn13;
    }

    public String getLanguage_code() { return language_code; }

    public void setLanguage_code(String language_code) {
        this.language_code = language_code;
    }

    public int getNum_pages() {
        return num_pages;
    }

    public void setNum_pages(int num_pages) {
        this.num_pages = num_pages;
    }

    public int getRating_count() {
        return rating_count;
    }

    public void setRating_count(int rating_count) {
        this.rating_count = rating_count;
    }

    public int getText_review_count() {
        return text_review_count;
    }

    public void setText_review_count(int text_review_count) {
        this.text_review_count = text_review_count;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }




}



