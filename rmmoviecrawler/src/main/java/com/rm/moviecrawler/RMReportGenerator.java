package com.rm.moviecrawler;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class RMReportGenerator {

	public static void createHTML(final List<MovieRating> movieRatingsList, File htmlOutPut) throws Exception {
		htmlOutPut.delete();
		FileUtils.writeStringToFile(htmlOutPut, "<title>\nRajiv Masand Movie Ratings\n</title>\n", "UTF-8", true);
		FileUtils.writeStringToFile(htmlOutPut, "<body>\n", "UTF-8", true);
		FileUtils.writeStringToFile(htmlOutPut, "<table border=\"1px\">\n", "UTF-8", true);
		FileUtils.writeStringToFile(htmlOutPut, "<tr>\n", "UTF-8", true);
		FileUtils.writeStringToFile(htmlOutPut,
				"<tr><td><b>S.No</b></td><td><b>Movie Name</b></td><td><b>Release Year</b></td><td><b>Rating</b></td></tr>\n",
				"UTF-8", true);
		int serial = 0;
		for (MovieRating movieRatings : movieRatingsList) {

			FileUtils.writeStringToFile(htmlOutPut, "<td>", "UTF-8", true);
			FileUtils.writeStringToFile(htmlOutPut, String.valueOf(++serial), "UTF-8", true);
			FileUtils.writeStringToFile(htmlOutPut, "<td>", "UTF-8", true);
			FileUtils.writeStringToFile(htmlOutPut, movieRatings.getMovieName(), "UTF-8", true);
			FileUtils.writeStringToFile(htmlOutPut, "</td>\n", "UTF-8", true);
			FileUtils.writeStringToFile(htmlOutPut, "<td>", "UTF-8", true);
			FileUtils.writeStringToFile(htmlOutPut, movieRatings.getReleaseDate() + "", "UTF-8", true);
			FileUtils.writeStringToFile(htmlOutPut, "</td>\n", "UTF-8", true);
			FileUtils.writeStringToFile(htmlOutPut, "<td>", "UTF-8", true);
			FileUtils.writeStringToFile(htmlOutPut, movieRatings.getMovieRating() + "", "UTF-8", true);
			FileUtils.writeStringToFile(htmlOutPut, "</td>\n", "UTF-8", true);
			FileUtils.writeStringToFile(htmlOutPut, "</tr>\n", "UTF-8", true);
		}
		FileUtils.writeStringToFile(htmlOutPut, "</table>\n", "UTF-8", true);
		FileUtils.writeStringToFile(htmlOutPut, "</body>\n", "UTF-8", true);
	}

}
