package Models;

import App.Categorie;
import App.Portion_text;
import org.sqlite.SQLiteException;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Bobol on 06/12/2016.
 */
public class M_bdd {

    private String database_name;
    protected Connection connect;

    public M_bdd(String name) throws ClassNotFoundException, SQLException {
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

    public void selectAllFromPortion() throws SQLException {
        Statement st = connect.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM portion_text");
        while ( rs.next() ) {
            int id = rs.getInt("id");
            String contenu = rs.getString("contenu");
            String motcle = rs.getString("mot_cle");
            int id_parent = rs.getInt("id_categorie");

            System.out.println("id = " + id);
            System.out.println("contenu = " + contenu);
            System.out.println("motcle = " + motcle);
            System.out.println("id_parent = " + id_parent);
        }

        st.close();
        rs.close();
    }

    public List<Categorie> getAllCategorie() throws SQLException {
        List<Categorie> list_cate = new ArrayList<Categorie>();
        Statement st = connect.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM categorie");
        while ( rs.next() ) {
            int id = rs.getInt("id");
            String libelle = rs.getString("libelle");
            int id_parent = rs.getInt("id_categorie_parent");
            list_cate.add(new Categorie(id,libelle,id_parent));
        }

        st.close();
        rs.close();
        return list_cate;
    }

    public List<Portion_text> getAllPortionText() throws SQLException {
        List<Portion_text> list_portion = new ArrayList<Portion_text>();
        Statement st = connect.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM portion_text");
        while ( rs.next() ) {
            int id = rs.getInt("id");
            String contenu = rs.getString("contenu");
            String mot_cle = rs.getString("mot_cle");
            int id_cat = rs.getInt("id_categorie");
            list_portion.add(new Portion_text(id,id_cat,contenu,mot_cle));
        }

        st.close();
        rs.close();
        return list_portion;
    }

    public void insertCategorie(String libelle, int idparent) {
        try {
            Statement st = connect.createStatement();
            String line = "INSERT INTO Categorie (libelle, id_categorie_parent) VALUES ('"+libelle+"',"+idparent+");";
            st.execute(line);
        } catch (SQLException e) {
            System.out.println("ERREUR DE SYNTAXE:");
            e.printStackTrace();
        }
    }

    public void removeCategory(int id) {
        try {
            Statement st = connect.createStatement();
            st.execute("DELETE FROM Categorie WHERE id="+id+";");
            System.out.println("DELETE FROM Categorie WHERE id="+id+";");
        } catch (SQLException e) {
            System.out.println("ERREUR DE SYNTAXE:");
            e.printStackTrace();
        }
    }

    public void removePortion(int id) {
        try {
            Statement st = connect.createStatement();
            st.execute("DELETE FROM Portion_text WHERE id="+id+";");
            System.out.println("DELETE FROM Portion_text WHERE id="+id+";");

        } catch (SQLException e) {
            System.out.println("ERREUR DE SYNTAXE:");
            e.printStackTrace();
        }
    }

}
