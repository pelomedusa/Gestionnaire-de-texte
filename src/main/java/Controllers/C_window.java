package Controllers;

import App.Categorie;
import App.Portion_text;
import Models.M_bdd;
import Models.M_window;
import Views.V_window;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pelomedusa on 08/12/2016.
 */
public class C_window {

    protected M_window mWindow;
    protected V_window vWindow;
    protected M_bdd mBdd;
    protected List<Categorie> list_categories;
    protected List<Portion_text> list_portion;

    public C_window(){
        this.mWindow = new M_window();
        this.prepareBdd();

        this.vWindow = new V_window(mWindow);
        this.createTree();
    }

    private void createTree() {
        DefaultMutableTreeNode top = null;
        for (Categorie cat : this.list_categories) {
            if (cat.getId_parent() == 0){
                top = new DefaultMutableTreeNode(cat);
            }
        }
        this.vWindow.init(top);
        createNodes(top);
    }

    private void createNodes(DefaultMutableTreeNode top) {
        List<DefaultMutableTreeNode> listNodes = new ArrayList<DefaultMutableTreeNode>();
        for (Categorie cat : this.list_categories) {
            listNodes.add(new DefaultMutableTreeNode(cat));
        }
        for (DefaultMutableTreeNode noeud : listNodes) {
            for (DefaultMutableTreeNode noeudCompare : listNodes) {
                if (((Categorie) noeud.getUserObject()).getId() == ((Categorie) noeudCompare.getUserObject()).getId_parent()){
                    if (((Categorie) noeud.getUserObject()).getId_parent() == 0){
                        top.add(noeudCompare);
                    }else {
                        noeud.add(noeudCompare);
                        //System.out.println(((Categorie) noeud.getUserObject()).getLibelle()+" -- a pour fils -- "+((Categorie) noeudCompare.getUserObject()).getLibelle());
                    }
                }
            }
        }

    }

    private void prepareBdd(){
        try {
            this.mBdd = new M_bdd("test.db");
            this.mBdd.importSQL(new FileInputStream("db/create.sql"));
            this.list_categories =   this.mBdd.getAllCategorie();
            this.list_portion =  this.mBdd.getAllPortionText();
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur de connection à la base de donnée");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Erreur de syntaxe SQL");
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.out.println("Le fichier de base de donnée n'existe pas");
            e.printStackTrace();
        }
    }

    public static void print(DefaultMutableTreeNode aNode)
    {
        String name = aNode.toString();
        int level= aNode.getLevel();
        String placement = "";
        while (level > 0)
        {
            placement += ">";
            level--;
        }
        if(aNode.isLeaf())
        {
            System.out.println(placement + name);
            return;
        }

        System.out.println(placement + "--- " + name + " ---");
        for(int i = 0 ; i < aNode.getChildCount() ; i++)
        {
            print((DefaultMutableTreeNode)aNode.getChildAt(i));
        }
        System.out.println(placement + "+++ " + name + " +++");
    }
}
