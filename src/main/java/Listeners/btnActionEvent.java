package Listeners;

import App.Categorie;
import Controllers.C_window;
import Views.V_input;
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

    protected C_window cWindow;

    public btnActionEvent(C_window cWindow) {
        this.cWindow = cWindow;
    }

    public void actionPerformed(ActionEvent e) {
        if (((JButton) e.getSource()) == cWindow.getvWindow().getBtAddCategory()){
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) this.cWindow.getvWindow().getTree().getLastSelectedPathComponent();
            int idparent = ((Categorie) node.getUserObject()).getId();
            V_input vInput = new V_input(this.cWindow,"categorie", idparent);
            vInput.setListener();
        }
    }
}

