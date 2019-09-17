package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileIoUtils {
    private static final Logger log = LoggerFactory.getLogger(FileIoUtils.class);

    public static byte[] loadFileFromClasspath(String filePath) {
        return getFileData(filePath);
    }

    public static boolean existResource(String uri) {
        return Files.exists(getPath(uri));
    }

    private static byte[] getFileData(String uri) {
        if (existResource(uri)) {
            return getFileBytes(uri);
        }
        throw new NoSuchResource();
    }

    private static byte[] getFileBytes(String uri) {
        try {
            return Files.readAllBytes(getPath(uri));
        } catch (IOException e) {
            throw new NoSuchResource();
        }
    }

    private static Path getPath(String uri) {
        try {
            return Paths.get(ClassLoader.getSystemResource(uri).toURI());
        } catch (URISyntaxException | NullPointerException e) {
            log.error(e.getMessage());
            throw new NoSuchResource();
        }
    }
}
