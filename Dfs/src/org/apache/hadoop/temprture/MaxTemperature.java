package org.apache.hadoop.temprture;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;


public class MaxTemperature {

  public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException, ClassNotFoundException {
	  Configuration conf = new Configuration();
	    conf.set("mapred.job.tracker", "hdfs://192.168.1.111:9001");
	    conf.set("fs.default.name","hdfs://192.168.1.111:9000");
	    
	    String[] ars = new String[]{"input", "output"};
	    String[] otherArgs = new GenericOptionsParser(conf, ars).getRemainingArgs();
	    if (otherArgs.length != 2) {
	      System.err.println("Usage: wordcount <in> <out>");
	      System.exit(2);
	    }
	    Job job = new Job(conf, "word count");
	    
	    File jarFile = EJob.createTempJar("bin");
	    EJob.addClasspath("/usr/local/hadoop/conf");
	    ClassLoader classLoader = EJob.getClassLoader();
	    Thread.currentThread().setContextClassLoader(classLoader);
	    ((JobConf) job.getConfiguration()).setJar(jarFile.toString()); 
	    
	    //job.setJarByClass(WordCount.class);
	    job.setMapperClass(MaxTemperatureMapper.class);
	    job.setReducerClass(MaxTemperatureReducer.class);
	    job.setOutputKeyClass(Text.class);
	    job.setOutputValueClass(IntWritable.class);
	    FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
	    FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
	    
	    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}
