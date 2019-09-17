package webserver;

import http.QueryParameter;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class QueryParameterTest {
    @Test
    void 파라미터_파싱_1개() {
        String uri = "name=andole";
        QueryParameter queryParameter = new QueryParameter(uri);

        assertThat(queryParameter.hasParameter()).isTrue();
        Map<String, String> parameters = queryParameter.getParameters();
        assertThat(parameters.get("name")).isEqualTo("andole");
    }

    @Test
    void 파라미터_파싱_여러개() {
        String uri = "name=andole&age=20&pair=coogi&whale=killer";
        QueryParameter queryParameter = new QueryParameter(uri);

        assertThat(queryParameter.hasParameter()).isTrue();

        Map<String, String> parameters = queryParameter.getParameters();
        assertThat(parameters.get("name")).isEqualTo("andole");
        assertThat(parameters.get("age")).isEqualTo("20");
        assertThat(parameters.get("pair")).isEqualTo("coogi");
        assertThat(parameters.get("whale")).isEqualTo("killer");
    }

    @Test
    void 파라미터_없음() {
        String uri = "";
        QueryParameter queryParameter = new QueryParameter(uri);

        assertThat(queryParameter.hasParameter()).isFalse();
    }
}