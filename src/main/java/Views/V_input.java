package Views;

import App.Categorie;
import Controllers.C_window;
import Models.M_bdd;
import Models.M_window;

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
 * Created by Pelomedusa on 09/12/2016.
 */
public class V_input extends JFrame {


    protected V_window vWindow;
    protected JPanel panoMain;
    protected JLabel labIntro;
    protected JButton btnValider;
    protected JTextArea taInput;
    protected int idParent;

    public V_input(V_window view, int idparent) {
        super();
        this.idParent = idparent;
        this.vWindow = view;
        this.setTitle("Titre de la categorie?");
        this.setSize(new Dimension(200,90));

        panoMain = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 0));
        this.taInput = new JTextArea();
        this.taInput.setPreferredSize(new Dimension(120,50));
        this.taInput.setLineWrap(true);
        this.taInput.setWrapStyleWord(true);
        this.panoMain.add(this.taInput);
        this.btnValider = new JButton("Ok");
        this.btnValider.putClientProperty("vInput",this);
        this.btnValider.setPreferredSize(new Dimension(50,50));
        this.panoMain.add(this.btnValider);
        this.setContentPane(this.panoMain);
        this.setVisible(true);
    }

    public void setListener(){
        btnValider.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                V_input vInput = (V_input) ((JButton)e.getSource()).getClientProperty( "vInput" );
                vInput.getvWindow().getModel_bdd().insertCategorie(vInput.getTaInput().getText(),vInput.getIdParent());
                vInput.dispose();

                try {
                    List<Categorie> list_categories =   vInput.getvWindow().getModel_bdd().getAllCategorie();
                    Categorie cat = null;
                    for (Categorie c : list_categories) {
                        if (c.getId_parent() == vInput.getIdParent()){
                            cat = c;
                        }
                    }

                    DefaultTreeModel model = (DefaultTreeModel)vInput.getvWindow().getTree().getModel();
                    DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();

                    Enumeration en = root.depthFirstEnumeration();
                    while (en.hasMoreElements()) {
                        DefaultMutableTreeNode node = (DefaultMutableTreeNode) en.nextElement();
                        if (node.getUserObject() instanceof Categorie){
                            if (((Categorie) node.getUserObject()).getId() == vInput.getIdParent()){
                                node.add(new DefaultMutableTreeNode(cat));
                            }
                        }
                    }
                    model.reload(root);
                } catch (SQLException er) {
                    System.out.println("Erreur de syntaxe SQL");
                    er.printStackTrace();
                }
            }
        });
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

    public JLabel getLabIntro() {
        return labIntro;
    }

    public void setLabIntro(JLabel labIntro) {
        this.labIntro = labIntro;
    }

    public JButton getBtnValider() {
        return btnValider;
    }

    public void setBtnValider(JButton btnValider) {
        this.btnValider = btnValider;
    }

    public JTextArea getTaInput() {
        return taInput;
    }

    public void setTaInput(JTextArea taInput) {
        this.taInput = taInput;
    }

    public int getIdParent() {
        return idParent;
    }

    public void setIdParent(int idParent) {
        this.idParent = idParent;
    }
}
