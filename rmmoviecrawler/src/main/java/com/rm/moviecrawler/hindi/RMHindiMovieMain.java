package com.rm.moviecrawler.hindi;

import com.rm.moviecrawler.common.MovieRating;
import com.rm.moviecrawler.common.RMReportGenerator;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class RMHindiMovieMain {
	private static File htmlOutPut = new File("report/report.html");

	public static void main(String[] args) throws Exception {
		Queue<String> movieUrls = RMHindiMovieListGetter.getMovieList(100);
		RMHindiMovieRatingGetter ratingGetter = RMHindiMovieRatingGetter.getInstance();
		List<MovieRating> ratingList = ratingGetter.getMovieRatings(movieUrls);
		List<MovieRating> newList = new ArrayList<>(ratingList);
		Collections.sort(newList);
		RMReportGenerator.createHTML(ratingList, htmlOutPut);
	}

}
