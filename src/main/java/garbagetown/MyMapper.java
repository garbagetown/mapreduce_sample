package garbagetown;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by garbagetown on 4/2/15.
 */
public class MyMapper extends Mapper<Text, Text, Text, Text> {

    private Text url = new Text();
    private Text id = new Text();

    @Override
    protected void map(Text key, Text value, Context context) throws IOException, InterruptedException {
        ApacheLogParser parser = ApacheLogParser.parse(value.toString());
        url.set(parser.tokens()[5]);
        id.set(parser.tokens()[2]);
        context.write(url, id);
    }
}
