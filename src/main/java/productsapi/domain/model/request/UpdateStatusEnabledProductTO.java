package productsapi.domain.model.request;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record UpdateStatusEnabledProductTO(BigDecimal price) {
}
