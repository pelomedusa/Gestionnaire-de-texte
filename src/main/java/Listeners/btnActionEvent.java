package Listeners;

import App.Categorie;
import App.Portion_text;
import Views.V_input;
import Views.V_texte;
import Views.V_window;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
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
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) this.vWindow.getTree().getLastSelectedPathComponent();
        if (node == null) return;
        DefaultTreeModel model = (DefaultTreeModel) this.vWindow.getTree().getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
        Enumeration en = root.depthFirstEnumeration();

        if (e.getSource() == vWindow.getBtAddCategory()){
            int idparent = ((Categorie) node.getUserObject()).getId();
            V_input vInput = new V_input(this.vWindow, idparent);
            vInput.setListener();
        } else if (e.getSource() == vWindow.getBtRemoveCategory()){

            while (en.hasMoreElements()) {
                DefaultMutableTreeNode nodeFound = (DefaultMutableTreeNode) en.nextElement();

                if (node == nodeFound){
                    clearNode(nodeFound);
                    nodeFound.removeFromParent();
                }

            }
            model.reload(root);
        }else if (e.getSource() == vWindow.getBtSauvegarderPortion()){
            Portion_text pt = (Portion_text) node.getUserObject();
            pt.setContenu(vWindow.getTaPreview().getText());
            this.vWindow.getModel_bdd().changeText(pt.getId(), pt.getContenu());
        }else if (e.getSource() == vWindow.getBtRemovePortion()){
            Portion_text pt = (Portion_text) node.getUserObject();
            this.vWindow.getModel_bdd().removePortion(pt.getId());

            while (en.hasMoreElements()) {
                DefaultMutableTreeNode nodeFound = (DefaultMutableTreeNode) en.nextElement();
                if (node == nodeFound){
                    nodeFound.removeFromParent();
                }

            }
            model.reload(root);
        }else if (e.getSource() == vWindow.getBtAddPortion()){
            Categorie cat = (Categorie) node.getUserObject();
            V_texte vText = new V_texte(this.vWindow, cat.getId());
            vText.setListener();
        }
        else if (e.getSource() == vWindow.getBtExport()){
            JFileChooser c = new JFileChooser();
            // Demonstrate "Save" dialog:
            int rVal = c.showSaveDialog(vWindow);
            System.out.println(vWindow.getTaResult().getText());
            if (rVal == JFileChooser.APPROVE_OPTION) {
                try {
                    byte data[] = vWindow.getTaResult().getText().getBytes();
                    FileOutputStream out = new FileOutputStream(c.getSelectedFile());
                    out.write(data);
                    out.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
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

