package utils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;




public interface ToJson {

    public UUID getID();

    /*
    Kirjutab objekti json-failina. Loob vajadusel vastava directory.
    */
    public default void toJsonFile() {
        File file = new File(String.format("%s/%s.json", Classreader.getDir(this.getClass()), this.getID()));

        File dir = file.getParentFile();
        if(!dir.exists()) dir.mkdirs();

        try {
            Classreader.getMapper().writerWithDefaultPrettyPrinter().writeValue(file, this);
            System.out.printf("%s instance saved: %s\n", this.getClass().getSimpleName(), this.getID());
            System.out.println(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}