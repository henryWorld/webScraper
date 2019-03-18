package sainsbury.scraper.utils;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * This class contains a collection of methods to extract information from a Document
 */
public final class JsoupDocumentRetriever {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsoupDocumentRetriever.class);

    private JsoupDocumentRetriever() {
    }

    /**
     * This method selects all Elements from the Document
     * @param document the grocery product page's document
     * @return  Elements
     */
    public static Elements getProductElements(Document document) {
        if (document != null) {
            return document.select(".productLister .gridItem");
        }
        return new Elements();
    }

    /**
     * This method selects the absolute link references to an element's information
     * @param element the entity to extract relevant data from
     * @return  absolute url
     */
    public static String getProductSummaryUrl(Element element) {
        LOGGER.info("Getting product Summary Section Url....");
        return element.select("a")
                .first()
                .attr("abs:href");
    }

    /**
     * This method extracts product's title
     * @param document the grocery product page's document
     * @return product title
     */
    public static String getTitle(Document document) {
        LOGGER.info("Getting product title");
        return document.select(".productTitleDescriptionContainer > h1")
                .text();
    }


    /**
     * This method extracts product's price
     * @param document the grocery product page's document
     * @return products price
     */
    public static BigDecimal getPrice(Document document) {
        LOGGER.info("Getting product Price");
        String rawPrice = document.select(".pricing > .pricePerUnit")
                .first()
                .text();
        String priceWithoutExtraText = StringUtils.replaceEach(rawPrice, new String[]{"£", "/unit"}, new String[]{"", ""});
        return new BigDecimal(priceWithoutExtraText);
    }

    /**
     * This method extracts product's description
     * @param document the grocery product page's document
     * @return product's description
     */
    public static String getDescription(Document document) {
        LOGGER.info("Getting product Description...");
        return document.select(".productText")
                .first()
                .text();

    }

    /**
     * This method extracts product's energy
     * @param document the grocery product page's document
     * @return product's energy
     */
    public static Integer getEnergy(Document document) {
        LOGGER.info("Getting product energy...");
        return Optional.ofNullable(document.select(".nutritionTable")
                .first())
                .map(energyElement -> {
                    String energy = energyElement.select("tbody tr")
                            .get(1).select("td")
                            .first()
                            .text();
                    return Integer.parseInt(energy.replace("kcal", ""));
                }).orElse(null);
    }

}
