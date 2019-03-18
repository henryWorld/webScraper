package sainsbury.scrapper.api;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import sainsbury.scrapper.model.CummulatedProductResults;
import sainsbury.scrapper.model.Product;
import sainsbury.scrapper.model.ProductGrossAndVat;
import sainsbury.scrapper.model.ProductResult;
import sainsbury.scrapper.utils.JsoupDocumentRetriever;

import java.math.BigDecimal;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class scrapes or crawls  the Sainsbury’s grocery site’s
 */
@AllArgsConstructor
@NoArgsConstructor

@Service
public class Scraper {
    private static final Logger LOGGER = LoggerFactory.getLogger(Scraper.class);
    private static final String VAT_FACTOR = "1.20";

    @Autowired
    @Qualifier("JsoupVisitorBean")
    private UrlVisitor visitor;

    /**
     * This method visits the Sainsbury website, returning a json representation of the grocery results
     *
     * @param url Sainsbury url
     * @return JsonElement
     */
    public JsonElement scrape(final String url) {

        if (StringUtils.isNotEmpty(url)) {
            Document doc = visit(url);
            List<Product> products = parseDocumentPage(doc);
            return convertToJsonFormat(products);
        }
        LOGGER.error("you have entered an empty URL");
        return new JsonObject();

    }

    private List<Product> parseDocumentPage(final Document document) {
        Elements productElements = JsoupDocumentRetriever.getProductElements(document);
        List<Product> products = new ArrayList<>();

        productElements.stream().map(JsoupDocumentRetriever::getProductSummaryUrl).map(this::visit).forEach(productDocument -> {
            String title = JsoupDocumentRetriever.getTitle(productDocument);
            Integer energy = JsoupDocumentRetriever.getEnergy(productDocument);
            BigDecimal unitPrice = JsoupDocumentRetriever.getPrice(productDocument);
            String description = JsoupDocumentRetriever.getDescription(productDocument);
            products.add(new Product(title, energy, unitPrice, description));
        });
        return products;
    }

    private JsonElement convertToJsonFormat(final List<Product> products) {
        List<ProductResult> productResults = products.stream()
                .map(product -> new ProductResult(product.getTitle(),
                        product.getEnergy(),
                        product.getPrice(),
                        product.getDescription()))
                .collect(Collectors.toList());

        CummulatedProductResults cummulatedProductResults = new CummulatedProductResults(productResults, calculateGrossAndVat(products));
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJsonTree(cummulatedProductResults);
    }

    private ProductGrossAndVat calculateGrossAndVat(final List<Product> products) {
        BigDecimal gross = products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        BigDecimal grossWhenVatIsNotAdded = gross.divide(new BigDecimal(VAT_FACTOR), 2, BigDecimal.ROUND_HALF_UP);
        BigDecimal vat = gross.subtract(grossWhenVatIsNotAdded);
        return new ProductGrossAndVat(gross, vat);
    }

    private Document visit(final String url) {
        Document document = null;
        try {
            document = visitor.visit(url);
        } catch (ConnectException e) {
            LOGGER.error("Unable to connect to the URL", e);
        }
        return document;
    }

}
