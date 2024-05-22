package productsapi.infra.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import productsapi.application.model.enums.Status;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_product")
public class ProductEntity {

    @Id
    Long id;
    String name;
    BigDecimal price;
    Status status;
}
