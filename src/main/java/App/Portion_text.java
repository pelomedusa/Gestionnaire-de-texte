package App;

/**
 * Created by Bobol on 06/12/2016.
 */
public class Portion_text {
    private int id;
    private int id_categorie;
    private String contenu;
    private String mot_cle;

    public Portion_text(int id, int id_categorie, String contenu, String mot_cle) {
        this.id = id;
        this.id_categorie = id_categorie;
        this.contenu = contenu;
        this.mot_cle = mot_cle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getMot_cle() {
        return mot_cle;
    }

    public void setMot_cle(String mot_cle) {
        this.mot_cle = mot_cle;
    }

    @Override
    public String toString() {
        return this.mot_cle;
    }
}
