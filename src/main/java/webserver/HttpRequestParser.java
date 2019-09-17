package webserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.FileIoUtils;
import utils.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

public class HttpRequestParser {
    private static final Logger logger = LoggerFactory.getLogger(RequestHandler.class);
    private static final String ROOT_PATH = "./templates";
    private static final String QUERYSTRING_INDICATOR = "?";
    private HttpRequestHeadLine headLine;

    public HttpRequestParser(InputStream in) throws IOException {
        this.headLine = new HttpRequestHeadLine(IOUtils.readData(in));
    }

    public static HttpRequest parse(InputStream in) {
        HttpRequestHeadLine headLine = new HttpRequestHeadLine(IOUtils.readData(in));

        String method = headLine.getMethod();
        String uri = headLine.getUri();
        String protocol = headLine.getProtocol();
        QueryParameter parameters = headLine.getParameters();
        return new HttpRequest(method, uri, protocol, parameter);
    }

    public byte[] doService() throws IOException, URISyntaxException {
        String uri = ROOT_PATH + headLine.getUri();
        logger.info(uri);

        if (uri.contains(QUERYSTRING_INDICATOR)) {
            System.out.println(uri);
        }

        if (FileIoUtils.existResource(uri)) {
            return FileIoUtils.loadFileFromClasspath(uri);
        }
        return "NOT_FOUND".getBytes();
    }
}
