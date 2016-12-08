package sql;

import App.categorie;
import App.portion_text;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Bobol on 06/12/2016.
 */
public class bdd {

    private String database_name;
    protected Connection connect;

    public bdd(String name) throws ClassNotFoundException, SQLException {
        database_name = name;
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:db/" + name;
        connect = DriverManager.getConnection(url);
    }

    public void importSQL(InputStream in) throws SQLException
    {
        Scanner s = new Scanner(in);
        s.useDelimiter("(;(\r)?\n)|(--\n)");
        Statement st = null;
        try
        {
            st = connect.createStatement();
            while (s.hasNext())
            {
                String line = s.next();
                if (line.startsWith("/*!") && line.endsWith("*/"))
                {
                    int i = line.indexOf(' ');
                    line = line.substring(i + 1, line.length() - " */".length());
                }

                if (line.trim().length() > 0)
                {
                    st.execute(line);
                }
            }
        }
        finally
        {
            if (st != null) st.close();
        }
    }

    public void selectAllFromCategorie() throws SQLException {
        Statement st = connect.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM categorie");
        while ( rs.next() ) {
            int id = rs.getInt("id");
            String libelle = rs.getString("libelle");
            int id_parent = rs.getInt("id_categorie_parent");
            System.out.println("id = " + id);
            System.out.println("libelle = " + libelle);
            System.out.println("id_parent_categorie = " + id_parent);
        }

        st.close();
        rs.close();
    }

    public List<categorie> getAllCategorie() throws SQLException {
        List<categorie> list_cate = new ArrayList<categorie>();
        Statement st = connect.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM categorie");
        while ( rs.next() ) {
            int id = rs.getInt("id");
            String libelle = rs.getString("libelle");
            int id_parent = rs.getInt("id_categorie_parent");
            list_cate.add(new categorie(id,libelle,id_parent));
        }

        st.close();
        rs.close();
        return list_cate;
    }

    public List<portion_text> getAllPortionText() throws SQLException {
        List<portion_text> list_portion = new ArrayList<portion_text>();
        Statement st = connect.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM portion_text");
        while ( rs.next() ) {
            int id = rs.getInt("id");
            String contenu = rs.getString("contenu");
            String mot_cle = rs.getString("mot_cle");
            int id_cat = rs.getInt("id_categorie");
            list_portion.add(new portion_text(id,id_cat,contenu,mot_cle));
        }

        st.close();
        rs.close();
        return list_portion;
    }
}
