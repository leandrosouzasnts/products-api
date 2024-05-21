package productsapi.application.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import productsapi.application.model.enums.Status;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductTest {

    Product productMock;

    @BeforeEach
    void setUp(){
        productMock = new Product(UUID.randomUUID(), "Product 1", new BigDecimal("10.00"), Status.DISABLED);
    }

    @Test
    public void testEnableProductWithPositivePrice() {
        productMock.enable();

        assertEquals(Status.ENABLED, productMock.getStatus());
    }

    @Test
    public void testEnableProductWithZeroPrice() {
        productMock.disable();

        assertEquals(Status.DISABLED, productMock.getStatus());
    }

    @Test
    public void testDisableProduct() {

        productMock.disable();

        assertEquals(BigDecimal.ZERO, productMock.getPrice());
        assertEquals(Status.DISABLED, productMock.getStatus());
    }

    @Test
    public void testIsValidWhenProductIsEnabled() {
        productMock.enable();

        assertTrue(productMock.isValid());
    }

    @Test
    public void testIsValidWhenProductIsDisabled() {
        assertFalse(productMock.isValid());
    }

}
