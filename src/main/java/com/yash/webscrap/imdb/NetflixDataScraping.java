package com.yash.webscrap.imdb;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Yashwanth
 */
public class NetflixDataScraping {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(final String[] args) throws IOException {
		try {
			Document doc = Jsoup.connect("https://www.imdb.com/search/keyword/?keywords=netflix-original").get();
			Elements pageDesc = doc.getElementsByClass("desc");
			String page = pageDesc.eachText().get(0);
			String[] pageDataCount = page.split("of");
			String[] tillPage = pageDataCount[0].split("to");
			int length = Integer.parseInt(tillPage[1].trim());
			int total = Integer.parseInt(pageDataCount[1].replaceAll("\\D+", "").trim());
			double val = ((double) total) / length;
			int pageCount = (int) Math.round(val);
			for (int i = 1; i <= pageCount; i++) {
				doc = Jsoup.connect(
						"https://www.imdb.com/search/keyword/?keywords=netflix-original&sort=moviemeter,asc&mode=detail&page=" + i +
						"&ref_=kw_nxt")
						.get();
				System.out.printf("Title: %s\n", doc.title());
				// Get the list of repositories
				Elements repositories = doc.getElementsByClass("lister-item-content");

				/**
				 * For each repository, extract the following information: 1. Title 2. Year 3. Genre 4. Rating
				 */
				for (Element repository : repositories) {
					for (Element topElement : repository.getElementsByClass("lister-item-content")) {
						for (Element headElement : topElement.getElementsByClass("lister-item-header")) {
							Elements elementName = headElement.getElementsByAttribute("href");
							Elements year = headElement.getElementsByClass("lister-item-year text-muted unbold");
							System.out.println("Title:" + elementName.text());
							String releasedYear = year.text();
							if ((releasedYear.length() > 6) && (releasedYear.charAt(6) == ' ')) {
								releasedYear = year.text().substring(0, 5) + ")";
							}
							if (releasedYear.startsWith("(I")) {
								releasedYear = year.text().substring(4, year.text().length());
								if (releasedYear.endsWith("– )")) {
									releasedYear = releasedYear.substring(0, releasedYear.indexOf("– )")) + ")";
								}
							}
							if (releasedYear.endsWith("TV Short)")) {
								releasedYear = year.text().substring(0, releasedYear.indexOf("TV Short)")).trim() + ")";
							}
							if (releasedYear.endsWith("TV Movie)")) {
								releasedYear = year.text().substring(0, releasedYear.indexOf("TV Movie)")).trim() + ")";
							}
							if (releasedYear.endsWith("TV Special)")) {
								releasedYear = year.text().substring(0, releasedYear.indexOf("TV Special)")).trim() + ")";
							}

							System.out.println("Year:" + releasedYear);
							for (Element bottomElement : topElement.getElementsByClass("text-muted text-small")) {
								if (!bottomElement.getElementsByClass("genre").isEmpty()) {
									System.out.println("Genre:" + bottomElement.getElementsByClass("genre").text());
								}
							}
							for (Element rateElement : topElement.getElementsByClass("inline-block ratings-imdb-rating")) {
								if (!rateElement.attr("data-value").isEmpty()) {
									System.out.println("Rating:" + rateElement.attr("data-value"));
								}
								System.out.println();
							}
						}
					}
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param str
	 * @return
	 */
	public static String capitalize(final String str) {
		if ((str == null) || str.isEmpty()) {
			return str;
		}
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
}

