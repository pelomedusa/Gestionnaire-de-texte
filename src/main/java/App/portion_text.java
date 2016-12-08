package App;

import App.categorie;

/**
 * Created by Bobol on 06/12/2016.
 */
public class portion_text {
    private int id;
    private int id_categorie;
    private String contenu;
    private String mot_cle;

    public portion_text(int id, int id_categorie, String contenu, String mot_cle) {
        this.id = id;
        this.id_categorie = id_categorie;
        this.contenu = contenu;
        this.mot_cle = mot_cle;
    }


}
