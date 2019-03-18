package sainsbury.scrapper.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor

/**
 * This class is a json representation of the product results
 */
public class ProductResult {

    @JsonProperty("title")
    private String title;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("kcal_per_100g")
    private Integer energy;

    @SerializedName("unit_price")
    private BigDecimal price;

    @SerializedName("description")
    private String description;

}
