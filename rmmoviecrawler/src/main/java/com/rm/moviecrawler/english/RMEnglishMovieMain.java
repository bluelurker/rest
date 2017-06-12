package com.rm.moviecrawler.english;

import com.rm.moviecrawler.common.MovieRating;
import com.rm.moviecrawler.common.RMReportGenerator;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class RMEnglishMovieMain {
	private static File htmlOutPut = new File("report/report.html");

	public static void main(String[] args) throws Exception {
		Queue<String> movieUrls = RMEnglishMovieListGetter.getMovieList(100);
		RMEnglishMovieRatingGetter ratingGetter = RMEnglishMovieRatingGetter.getInstance();
		List<MovieRating> ratingList = ratingGetter.getMovieRatings(movieUrls);
		Collections.sort(ratingList);
		RMReportGenerator.createHTML(ratingList, htmlOutPut);
	}

}
