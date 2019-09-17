package http;

import java.util.Arrays;
import java.util.List;

public class HttpRequestHeadLine {
    private static final String DELIMITER = " ";
    private static final String QUERY_PARAMETER_INDICATOR = "?";
    private static final int TOKEN_SIZE = 3;
    private List<String> tokens;
    private QueryParameter queryParameter;

    public HttpRequestHeadLine(String requestLine) {
        tokens = Arrays.asList(requestLine.split(DELIMITER));
        parseParameter();
        validate(tokens);
    }

    private void validate(List<String> tokens) {
        if (tokens.size() != TOKEN_SIZE) {
            throw new IllegalHttpRequestException();
        }
    }

    private void parseParameter() {
        String uri = getUri();
        String queryString = "";
        if (uri.contains(QUERY_PARAMETER_INDICATOR)) {
            String[] splitUri = uri.split("\\?");
            tokens.set(1, splitUri[0]);
            queryString = splitUri[1];
        }
        this.queryParameter = new QueryParameter(queryString);
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

    public QueryParameter getParameter() {
        return queryParameter;
    }
}
