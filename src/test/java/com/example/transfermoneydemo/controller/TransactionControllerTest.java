package com.example.transfermoneydemo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.transfermoneydemo.model.dto.TransactionDto;
import com.example.transfermoneydemo.service.TransactionService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)
class TransactionControllerTest {

  @MockBean
  private TransactionService transactionService;

  @Autowired
  private MockMvc mockMvc;

  @Test
  void createTransaction_test_bad_request() throws Exception {
    mockMvc.perform(post("/transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{}"))
        .andExpect(status().isBadRequest());
  }

  @Test
  void createTransaction_test() throws Exception {
    when(transactionService.createTransaction(any())).thenReturn(new TransactionDto());
    mockMvc.perform(post("/transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"amount\":10,\"sourceAccountId\":1,\"targetAccountId\":2,\"currency\":\"EUR\"}"))
        .andExpect(status().isAccepted());
  }

  @Test
  void fetchAll_test() throws Exception {
    mockMvc.perform(get("/transactions"))
        .andExpect(status().isOk());
  }

  @Test
  void fetchAll_test_not_found() throws Exception {
    mockMvc.perform(get("/transactionss"))
        .andExpect(status().isNotFound());
  }
}