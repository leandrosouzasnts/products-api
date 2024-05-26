package productsapi.domain.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import productsapi.domain.model.Product;
import productsapi.domain.model.request.UpdateStatusEnabledProductTO;
import productsapi.domain.persistence.ReadProductPersistenceAdapter;
import productsapi.domain.persistence.WriteProductPersistenceAdapter;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static productsapi.domain.model.enums.Status.DISABLED;
import static productsapi.domain.model.enums.Status.ENABLED;

@SpringBootTest
class ProductServiceTest {

    @InjectMocks
    ProductService productServiceMock;

    @Mock
    WriteProductPersistenceAdapter writeProductPersistenceAdapter;

    @Mock
    ReadProductPersistenceAdapter readProductPersistenceAdapter;

    Product productMock;

    @BeforeEach
    void setUp() throws Exception {
        productMock = new Product("1", "Product 1", BigDecimal.valueOf(100), ENABLED);
    }

    @Test
    void givenValidProduct_whenGetByUUID_thenReturnsSuccessfully() {
        when(readProductPersistenceAdapter.get(anyString())).thenReturn(productMock);

        Product productTest = productServiceMock.get("1");

        assertEquals(productTest.getId(), productMock.getId());
    }

    @Test
    void givenValidProduct_whenCreated_thenReturnsSuccessfully() throws Exception {
        when(writeProductPersistenceAdapter.save(any())).thenReturn(productMock);

        Product productTest = productServiceMock.create("Product 1", BigDecimal.valueOf(200));

        assertEquals(productTest.getId(), productMock.getId());
    }

    @Test
    void givenValidProduct_whenEnabled_thenReturnsSuccessfully() throws Exception {
        //Setup
        when(writeProductPersistenceAdapter.save(any())).thenReturn(productMock);
        when(productServiceMock.get("1")).thenReturn(productMock);

        UpdateStatusEnabledProductTO to = UpdateStatusEnabledProductTO
                .builder()
                    .price(BigDecimal.valueOf(100.0))
                .build();
        //Act
        Product productTest = productServiceMock.enable("1", to);

        //Verify
        assertNotNull(productTest);
        assertEquals(ENABLED, productTest.getStatus());
    }

    @Test
    void givenValidProduct_whenDisabled_thenReturnsSuccessfully() throws Exception {
        //Arrange
        when(writeProductPersistenceAdapter.save(any())).thenReturn(productMock);
        when(productServiceMock.get("1")).thenReturn(productMock);

        //Act
        Product productTest = productServiceMock.disable("1");

        //Assert
        assertNotNull(productTest);
        assertEquals(DISABLED, productTest.getStatus());
    }
}
