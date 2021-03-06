package org.apache.file;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class FileSystemApiTest {
	public static void main(String[] args) throws IOException {
		String uri = "hdfs://192.168.1.111:9000/user/Administrator/input/input.txt";
	    Configuration conf = new Configuration();
	    FileSystem fs = FileSystem.get(URI.create(uri),conf);
	    InputStream in = null;
	    try {
			in = fs.open(new Path(uri));
			IOUtils.copyBytes(in, System.out, 4096, false);
		} catch (Exception e) {
            IOUtils.closeStream(in);
            e.printStackTrace();
		}
	}
}
