/**
 * Created by Bobol on 06/12/2016.
 */
import App.categorie;
import App.portion_text;
import Controllers.C_main;
import Controllers.C_window;
import Controllers.bdd;

import javax.sound.midi.ControllerEventListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

public class main {
    private static List<categorie> list_cate;
    private static List<portion_text> list_portion;


    public static void main(String[] args) throws SQLException, ClassNotFoundException, FileNotFoundException {
        /*bdd db = new bdd("test.db");
        db.importSQL(new FileInputStream("db/create.sql"));
        list_cate =  db.getAllCategorie();
        list_portion = db.getAllPortionText();*/

        C_main controller = new C_main();

    }
}
