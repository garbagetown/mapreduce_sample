package garbagetown;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by garbagetown on 4/2/15.
 */
public class MyMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    private Text url = new Text();
    private IntWritable one = new IntWritable(1);

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        ApacheLogParser parser = ApacheLogParser.parse(value.toString());

        url.set(parser.tokens()[5]);

        context.write(url, one);
    }
}
