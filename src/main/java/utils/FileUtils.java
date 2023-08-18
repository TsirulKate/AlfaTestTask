package utils;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Objects;

public class FileUtils {
    public static String getAbsolutePath(String path) {
        return getFile(path).getAbsolutePath();
    }

    public static File getFile(String path) {
        URL resource = FileUtils.class.getResource(path);
        File file;
        try {
            file = Paths.get(Objects.requireNonNull(resource).toURI()).toFile();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new RuntimeException("File not found: " + path);
        }
        return file;
    }
}
