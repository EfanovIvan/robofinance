package com.example.robofinance;

import com.example.robofinance.controllers.AddressController;
import com.example.robofinance.domain.Address;
import com.example.robofinance.services.AddressService;
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

@WebMvcTest(controllers = AddressController.class)
public class AddressControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressService addressService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createAddress_ShouldBeStatus200() throws Exception{
        Address address = Address.builder()
                .id(1L)
                .city("Test")
                .flat("32")
                .build();

        Mockito.when(addressService.save(Mockito.any())).thenReturn(0L);

        mockMvc.perform(post("/address")
                .content(objectMapper.writeValueAsString(address))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void findExistingAddress_ShouldReturnAddress() throws Exception{

        Address address = Address.builder()
                .id(1L)
                .city("Test")
                .flat("32")
                .build();


        Mockito.when(addressService.find(Mockito.any())).thenReturn(address);

        mockMvc.perform(get("/address/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.city").value("Test"))
                .andExpect(jsonPath("$.flat").value("32"));
    }
}

