package workshop.project.carl;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


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
    private CheckBox StockBox;

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
                StockBox.setSelected(selectedBook.isInStock());

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
                StockBox.isSelected(),
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
                    StockBox.isSelected(),
                    DescriptionFld.getText()
            );

            clearFields();
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
        StockBox.setSelected(false);
    }
}
