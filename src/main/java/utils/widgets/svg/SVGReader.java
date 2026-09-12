package utils.widgets.svg;

import javafx.scene.shape.SVGPath;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/*
    Loeb failist SVGPath objekti, mida saab kasutada ikoonide jaoks.
    SVGPath tuleks lisada mingi teise node sisse (nt button, region), et talle saaks suurust määrata.
*/

public class SVGReader {
    private static final String srcPath = "/svg/";

    public static SVGPath read(String filepath) {
        SVGPath svg = new SVGPath();
        svg.setContent(readData(filepath));
        svg.getStyleClass().add("svgpath");

        return svg;
    }

    private static String readData(String filepath){
        try (InputStream in = SVGReader.class.getResourceAsStream(srcPath + filepath)) {

            if (in == null) {
                throw new IllegalArgumentException("Icon not found: " + filepath);
            }

            return new String(in.readAllBytes(),StandardCharsets.UTF_8).trim();

        } catch (IOException e) {
            throw new RuntimeException("Could not load icon: " + filepath, e);
        }
    }
}