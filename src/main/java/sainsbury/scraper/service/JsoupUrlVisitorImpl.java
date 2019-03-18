package sainsbury.scraper.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.ConnectException;

/**
 * This class fetches a document Instance using the Jsoup library
 */

@Service
@Qualifier("JsoupVisitorBean")
public class JsoupUrlVisitorImpl implements UrlVisitor {

    /**
     * This method fetches an instance of a Document
     * @param url the url that contains document to be fetched
     * @return the Document instance
     * @throws ConnectException if unnable to connect
     */
    public Document visit(final String url) throws ConnectException {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new ConnectException("Unable to connect to the URL");
        }
    }
}
