package sainsbury.scraper.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class CummulatedProductResults {
    @SerializedName("results")
    private List<ProductResult> products;
    @SerializedName("productGrossAndVat")
    private ProductGrossAndVat productGrossAndVat;

}

