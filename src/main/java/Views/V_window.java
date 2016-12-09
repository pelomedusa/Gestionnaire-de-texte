package Views;

import App.Categorie;
import Models.M_window;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;

/**
 * Created by Pelomedusa on 08/12/2016.
 */
public class V_window extends JFrame implements TreeSelectionListener {
    private M_window model;

    private JPanel panoMain, panoTree, panoToolbar, panoPreview, panoResult;

    protected JTree tree;

    public V_window(M_window model){
        super();
        this.model = model;
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

        panoTree= new JPanel();
        panoTree.setBackground(Color.BLUE);
        panoTree.setPreferredSize(model.getSizePanoTree());
        this.tree = new JTree(top);
        this.tree.setPreferredSize(new Dimension(150,400));
        this.tree.getSelectionModel().setSelectionMode
                (TreeSelectionModel.SINGLE_TREE_SELECTION);

        //Listen for when the selection changes.
        this.tree.addTreeSelectionListener(this);
        this.panoTree.add(tree);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        panoMain.add(panoTree, gbc);

        panoToolbar= new JPanel();
        panoToolbar.setBackground(Color.RED);
        panoToolbar.setPreferredSize(model.getSizePanoToolbar());
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        panoMain.add(panoToolbar, gbc);

        panoPreview= new JPanel();
        panoPreview.setBackground(Color.YELLOW);
        panoPreview.setPreferredSize(model.getSizePanoPreview());
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 2;
        gbc.gridwidth = 1;
        panoMain.add(panoPreview, gbc);

        panoResult= new JPanel();
        panoResult.setBackground(Color.GREEN);
        panoResult.setPreferredSize(model.getSizePanoResult());
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        panoMain.add(panoResult, gbc);


        this.setContentPane(panoMain);
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

    public JPanel getPanoToolbar() {
        return panoToolbar;
    }

    public void setPanoToolbar(JPanel panoToolbar) {
        this.panoToolbar = panoToolbar;
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

    public void valueChanged(TreeSelectionEvent e) {
//Returns the last path element of the selection.
//This method is useful only when the selection model allows a single selection.
        System.out.println("wesh");
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                tree.getLastSelectedPathComponent();

        if (node == null)
            //Nothing is selected.
            return;

        Object nodeInfo = node.getUserObject();
    }
}
