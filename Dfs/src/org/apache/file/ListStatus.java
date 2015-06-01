package org.apache.file;

import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;

public class ListStatus {
	public static void main(String[] args) throws IOException {
		String uri = "hdfs://192.168.1.111:9000/user/Administrator/input";
         Configuration conf = new Configuration();
         FileSystem fs = FileSystem.get(URI.create(uri),conf);
         Path[] paths = new Path[1];
         paths[0] = new Path(uri);
         FileStatus[] status = fs.listStatus(paths[0]);
         Path[] listedPaths = FileUtil.stat2Paths(status);
         for (Path path : listedPaths) {
			System.out.println(path);
		}
	}
}
