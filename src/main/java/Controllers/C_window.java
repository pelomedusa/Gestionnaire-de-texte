package Controllers;

import Models.M_window;
import Views.V_window;

/**
 * Created by Pelomedusa on 08/12/2016.
 */
public class C_window {

    private M_window mWindow;
    private V_window vWindow;


    public C_window(){
        this.mWindow = new M_window();
        this.vWindow = new V_window();

        vWindow.init(mWindow.getTitle());
    }
}
