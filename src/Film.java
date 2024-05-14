public class Film {
    private int id;
    private String titre;
    private String genre;
    private String descreption;
    private int duree;
    private String version; // Vo ou Vf

    public Film(int id, String titre, String genre, String descreption, int duree, String version) {

        this.id = id;
        this.titre = titre;
        this.genre = genre;
        this.descreption = descreption;
        this.duree = duree;
        this.version = version;

    }

    public int getId() {
        return id;
    }
    public String getTitre() {
        return titre;
    }
    public String getGenre() {
        return genre;
    }
    public String getDescreption() {
        return descreption;
    }
    public int getDuree() {
        return duree;
    }
    public String getVersion() {
        return version;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public void setDescreption(String descreption) {
        this.descreption = descreption;
    }
    public void setDuree(int duree) {
        this.duree = duree;
    }
    public void setVersion(String version) {
        this.version = version;
    }

}