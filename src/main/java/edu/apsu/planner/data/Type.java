package edu.apsu.planner.data;

import javafx.scene.image.Image;

public class Type {

    // Instances
    private String name;
    private Image symbol;
    private boolean isVisible;


    // Constructors
    public Type() {
        name = "";
        symbol = null;
        isVisible = true;
    }

    public Type(String name, Image symbol, boolean isVisible) {
        this.name = name;
        this.symbol = symbol;
        this.isVisible = isVisible;
    }


    // Methods

    // Accessors and Mutators
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Image getSymbol() {
        return symbol;
    }

    public void setSymbol(Image symbol) {
        this.symbol = symbol;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}
