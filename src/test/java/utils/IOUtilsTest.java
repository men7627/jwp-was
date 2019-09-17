package utils;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringReader;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class IOUtilsTest {
    private static final Logger logger = LoggerFactory.getLogger(IOUtilsTest.class);

    @Test
    public void readData() throws Exception {
        String data = "abcd123";
        StringReader sr = new StringReader(data);
        BufferedReader br = new BufferedReader(sr);

        logger.debug("parse body : {}", IOUtils.readData(br, data.length()));
    }

    @Test
    void readDataOverload() throws Exception {
        String data = "abcdefg123";
        InputStream inputStream = new ByteArrayInputStream(data.getBytes());

        assertThat(IOUtils.readData(inputStream)).isEqualTo(data);
    }
}
