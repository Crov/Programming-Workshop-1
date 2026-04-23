package workshop.project.carl;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

enum Genre {
    Fantasy, Horror, Mystery, SciFi, Thriller, Sports, Poetry
}

public class Book {
    private SimpleStringProperty isbn;
    private SimpleStringProperty title;
    private SimpleStringProperty author;
    private LocalDate releaseDate;
    private Genre genre;
    private SimpleStringProperty ageGroup;
    private SimpleDoubleProperty rating;
    private SimpleBooleanProperty inStock;
    private SimpleStringProperty description;

    public Book(String isbn, String title, String author, LocalDate releaseDate, Genre genre,
                String ageGroup, double rating, boolean inStock, String description) {
        this.isbn = new SimpleStringProperty(isbn);
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.ageGroup = new SimpleStringProperty(ageGroup);
        this.rating = new SimpleDoubleProperty(rating);
        this.inStock = new SimpleBooleanProperty(inStock);
        this.description = new SimpleStringProperty(description);
    }

    public String getIsbn() {return isbn.get();}
    public void setIsbn(String isbn) {this.isbn.set(isbn);}

    public String getTitle() {return title.get();}
    public void setTitle(String title) {this.title.set(title);}

    public String getAuthor() {return author.get();}
    public void setAuthor(String author) {this.author.set(author);}

    public LocalDate getReleaseDate() {return releaseDate;}
    public void setReleaseDate(LocalDate releaseDate) {this.releaseDate = releaseDate;}

    public Genre getGenre() {return genre;}
    public void setGenre(Genre genre) {this.genre = genre;}

    public String getAgeGroup() {return ageGroup.get();}
    public void setAgeGroup(String ageGroup) {this.ageGroup.set(ageGroup);}

    public double getRating() {return rating.get();}
    public void setRating(double rating) {this.rating.set(rating);}

    public boolean isInStock() {return inStock.get();}
    public void setInStock(boolean inStock) {this.inStock.set(inStock);}

    public String getDescription() {return description.get();}
    public void setDescription(String description) {this.description.set(description);}
}
