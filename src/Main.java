import java.sql.Connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            // Établir une connexion à la base de données
            connection = DriverManager.getConnection("jdbc:postgresql://cinemadb-gtrasma.k.aivencloud.com:19697/defaultdb", "avnadmin", "AVNS_O7FNZcgdSruUsgcp3SB");

            // Créer une instance de GestionFilms en lui passant la connexion
            GestionFilms gestionFilms = new GestionFilms(connection);

            // Création d'un film
            Film monFilm = new Film(1, "Titre du Film", "Genre du Film", "Description du Film", 120, "Vo");

            // Ajouter le film à la base de données
            gestionFilms.ajouterFilm(monFilm);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fermer la connexion à la base de données
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}