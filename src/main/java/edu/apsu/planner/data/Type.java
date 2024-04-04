package edu.apsu.planner.data;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.Image;

public class Type {

    // Instances
    private String name;
    private Image symbol;
    private final BooleanProperty isVisible = new SimpleBooleanProperty();


    // Constructors
    public Type() {
        name = "";
        symbol = null;
    }

    public Type(String name, Image symbol, boolean isVisible) {
        this.name = name;
        this.symbol = symbol;
        this.isVisible.set(isVisible);
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

    public Property<Boolean> isVisibleProperty() {
        return isVisible;
    }

    public boolean isVisible() {
        return isVisible.get();
    }

    public void setVisible(boolean visible) {
        isVisible.set(visible);
    }
}
