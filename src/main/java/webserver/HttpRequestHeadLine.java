package webserver;

import java.util.Arrays;
import java.util.List;

public class HttpRequestHeadLine {
    private static final String DELIMITER = " ";
    private List<String> tokens;

    public HttpRequestHeadLine(String requestLine) {
        tokens = Arrays.asList(requestLine.split(DELIMITER));
        validate(tokens);
    }

    private void validate(List<String> tokens) {
        if (tokens.size() != 3) {
            throw new IllegalHttpRequestException();
        }
    }

    public String getMethod() {
        return tokens.get(0);
    }

    public String getUri() {
        return tokens.get(1);
    }

    public String getProtocol() {
        return tokens.get(2);
    }
}
