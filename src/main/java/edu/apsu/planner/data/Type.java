package edu.apsu.planner.data;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class Type {

    // Instances
    private Tag tag;
    private Image symbol;
    private Rectangle defaultSymbol;


    private boolean symbolIsVisable;
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
        this.symbolIsVisable = false;
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


    public static Image loadImageIntoLabel( String imagePath) {
        // Load the image
        Image image = new Image(Type.class.getResource(imagePath).toString());
       return image;
    }


    public boolean isSymbolIsVisable() {
        return symbolIsVisable;
    }

    public void setSymbolIsVisable(boolean symbolIsVisable) {
        this.symbolIsVisable = symbolIsVisable;
    }

}
