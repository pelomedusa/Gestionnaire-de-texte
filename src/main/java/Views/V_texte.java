package Views;

import App.Categorie;
import App.Portion_text;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

/**
 * Created by pelomedusa on 11/12/2016.
 */
public class V_texte extends JFrame {


    protected V_window vWindow;
    protected JPanel panoMain;
    protected JButton btnValider;
    protected JTextArea taInputTexte;
    protected JTextArea taInputTags;
    protected int idparent;

    public V_texte(V_window view, int idparent) {
        super();
        this.vWindow = view;
        this.idparent = idparent;
        this.setTitle("Entrez le text et les tags");
        this.setSize(new Dimension(350, 200));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        panoMain = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 10));
        this.taInputTexte = new JTextArea();
        this.taInputTexte.setPreferredSize(new Dimension(200, 100));
        this.taInputTexte.setLineWrap(true);
        this.taInputTexte.setWrapStyleWord(true);
        this.panoMain.add(this.taInputTexte);

        this.btnValider = new JButton("Ok");
        this.btnValider.putClientProperty("vTexte", this);
        this.btnValider.setPreferredSize(new Dimension(80, 50));
        this.panoMain.add(this.btnValider);

        this.taInputTags = new JTextArea();
        this.taInputTags.setPreferredSize(new Dimension(200, 30));
        this.taInputTags.setLineWrap(true);
        this.taInputTags.setWrapStyleWord(true);
        this.panoMain.add(this.taInputTags);
        this.setContentPane(this.panoMain);
        this.setVisible(true);
    }

    public void setListener(){
        btnValider.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                V_texte vTexte = (V_texte) ((JButton)e.getSource()).getClientProperty( "vTexte" );
                int idinserted = 0;
                try {
                    idinserted = vTexte.getvWindow().getModel_bdd().insertPortion(vTexte.getTaInputTexte().getText(),vTexte.getTaInputTags().getText(),vTexte.getIdparent());
                } catch (SQLException e1) {
                    System.out.println("ERREUR SQL");
                    e1.printStackTrace();
                }
                vTexte.dispose();

                List<Categorie> list_categories = null;
                try {
                    list_categories = vTexte.getvWindow().getModel_bdd().getAllCategorie();
                    Categorie cat = null;
                    for (Categorie c : list_categories) {
                        if (c.getId_parent() == vTexte.getIdparent()){
                            cat = c;
                        }
                    }

                    DefaultTreeModel model = (DefaultTreeModel)vTexte.getvWindow().getTree().getModel();
                    DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();

                    Enumeration en = root.depthFirstEnumeration();
                    while (en.hasMoreElements()) {
                        DefaultMutableTreeNode node = (DefaultMutableTreeNode) en.nextElement();
                        if (node.getUserObject() instanceof Categorie){
                            if (((Categorie) node.getUserObject()).getId() == vTexte.getIdparent()){
                                node.add(new DefaultMutableTreeNode(new Portion_text(idinserted,vTexte.getIdparent(),vTexte.getTaInputTexte().getText(),vTexte.getTaInputTags().getText())));
                            }
                        }
                    }
                    model.reload(root);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

    }

    public JTextArea getTaInputTags() {
        return taInputTags;
    }

    public void setTaInputTags(JTextArea taInputTags) {
        this.taInputTags = taInputTags;
    }

    public int getIdparent() {
        return idparent;
    }

    public void setIdparent(int idparent) {
        this.idparent = idparent;
    }

    public V_window getvWindow() {
        return vWindow;
    }

    public void setvWindow(V_window vWindow) {
        this.vWindow = vWindow;
    }

    public JPanel getPanoMain() {
        return panoMain;
    }

    public void setPanoMain(JPanel panoMain) {
        this.panoMain = panoMain;
    }

    public JButton getBtnValider() {
        return btnValider;
    }

    public void setBtnValider(JButton btnValider) {
        this.btnValider = btnValider;
    }

    public JTextArea getTaInputTexte() {
        return taInputTexte;
    }

    public void setTaInputTexte(JTextArea taInputTexte) {
        this.taInputTexte = taInputTexte;
    }
}
