package sainsbury.scrapper;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import sainsbury.scrapper.service.SpringBootConsoleApplication;

public class Main {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringBootConsoleApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }
}
