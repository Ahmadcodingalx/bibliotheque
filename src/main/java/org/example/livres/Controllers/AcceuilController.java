package org.example.livres.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.livres.BookData;
import org.example.livres.GetData;
import org.example.livres.HelloApplication;
import org.example.livres.database;

import javax.swing.text.html.ImageView;
import java.io.File;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class AcceuilController implements Initializable {

    @FXML
    private Label acceuil_LD;

    @FXML
    private Label acceuil_TM;

    @FXML
    private Label acceuil_TV;

    @FXML
    private Button acceuil_btn;

    @FXML
    private AnchorPane acceuil_form;

    @FXML
    private BarChart<?, ?> acceuil_statistiqueClient;

    @FXML
    private AreaChart<?, ?> acceuil_statistiqueRevenus;

    @FXML
    private Button close;

    @FXML
    private Button close1;

    @FXML
    private TextField livreDispo_Auteur;

    @FXML
    private DatePicker livreDispo_Date;

    @FXML
    private TextField livreDispo_Genre;

    @FXML
    private TextField livreDispo_ID;

    @FXML
    private TextField livreDispo_Prix;

    @FXML
    private TextField livreDispo_Titre;

    @FXML
    private TableColumn<?, ?> livreDispo_col_Auteur;

    @FXML
    private TableColumn<?, ?> livreDispo_col_Date;

    @FXML
    private TableColumn<?, ?> livreDispo_col_Genre;

    @FXML
    private TableColumn<?, ?> livreDispo_col_ID;

    @FXML
    private TableColumn<?, ?> livreDispo_col_Prix;

    @FXML
    private TableColumn<?, ?> livreDispo_col_Titre;

    @FXML
    private AnchorPane livreDispo_form;

    @FXML
    private javafx.scene.image.ImageView livreDispo_imageView;

//    public ImageView livreDispo_imageView;

    @FXML
    private Button livreDispo_importBtn;

    @FXML
    private TextField livreDispo_search;

    @FXML
    private TableView<BookData> livreDispo_tableView;

    @FXML
    private Button livresDisponible_btn;

    @FXML
    private Button logout;

    @FXML
    private AnchorPane main_form;

    @FXML
    private ComboBox<?> pannier_ID;

    @FXML
    private ComboBox<?> pannier_Titre;

    @FXML
    private Label pannier_Total;

    @FXML
    private Button pannier_addBtn;

    @FXML
    private Button pannier_btn;

    @FXML
    private TableColumn<BookData, String> pannier_col_Auteur;

    @FXML
    private TableColumn<BookData, String> pannier_col_Genre;

    @FXML
    private TableColumn<BookData, String> pannier_col_ID;

    @FXML
    private TableColumn<BookData, String> pannier_col_Prix;

    @FXML
    private TableColumn<BookData, String> pannier_col_Quantité;

    @FXML
    private TableColumn<BookData, String> pannier_col_Titre;

    @FXML
    private AnchorPane pannier_form;

    @FXML
    private Label pannier_info_Auteur;

    @FXML
    private Label pannier_info_Date;

    @FXML
    private Label pannier_info_Genre;

    @FXML
    private Label pannier_info_ID;

    @FXML
    private Label pannier_info_Titre;

    @FXML
    private Button pannier_payBtn;

    @FXML
    private TableView<?> pannier_tableView;

    @FXML
    private Label username;

    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Connection connection;

    public ObservableList<BookData> BookListe() {

        ObservableList<BookData> listData = FXCollections.observableArrayList();
        String req = "SELECT * FROM book";
        connection = database.connectDB();

        try {
            assert connection != null;
            preparedStatement = connection.prepareStatement(req);
            resultSet = preparedStatement.executeQuery();

            BookData bookData;

            while (resultSet.next()) {

                bookData = new BookData(
                        resultSet.getInt("ID_book"),
                        resultSet.getString("titre"),
                        resultSet.getString("auteur"),
                        resultSet.getString("genre"),
                        resultSet.getDate("date_de_publication"),
                        resultSet.getDouble("prix"),
                        resultSet.getString("image")
                );

                listData.add(bookData);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listData;
    }

    private Image images;

    private ObservableList<BookData> availableBookList;

    public void BookShow() {

        availableBookList = BookListe();

        livreDispo_col_ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        livreDispo_col_Titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        livreDispo_col_Auteur.setCellValueFactory(new PropertyValueFactory<>("auteur"));
        livreDispo_col_Genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        livreDispo_col_Date.setCellValueFactory(new PropertyValueFactory<>("dateDePublication"));
        livreDispo_col_Prix.setCellValueFactory(new PropertyValueFactory<>("prix"));

        livreDispo_tableView.setItems(availableBookList);
    }

    public  void bookAdd() {

        String sql = "INSERT INTO book (ID_book, titre, auteur, genre, date_de_publication, prix, image) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        connection = database.connectDB();

        try {

            Alert alert;

            if (livreDispo_ID.getText().isEmpty() || livreDispo_Titre.getText().isEmpty() ||
                    livreDispo_Auteur.getText().isEmpty() || livreDispo_Genre.getText().isEmpty() ||
                    livreDispo_Date.getValue() == null || livreDispo_Prix.getText().isEmpty() ||
                    GetData.path == null || GetData.path.isEmpty()) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("S'il vous plait, remplir tout les champs");
                alert.showAndWait();

            } else {

                String check = "SELECT ID_book FROM book WHERE ID_book = '" + livreDispo_ID.getText() + "'";

                Statement statement = connection.createStatement();
                resultSet = statement.executeQuery(check);

                if (resultSet.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Message d'erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("ID: " + livreDispo_ID.getText() + " existe dèjà!");
                    alert.showAndWait();
                } else {

                    assert connection != null;
                    preparedStatement = connection.prepareStatement(sql);

                    preparedStatement.setString(1, livreDispo_ID.getText());
                    preparedStatement.setString(2, livreDispo_Titre.getText());
                    preparedStatement.setString(3, livreDispo_Auteur.getText());
                    preparedStatement.setString(4, livreDispo_Genre.getText());
                    preparedStatement.setString(5, String.valueOf(livreDispo_Date.getValue()));
                    preparedStatement.setString(6, livreDispo_Prix.getText());

                    String uri = GetData.path;
                    uri = uri.replace("\\", "\\\\");

                    preparedStatement.setString(7, uri);

                    preparedStatement.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Message d'information");
                    alert.setHeaderText(null);
                    alert.setContentText("Ajout réussie");
                    alert.showAndWait();

                    BookShow();
                    bookClear();
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void bookClear() {

        livreDispo_ID.setText("");
        livreDispo_Titre.setText("");
        livreDispo_Auteur.setText("");
        livreDispo_Genre.setText("");
        livreDispo_Date.setValue(null);
        livreDispo_Prix.setText("");

        GetData.path = "";
        livreDispo_imageView.setImage(null);

    }

    public void updateBook() {

        String uri = GetData.path;
        uri = uri.replace("\\", "\\\\");

        String updateReq = "UPDATE book SET ID_book = ?, titre = ?, auteur = ?, genre = ?, date_de_publication = ?, " +
                "prix = ?, image = ? WHERE ID_book = ?";

        Connection connection = database.connectDB();

        try {
            Alert alert;

            if (livreDispo_ID.getText().isEmpty() || livreDispo_Titre.getText().isEmpty() ||
                    livreDispo_Auteur.getText().isEmpty() || livreDispo_Genre.getText().isEmpty() ||
                    livreDispo_Date.getValue() == null || livreDispo_Prix.getText().isEmpty() ||
                    GetData.path == null || GetData.path.isEmpty()) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("Remplir tout les champs");
                alert.showAndWait();

            } else {

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Message de confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Voulez-vous vraiment modifier ID: " + livreDispo_ID.getText() + " ?");

                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {

                    assert connection != null;
                    preparedStatement = connection.prepareStatement(updateReq);

                    preparedStatement.setString(1, livreDispo_ID.getText());
                    preparedStatement.setString(2, livreDispo_Titre.getText());
                    preparedStatement.setString(3, livreDispo_Auteur.getText());
                    preparedStatement.setString(4, livreDispo_Genre.getText());
                    preparedStatement.setString(5, String.valueOf(livreDispo_Date.getValue()));
                    preparedStatement.setString(6, livreDispo_Prix.getText());
                    preparedStatement.setString(7, uri);
                    preparedStatement.setString(8, livreDispo_ID.getText());

                    preparedStatement.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Message d'information");
                    alert.setHeaderText(null);
                    alert.setContentText("Modifer avec succès!");
                    alert.showAndWait();

                    BookShow();
                    bookClear();

                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void bookSearch() {

        FilteredList<BookData> filter = new FilteredList<>(availableBookList, e -> true);

        livreDispo_search.textProperty().addListener((Observable, oldValue, newValue) -> {

            filter.setPredicate(predicateBookData -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String searchKey = newValue.toLowerCase();

                if (predicateBookData.getID().toString().contains(searchKey)) {
                    return true;
                } else if (predicateBookData.getTitre().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateBookData.getAuteur().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateBookData.getGenre().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateBookData.getDateDePublication().toString().contains(searchKey)) {
                    return true;
                } else if (predicateBookData.getPrix().toString().contains(searchKey)) {
                    return true;
                } else return false;
            });
        });

        SortedList<BookData> sortedList = new SortedList<>(filter);
        sortedList.comparatorProperty().bind(livreDispo_tableView.comparatorProperty());
        livreDispo_tableView.setItems(sortedList);

    }

    public void bookInfo() {

        String sql = "SELECT * FROM book WHERE titre = ?";
        connection = database.connectDB();

        String id = "";
        String titre = "";
        String auteur = "";
        String genre = "";
        String date = "";

        try {
            assert connection != null;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, (String) pannier_Titre.getSelectionModel().getSelectedItem());
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                id = resultSet.getString("ID_book");
                titre = resultSet.getString("titre");
                auteur = resultSet.getString("auteur");
                genre = resultSet.getString("genre");
                date = resultSet.getString("date_de_publication");

            }

            pannier_info_ID.setText(id);
            pannier_info_Titre.setText(titre);
            pannier_info_Auteur.setText(auteur);
            pannier_info_Genre.setText(genre);
            pannier_info_Date.setText(date);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void pannierBook() {

        String sql = "SELECT ID_book FROM book";
        connection = database.connectDB();

        try {
            assert connection != null;
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            ObservableList observableList = FXCollections.observableArrayList();

            while (resultSet.next()) {
                observableList.add(resultSet.getString("ID_book"));
            }

            pannier_ID.setItems(observableList);
            bookTitre();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    
    public void bookTitre() {

        String sql = "SELECT ID_book, titre FROM book WHERE ID_book = ?";
        connection = database.connectDB();

        try {
            assert connection != null;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, (String) pannier_ID.getSelectionModel().getSelectedItem());

            resultSet = preparedStatement.executeQuery();

            ObservableList observableList = FXCollections.observableArrayList();

            while (resultSet.next()) {
                observableList.add(resultSet.getString("titre"));
            }

            pannier_Titre.setItems(observableList);
            bookInfo();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public void deleteBook() {

        String sqldelete = "DELETE FROM book WHERE ID_book  = ?";

        Connection connection = database.connectDB();

        try {
            Alert alert;

            if (livreDispo_ID.getText().isEmpty() || livreDispo_Titre.getText().isEmpty() ||
                    livreDispo_Auteur.getText().isEmpty() || livreDispo_Genre.getText().isEmpty() ||
                    livreDispo_Date.getValue() == null || livreDispo_Prix.getText().isEmpty() ||
                    GetData.path == null || GetData.path.isEmpty()) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Message d'erreur");
                alert.setHeaderText(null);
                alert.setContentText("Choisir d'abord le livre à supprimer");
                alert.showAndWait();

            } else {

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Message de confiramtion");
                alert.setHeaderText(null);
                alert.setContentText("Voulez-vous vraiment supprimer ID: " +livreDispo_ID.getText()+ " ?");

                Optional<ButtonType> optional = alert.showAndWait();

                if (optional.get().equals(ButtonType.OK)) {
                    PreparedStatement prepar = connection.prepareStatement(sqldelete);
                    prepar.setString(1, livreDispo_ID.getText());
                    prepar.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Message d'information");
                    alert.setHeaderText(null);
                    alert.setContentText("Suppression réussie!");
                    alert.showAndWait();

                    BookShow();
                    bookClear();

                } else {
                    return;
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void bookImage() {

        FileChooser img = new FileChooser();
        img.setTitle("Ouvrire l'image");
        img.getExtensionFilters().add(new FileChooser.ExtensionFilter("File image", "*jpg", "*png"));

        File file = img.showOpenDialog(main_form.getScene().getWindow());

        if (file != null) {
            GetData.path = file.getAbsolutePath();

            images = new Image(file.toURI().toString(), 112, 135, false, true);
            livreDispo_imageView.setImage(images);
        }

    }

    public void bookSelected() {

        BookData bookData = (BookData) livreDispo_tableView.getSelectionModel().getSelectedItem();

        int num = livreDispo_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) { return; }

        livreDispo_ID.setText(String.valueOf(bookData.getID()));
        livreDispo_Titre.setText(bookData.getTitre());
        livreDispo_Auteur.setText(bookData.getAuteur());
        livreDispo_Genre.setText(bookData.getGenre());
        livreDispo_Date.setValue(LocalDate.parse(String.valueOf(bookData.getDateDePublication())));
        livreDispo_Prix.setText(String.valueOf(bookData.getPrix()));

        GetData.path = bookData.getImage();

        String uri = "file:" + bookData.getImage();

        images = new Image(uri, 112, 135, false, true);
        livreDispo_imageView.setImage(images);

    }

    public void displayUsername() {
        String user = GetData.username;
        user = user.substring(0, 1).toUpperCase() + user.substring(1);
        username.setText(user);
    }


    public void switchForm(ActionEvent event) {

        if (event.getSource() == acceuil_btn) {
            acceuil_form.setVisible(true);
            livreDispo_form.setVisible(false);
            pannier_form.setVisible(false);

            acceuil_btn.setStyle("-fx-background-color : linear-gradient(to left, #dda6ff, #cc75ff, #cc75ff, #cc75ff, #dda6ff);");
            livresDisponible_btn.setStyle("-fx-background-color : transparent;");
            pannier_btn.setStyle("-fx-background-color : transparent;");

        } else if (event.getSource() == livresDisponible_btn) {
            acceuil_form.setVisible(false);
            livreDispo_form.setVisible(true);
            pannier_form.setVisible(false);

            acceuil_btn.setStyle("-fx-background-color : transparent;");
            livresDisponible_btn.setStyle("-fx-background-color : linear-gradient(to left, #dda6ff, #cc75ff, #cc75ff, #cc75ff, #dda6ff);");
            pannier_btn.setStyle("-fx-background-color : transparent;");

            BookShow();
            bookSearch();

        } else if (event.getSource() == pannier_btn) {
            acceuil_form.setVisible(false);
            livreDispo_form.setVisible(false);
            pannier_form.setVisible(true);

            acceuil_btn.setStyle("-fx-background-color : transparent;");
            livresDisponible_btn.setStyle("-fx-background-color : transparent;");
            pannier_btn.setStyle("-fx-background-color : linear-gradient(to left, #dda6ff, #cc75ff, #cc75ff, #cc75ff, #dda6ff);");

            bookTitre();
            pannierBook();
        }

    }
//    public void

    private double x = 0;
    private double y = 0;

    public void logout() {
        try {

            // Alert pour confirmation de l'action
            Alert alt = new Alert(Alert.AlertType.CONFIRMATION);
            alt.setTitle("Message de confirmation");
            alt.setHeaderText(null);
            alt.setContentText("Êtes-vous sûr de vouloir vous déconnecter ?");
            Optional<ButtonType> oooption = alt.showAndWait();

            if (oooption.get().equals(ButtonType.OK)) { // Condition si on clique sur le button OK

                // Pour cacher la fenêtre de la page d'acceuil
                logout.getScene().getWindow().hide();

                // Pour naviguer vers la fenêtre de connexion
                Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("connection.fxml")));

                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) ->{
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) ->{ //Responsable de l'oppaciter l'orsqu'on traîne l'écran.
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

                    stage.setOpacity(.8);
                });

                root.setOnMouseReleased((MouseEvent event) ->{
                    stage.setOpacity(1);
                });

                stage.initStyle(StageStyle.TRANSPARENT); // Pour faire disparaitre la petite bande en haut de l'écran

                stage.setTitle("Connection");
                stage.setScene(scene);
                stage.show();
            }
        }catch (Exception excp) {
            excp.printStackTrace();
        }
    }

    // Methode qui permet de fermer la fenêtre
    public void close() {
        System.exit(0);
    }

    // Methode qui permet de reduire la fenêtre
    public void minimize() {
        Stage stg = (Stage) main_form.getScene().getWindow();
        stg.setIconified(true);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        bookTitre();
        pannierBook();
    }
}
