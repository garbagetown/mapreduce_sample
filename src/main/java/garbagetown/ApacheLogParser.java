package garbagetown;

import org.apache.hadoop.io.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by garbagetown on 4/2/15.
 */
public class ApacheLogParser {

    private static final String REGEX = "^" +
            // group 1-3, e.g. 44.168.187.141 - 5a5b1d06300afeab28c6cf7429fa2229d2311b79
            "([\\d.]+) (\\S+) (\\S+) " +
            // group 4, e.g. [22/Mar/2015:06:48:13 +0900]
            "\\[([\\w:/]+ [\\+\\-]\\d{4})\\] " +
            // group 5-9, e.g. "GET /category/music?from=20 HTTP/1.1" 200 127
            "\"(\\S+) ([^?]+).* (\\S+)\" (\\d{3}) (\\d+) " +
            // group 10, e.g. "/category/music?from=10"
            "\"(\\S+)\" " +
            // group 11, e.g. "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:10.0.1) Gecko/20100101 Firefox/10.0.1"
            "\"([^\"]+)\"" +
            "$";

    private Text[] tokens;

    /**
     *
     * @param line
     * @return
     */
    public static ApacheLogParser parse(String line) {
        ApacheLogParser instance = new ApacheLogParser();
        instance.tokens = new Text[11];
        Matcher m = Pattern.compile(REGEX).matcher(line);
        if (m.matches()) {
            for (int i = 0; i < 11; i++) {
                instance.tokens[i] = new Text(m.group(i + 1));
            }
        }
        return instance;
    }

    /**
     *
     * @return
     */
    public Text[] tokens() {
        return tokens;
    }
}
