package edu.apsu.planner.data;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.io.Serializable;
import java.util.Objects;

public class Type implements Serializable {

    // Instances
    private final Tag tag;
    private final Image symbol;
    private Rectangle defaultSymbol;


    private boolean symbolVisible;
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
        this.symbolVisible = false;
    }


    // Methods

    // Accessors and Mutators
    public Tag getTag() {
        return tag;
    }

    public Image getSymbol() {
        return symbol;
    }

    public Rectangle copyDefaultSymbol() {
        Rectangle temp;
        temp = new Rectangle(defaultSymbol.getWidth(), defaultSymbol.getHeight(), defaultSymbol.getFill());
        return temp;
    }

    public Property<Boolean> isVisibleProperty() {
        return isVisible;
    }

    public boolean isVisible() {
        return isVisible.get();
    }


    public static Image loadImageIntoLabel(String imagePath) {
        // Load the image
        return new Image(Objects.requireNonNull(Type.class.getResource(imagePath)).toString());
    }


    public boolean isSymbolVisible() {
        return symbolVisible;
    }

    public void setSymbolVisible(boolean symbolVisible) {
        this.symbolVisible = symbolVisible;
    }

}
