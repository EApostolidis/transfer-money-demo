package com.example.transfermoneydemo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.transfermoneydemo.model.entity.AccountEntity;
import com.example.transfermoneydemo.service.AccountService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
class AccountControllerTest {

  @MockBean
  private AccountService accountService;

  @Autowired
  private MockMvc mockMvc;

  @Test
  void createAccount_test_bad_request() throws Exception {
    mockMvc.perform(post("/accounts")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{}"))
        .andExpect(status().isBadRequest());
  }

  @Test
  void createAccount_test() throws Exception {

    when(accountService.createAccount(any())).thenReturn(new AccountEntity());

    mockMvc.perform(post("/accounts")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"balance\":1000.20,\"currency\":\"EUR\"}"))
        .andExpect(status().isAccepted());
  }

  @Test
  void fetchAll_test() throws Exception {
    mockMvc.perform(get("/accounts"))
        .andExpect(status().isOk());
  }

  @Test
  void fetchAll_test_not_found() throws Exception {
    mockMvc.perform(get("/accountss"))
        .andExpect(status().isNotFound());
  }
}