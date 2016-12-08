package Models;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pelomedusa on 08/12/2016.
 */

public class M_window {
    private String title;
    private Map<String, Integer> panoMain_size;

    public M_window(){
        this.title = "Gestionnaire de texte";

        this.panoMain_size = new HashMap<String, Integer>();
        this.panoMain_size.put("width",5);
        this.panoMain_size.put("heigth",5);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
