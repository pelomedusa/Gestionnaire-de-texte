package App;

/**
 * Created by Bobol on 06/12/2016.
 */
public class Categorie {
    protected int id;
    protected String libelle;
    protected int id_parent;


    public Categorie(int id, String libelle, int id_parent) {
        this.id = id;
        this.libelle = libelle;
        this.id_parent = id_parent;
    }

    public Categorie(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
        this.id_parent = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getId_parent() {
        return id_parent;
    }

    public void setId_parent(int id_parent) {
        this.id_parent = id_parent;
    }

    @Override
    public String toString() {
        return libelle;
    }
}
