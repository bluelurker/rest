package com.rm.moviecrawler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import static com.rm.moviecrawler.RMWebCrawlerConstants.*;

public class RMEnglishMovieListGetter {
	private static final Queue<String> movieUrls = new ArrayBlockingQueue<>(10000);

	public static Queue<String> getMovieList(int maxPageLimit) throws Exception {
		if (maxPageLimit < 1) {
			throw new IllegalArgumentException("maxPageLimit can't be less than 1");
		}
		RMEnglishMovieListGetter rObj = new RMEnglishMovieListGetter();
		ExecutorService exec = Executors.newFixedThreadPool(40);
		for (int i = 1; i <= maxPageLimit; i++) {
			MovieUrlGetter rajeevMasand = rObj.new MovieUrlGetter(i);
			exec.execute(rajeevMasand);
		}
		exec.shutdown();
		exec.awaitTermination(1, TimeUnit.DAYS);
		return movieUrls;
	}

	private class MovieUrlGetter implements Runnable {
		private int pageNumber;

		public MovieUrlGetter(int pageNumber) {
			super();
			this.pageNumber = pageNumber;
		}

		@Override
		public void run() {
			try {
				URL url = new URL(PAGEPREFIX + "/" + this.pageNumber + "/");
				URLConnection yccon = url.openConnection();
				BufferedReader in = new BufferedReader(new InputStreamReader(yccon.getInputStream()));
				String inputLine;
				while ((inputLine = in.readLine()) != null) {
					if (inputLine.contains(PREFIX)) {
						String s = inputLine.substring(inputLine.indexOf(PREFIX),
								inputLine.indexOf("\"", inputLine.indexOf(PREFIX)));
						if (!movieUrls.contains(s)) {
							movieUrls.add(s);
						}
					}
				}
			} catch (FileNotFoundException e) {
				// Ignore this exception as there can be a page
				// which does not exist
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}

	}

}
