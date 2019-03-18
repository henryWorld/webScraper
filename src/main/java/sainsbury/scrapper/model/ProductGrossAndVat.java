package sainsbury.scrapper.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor

/**
 * This class is an aggregator of the total of all items on the page and
 * the vat on the total
 * each item has a 20% VAT
 */
public class ProductGrossAndVat {
    @SerializedName("gross")
    private BigDecimal gross;

    @SerializedName("vat")
    private BigDecimal vat;

}

