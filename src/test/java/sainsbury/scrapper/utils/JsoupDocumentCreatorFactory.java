package sainsbury.scrapper.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Utility Class to help in Document creation.
 */

class JsoupDocumentCreatorFactory {

    private static final String GRIDITEM_LI_OPEN_TAG = "<li class=\"gridItem\">";
    private static final String GRIDITEM_LI_CLOSING_TAG = "</li>";
    private static final String DIV_CLOSING_TAG = "</div>";

    private JsoupDocumentCreatorFactory() {

    }

    static Document createDocument() {
        String pageHtml = "<ul class=\"productLister gridView\">" +
                GRIDITEM_LI_OPEN_TAG +
                GRIDITEM_LI_CLOSING_TAG + GRIDITEM_LI_OPEN_TAG +
                GRIDITEM_LI_CLOSING_TAG + GRIDITEM_LI_OPEN_TAG +
                GRIDITEM_LI_CLOSING_TAG + GRIDITEM_LI_OPEN_TAG +
                GRIDITEM_LI_CLOSING_TAG + GRIDITEM_LI_OPEN_TAG +
                GRIDITEM_LI_CLOSING_TAG + GRIDITEM_LI_OPEN_TAG +
                GRIDITEM_LI_CLOSING_TAG + GRIDITEM_LI_OPEN_TAG +
                GRIDITEM_LI_CLOSING_TAG + GRIDITEM_LI_OPEN_TAG +
                GRIDITEM_LI_CLOSING_TAG + GRIDITEM_LI_OPEN_TAG +
                GRIDITEM_LI_CLOSING_TAG + GRIDITEM_LI_OPEN_TAG +
                GRIDITEM_LI_CLOSING_TAG +
                "</ul>";

        return Jsoup.parse(pageHtml);
    }

    static Document createPriceDocument() {
        String priceHtml = "<div class=\"pricing\">" +
                "<p class=\"pricePerUnit\">" +
                "£1.75" +
                "<abbr title=\"per\">/</abbr>" +
                "<abbr title=\"unit\"><span class=\"pricePerUnitUnit\">unit</span></abbr>" +
                "</p>" +
                DIV_CLOSING_TAG;
        return Jsoup.parse(priceHtml);
    }

    static Document createDescriptionDocument() {
        String descriptionHtml = "<div class=\"productText\">" +
                "by Sainsbury's strawberries" +
                DIV_CLOSING_TAG;
        return Jsoup.parse(descriptionHtml);
    }

    static Document createEnergyDocument() {
        String energyHtml = "<table class=\"nutritionTable\">" +
                "<tbody>" +
                "<tr></tr>" +
                "<tr>" +
                "<td class=\"1\">" +
                "33kcal" +
                "</td>" +
                "<td class=\"2\">" +
                "2%" +
                "</td>" +
                "</tr>" +
                "</tbody>" +
                "</table>";
        return Jsoup.parse(energyHtml);
    }

    static Document createTitleDocument() {
        String titlehtml = "<div class = \"productTitleDescriptionContainer\">" +
                "<h1>Sainsbury's Blueberries 400g</h1>" +
                DIV_CLOSING_TAG;
        return Jsoup.parse(titlehtml);
    }
}
