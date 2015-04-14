package garbagetown;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Counters;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.TaskCounter;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by garbagetown on 4/1/15.
 */
public class MyDriver extends Configured implements Tool {

    private static final Logger logger = LogManager.getLogger(MyDriver.class);

    /**
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new Configuration(), new MyDriver(), args);
        System.exit(exitCode);
    }

    @Override
    public int run(String[] args) throws Exception {

        if (args.length != 2) {
            return -1;
        }

        Job job = Job.getInstance();
        job.setJobName("count pv");
        job.setJarByClass(MyDriver.class);

        job.setMapperClass(MyMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setCombinerClass(MyReducer.class);

        job.setNumReduceTasks(2);
        job.setReducerClass(MyReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        boolean success = job.waitForCompletion(true);

        Counters counters = job.getCounters();
        logger.info(counters.findCounter(TaskCounter.MAP_INPUT_RECORDS).getValue());
        logger.info(counters.findCounter(TaskCounter.MAP_OUTPUT_RECORDS).getValue());
        logger.info(counters.findCounter(TaskCounter.REDUCE_INPUT_RECORDS).getValue());
        logger.info(counters.findCounter(TaskCounter.REDUCE_OUTPUT_RECORDS).getValue());

        return success ? 0 : 1;
    }
}
