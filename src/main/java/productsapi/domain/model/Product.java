package productsapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import productsapi.domain.model.enums.Status;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private String id;
    private String name;
    private BigDecimal price;
    private Status status;

    public void enable() {
        if (this.price.compareTo(BigDecimal.ZERO) > 0) {
            this.status = Status.ENABLED;
        }
    }

    public void disable() {
        this.price = BigDecimal.ZERO;

        this.status = Status.DISABLED;
    }

    public Boolean isValid() {
        if (this.id == null || this.name.isBlank()) {
            return false;
        }
        return (this.status == Status.ENABLED) && (this.price.compareTo(BigDecimal.ZERO) > 0);
    }
}
