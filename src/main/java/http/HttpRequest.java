package http;

public class HttpRequest {
    private HttpRequestHeadLine headLine;

    public HttpRequest(HttpRequestHeadLine headLine) {
        this.headLine = headLine;
    }

    public String getUri() {
        return headLine.getUri();
    }
}
