package utils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


/*
Suudab kirjutada ja lugeda klasse, mis implementeerivad Filesrw liidest. Loob vajadusel vastava directory.
*/

public interface ToJson {

    public UUID getID();

    public default void toJsonFile() {
        File file = new File(String.format("/%s/%s.json", Classreader.getDir(this.getClass()), this.getID()));

        File dir = file.getParentFile();
        if(!dir.exists()) dir.mkdirs();

        try {
            Classreader.getMapper().writerWithDefaultPrettyPrinter().writeValue(file, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}