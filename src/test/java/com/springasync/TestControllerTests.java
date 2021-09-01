package com.springasync;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
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
    void testReponse() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/test")).andExpect(status().isOk()).andReturn();
        com.springasync.TestController.ResponseModel response = (com.springasync.TestController.ResponseModel)mvcResult.getAsyncResult();
        assertEquals("body", response.value);
    }

    @Test
    void testNonReponse() throws Exception {
        MvcResult resultActions = mockMvc.perform(get("/api/test1"))
            .andExpect(request().asyncStarted())
            .andReturn();

        MvcResult mvcResult = mockMvc.perform(asyncDispatch(resultActions))
                                    .andExpect(status().is2xxSuccessful()).andReturn();
        String response = mvcResult.getResponse().getContentAsString();

        assertEquals("value", response);
    }
}