package sainsbury.scraper.service;

import org.jsoup.nodes.Document;

import java.net.ConnectException;
/**
 * The UrlVisitor interface provides the contract for fetching a Document instance from a webpage
 */
public interface UrlVisitor {
    Document visit (String url) throws ConnectException;
}
