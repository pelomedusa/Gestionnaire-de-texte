package Views;

import App.Categorie;
import Models.M_bdd;
import Models.M_window;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Pelomedusa on 08/12/2016.
 */
public class V_window extends JFrame {
    private M_bdd model_bdd;
    private M_window model;

    private JPanel panoMain, panoTree, panoToolbarCategory, panoToolbarPortion, panoPreview, panoResult;
    private JTextArea taPreview,taResult;
    private JButton btAddCategory, btRemoveCategory,btAddPortion;
    protected JTree tree;
    protected JButton btSauvegarderPortion,btRemovePortion;
    protected JButton btExport;

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
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);


        panoMain = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 0));
        panoMain.setPreferredSize(model.getSizeWindow());
        panoMain.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        panoTree= new JPanel(new FlowLayout(FlowLayout.LEADING, 10, 10));
        panoTree.setPreferredSize(model.getSizePanoTree());
        panoTree.setBorder(BorderFactory.createLineBorder(Color.black));
        panoTree.setBackground(Color.WHITE);
        DefaultTreeModel treeModel = new DefaultTreeModel(top);
        //treeModel.addTreeModelListener(new MyTreeModelListener());
        this.tree = new JTree(treeModel);
        this.tree.setPreferredSize(model.getSizeTree());
        this.tree.getSelectionModel().setSelectionMode
                (TreeSelectionModel.SINGLE_TREE_SELECTION);
        setRender();
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
        panoToolbarCategory.setPreferredSize(model.getSizePanoToolbar());
        this.btAddCategory = new JButton("Ajouter catégorie");
        this.btRemoveCategory = new JButton("Supprimer catégorie");
        this.btAddPortion = new JButton("Ajouter portion de texte");
        this.btExport = new JButton("Exporter");
        panoToolbarCategory.add(this.btAddCategory);
        panoToolbarCategory.add(this.btRemoveCategory);
        panoToolbarCategory.add(this.btAddPortion);
        panoToolbarCategory.add(this.btExport);

        panoToolbarPortion = new JPanel();
        panoToolbarPortion.setPreferredSize(model.getSizePanoToolbar());
        this.btSauvegarderPortion = new JButton("Sauvegarder Portion");
        this.btRemovePortion = new JButton("Supprimer Portion");
        panoToolbarPortion.add(this.btSauvegarderPortion);
        panoToolbarPortion.add(this.btRemovePortion);

        panoPreview= new JPanel(new FlowLayout(FlowLayout.LEADING, 10, 10));
        panoPreview.setPreferredSize(model.getSizePanoPreview());
        this.taPreview = new JTextArea();
        this.taPreview.setPreferredSize(model.getSizeTaPreview());
        this.taPreview.setBorder(BorderFactory.createLineBorder(Color.black));
        this.taPreview.setBorder(BorderFactory.createCompoundBorder(
                taPreview.getBorder(),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        panoPreview.add(this.taPreview);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 2;
        gbc.gridwidth = 1;
        panoMain.add(panoPreview, gbc);

        panoResult= new JPanel(new FlowLayout(FlowLayout.LEADING, 10, 10));
        panoResult.setPreferredSize(model.getSizePanoResult());
        this.taResult = new JTextArea();
        this.taResult.setPreferredSize(model.getSizeTaResult());
        this.taResult.setBorder(BorderFactory.createLineBorder(Color.black));
        this.taResult.setBorder(BorderFactory.createCompoundBorder(
                taResult.getBorder(),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        panoResult.add(this.taResult);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        panoMain.add(panoResult, gbc);


        this.setContentPane(panoMain);
        setVisible(true);

    }

    private void setRender() {
        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer() {
            @Override
            public Component getTreeCellRendererComponent(JTree tree,
                                                          Object value, boolean selected, boolean expanded,
                                                          boolean leaf, int row, boolean hasFocus) {
                super.getTreeCellRendererComponent(tree, value, selected,expanded, leaf, row, hasFocus);
                DefaultMutableTreeNode nodo = (DefaultMutableTreeNode) value;
                ClassLoader classLoader = getClass().getClassLoader();

                if (nodo.getUserObject() instanceof Categorie){
                    setIcon(new ImageIcon(classLoader.getResource("icons/folder.png")));
                } else {
                    setIcon(new ImageIcon(classLoader.getResource("icons/text.png")));
                }
                return this;
            }
        };
        this.tree.setCellRenderer(renderer);
    }

    public void showToolbar(String s){
        try{
            this.getContentPane().remove(this.panoToolbarPortion);
            System.out.println("remove portion");
            this.getContentPane().remove(this.panoToolbarCategory);
        } catch (NullPointerException e){
        }
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        if (s.equals("category")){
            panoToolbarCategory.add(btExport);
            panoMain.add(panoToolbarCategory, gbc);
            System.out.println("add category");
        } else {
            panoToolbarPortion.add(btExport);
            panoMain.add(panoToolbarPortion, gbc);
            System.out.println("add portion");
        }
        repaint();
    }

    public JButton getBtExport() {
        return btExport;
    }

    public void setBtExport(JButton btExport) {
        this.btExport = btExport;
    }


    public JButton getBtAddPortion() {
        return btAddPortion;
    }

    public void setBtAddPortion(JButton btAddPortion) {
        this.btAddPortion = btAddPortion;
    }

    public JPanel getPanoToolbarPortion() {
        return panoToolbarPortion;
    }

    public void setPanoToolbarPortion(JPanel panoToolbarPortion) {
        this.panoToolbarPortion = panoToolbarPortion;
    }

    public JButton getBtSauvegarderPortion() {
        return btSauvegarderPortion;
    }

    public void setBtSauvegarderPortion(JButton btSauvegarderPortion) {
        this.btSauvegarderPortion = btSauvegarderPortion;
    }

    public JButton getBtRemovePortion() {
        return btRemovePortion;
    }

    public void setBtRemovePortion(JButton btRemovePortion) {
        this.btRemovePortion = btRemovePortion;
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
