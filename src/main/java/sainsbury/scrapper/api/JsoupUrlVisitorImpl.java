package sainsbury.scrapper.api;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.ConnectException;

@Service
@Qualifier("JsoupVisitorBean")
public class JsoupUrlVisitorImpl implements UrlVisitor {

    public Document visit(final String  url) throws ConnectException {
        try {
            return Jsoup.connect(url).get();
        }  catch (IOException e) {
            throw new ConnectException("Unable to connect to the URL");
        }
    }
}
