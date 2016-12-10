package Listeners;

import App.Portion_text;
import Views.V_window;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Created by Pelomedusa on 09/12/2016.
 */
public class CustomSelectionListener implements TreeSelectionListener {

    protected V_window vWindow;

    public CustomSelectionListener(V_window vWindow) {
        this.vWindow = vWindow;
    }

    public void valueChanged(TreeSelectionEvent e) {
        //Returns the last path element of the selection.
        //This method is useful only when the selection model allows a single selection.
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                this.vWindow.getTree().getLastSelectedPathComponent();

        if (node == null)
            //Nothing is selected.
            return;

        Object nodeInfo = node.getUserObject();
        if (nodeInfo instanceof Portion_text){
            this.vWindow.getTaPreview().setText(((Portion_text) nodeInfo).getContenu());
            this.vWindow.getBtAddCategory().setEnabled(false);
            this.vWindow.getBtAddPortion().setEnabled(false);

        } else {
            this.vWindow.getBtAddCategory().setEnabled(true);
            this.vWindow.getBtAddPortion().setEnabled(true);
        }
    }
}
