package garbagetown;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by garbagetown on 4/2/15.
 */
public class MyReducer extends Reducer<Text, Text, Text, Text> {

    private Text result = new Text();

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

        Set<String> ids = new HashSet<>();

        int pv = 0;
        for (Text value : values) {
            ids.add(value.toString());
            pv += 1;
        }
        int uu = ids.size();

        result.set(pv + "\t" + uu);

        context.write(key, result);
    }
}
