package sainsbury.scrapper.utils;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class JsoupDocumentRetrieverTest {

    @Test
    public void whenDocumentIsNullThenReturnEmptyElements() {
        int expected = 0;
        assertEquals(expected, JsoupDocumentRetriever.getProductElements(null).size());
    }


    @Test
    public void whenDocumentSelectorDoesNotExistReturnEmptyElements() {
        int expected = 0;
        Document document = JsoupDocumentCreatorFactory.createDocument();
        assertEquals(expected, document.select(".selectorDoesnotExist").size());
    }


    @Test
    public void whenDocumentIsNotNullThenReturnElements() {
        int expected = 0;
        Elements elements = JsoupDocumentRetriever.getProductElements(JsoupDocumentCreatorFactory.createDocument());
        assertNotEquals(expected, elements.size());
    }


    @Test
    public void whenDocumentIsGivenThenExtractPrice() {
        Document document = JsoupDocumentCreatorFactory.createPriceDocument();
        BigDecimal expected = BigDecimal.valueOf(1.75);
        BigDecimal actual = JsoupDocumentRetriever.getPrice(document);
        assertEquals(expected, actual);
    }

    @Test
    public void whenDocumentIsGivenThenExtractDescription() {
        Document document = JsoupDocumentCreatorFactory.createDescriptionDocument();
        String expected = "by Sainsbury's strawberries";
        String actual = JsoupDocumentRetriever.getDescription(document);
        assertEquals(expected, actual);
    }

    @Test
    public void whenDocumentIsGivenThenExtractEnergy() {
        Document document = JsoupDocumentCreatorFactory.createEnergyDocument();
        Integer expected = 33;
        Integer actual = JsoupDocumentRetriever.getEnergy(document);
        assertEquals(expected, actual);
    }

    @Test
    public void whenDocumentIsGivenThenExtractTitle() {
        Document document = JsoupDocumentCreatorFactory.createTitleDocument();
        String expected = "Sainsbury's Blueberries 400g";
        String actual = JsoupDocumentRetriever.getTitle(document);
        assertEquals(expected, actual);

    }

}


