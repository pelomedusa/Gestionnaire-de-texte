package App;

/**
 * Created by Bobol on 06/12/2016.
 */
public class categorie {
    protected int id;
    protected String libelle;
    protected int id_parent;


    public categorie(int id, String libelle, int id_parent) {
        this.id = id;
        this.libelle = libelle;
        this.id_parent = id_parent;
    }

    public categorie(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
        this.id_parent = 0;
    }
}
