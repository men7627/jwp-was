package http;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class QueryParameter {
    private static final String KEY_VALUE_DIVIDER = "=";
    private static final String DELIMITER = "&";
    private Map<String, String> parameters;

    public QueryParameter(String uri) {
        this.parameters = parse(uri);
    }

    private Map<String, String> parse(String uri) {
        if (!StringUtils.isEmpty(uri)) {
            return createParameters(uri);
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

    public boolean hasParameter() {
        return parameters.size() != 0;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }
}
