package sainsbury.scraper.service;

import com.google.gson.JsonElement;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ScraperTest {
    private static final String VALID_SAINSBURY_URL = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";
    private static final String INVALID_SAINSBURY_URL = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6038.html";

    @Test
    public void whenUrlIsEmpty() {
        Scraper scraper = new Scraper(new JsoupUrlVisitorImpl());
        JsonElement actualJson  =  scraper.scrape("");
        assertEquals("{}", actualJson.toString());
    }


    @Test
    public void whenUrlIsInvalid() {
        String expectedJson = "{\"results\":[],\"productGrossAndVat\":{\"gross\":0,\"vat\":0.00}}";
        Scraper scraper = new Scraper(new JsoupUrlVisitorImpl());
        JsonElement actualJson  = scraper.scrape(INVALID_SAINSBURY_URL);
        assertEquals(expectedJson , actualJson.toString());
    }


    @Test
    public void whenUrlIsValid() throws IOException {
        String expectedJson = readJsonFromFile("Expected.json");
        Scraper scraper = new Scraper(new JsoupUrlVisitorImpl());
        JsonElement actualJson = scraper.scrape(VALID_SAINSBURY_URL);
        assertEquals(expectedJson, actualJson.toString());
    }

    private String readJsonFromFile(String fileName) throws IOException {
        return IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream(fileName), "UTF-8");
    }


}
