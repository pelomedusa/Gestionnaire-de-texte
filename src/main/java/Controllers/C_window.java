package Controllers;

import App.Categorie;
import App.Portion_text;
import Listeners.CustomSelectionListener;
import Listeners.CustomTreeModelListener;
import Listeners.DoubleClickListener;
import Listeners.btnActionEvent;
import Models.M_bdd;
import Models.M_window;
import Views.V_window;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
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

        this.vWindow = new V_window(mWindow, mBdd);
        this.createTree();
        this.vWindow.getTree().addTreeSelectionListener(new CustomSelectionListener(this.vWindow));
        this.vWindow.getTree().getModel().addTreeModelListener(new CustomTreeModelListener());
        this.vWindow.getTree().addMouseListener(new DoubleClickListener(this.vWindow.getTree(), this.vWindow.getTaResult()));
        this.vWindow.getBtAddCategory().addActionListener(new btnActionEvent(this.vWindow));
        this.vWindow.getBtRemoveCategory().addActionListener(new btnActionEvent(this.vWindow));
        this.vWindow.getBtSauvegarderPortion().addActionListener(new btnActionEvent(this.vWindow));
        this.vWindow.getBtRemovePortion().addActionListener(new btnActionEvent(this.vWindow));
        this.vWindow.getBtAddPortion().addActionListener(new btnActionEvent(this.vWindow));
        this.vWindow.getBtExport().addActionListener(new btnActionEvent(this.vWindow));
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
        List<DefaultMutableTreeNode> listLeafs = new ArrayList<DefaultMutableTreeNode>();

        for (Categorie cat : this.list_categories) {
            listNodes.add(new DefaultMutableTreeNode(cat));
        }
        for (Portion_text pt : this.list_portion){
            listLeafs.add(new DefaultMutableTreeNode(pt));
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
            for (DefaultMutableTreeNode feuille: listLeafs){
                if (((Categorie) noeud.getUserObject()).getId() == ((Portion_text) feuille.getUserObject()).getId_categorie()){
                    if (((Categorie) noeud.getUserObject()).getId_parent() == 0){
                        top.add(feuille);
                    }else {
                        noeud.add(feuille);
                        //System.out.println(((Categorie) noeud.getUserObject()).getLibelle()+" -- a pour fils -- "+((Categorie) noeudCompare.getUserObject()).getLibelle());
                    }
                }
            }
        }

    }

    private void resetDatabase() throws FileNotFoundException, SQLException {
        this.mBdd.importSQL(new FileInputStream("db/create.sql"));
    }

    private void prepareBdd(){
        try {
            this.mBdd = new M_bdd("test.db");
            this.list_categories =   this.mBdd.getAllCategorie();
            this.list_portion =  this.mBdd.getAllPortionText();
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur de connection à la base de données");
            e.printStackTrace();
        } catch (SQLException e) {
            try {
                resetDatabase();
                System.out.println("La BDD est vide, elle est initializée");
                prepareBdd();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }




    public M_window getmWindow() {
        return mWindow;
    }

    public void setmWindow(M_window mWindow) {
        this.mWindow = mWindow;
    }

    public V_window getvWindow() {
        return vWindow;
    }

    public void setvWindow(V_window vWindow) {
        this.vWindow = vWindow;
    }

    public M_bdd getmBdd() {
        return mBdd;
    }

    public void setmBdd(M_bdd mBdd) {
        this.mBdd = mBdd;
    }

    public List<Categorie> getList_categories() {
        return list_categories;
    }

    public void setList_categories(List<Categorie> list_categories) {
        this.list_categories = list_categories;
    }

    public List<Portion_text> getList_portion() {
        return list_portion;
    }

    public void setList_portion(List<Portion_text> list_portion) {
        this.list_portion = list_portion;
    }

}
