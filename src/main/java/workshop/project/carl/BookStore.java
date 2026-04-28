package workshop.project.carl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public class BookStore {
    private final ObservableList<Book> books = FXCollections.observableArrayList();

    public BookStore() {
        this.books.addAll(
                (new Book("9780152023980", "The Little Prince", "Antoine de Saint-Exupéry", LocalDate.parse("1943-04-06"), Genre.Fantasy, "5-8", 5.0, true, "A pilot stranded in the desert meets a mysterious young boy who calls himself the Little Prince. As the boy shares stories of his travels from planet to planet, he reveals simple yet profound truths about friendship, love, loneliness, and what truly matters in life. Through their encounter, the pilot comes to see the world through the eyes of a child—and learns that what is essential is invisible to the eye.")),
                (new Book("9780062315007", "The Alchemist", "Paulo Coelho", LocalDate.parse("1993-05-01"), Genre.Fantasy, "13-18", 3.92, false, "The Alchemist follows Santiago, a young Andalusian shepherd who repeatedly dreams of a hidden treasure buried near the Egyptian pyramids. Motivated by this vision, he leaves Spain and embarks on a journey across North Africa in search of his “Personal Legend” — his life’s true purpose.\nAlong the way, he encounters a series of figures who shape his understanding of the world, including a king, a crystal merchant, an Englishman studying alchemy, and an alchemist. Through these encounters, Santiago learns to interpret the language of the world, trust his intuition, and recognize that the journey itself is as meaningful as the destination.\nAt its core, the story explores themes of destiny, spirituality, and self-discovery, emphasizing that when a person truly wants something, the universe conspires to help them achieve it."))
        );
    }


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

    public void updateBook(Book book, String isbn, String title, String author,LocalDate releaseDate,
                             Genre genre, String ageGroup, double rating, boolean inStock, String description) {
        if (book != null) {
            book.setIsbn(isbn);
            book.setTitle(title);
            book.setAuthor(author);
            book.setReleaseDate(releaseDate);
            book.setGenre(genre);
            book.setAgeGroup(ageGroup);
            book.setRating(rating);
            book.setInStock(inStock);
            book.setDescription(description);
        }
    }
}
