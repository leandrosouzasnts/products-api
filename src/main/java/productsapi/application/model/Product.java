package productsapi.application.model;

import productsapi.application.model.enums.Status;

import java.math.BigDecimal;


public class Product {

    private Long id;
    private String name;
    private BigDecimal price;
    private Status status;

    public Product(Long id, String name, BigDecimal price, Status status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.status = status;
    }

    public void enable(){
        if (this.price.compareTo(BigDecimal.ZERO) > 0) {
            this.status = Status.ENABLED;
        }
    }

    public void disable(){
        this.price = BigDecimal.ZERO;

        this.status = Status.DISABLED;
    }

    public Boolean isValid(){
        if (this.id == null || this.name.isBlank()) {
            return false;
        }
        return (this.status == Status.ENABLED) && (this.price.compareTo(BigDecimal.ZERO) > 0);
    }

    public Status getStatus() {
        return this.status;
    }

    public BigDecimal getPrice(){
        return this.price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
