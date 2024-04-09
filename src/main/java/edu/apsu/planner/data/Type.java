package edu.apsu.planner.data;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Type {

    // Instances
    private Tag tag;
    private Image symbol;
    private Rectangle defaultSymbol;
    private final BooleanProperty isVisible = new SimpleBooleanProperty();


    // Constructors
    public Type() {
        tag = Tag.CUSTOM;
        symbol = null;
    }

    public Type(Tag tag, Image symbol, Rectangle defaultSymbol, boolean isVisible) {
        this.tag = tag;
        this.symbol = symbol;
        this.defaultSymbol = defaultSymbol;
        this.isVisible.set(isVisible);
    }


    // Methods

    // Accessors and Mutators
    public Tag getTag() {
        return tag;
    }
    public String getName() {return tag.name();}

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Image getSymbol() {
        return symbol;
    }

    public void setSymbol(Image symbol) {
        this.symbol = symbol;
    }

    public Rectangle getDefaultSymbol() {
        return defaultSymbol;
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
