package Listeners;

import App.Categorie;
import Views.V_window;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Pelomedusa on 09/12/2016.
 */
public class btnActionEvent implements ActionListener {

    protected V_window vWindow;

    public btnActionEvent(V_window vWindow) {
        this.vWindow = vWindow;
    }

    public void actionPerformed(ActionEvent e) {
        if (((JButton) e.getSource()) == vWindow.getBtAddCategory()){
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) this.vWindow.getTree().getLastSelectedPathComponent();
            int idparent = ((Categorie) node.getUserObject()).getId();
            this.vWindow.getModel_bdd().insertCategorie("Nouvelle categorie", idparent);
        }
    }
}
