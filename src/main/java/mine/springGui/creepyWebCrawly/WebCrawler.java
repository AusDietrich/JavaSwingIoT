package mine.springGui.creepyWebCrawly;

import java.io.IOException;
import java.util.HashSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebCrawler {

    private HashSet<String> links;

    public WebCrawler() {
        links = new HashSet<String>();
    }

    public String getPageLinks(String URL) {
    	String temper = "";
        //4. Check if you have already crawled the URLs
        //(we are intentionally not checking for duplicate content in this example)
        if (!links.contains(URL)) {
            try {
                //4. (i) If not add it to the index
                if (links.add(URL)) {
                    System.out.println(URL);
                }

                //2. Fetch the HTML code
                Document document = Jsoup.connect(URL).get();
                //3. Parse the HTML to extract links to other URLs
                String linksOnPage = document.select("span").text();

                //5. For each extracted URL... go back to St`ep 4.
                System.out.println("link: "+linksOnPage);
                String[] listOnPage = linksOnPage.split("^*(star_ratehome)$*");
                int x = 0;
                for (String list : listOnPage) {
                	if (x==1) {
                		String[] temperature = listOnPage[x].split("F");
                		temper = "Temperature: "+temperature[0]+"F";
                	}
                	x++;
                }
//                for (Element page : linksOnPage) {
//                    getPageLinks(page.attr("abs:class"));
//                }
            } catch (IOException e) {
                System.err.println("For '" + URL + "': " + e.getMessage());
            }
        }
        return temper;
    }

    public static void main(String[] args) {
        //1. Pick a URL from the frontier
        new WebCrawler().getPageLinks("https://www.wunderground.com/weather/us/az/tempe");
    }
}
