package http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.FileIoUtils;
import utils.IOUtils;
import webserver.RequestHandler;

import java.io.InputStream;

public class HttpRequestParser {
    private static final Logger logger = LoggerFactory.getLogger(RequestHandler.class);
    private static final String ROOT_PATH = "./templates";
    private static final String QUERY_STRING_INDICATOR = "?";
    private HttpRequestHeadLine headLine;

    public HttpRequestParser(InputStream in) {
        this.headLine = new HttpRequestHeadLine(IOUtils.readData(in));
    }

    public static HttpRequest parse(InputStream in) {
        HttpRequestHeadLine headLine = new HttpRequestHeadLine(IOUtils.readData(in));

        String method = headLine.getMethod();
        String uri = headLine.getUri();
        String protocol = headLine.getProtocol();
        QueryParameter parameters = headLine.getParameter();
        return new HttpRequest(method, uri, protocol, parameters);
    }

    public byte[] doService() {
        String uri = ROOT_PATH + headLine.getUri();
        logger.info(uri);

        if (uri.contains(QUERY_STRING_INDICATOR)) {
            System.out.println(uri);
        }

        if (FileIoUtils.existResource(uri)) {
            return FileIoUtils.loadFileFromClasspath(uri);
        }
        return "NOT_FOUND".getBytes();
    }
}
