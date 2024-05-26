package productsapi.api.rest.adapters.endpoints;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import productsapi.domain.model.Product;
import productsapi.domain.model.enums.Status;
import productsapi.domain.service.ProductServiceUseCase;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ProductServiceUseCase productServiceUseCase;

    @Autowired
    private ObjectMapper objectMapper;

    Product product;

    @BeforeEach
    void setUp() {
        product = new Product("1", "Product Test", BigDecimal.valueOf(100), Status.ENABLED.ENABLED);
    }

    @Test
    void givenValidProduct_whenCreateProduct_thenReturnsSuccessfully() throws Exception {
        //when
        Mockito.when(productServiceUseCase.create(anyString(), any())).thenReturn(product);
        String jsonCreated = "{\"name\":\"Product Test\",\"price\":100}";

        //act //verify
        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonCreated))
                .andExpect(status().isCreated());

    }

    @Test
    void givenValidProduct_whenFindById_thenReturnsSuccessfully() throws Exception {
        Mockito.when(productServiceUseCase.get(anyString())).thenReturn(product);
        Mockito.when(productServiceUseCase.create(anyString(), any())).thenReturn(product);

        String jsonCreated = "{\"name\":\"Product Test\",\"price\":100}";

        MvcResult result = mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonCreated))
                .andExpect(status().isCreated())
                .andReturn();

        // Extrai o conteúdo da resposta
        String contentAsString = result.getResponse().getContentAsString();

        // Converte a resposta em um objeto Product
        Product createdProduct = objectMapper.readValue(contentAsString, Product.class);

        // Usa o ID do produto criado para a requisição GET
        mockMvc.perform(get("/products/{id}", createdProduct.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(createdProduct)));
    }

    @Test
    void givenValidProduct_whenDisabled_thenReturnsSuccessfully() throws Exception {
        Mockito.when(productServiceUseCase.get(anyString())).thenReturn(product);
        Mockito.when(productServiceUseCase.create(anyString(), any())).thenReturn(product);

        String jsonCreated = "{\"name\":\"Product Test\",\"price\":100}";

        MvcResult result = mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonCreated))
                .andExpect(status().isCreated())
                .andReturn();

        String contentAsString = result.getResponse().getContentAsString();

        Product createdProduct = objectMapper.readValue(contentAsString, Product.class);
        createdProduct.disable();

        mockMvc.perform(patch("/products/{productId}/disabled", createdProduct.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(createdProduct)));
    }

    @Test
    void givenValidProduct_whenEnabled_thenReturnsSuccessfully() throws Exception {
        Mockito.when(productServiceUseCase.get(anyString())).thenReturn(product);
        Mockito.when(productServiceUseCase.create(anyString(), any())).thenReturn(product);

        String jsonCreated = "{\"name\":\"Product Test\",\"price\":100}";
        String jsonEnabled = "{\"price\":100}";

        MvcResult result = mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonCreated))
                .andExpect(status().isCreated())
                .andReturn();

        String contentAsString = result.getResponse().getContentAsString();

        Product createdProduct = objectMapper.readValue(contentAsString, Product.class);

        mockMvc.perform(patch("/products/{productId}/enabled", createdProduct.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonEnabled))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(createdProduct)));
    }
}
