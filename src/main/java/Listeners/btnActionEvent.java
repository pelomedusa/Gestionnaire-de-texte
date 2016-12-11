package Listeners;

import App.Categorie;
import App.Portion_text;
import Views.V_input;
import Views.V_window;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Enumeration;

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
            V_input vInput = new V_input(this.vWindow,"categorie", idparent);
            vInput.setListener();
        } else if (((JButton) e.getSource()) == vWindow.getBtRemoveCategory()){
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) this.vWindow.getTree().getLastSelectedPathComponent();

            DefaultTreeModel model = (DefaultTreeModel) this.vWindow.getTree().getModel();
            DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();

            Enumeration en = root.depthFirstEnumeration();
            while (en.hasMoreElements()) {
                DefaultMutableTreeNode nodeFound = (DefaultMutableTreeNode) en.nextElement();

                if (node == nodeFound){
                    clearNode(nodeFound);
                    nodeFound.removeFromParent();
                }

            }
            model.reload(root);
        }
    }

    public void clearNode(DefaultMutableTreeNode node) {
        if (node.getUserObject() instanceof Categorie){
            vWindow.getModel_bdd().removeCategory(((Categorie) node.getUserObject()).getId());
        } else {
            vWindow.getModel_bdd().removePortion(((Portion_text) node.getUserObject()).getId());
        }

        for (int i = 0; i < node.getChildCount(); i++) {
            DefaultMutableTreeNode child = (DefaultMutableTreeNode) node.getChildAt(i);
            clearNode(child);
        }
        node.removeAllChildren();
    }
}

