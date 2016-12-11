package Views;

import Models.M_bdd;
import Models.M_window;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;

/**
 * Created by Pelomedusa on 08/12/2016.
 */
public class V_window extends JFrame {
    private M_bdd model_bdd;
    private M_window model;

    private JPanel panoMain, panoTree, panoToolbarCategory, panoPreview, panoResult;
    private JTextArea taPreview,taResult;
    private JButton btAddCategory, btRemoveCategory;
    protected JTree tree;

    public V_window(M_window model, M_bdd mBdd){
        super();
        this.model = model;
        this.model_bdd = mBdd;
    }

    public void init(DefaultMutableTreeNode top){
        setUndecorated(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setSize(model.getSizeWindow());



        panoMain = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 0));
        panoMain.setPreferredSize(model.getSizeWindow());
        panoMain.setBackground(Color.BLACK);
        panoMain.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        panoTree= new JPanel(new FlowLayout(FlowLayout.LEADING, 10, 10));
        panoTree.setPreferredSize(model.getSizePanoTree());

        DefaultTreeModel treeModel = new DefaultTreeModel(top);
        //treeModel.addTreeModelListener(new MyTreeModelListener());
        this.tree = new JTree(treeModel);
        this.tree.setPreferredSize(model.getSizeTree());
        this.tree.getSelectionModel().setSelectionMode
                (TreeSelectionModel.SINGLE_TREE_SELECTION);

        //Listen for when the selection changes.
        this.panoTree.add(tree);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        panoMain.add(panoTree, gbc);

        panoToolbarCategory = new JPanel();
        panoToolbarCategory.setBackground(Color.RED);
        panoToolbarCategory.setPreferredSize(model.getSizePanoToolbar());
        this.btAddCategory = new JButton("Ajouter catégorie");
        this.btRemoveCategory = new JButton("Supprimer catégorie");
        this.btAddCategory.setEnabled(false);
        this.btRemoveCategory.setEnabled(false);
        panoToolbarCategory.add(this.btAddCategory);
        panoToolbarCategory.add(this.btRemoveCategory);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        panoMain.add(panoToolbarCategory, gbc);

        panoPreview= new JPanel(new FlowLayout(FlowLayout.LEADING, 10, 10));
        panoPreview.setPreferredSize(model.getSizePanoPreview());
        this.taPreview = new JTextArea();
        this.taPreview.setPreferredSize(model.getSizeTaPreview());
        panoPreview.add(this.taPreview);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 2;
        gbc.gridwidth = 1;
        panoMain.add(panoPreview, gbc);

        panoResult= new JPanel(new FlowLayout(FlowLayout.LEADING, 10, 10));
        panoResult.setBackground(Color.GREEN);
        panoResult.setPreferredSize(model.getSizePanoResult());
        this.taResult = new JTextArea();
        this.taResult.setPreferredSize(model.getSizeTaResult());
        panoResult.add(this.taResult);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        panoMain.add(panoResult, gbc);


        this.setContentPane(panoMain);
        pack();
        setVisible(true);

    }


    public M_window getModel() {
        return model;
    }

    public void setModel(M_window model) {
        this.model = model;
    }

    public JPanel getPanoMain() {return panoMain;}

    public void setPanoMain(JPanel panoMain) {
        this.panoMain = panoMain;
    }

    public JPanel getPanoTree() {
        return panoTree;
    }

    public void setPanoTree(JPanel panoTree) {
        this.panoTree = panoTree;
    }

    public JPanel getPanoToolbarCategory() {
        return panoToolbarCategory;
    }

    public void setPanoToolbarCategory(JPanel panoToolbarCategory) {
        this.panoToolbarCategory = panoToolbarCategory;
    }

    public JPanel getPanoPreview() {
        return panoPreview;
    }

    public void setPanoPreview(JPanel panoPreview) {
        this.panoPreview = panoPreview;
    }

    public JPanel getPanoResult() {
        return panoResult;
    }

    public void setPanoResult(JPanel panoResult) {
        this.panoResult = panoResult;
    }

    public JTextArea getTaPreview() {
        return taPreview;
    }

    public void setTaPreview(JTextArea taPreview) {
        this.taPreview = taPreview;
    }

    public JTree getTree() {
        return tree;
    }

    public void setTree(JTree tree) {
        this.tree = tree;
    }

    public JTextArea getTaResult() {
        return taResult;
    }

    public void setTaResult(JTextArea taResult) {
        this.taResult = taResult;
    }

    public JButton getBtAddCategory() {
        return btAddCategory;
    }

    public void setBtAddCategory(JButton btAddCategory) {
        this.btAddCategory = btAddCategory;
    }

    public JButton getBtRemoveCategory() {
        return btRemoveCategory;
    }

    public void setBtRemoveCategory(JButton btRemoveCategory) {
        this.btRemoveCategory = btRemoveCategory;
    }

    public M_bdd getModel_bdd() {
        return model_bdd;
    }

    public void setModel_bdd(M_bdd model_bdd) {
        this.model_bdd = model_bdd;
    }

}
