package com.library;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.stream.Collectors;

public class Controller {

    @FXML private TextField searchField;
    @FXML private ListView<Movie> movieListView;
    @FXML private MediaView mediaView;
    @FXML private WebView webView;
    @FXML private StackPane mediaContainer;

    @FXML private Label lblTitle;
    @FXML private Label lblGenre;
    @FXML private Label lblImdbRating;
    @FXML private Label lblPersonalRating;
    @FXML private TextArea txtDescription;
    @FXML private TextArea txtImpressions;
    @FXML private Spinner<Integer> spinnerPersonalRating;

    private ObservableList<Movie> allMovies = FXCollections.observableArrayList();
    private MediaPlayer mediaPlayer;
    private Movie selectedMovie;

    @FXML
    public void initialize() {
        loadDefaultMovies();
        movieListView.setItems(allMovies);

        mediaContainer.setMinWidth(400);
        mediaContainer.setMinHeight(250);
        mediaContainer.setMaxWidth(800);
        mediaContainer.setMaxHeight(450);

        mediaView.setFitWidth(640);
        mediaView.setFitHeight(360);
        mediaView.setPreserveRatio(true);
        mediaView.setSmooth(true);

        mediaView.setFocusTraversable(false);
        mediaContainer.setFocusTraversable(false);

        if (!allMovies.isEmpty()) {
            movieListView.getSelectionModel().selectFirst();
            displayMovie(allMovies.get(0));
        }
    }

    private void loadDefaultMovies() {
        allMovies.add(new Movie("1", "Людина-павук: Крізь Всесвіт", "Мультфільм, Фантастика", 8.6, "Майлз Моралес повертається...", "Шедевр візуального стилю та анімації!", 0, "https://www.youtube.com/watch?v=g4Hbz2EWDZg", false));
        allMovies.add(new Movie("2", "Месники: Завершення", "Фантастика, Екшн", 8.4, "Після руйнівних подій, спричинених Таносом...", "Епічне завершення десятирічної епохи Marvel.", 0, "https://www.youtube.com/watch?v=TcMBFSGVi1c", false));
        allMovies.add(new Movie("3", "Шрек", "Комедія, Мультфільм", 7.9, "Зелений велетень-людожер Шрек...", "Абсолютна класика мого дитинства!", 0, "https://www.youtube.com/watch?v=CwXOrWv589g", false));
        allMovies.add(new Movie("4", "Дюна: Частина друга", "Фантастика, Пригоди", 8.5, "Продовження подорожі Пола Атріда...", "Неймовірний візуальний та звуковий досвід!", 0, "https://www.youtube.com/watch?v=U2Qp5pL396U", false));
        allMovies.add(new Movie("5", "Інтерстеллар", "Фантастика, Драма", 8.7, "Коли посуха та пилові бурі ставлять людство...", "Один із моїх найулюбленіших фільмів Нолана.", 0, "https://www.youtube.com/watch?v=zSWdZAto3Qc", false));
        allMovies.add(new Movie("6", "Гаррі Поттер і філософський камінь", "Фентезі, Пригоди", 7.6, "Одинадцятирічний сирота Гаррі Поттер...", "Фільм, який подарував нам віру в магію.", 0, "https://www.youtube.com/watch?v=mNgwNXKJJW4", false));
        allMovies.add(new Movie("7", "Король Лев", "Мультфільм, Драма", 8.5, "Історія про левеня Сімбу, спадкоємця престолу...", "Неймовірний шедевр від Disney, який не старіє.", 0, "https://www.youtube.com/watch?v=lFzVJEksoDY", false));
        allMovies.add(new Movie("8", "Оппенгеймер", "Біографія, Драма", 8.9, "Епічний біографічний трилер Крістофера Нолана...", "Приголомшлива акторська гра Кілліана Мерфі.", 0, "https://www.youtube.com/watch?v=uYPbbksJxIg", false));
        allMovies.add(new Movie("9", "Рататуй", "Мультфільм, Комедія", 8.1, "Пацюк Ремі володіє унікальним смаком...", "Чудовий, теплий та неймовірно апетитний мультфільм.", 0, "https://www.youtube.com/watch?v=NgsQ8mVEqd8", false));
        allMovies.add(new Movie("10", "Мавка. Лісова пісня", "Мультфільм, Фентезі", 7.3, "Мавка — Душа Лісу — закохується у Лукаша...", "Гордість української анімації! Неймовірно красиво.", 0, "https://www.youtube.com/watch?v=9X5S5f7C1L4", false));
    }

    @FXML
    private void handleMovieSelect() {
        Movie selected = movieListView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            displayMovie(selected);
        }
    }

    private void displayMovie(Movie movie) {
        this.selectedMovie = movie;

        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }

        lblTitle.setText(movie.getTitle());
        lblGenre.setText("Жанр: " + movie.getGenre());

        if (movie.getImdbRating() == 0.0) {
            lblImdbRating.setText("IMDb: -");
        } else {
            lblImdbRating.setText("IMDb: " + movie.getImdbRating());
        }

        lblPersonalRating.setText("Моя оцінка: " + movie.getPersonalRating() + "/10");
        txtDescription.setText(movie.getDescription());
        txtImpressions.setText(movie.getPersonalImpressions());
        spinnerPersonalRating.getValueFactory().setValue(movie.getPersonalRating());

        if (movie.getVideoUrl().startsWith("http://") || movie.getVideoUrl().startsWith("https://")) {
            webView.setVisible(true);
            mediaView.setVisible(false);

            String stubHtml = "<!DOCTYPE html><html><head><style>" +
                    "body { background-color: #09090b; color: #a1a1aa; font-family: 'Segoe UI', sans-serif; " +
                    "display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }" +
                    "h3 { color: #ffffff; text-align: center; font-weight: 500; }" +
                    "</style></head><body>" +
                    "<h3>Трейлер готовий до перегляду в браузері</h3>" +
                    "</body></html>";
            webView.getEngine().loadContent(stubHtml);
        } else {
            webView.setVisible(false);
            mediaView.setVisible(true);
        }
    }

    @FXML
    private void handlePlay() throws Exception {
        if (selectedMovie == null) return;

        if (selectedMovie.getVideoUrl().startsWith("http://") || selectedMovie.getVideoUrl().startsWith("https://")) {
            String url = selectedMovie.getVideoUrl();
            java.awt.Desktop.getDesktop().browse(new java.net.URI(url));
        } else {
            if (mediaPlayer != null) {
                mediaPlayer.play();
            }
        }
    }

    @FXML
    private void handleSaveChanges() {
        if (selectedMovie != null) {
            selectedMovie.setPersonalImpressions(txtImpressions.getText());
            selectedMovie.setPersonalRating(spinnerPersonalRating.getValue());
            lblPersonalRating.setText("Моя оцінка: " + selectedMovie.getPersonalRating() + "/10");

            movieListView.refresh();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Успіх");
            alert.setHeaderText(null);
            alert.setContentText("Ваші зміни та враження збережено!");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleDeleteMovie() {
        Movie selected = movieListView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            allMovies.remove(selected);
            if (!allMovies.isEmpty()) {
                movieListView.getSelectionModel().selectFirst();
                displayMovie(allMovies.get(0));
            } else {
                clearDetails();
            }
        }
    }

    private void clearDetails() {
        lblTitle.setText("Список порожній");
        lblGenre.setText("Жанр: -");
        lblImdbRating.setText("IMDb: -");
        lblPersonalRating.setText("Моя оцінка: -");
        txtDescription.setText("");
        txtImpressions.setText("");
        webView.getEngine().loadContent("");
    }

    @FXML
    private void handleSearch() {
        String query = searchField.getText().toLowerCase();
        if (query.isEmpty()) {
            movieListView.setItems(allMovies);
        } else {
            ObservableList<Movie> filtered = FXCollections.observableArrayList(
                    allMovies.stream()
                            .filter(m -> m.getTitle().toLowerCase().contains(query) ||
                                    m.getGenre().toLowerCase().contains(query))
                            .collect(Collectors.toList())
            );
            movieListView.setItems(filtered);
        }
    }

    @FXML
    private void handleAddNewMovie() {
        Dialog<Movie> dialog = new Dialog<>();
        dialog.setTitle("Додати новий трейлер");
        dialog.setHeaderText("Введіть інформацію про новий фільм або мультфільм:");

        ButtonType btnSubmit = new ButtonType("Додати", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(btnSubmit, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new javafx.geometry.Insets(20, 150, 10, 10));

        TextField txtTitle = new TextField();
        txtTitle.setPromptText("Назва фільму");
        TextField txtGenre = new TextField();
        txtGenre.setPromptText("Комедія, Екшн, Пригоди");
        TextArea txtDesc = new TextArea();
        txtDesc.setPromptText("Опис сюжету...");
        txtDesc.setPrefRowCount(3);

        ToggleGroup group = new ToggleGroup();
        RadioButton rbYoutube = new RadioButton("YouTube посилання");
        rbYoutube.setToggleGroup(group);
        rbYoutube.setSelected(true);
        RadioButton rbLocal = new RadioButton("Локальний відеофайл");
        rbLocal.setToggleGroup(group);

        TextField txtVideoUrl = new TextField();
        txtVideoUrl.setPromptText("https://www.youtube.com/watch?v=...");

        Button btnOpenFile = new Button("Оглянути файл...");
        btnOpenFile.setDisable(true);

        rbYoutube.setOnAction(e -> {
            txtVideoUrl.setDisable(false);
            btnOpenFile.setDisable(true);
        });
        rbLocal.setOnAction(e -> {
            txtVideoUrl.setDisable(true);
            btnOpenFile.setDisable(false);
        });

        btnOpenFile.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Оберіть відеофайл трейлеру");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Відеофайли", "*.mp4", "*.mkv", "*.avi")
            );
            Stage stage = (Stage) btnOpenFile.getScene().getWindow();
            File selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile != null) {
                txtVideoUrl.setText(selectedFile.getAbsolutePath());
            }
        });

        grid.add(new Label("Назва:"), 0, 0);
        grid.add(txtTitle, 1, 0);
        grid.add(new Label("Жанр:"), 0, 1);
        grid.add(txtGenre, 1, 1);
        grid.add(new Label("Джерело відео:"), 0, 2);
        grid.add(new HBox(10, rbYoutube, rbLocal), 1, 2);
        grid.add(new Label("Шлях/Посилання:"), 0, 3);
        grid.add(new HBox(10, txtVideoUrl, btnOpenFile), 1, 3);
        grid.add(new Label("Опис сюжету:"), 0, 4);
        grid.add(txtDesc, 1, 4);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == btnSubmit) {
                return new Movie(
                        String.valueOf(System.currentTimeMillis()),
                        txtTitle.getText(),
                        txtGenre.getText(),
                        0.0,
                        txtDesc.getText(),
                        "Мій новий улюблений трейлер!",
                        0,
                        txtVideoUrl.getText(),
                        true
                );
            }
            return null;
        });

        dialog.showAndWait().ifPresent(newMovie -> {
            allMovies.add(newMovie);
            movieListView.getSelectionModel().select(newMovie);
            displayMovie(newMovie);
        });
    }
}