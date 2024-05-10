import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;


public class Comptes {
    private int id;
    private int id_employe;
    private String username;
    private String password;
    private String type;

    // Constructeur
    public Comptes(int id, int id_employe, String username, String password, String type) {
        this.id = id;
        this.id_employe = id_employe;
        this.username = username;
        this.password = password;
        this.type = type;
    }

    // Méthode pour enregistrer le compte dans la base de données
    public void save(Connection connection) throws SQLException {
        String hashedPassword = BCrypt.hashpw(this.password, BCrypt.gensalt());
        String sql = "INSERT INTO comptes (id, id_employe, username, password, type) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, this.id);
            statement.setInt(2, this.id_employe);
            statement.setString(3, this.username);
            statement.setString(4, hashedPassword);
            statement.setString(5, this.type);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Compte enregistré avec succès.");
            } else {
                System.out.println("Échec de l'enregistrement du compte.");
            }
        }
    }

    // Méthode pour mettre à jour le compte dans la base de données
    public void update(Connection connection) throws SQLException {
        String hashedPassword = BCrypt.hashpw(this.password, BCrypt.gensalt());
        String sql = "UPDATE comptes SET username = ?, password = ?, type = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, this.username);
            statement.setString(2, hashedPassword);
            statement.setString(3, this.type);
            statement.setInt(4, this.id);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Compte mis à jour avec succès.");
            } else {
                System.out.println("Échec de la mise à jour du compte.");
            }
        }
    }

    // Méthode pour supprimer le compte de la base de données
    public void delete(Connection connection) throws SQLException {
        String sql = "DELETE FROM comptes WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, this.id);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Compte supprimé avec succès.");
            } else {
                System.out.println("Échec de la suppression du compte.");
            }
        }
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_employe() {
        return id_employe;
    }

    public void setId_employe(int id_employe) {
        this.id_employe = id_employe;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

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
