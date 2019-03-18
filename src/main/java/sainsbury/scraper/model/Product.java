package sainsbury.scraper.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

/**
 * This class is a representation of a single grocery product
 */
public class Product {

    private String title;
    private Integer energy;
    private BigDecimal price;
    private String description;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

