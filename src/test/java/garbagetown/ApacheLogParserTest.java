package garbagetown;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by garbagetown on 4/2/15.
 */
public class ApacheLogParserTest {

    @Test
    public void testGetRequest() {
        String line = "44.168.187.141 - 5a5b1d06300afeab28c6cf7429fa2229d2311b79 " +
                "[22/Mar/2015:06:48:13 +0900] " +
                "\"GET /category/electronics HTTP/1.1\" 200 127 " +
                "\"/category/music?from=10\" " +
                "\"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:10.0.1) Gecko/20100101 Firefox/10.0.1\"";
        ApacheLogParser parser = ApacheLogParser.parse(line);
        assertThat(parser.tokens()[5].toString(), is("/category/electronics"));
    }

    @Test
    public void testGetRequestWithQueryParameter() {
        String line = "44.168.187.141 - 5a5b1d06300afeab28c6cf7429fa2229d2311b79 " +
                "[22/Mar/2015:06:48:13 +0900] " +
                "\"GET /category/music?from=20 HTTP/1.1\" 200 127 " +
                "\"/category/music?from=10\" " +
                "\"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:10.0.1) Gecko/20100101 Firefox/10.0.1\"";
        ApacheLogParser parser = ApacheLogParser.parse(line);
        assertThat(parser.tokens()[5].toString(), is("/category/music"));
    }
}
