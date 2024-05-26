package productsapi.domain.persistence.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import productsapi.domain.model.enums.Status;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_product")
@Data
public class ProductEntity {

    @Id
    String id;
    String name;
    BigDecimal price;
    Status status;
}
