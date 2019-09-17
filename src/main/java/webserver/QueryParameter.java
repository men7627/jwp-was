package webserver;

import java.util.HashMap;
import java.util.Map;

public class QueryParameter {
    private static final String INDICATOR = "?";
    private static final String KEY_VALUE_DIVIDER = "=";
    private static final String DELIMITER = "&";
    private Map<String, String> parameters;

    public QueryParameter(String uri) {
        this.parameters = parse(uri);
    }

    private Map<String, String> parse(String uri) {
        if (uri.contains(INDICATOR)) {
            String queryString = toQueryString(uri);
            return createParameters(queryString);
        }
        return new HashMap<>();
    }

    private Map<String, String> createParameters(String queryString) {
        parameters = new HashMap<>();
        for (String parameter : queryString.split(DELIMITER)) {
            String[] entry = parameter.split(KEY_VALUE_DIVIDER);
            parameters.put(entry[0], entry[1]);
        }
        return parameters;
    }

    private String toQueryString(String uri) {
        return uri.substring(uri.indexOf(INDICATOR) + 1);
    }

    public boolean hasParameter() {
        return parameters.size() != 0;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }
}
