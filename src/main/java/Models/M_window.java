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
    private Dimension sizeTaPreview;
    private Dimension sizeTree;
    private Dimension sizeTaResult;

    public M_window(){
        this.title = "Gestionnaire de texte";
        init();
    }

    private void init() {
        sizeWindow = new Dimension(900,900);
        int height = (int) sizeWindow.getHeight();
        int width = (int) sizeWindow.getWidth()-40;

        this.sizePanoTree = new Dimension(width/4,500);
        this.sizeTree = new Dimension(width/4-20,500-20);
        this.sizePanoToolbar = new Dimension((width/4)*3,100);
        this.sizePanoPreview = new Dimension((width/4)*3,400);
        this.sizeTaPreview = new Dimension((width/4)*3-20,400-20);
        this.sizePanoResult = new Dimension(width,250);
        this.sizeTaResult = new Dimension(width-20,250-20);

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

    public Dimension getSizeTaPreview() {
        return sizeTaPreview;
    }

    public void setSizeTaPreview(Dimension sizeTaPreview) {
        this.sizeTaPreview = sizeTaPreview;
    }

    public Dimension getSizeTree() {
        return sizeTree;
    }

    public void setSizeTree(Dimension sizeTree) {
        this.sizeTree = sizeTree;
    }

    public Dimension getSizeTaResult() {
        return sizeTaResult;
    }

    public void setSizeTaResult(Dimension sizeTaResult) {
        this.sizeTaResult = sizeTaResult;
    }
}
