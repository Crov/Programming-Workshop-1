package workshop.project.carl;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;


public class BookController {

    @FXML
    private TableColumn<Book, String> AgeCol;

    @FXML
    private TableColumn<Book, String> AuthorCol;

    @FXML
    private TextField AuthorFld;

    @FXML
    private DatePicker DateFld;

    @FXML
    private TextArea DescriptionFld;

    @FXML
    private TableColumn<Book, Genre> GenreCol;

    @FXML
    private ChoiceBox<Genre> GenreFld;

    @FXML
    private TableColumn<Book, String> ISBNCol;

    @FXML
    private TextField ISBNFld;

    @FXML
    private TableColumn<Book, Double> RatingCol;

    @FXML
    private Spinner<Double> RatingFld;

    @FXML
    private TableColumn<Book, String> TitleCol;

    @FXML
    private TextField TitleFld;

    @FXML
    private Button addBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private TableView<Book> table;

    @FXML
    private Button updateBtn;

    @FXML
    private CheckBox stockBox;

    @FXML private RadioButton age1;
    @FXML private RadioButton age2;
    @FXML private RadioButton age3;
    @FXML private RadioButton age4;
    @FXML private RadioButton age5;
    private ToggleGroup ageGroup;

    private final BookStore bookStore = new BookStore();

    private Book selectedBook;

    @FXML
    public void initialize() {
        ISBNCol.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        TitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        AuthorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        AgeCol.setCellValueFactory(new PropertyValueFactory<>("ageGroup"));
        GenreCol.setCellValueFactory(new PropertyValueFactory<>("genre"));
        RatingCol.setCellValueFactory(new PropertyValueFactory<>("rating"));

        bookStore.addBook(new Book("9780152023980", "The Little Prince", "Antoine de Saint-Exupéry", LocalDate.parse("1943-04-06"), Genre.Fantasy, "5-8", 5.0, true, "A pilot stranded in the desert meets a mysterious young boy who calls himself the Little Prince. As the boy shares stories of his travels from planet to planet, he reveals simple yet profound truths about friendship, love, loneliness, and what truly matters in life. Through their encounter, the pilot comes to see the world through the eyes of a child—and learns that what is essential is invisible to the eye."));
        bookStore.addBook(new Book("9780062315007", "The Alchemist", "Paulo Coelho", LocalDate.parse("1993-05-01"), Genre.Fantasy, "13-18", 3.92, true, "The Alchemist follows Santiago, a young Andalusian shepherd who repeatedly dreams of a hidden treasure buried near the Egyptian pyramids. Motivated by this vision, he leaves Spain and embarks on a journey across North Africa in search of his “Personal Legend” — his life’s true purpose.\nAlong the way, he encounters a series of figures who shape his understanding of the world, including a king, a crystal merchant, an Englishman studying alchemy, and an alchemist. Through these encounters, Santiago learns to interpret the language of the world, trust his intuition, and recognize that the journey itself is as meaningful as the destination.\nAt its core, the story explores themes of destiny, spirituality, and self-discovery, emphasizing that when a person truly wants something, the universe conspires to help them achieve it."));
        table.setItems(bookStore.getBooksList());

        GenreFld.setItems(FXCollections.observableArrayList(Genre.values()));
        GenreFld.setValue(Genre.values()[0]);

        RatingFld.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 5.0, 0.0, 0.5));

        ageGroup = new ToggleGroup();
        age1.setToggleGroup(ageGroup);
        age2.setToggleGroup(ageGroup);
        age3.setToggleGroup(ageGroup);
        age4.setToggleGroup(ageGroup);
        age5.setToggleGroup(ageGroup);
        age1.setSelected(true);


        table.getSelectionModel().selectedItemProperty().addListener(e -> {
            Book book = table.getSelectionModel().getSelectedItem();
            if (book != null) {
                selectedBook = book;

                ISBNFld.setText(selectedBook.getIsbn());
                TitleFld.setText(selectedBook.getTitle());
                AuthorFld.setText(selectedBook.getAuthor());
                DateFld.setValue(selectedBook.getReleaseDate());
                DescriptionFld.setText(selectedBook.getDescription());
                GenreFld.setValue(selectedBook.getGenre());
                RatingFld.getValueFactory().setValue(selectedBook.getRating());

                switch(selectedBook.getAgeGroup()) {
                    case "0-4" -> ageGroup.selectToggle(age1);
                    case "5-8" -> ageGroup.selectToggle(age2);
                    case "9-12" -> ageGroup.selectToggle(age3);
                    case "13-18" -> ageGroup.selectToggle(age4);
                    case "18+" -> ageGroup.selectToggle(age5);
                }
            }
        });
    }

    @FXML
    void add(ActionEvent event) {
        String age = ((RadioButton) ageGroup.getSelectedToggle()).getText();

        Book book = new Book(
                ISBNFld.getText(),
                TitleFld.getText(),
                AuthorFld.getText(),
                DateFld.getValue(),
                GenreFld.getValue(),
                age,
                RatingFld.getValue(),
                stockBox.isSelected(),
                DescriptionFld.getText()
        );

        bookStore.addBook(book);
        clearFields();
    }

    @FXML
    void delete(ActionEvent event) {
        selectedBook = table.getSelectionModel().getSelectedItem();

        if (selectedBook != null) {
            bookStore.deleteBook(selectedBook);
            clearFields();
        }
    }

    @FXML
    void update(ActionEvent event) {
        selectedBook = table.getSelectionModel().getSelectedItem();

        if (selectedBook != null) {
            RadioButton selected = (RadioButton) ageGroup.getSelectedToggle();
            String age = selected != null ? selected.getText() : "";

            bookStore.updateBook(
                    selectedBook,
                    ISBNFld.getText(),
                    TitleFld.getText(),
                    AuthorFld.getText(),
                    DateFld.getValue(),
                    GenreFld.getValue(),
                    age,
                    RatingFld.getValue(),
                    stockBox.isSelected(),
                    DescriptionFld.getText()
            );

            table.refresh();
        }
    }


    private void clearFields() {
        ISBNFld.clear();
        TitleFld.clear();
        AuthorFld.clear();
        DescriptionFld.clear();
        DateFld.setValue(null);
        GenreFld.setValue(Genre.values()[0]);
        RatingFld.getValueFactory().setValue(0.0);
        ageGroup.selectToggle(age1);
        selectedBook = null;
        stockBox.setSelected(false);
    }
}
