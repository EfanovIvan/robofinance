package com.example.robofinance;

import com.example.robofinance.controllers.CustomerController;
import com.example.robofinance.domain.Customer;
import com.example.robofinance.services.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createCustomer_ShouldBeStatus200() throws Exception{
        Customer customer = Customer.builder()
                .id(1L)
                .firstName("Test")
                .lastName("Test")
                .build();

        Mockito.when(customerService.save(Mockito.any())).thenReturn(0L);

        mockMvc.perform(post("/customer")
                .content(objectMapper.writeValueAsString(customer))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void findExistingCustomer_ShouldReturnCustomer() throws Exception{

        Customer customer = Customer.builder()
                .id(1L)
                .firstName("Test")
                .lastName("Test")
                .build();

        Mockito.when(customerService.find(Mockito.any())).thenReturn(customer);

        mockMvc.perform(get("/customer/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.firstName").value("Test"))
                .andExpect(jsonPath("$.lastName").value("Test"));
    }

    @Test
    public void findCustomerByFirstNameAndLastName_shouldReturnCustomer() throws Exception{

        Customer customer = Customer.builder()
                .id(1L)
                .firstName("Test")
                .lastName("Test")
                .build();

        Mockito.when(customerService.findByFirstNameAndLastName(Mockito.anyString(), Mockito.anyString())).thenReturn(customer);

        mockMvc.perform(get("/customer/?firstName=Test&lastName=Test"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.firstName").value("Test"))
                .andExpect(jsonPath("$.lastName").value("Test"));
    }
}

