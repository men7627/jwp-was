package webserver;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.*;
import java.net.Socket;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

class RequestHandlerTest {
    private static final String INDEX_REQUEST = "GET /index.html HTTP/1.1";
    private static final String ILLEGAL_REQUEST = "GET /something HTTP/1.1";

    @Mock
    Socket socket;

    @InjectMocks
    RequestHandler requestHandler;

    private InputStream inputStream;
    private OutputStream outputStream;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    void 인덱스_요청_정상_테스트() {
        inputStream = new ByteArrayInputStream(INDEX_REQUEST.getBytes());
        outputStream = new ByteArrayOutputStream();

        try {
            when(socket.getInputStream()).thenReturn(inputStream);
            when(socket.getOutputStream()).thenReturn(outputStream);

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        requestHandler.run();

        System.err.println(outputStream.toString());

        assertThat(outputStream.toString()).isNotEmpty();
    }

    @Test
    void 존재하지_않는_자원_요청_테스트() {
        inputStream = new ByteArrayInputStream(ILLEGAL_REQUEST.getBytes());
        outputStream = new ByteArrayOutputStream();

        try {
            when(socket.getInputStream()).thenReturn(inputStream);
            when(socket.getOutputStream()).thenReturn(outputStream);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        requestHandler.run();

        System.err.println(outputStream.toString());

        assertThat(outputStream.toString().contains("NOT_FOUND")).isTrue();
    }
}