package http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.FileIoUtils;
import utils.NoSuchResource;

public class HttpRequestHandler {
    private static final Logger log = LoggerFactory.getLogger(HttpRequestHandler.class);
    private static final String ROOT_PATH = "./templates";

    private HttpRequest httpRequest;

    public HttpRequestHandler(HttpRequest httpRequest) {
        this.httpRequest = httpRequest;
    }

    public HttpRequestHandler() {

    }

    public byte[] doService() {
        String uri = ROOT_PATH + httpRequest.getUri();
        log.info(uri);

        try {
            return FileIoUtils.loadFileFromClasspath(uri);
        } catch (NoSuchResource e) {
            log.error(e.getMessage());
            return "NOT_FOUND".getBytes();
        }
    }
}
