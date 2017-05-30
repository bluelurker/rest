package com.rm.moviecrawler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Queue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class RMEnglishMovieRatingGetter {
	public final static List<MovieRating> movieRatingList = new CopyOnWriteArrayList<MovieRating>();

	private static RMEnglishMovieRatingGetter obj = new RMEnglishMovieRatingGetter();

	private RMEnglishMovieRatingGetter() {

	}

	public static RMEnglishMovieRatingGetter getInstance() {
		return obj;
	}

	public List<MovieRating> getMovieRatings(Queue<String> movieListUrls) {
		try {
			ExecutorService exec = Executors.newFixedThreadPool(35);
			for (String url : movieListUrls) {
				MovieProcessor rajeevMasand = new MovieProcessor(url);
				exec.execute(rajeevMasand);
			}
			exec.shutdown();
			exec.awaitTermination(1, TimeUnit.DAYS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return movieRatingList;
	}

	private class MovieProcessor implements Runnable {
		private String movieUrl;

		public MovieProcessor(String movieUrl) {
			super();
			this.movieUrl = movieUrl;
		}

		@Override
		public void run() {
			MovieRating movieRating = new MovieRating();
			try {
				Document doc = Jsoup.connect(movieUrl).get();
				Elements elmnts = doc.getElementsByClass("have-you-seen");
				movieRating.setMovieName(elmnts.select("h3").get(0).ownText());
				movieRating
						.setMovieRating(Float.valueOf(elmnts.select("p").get(0).getElementsByTag("img").attr("alt")));
				movieRating.setReleaseDate(getMovieReleaseYear(elmnts.select("p").get(1).ownText()));
				movieRatingList.add(movieRating);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Exception for :" + movieUrl);
			}
		}
	}

	@SuppressWarnings("deprecation")
	private int getMovieReleaseYear(String date) throws Exception {
		DateFormat fmt = new SimpleDateFormat("MMMM dd, yyyy", Locale.US);
		Date d = null;
		d = fmt.parse(date);
		if (d != null) {
			return d.getYear() + 1900;
		} else {
			return 0;
		}
	}
}
