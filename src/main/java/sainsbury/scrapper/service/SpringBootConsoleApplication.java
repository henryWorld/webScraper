package sainsbury.scrapper.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import sainsbury.scrapper.api.Scraper;

/**
 * This class runs the programme from the CommandLine using SpringBoot Application
 */

@SpringBootApplication
@ComponentScan(basePackages = {"sainsbury.scrapper.api"})
public class SpringBootConsoleApplication implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootConsoleApplication.class);
    private static String SAINSBURY_URL = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";

    private Scraper scraper;

    @Autowired
    public SpringBootConsoleApplication(Scraper scraper) {
        this.scraper = scraper;
    }

    @Override
    public void run(String... args) {
        try {
            scraper.scrape(SAINSBURY_URL);
            if (args.length > 0 && StringUtils.isNotEmpty(args[0])) {
                SAINSBURY_URL = args[0];
            }
            scraper.scrape(SAINSBURY_URL);

        } catch (Exception e) {
            LOGGER.error("An error has occured", e);
        }

    }
}

