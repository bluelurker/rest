package com.rm.movie;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.rm.moviecrawler.english.RMEnglishMovieListGetter;

public class MovieListTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getMovieListTest() {
		try {
			RMEnglishMovieListGetter.getMovieList(100);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
