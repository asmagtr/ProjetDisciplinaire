import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("votre_fichier.fxml"));
        primaryStage.setTitle("Votre titre");
        primaryStage.setScene(new Scene(root, 1364, 707));
        primaryStage.show();

        // Créez et ajoutez des instances de FilmPane dynamiquement ici
        FilmPane film1 = new FilmPane("url_image", "Titre film 1", "Durée film 1", "Genre film 1", "Description film 1");
        FilmPane film2 = new FilmPane("url_image", "Titre film 2", "Durée film 2", "Genre film 2", "Description film 2");

        // Accédez au FlowPane à partir du fichier FXML
        FlowPane flowPane = (FlowPane) root.lookup("#flowPane");

        // Ajoutez les instances de FilmPane au FlowPane
        flowPane.getChildren().addAll(film1, film2);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
