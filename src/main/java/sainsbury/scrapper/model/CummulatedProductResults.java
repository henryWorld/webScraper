package sainsbury.scrapper.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class CummulatedProductResults {
    @SerializedName("results")
    private List<ProductResult> products;
    @SerializedName("productGrossAndVat")
    private ProductGrossAndVat productGrossAndVat;

}

