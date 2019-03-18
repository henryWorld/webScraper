package sainsbury.scrapper.api;

import org.jsoup.nodes.Document;

import java.net.ConnectException;
/**
 * This interface provides the blue print for custom parser frameworks.
 * This class decouples the interfaces from third party framework and hides the implementation
 */
public interface UrlVisitor {
    Document visit (String url) throws ConnectException;
}
