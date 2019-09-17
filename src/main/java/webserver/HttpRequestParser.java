package webserver;

import utils.FileIoUtils;
import utils.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

public class HttpRequestParser {
    private static final String ROOT_PATH = "./templates/";
    private String requestLine;
    private HttpRequestHeadLine headLine;

    public HttpRequestParser(InputStream in) throws IOException {
        this.requestLine = IOUtils.readData(in);
        this.headLine = new HttpRequestHeadLine(requestLine);
    }

    public byte[] doService() throws IOException, URISyntaxException {
        String uri = headLine.getUri();
        if (FileIoUtils.existResource(uri)) {
            return FileIoUtils.loadFileFromClasspath(ROOT_PATH + uri);
        }
        return "NOT_FOUND".getBytes();
    }
}
