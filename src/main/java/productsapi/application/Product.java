package productsapi.application;

import java.math.BigDecimal;

public class Product {

    private String id;
    private String name;
    private BigDecimal price;
    private Status status;

    public Product(String id, String name, BigDecimal price, Status status) {
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
        this.status = Status.DISABLED;
    }

    public Boolean isValid(){
        return this.status == Status.ENABLED;
    }

    public Status getStatus() {
        return this.status;
    }
}
