package productsapi.application.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import productsapi.application.model.Product;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductServiceTest {

    @InjectMocks
    ProductServiceImpl productServiceMock ;

    @Mock
    ProductPersistence productPersistenceMock;

    Product productMock;

    @BeforeEach
    void setUp() throws Exception {
        productMock = new Product("Product 1", BigDecimal.valueOf(200));
    }

    @Test
    void givenValidUUID_whenGetByUUID_thenReturnsSuccessfully() {
        when(productServiceMock.get(any())).thenReturn(productMock);

        Product productTest = productServiceMock.get(UUID.randomUUID());

        assertEquals(productTest.getId(), productMock.getId());
    }
}
