package webserver;

import utils.FileIoUtils;
import utils.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

public class HttpRequestParser {
    private static final String DELIMITER = " ";
    private String requestLine;
    private String[] requestHeaders;

    public HttpRequestParser(InputStream in) throws IOException {
        this.requestLine = IOUtils.readData(in);
        this.requestHeaders = requestLine.split(DELIMITER);
    }

    public byte[] doService() throws IOException, URISyntaxException {
        if (requestHeaders[1].equals("/index.html")) {
            return FileIoUtils.loadFileFromClasspath("./templates/index.html");

        }
        return "NOT_FOUND".getBytes();
    }
}
