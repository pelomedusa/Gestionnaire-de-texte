/**
 * Created by Bobol on 06/12/2016.
 */
import App.Categorie;
import App.Portion_text;
import Controllers.C_main;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

public class main {
    private static List<Categorie> list_cate;
    private static List<Portion_text> list_portion;


    public static void main(String[] args) throws SQLException, ClassNotFoundException, FileNotFoundException {

        C_main controller = new C_main();

    }
}
