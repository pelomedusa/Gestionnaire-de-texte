package Views;

import Controllers.C_window;
import Models.M_bdd;
import Models.M_window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Created by Pelomedusa on 09/12/2016.
 */
public class V_input extends JFrame {


    protected C_window cWindow;
    protected JPanel panoMain;
    protected JLabel labIntro;
    protected JButton btnValider;
    protected JTextArea taInput;
    protected int idParent;

    public V_input(C_window controller, String type, int idparent) {
        super();
        this.idParent = idparent;
        this.cWindow = controller;
        this.setTitle("Hey?");
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
                vInput.getcWindow().getmBdd().insertCategorie(vInput.getTaInput().getText(),vInput.getIdParent());
                vInput.getcWindow().updateTree(vInput.getTaInput().getText(),vInput.getIdParent());
                vInput.dispose();
            }
        });
    }

    public C_window getcWindow() {
        return cWindow;
    }

    public void setcWindow(C_window cWindow) {
        this.cWindow = cWindow;
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
