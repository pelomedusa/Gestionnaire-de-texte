package Views;

import javax.swing.*;

/**
 * Created by Pelomedusa on 08/12/2016.
 */
public class V_window extends JFrame {

    private JPanel panoMain, panoTree, panoToolbar, panoPreview, panoResult;

    public V_window(){
        super();
        System.out.println("wesssh");
    }

    public void init(String title){
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);
        setVisible(true);
    }
}
