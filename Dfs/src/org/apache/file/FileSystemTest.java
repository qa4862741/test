package org.apache.file;

import java.io.InputStream;
import java.net.URL;

import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.io.IOUtils;

public class FileSystemTest {

	static {
		URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
	}

	public static void main(String[] args) {
		InputStream in = null;
		try {
           in = new URL("hdfs://192.168.1.111:9000/user/Administrator/input/input.txt").openStream();
           IOUtils.copyBytes(in, System.out, 4096, false);
		} catch (Exception e) {
			IOUtils.closeStream(in);
			e.printStackTrace();
		}
	}
}
