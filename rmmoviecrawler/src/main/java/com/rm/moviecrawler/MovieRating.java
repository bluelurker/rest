package com.rm.moviecrawler;

public class MovieRating implements Comparable<MovieRating> {
	private String movieName;
	private float movieRating;
	private int releaseDate;

	public int getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(int releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public float getMovieRating() {
		return movieRating;
	}

	public void setMovieRating(float movieRating) {
		this.movieRating = movieRating;
	}

	@Override
	public int compareTo(MovieRating o) {
		if (this.movieRating == o.movieRating) {
			return 0;
		} else if (this.movieRating < o.movieRating) {
			return 1;
		} else {
			return -1;
		}
	}

}