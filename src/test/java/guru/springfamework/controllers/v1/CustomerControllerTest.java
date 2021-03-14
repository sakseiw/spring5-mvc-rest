package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest extends AbstractRestControllerTest{

    public static final Long ID = 1L;
    public static final String FIRST_NAME = "Jim";
    public static final String LAST_NAME = "Parson";

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();

    }

    @Test
    public void testListCustomers() throws Exception {
        CustomerDTO customer1 = new CustomerDTO();
        customer1.setId(1l);
        customer1.setFirstname(FIRST_NAME);
        customer1.setLastname(LAST_NAME);

        CustomerDTO customer2 = new CustomerDTO();
        customer2.setId(2l);
        customer2.setFirstname("Bob");
        customer2.setLastname("Sponge");

        List<CustomerDTO> customers = Arrays.asList(customer1, customer2);

        when(customerService.getAllCustomers()).thenReturn(customers);

        mockMvc.perform(get("/api/v1/customers/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));
    }

    @Test
    public void testGetById() throws Exception {
        CustomerDTO customer1 = new CustomerDTO();
        customer1.setId(ID);
        customer1.setFirstname(FIRST_NAME);
        customer1.setLastname(LAST_NAME);

        when(customerService.getCustomerById(anyLong())).thenReturn(customer1);

        mockMvc.perform(get("/api/v1/customers/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo(FIRST_NAME)))
                .andExpect(jsonPath("$.lastname", equalTo(LAST_NAME)));
    }

    @Test
    public void insert() throws Exception {
        //given
        CustomerDTO customer1 = new CustomerDTO();
        customer1.setFirstname(FIRST_NAME);
        customer1.setLastname(LAST_NAME);

        CustomerDTO returnDTO = new CustomerDTO();
        returnDTO.setId(ID);
        returnDTO.setFirstname(FIRST_NAME);
        returnDTO.setLastname(LAST_NAME);
        returnDTO.setCustomerUrl("/api/v1/customers/"+ID);

        //when
        when(customerService.insert(any(CustomerDTO.class))).thenReturn(returnDTO);

        //Then
        mockMvc.perform(post("/api/v1/customers/")
                  .contentType(MediaType.APPLICATION_JSON)
                  .content(asJsonString(customer1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstname", equalTo(FIRST_NAME)))
                .andExpect(jsonPath("$.customer_url", equalTo("/api/v1/customers/"+ID)));
    }

    @Test
    public void update() throws Exception {
        //given
        CustomerDTO customer1 = new CustomerDTO();
        customer1.setFirstname(FIRST_NAME + "tj");
        customer1.setLastname(LAST_NAME);

        CustomerDTO returnDTO = new CustomerDTO();
        returnDTO.setId(ID);
        returnDTO.setFirstname(FIRST_NAME + "Tj");
        returnDTO.setLastname(LAST_NAME);
        returnDTO.setCustomerUrl("/api/v1/customers/"+ID);

        //when
        when(customerService.update(anyLong(), any(CustomerDTO.class))).thenReturn(returnDTO);

        //Then
        mockMvc.perform(put("/api/v1/customers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo(FIRST_NAME+"Tj")))
                .andExpect(jsonPath("$.customer_url", equalTo("/api/v1/customers/"+ID)));
    }
}