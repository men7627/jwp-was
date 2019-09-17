package utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileIoUtils {
    public static byte[] loadFileFromClasspath(String filePath) throws IOException, URISyntaxException {
        Path path = getPath(filePath);
        return Files.readAllBytes(path);
    }

    public static boolean existResource(String uri) throws URISyntaxException {
        return Files.exists(getPath(uri));
    }

    private static Path getPath(String uri) throws URISyntaxException {
        return Paths.get(FileIoUtils.class.getClassLoader().getResource(uri).toURI());
    }
}
