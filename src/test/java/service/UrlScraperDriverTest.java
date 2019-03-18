package service;

import com.google.gson.JsonElement;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import sainsbury.scrapper.api.JsoupUrlVisitorImpl;
import sainsbury.scrapper.api.Scraper;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class UrlScraperDriverTest {

    @Test
    public void whenUrlIsValid() throws IOException {
        String expectedJson = readJsonFromFile("Expected.json");
        Scraper scraper = new Scraper(new JsoupUrlVisitorImpl());
        JsonElement actualJson = scraper.scrape(getSainsburyUrl());
        assertEquals(expectedJson, actualJson.toString());
    }


    private String getSainsburyUrl() {
        return "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";

    }

    private String getWrongSainsburyUrl() {
        return "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6038.html";

    }

    private String readJsonFromFile(String fileName) throws IOException {
        String json = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream(fileName), "UTF-8");
        return json;
    }


}
