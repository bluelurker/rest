package com.rm.movie;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jdk.nashorn.internal.ir.annotations.Ignore;

public class MovieListTest2 {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getMovieListTest() {
		try {
			Document doc = Jsoup.connect("http://www.rajeevmasand.com/reviews/their-films/claws-out-2/").get();
			Elements elmnts = doc.getElementsByClass("have-you-seen");
			System.out.println(elmnts.select("h3").get(0).ownText());
			System.out.println(elmnts.select("p").get(0).getElementsByTag("img").attr("alt"));
			System.out.println(elmnts.select("p").get(1).ownText());

			for (Element elt : elmnts.select("p")) {
				// System.out.println(elt);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	@Ignore
	public void dateFormatter() throws Exception {
		DateFormat fmt = new SimpleDateFormat("MMMM dd, yyyy", Locale.US);
		Date d = fmt.parse("June 27,  2007");

		DateFormat newfmt = new SimpleDateFormat("yyyy/MM/dd");
		System.out.println(newfmt.format(d));
	}

}
