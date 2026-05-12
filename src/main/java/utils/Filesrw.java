package utils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/*
Suudab kirjutada ja lugeda klasse, mis implementeerivad Filesrw liidest.

toJsonFile() loob vajadusel vastava directory.
fromJsonFile() on staatiline.   TODO: kas saab teha default meetodiks?
                                nt saaks teha implementeerivasse klassi konstruktori, mille argumendiks on UUID ja kutsub välja fromjsonfile.

*/

public interface Filesrw {
    static final String path = "src/main/data";
    static final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    public UUID getID();

    public default void toJsonFile() {
        File file = new File(String.format("%s/%s/%s.json", path, this.getClass().getSimpleName(), this.getID()));

        File dir = file.getParentFile();
        if(!dir.exists()) dir.mkdirs();

        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T extends Filesrw> T fromJsonFile(UUID uuid, Class<T> clazz) {
        File file = new File(String.format("%s/%s/%s.json", path, clazz.getSimpleName(), uuid));

        try {
            if (!file.isFile()) {
                String errormsg = String.format("File not found from path: %s\n", file.toString());
                throw new TodoException(errormsg);
            }

            return mapper.readValue(file, clazz);

        } catch (TodoException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

