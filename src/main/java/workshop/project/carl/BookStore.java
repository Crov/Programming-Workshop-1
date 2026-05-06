package workshop.project.carl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import workshop.project.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class BookStore {
    private final ObservableList<Book> books = FXCollections.observableArrayList();

    public BookStore() {
        loadBooks();
    }

    public ObservableList<Book> getBooksList() {
        return books;
    }

    public void addBook(Book book) {
        if (book == null) return;

        String query = "INSERT INTO books VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, book.getIsbn());
            stmt.setString(2, book.getTitle());
            stmt.setString(3, book.getAuthor());
            stmt.setDate(4, java.sql.Date.valueOf(book.getReleaseDate()));
            stmt.setString(5, book.getGenre().name());
            stmt.setString(6, book.getAgeGroup());
            stmt.setDouble(7, book.getRating());
            stmt.setBoolean(8, book.isInStock());
            stmt.setString(9, book.getDescription());

            stmt.executeUpdate();
            books.add(book);
        } catch (SQLException e) {
            if (e.getMessage().contains("Duplicate entry")) {
                throw new RuntimeException("ISBN already exists.");
            }

            throw new RuntimeException(e);
        }
    }

    public void deleteBook(Book book) {
        if (book == null) return;

        String query = "DELETE FROM books WHERE isbn = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, book.getIsbn());
            stmt.executeUpdate();

            books.remove(book);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateBook(Book book, String isbn, String title, String author,LocalDate releaseDate,
                             Genre genre, String ageGroup, double rating, boolean inStock, String description) {
        if (book == null) return;

        String query = """
                UPDATE books
                SET isbn=?, title=?, author=?, release_date=?, genre=?,
                    age_group=?, rating=?, in_stock=?, description=?
                WHERE isbn=?
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, isbn);
            stmt.setString(2, title);
            stmt.setString(3, author);
            stmt.setDate(4, java.sql.Date.valueOf(releaseDate));
            stmt.setString(5, genre.name());
            stmt.setString(6, ageGroup);
            stmt.setDouble(7, rating);
            stmt.setBoolean(8, inStock);
            stmt.setString(9, description);
            stmt.setString(10, book.getIsbn());

            stmt.executeUpdate();

            book.setIsbn(isbn);
            book.setTitle(title);
            book.setAuthor(author);
            book.setReleaseDate(releaseDate);
            book.setGenre(genre);
            book.setAgeGroup(ageGroup);
            book.setRating(rating);
            book.setInStock(inStock);
            book.setDescription(description);
        } catch (SQLException e) {
            if (e.getMessage().contains("Duplicate entry")) {
                throw new RuntimeException("ISBN already exists.");
            }

            throw new RuntimeException(e);
        }
    }

    public void loadBooks() {
        String query = "SELECT * FROM books";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery();) {

            while (rs.next()) {
                Book book = new Book(
                        rs.getString("isbn"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getDate("release_date").toLocalDate(),
                        Genre.valueOf(rs.getString("genre")),
                        rs.getString("age_group"),
                        rs.getDouble("rating"),
                        rs.getBoolean("in_stock"),
                        rs.getString("description")
                );

                books.add(book);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
