package com.springasync;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@AutoConfigureMockMvc
public class TestControllerTests {

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testResponse() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/test"))
                .andExpect(request().asyncStarted())
                .andExpect(status().isOk()).andReturn();
        com.springasync.TestController.ResponseModel response = (com.springasync.TestController.ResponseModel)mvcResult.getAsyncResult();
        assertEquals("body", response.value);
    }

    @Test
    void testNonResponse() throws Exception {
        MvcResult resultActions = mockMvc.perform(get("/api/test1")).andReturn();

        String response = (String)resultActions.getResponse().getContentAsString();
        assertEquals("{\"cancelled\":false,\"done\":true}", response);
    }
}