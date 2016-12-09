package Models;

import java.awt.*;

/**
 * Created by Pelomedusa on 08/12/2016.
 */

public class M_window {
    private String title;
    private Dimension sizeWindow;
    private Dimension sizePanoTree;
    private Dimension sizePanoToolbar;
    private Dimension sizePanoPreview;
    private Dimension sizePanoResult;

    public M_window(){
        this.title = "Gestionnaire de texte";
        init();
    }

    private void init() {
        sizeWindow = new Dimension(800,800);
        int height = (int) sizeWindow.getHeight();
        int width = (int) sizeWindow.getWidth()-20;

        this.sizePanoTree = new Dimension(width/4,500);
        this.sizePanoToolbar = new Dimension((width/4)*3,100);
        this.sizePanoPreview = new Dimension((width/4)*3,400);
        this.sizePanoResult = new Dimension(width,250);

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Dimension getSizeWindow() {
        return sizeWindow;
    }

    public void setSizeWindow(Dimension sizeWindow) {
        this.sizeWindow = sizeWindow;
    }

    public Dimension getSizePanoTree() {
        return sizePanoTree;
    }

    public void setSizePanoTree(Dimension sizePanoTree) {
        this.sizePanoTree = sizePanoTree;
    }

    public Dimension getSizePanoToolbar() {
        return sizePanoToolbar;
    }

    public void setSizePanoToolbar(Dimension sizePanoToolbar) {
        this.sizePanoToolbar = sizePanoToolbar;
    }

    public Dimension getSizePanoPreview() {
        return sizePanoPreview;
    }

    public void setSizePanoPreview(Dimension sizePanoPreview) {
        this.sizePanoPreview = sizePanoPreview;
    }

    public Dimension getSizePanoResult() {
        return sizePanoResult;
    }

    public void setSizePanoResult(Dimension sizePanoResult) {
        this.sizePanoResult = sizePanoResult;
    }

}
