package productsapi.domain.model.request;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductRequest(String name, BigDecimal price) {
}
