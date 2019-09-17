package http;

import utils.IOUtils;

import java.io.InputStream;

public class HttpRequestParser {
    public static HttpRequest parse(InputStream in) {
        return new HttpRequest(new HttpRequestHeadLine(IOUtils.readData(in)));
    }
}
