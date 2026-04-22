package workshop.project.library.carl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public class BookStore {
    private final ObservableList<Book> books = FXCollections.observableArrayList();

    public BookStore() {}

    public ObservableList<Book> getBooksList() {
        return books;
    }

    public void addBook(Book book) {
        if(book != null) {
            this.books.add(book);
        }
    }

    public void deleteBook(Book book) {
        if (book != null) {
            this.books.remove(book);
        }
    }

    public void updatePerson(Book book, String isbn, String title, String author,LocalDate releaseDate,
                             Genre genre, String ageGroup, double rating, boolean inStock) {
        if (book != null) {
            book.setIsbn(isbn);
            book.setTitle(title);
            book.setAuthor(author);
            book.setReleaseDate(releaseDate);
            book.setGenre(genre);
            book.setAgeGroup(ageGroup);
            book.setRating(rating);
            book.setInStock(inStock);
        }
    }
}
