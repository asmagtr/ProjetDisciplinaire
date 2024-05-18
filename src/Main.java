
import java.sql.Connection;
import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        fonctionsBD db=new fonctionsBD();
        Connection conn=db.connect_to_db("defaultdb","avnadmin","AVNS_O7FNZcgdSruUsgcp3SB");
        if (conn != null) {
            // Créer un nouveau compte à enregistrer
            Comptes nouveauCompte = new Comptes("utilisateur1","nom1","prenom1", "motdepasse123", "admin");

            try {
                // Enregistrer le compte dans la base de données
                nouveauCompte.save(conn);
            } catch (SQLException e) {
                System.err.println("Erreur lors de l'enregistrement du compte : " + e.getMessage());
            }

            // Fermer la connexion à la base de données après utilisation
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
            }
        } else {
            System.err.println("La connexion à la base de données a échoué.");
        }
    }

}