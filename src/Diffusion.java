
import java.util.Date;

public class Diffusion {
    private Film film;
    private Salle salle;
    private Date date;
    private String heureDebut;
    private String heureFin;

    public Diffusion(Film film, Salle salle, Date date, String heureDebut, String heureFin) {
        this.film = film;
        this.salle = salle;
        this.date = date;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
    }

    // Getter pour l'attribut 'film'
    public Film getFilm() {
        return film;
    }

    // Setter pour l'attribut 'film'
    public void setFilm(Film film) {
        this.film = film;
    }

    // Getter pour l'attribut 'salle'
    public Salle getSalle() {
        return salle;
    }

    // Setter pour l'attribut 'salle'
    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    // Getter pour l'attribut 'date'
    public java.sql.Date getDate() {
        return date;
    }

    // Setter pour l'attribut 'date'
    public void setDate(Date date) {
        this.date = date;
    }

    // Getter pour l'attribut 'heureDebut'
    public String getHeureDebut() {
        return heureDebut;
    }

    // Setter pour l'attribut 'heureDebut'
    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }

    // Getter pour l'attribut 'heureFin'
    public String getHeureFin() {
        return heureFin;
    }

    // Setter pour l'attribut 'heureFin'
    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
    }

    // Méthodes supplémentaires au besoin
}