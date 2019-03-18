package sainsbury.scrapper.utils;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Optional;

public final class JsoupDocumentRetriever {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsoupDocumentRetriever.class);

    private JsoupDocumentRetriever() {
    }

    public static Elements getProductElements(Document document) {
        if (document != null) {
            return document.select(".productLister .gridItem");
        }
        return new Elements();
    }

    public static String getProductSummaryUrl(Element element) {
        LOGGER.info("Getting product Summary Section Url....");
        return element.select("a")
                .first()
                .attr("abs:href");
    }

    public static String getTitle(Document document) {
        LOGGER.info("Getting product title");
        return document.select(".productTitleDescriptionContainer > h1")
                .text();
    }


    public static BigDecimal getPrice(Document document) {
        LOGGER.info("Getting product Price");
        String rawPrice = document.select(".pricing > .pricePerUnit")
                .first()
                .text();
        String cleanedPrice = StringUtils.replaceEach(rawPrice, new String[]{"£", "/unit"}, new String[]{"", ""});
        return new BigDecimal(cleanedPrice);
    }


    public static String getDescription(Document document) {
        LOGGER.info("Getting product Description...");
        return document.select(".productText")
                .first()
                .text();

    }


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
