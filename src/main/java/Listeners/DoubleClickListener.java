package Listeners;

import App.Portion_text;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Pelomedusa on 09/12/2016.
 */
public class DoubleClickListener extends MouseAdapter {


    protected JTree tree;
    protected JTextArea taResult;

    public DoubleClickListener(JTree tree, JTextArea taResult) {
        this.tree = tree;
        this.taResult = taResult;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int selRow = tree.getRowForLocation(e.getX(), e.getY());
        TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
        if(selRow != -1) {
            if(e.getClickCount() == 2) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) selPath.getLastPathComponent();
                if (node.getUserObject() instanceof Portion_text){
                    this.taResult.setText(this.taResult.getText()+((Portion_text) node.getUserObject()).getContenu()+"\n");
                }
            }
        }
    }
}
