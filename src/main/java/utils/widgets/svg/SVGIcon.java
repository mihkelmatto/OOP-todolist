package utils.widgets.svg;

import javafx.scene.layout.Region;
import javafx.scene.shape.SVGPath;

/*
    Wrapper-klass SVGPathile.

    css:
    .svgicon{
        -fx-pref-width: 50;
        -fx-background-color: red;
    }

    .svgicon .svgpath{
        -fx-fill: white;
        -fx-opacity: 1.0;
    }
*/

public class SVGIcon extends Region{
    private SVGPath svg;

    public SVGIcon(String filepath){
        this.svg = SVGReader.read(filepath);

        this.setShape(svg);

        this.getStyleClass().add("svgicon");
    }
}
