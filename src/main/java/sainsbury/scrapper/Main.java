package sainsbury.scrapper;

import sainsbury.scrapper.api.JsoupUrlVisitorImpl;
import sainsbury.scrapper.api.Scraper;

public class Main {
    private static String sainsBuryUrl = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";

    public static void main(String[] args) {
        Scraper scraper = new Scraper(new JsoupUrlVisitorImpl());
        scraper.scrape(sainsBuryUrl);
    }
}
