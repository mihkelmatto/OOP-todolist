package utils.widgets.svg;

import javafx.scene.control.Button;
import javafx.scene.shape.SVGPath;

/*
    Wrapper-klass SVGPathile.

    css:
    .svgbutton{
        -fx-pref-width: 50;
        -fx-background-color: red;
    }

    .svgbutton .svgpath{
        -fx-fill: white;
        -fx-opacity: 1.0;
    }
*/

public class SVGButton extends Button{
    private SVGPath shape;

    public SVGButton(String filepath){
        this.shape = SVGReader.read(filepath);

        this.setShape(shape);

        this.getStyleClass().add("svgbutton");
    }
}
