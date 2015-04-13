package garbagetown;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by garbagetown on 4/2/15.
 */
public class MyReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    private static final Logger logger = LogManager.getLogger(MyReducer.class);
    private IntWritable result = new IntWritable();

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {

        int sum = 0;
        for (IntWritable value : values) {
            logger.info(value.get());
            sum += value.get();
        }
        result.set(sum);

        context.write(key, result);
    }
}
