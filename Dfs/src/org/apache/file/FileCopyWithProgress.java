package org.apache.file;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

public class FileCopyWithProgress {
	public static void main(String[] args) throws IOException {
		String localStr = "E:\\Java\\EclipseWorkSpace\\Dfs\\src\\org\\apache\\file\\FileCopyWithProgress.java";
		String dest = "hdfs://192.168.1.111:9000/user/Administrator/input/progress.txt";

		InputStream in = new BufferedInputStream(new FileInputStream(localStr));
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(URI.create(dest), conf);
		OutputStream out = fs.create(new Path(dest), new Progressable() {

			@Override
			public void progress() {
				System.out.println(".");
			}
			
		});

		IOUtils.copyBytes(in, out, 4096, false);
	}
}
