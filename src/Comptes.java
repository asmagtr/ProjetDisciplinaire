import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//import org.mindrot.jbcrypt.BCrypt;




public class Comptes {
//    private int id;
//    private int id_employe;
    private String username;
    private String nom;
    private String prenom;
    private String password;
    private String type;

    // Constructeur
    public Comptes(String username,String nom, String prenom, String password, String type) {

        this.username = username;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.type = type;
    }

    // Méthode pour enregistrer le compte dans la base de données
    public void save(Connection connection) throws SQLException {
        String hashedPassword = BCrypt.hashpw(this.password, BCrypt.gensalt());
        String sql = "INSERT INTO comptes (username, password, type,nom,prenom) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, this.username);
            statement.setString(2, hashedPassword);
            statement.setString(3 , this.type);
            statement.setString(4, this.nom);
            statement.setString(5, this.prenom);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Compte enregistré avec succès.");
            } else {
                System.out.println("Échec de l'enregistrement du compte.");
            }
        }
    }

    // Méthode pour mettre à jour le compte dans la base de données
    public void updateType(Connection connection,String newType) throws SQLException {
        String hashedPassword = BCrypt.hashpw(this.password, BCrypt.gensalt());
        String sql = "UPDATE comptes SET type = ? WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, newType);
            statement.setString(2, this.username);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Compte mis à jour avec succès.");
            } else {
                System.out.println("Échec de la mise à jour du compte.");
            }
        }
    }

    public void updateUsername(Connection connection, String newUsername) throws SQLException {
        String sql = "UPDATE comptes SET username = ? WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newUsername);
            statement.setString(2, this.username);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Nom d'utilisateur mis à jour avec succès.");
                this.username = newUsername; // Update object state
            } else {
                System.out.println("Échec de la mise à jour du nom d'utilisateur.");
            }
        }
    }


    public void updatePassword(Connection connection, String newPassword) throws SQLException {
        String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
        String sql = "UPDATE comptes SET password = ? WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, hashedPassword);
            statement.setString(2, this.username);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Mot de passe mis à jour avec succès.");
            } else {
                System.out.println("Échec de la mise à jour du mot de passe.");
            }
        }
    }


    // Méthode pour supprimer le compte de la base de données
    public void delete(Connection connection) throws SQLException {
        String sql = "DELETE FROM comptes WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, this.username);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Compte supprimé avec succès.");
            } else {
                System.out.println("Échec de la suppression du compte.");
            }
        }
    }

    // Getters et Setters


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//    public String getPassword() {
//        return password;
//    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
