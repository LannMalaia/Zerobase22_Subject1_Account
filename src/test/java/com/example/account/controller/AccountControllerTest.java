package com.example.account.controller;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.account.domain.Account;
import com.example.account.dto.AccountDto;
import com.example.account.dto.CreateAccount;
import com.example.account.dto.DeleteAccount;
import com.example.account.service.AccountService;
import com.example.account.service.RedisTestService;
import com.example.account.type.AccountStatus;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(AccountController.class)
class AccountControllerTest {
    @MockBean
    private AccountService accountService;

    @MockBean
    private RedisTestService redisTestService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    void successCreateAccount() throws Exception {
    	// given
    	given(accountService.createAccount(anyLong(), anyLong()))
    		.willReturn(AccountDto.builder()
    			.userId(1L)
    			.accountNumber("123456789")
    			.registeredAt(LocalDateTime.now())
    			.unRegisteredAt(LocalDateTime.now())
    			.build());
    	// when
    	
    	// then
    	mockMvc.perform(
    		post("/account")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(objectMapper.writeValueAsString(
    					new CreateAccount.Request(1L, 100L)
    			)
    		)
    	).andExpect(status().isOk())
    	.andExpect(jsonPath("$.userId").value(1))
    	.andExpect(jsonPath("$.accountNumber").value("123456789"))
    	.andDo(print());
    }
    
    @Test
    void successGetAccount() throws Exception {
        //given
        given(accountService.getAccount(anyLong()))
                .willReturn(Account.builder()
                        .accountNumber("3456")
                        .accountStatus(AccountStatus.IN_USE)
                        .build());

        //when
        //then
        mockMvc.perform(get("/account"))
                .andDo(print())
                .andExpect(jsonPath("$.accountNumber").value("3456"))
                .andExpect(jsonPath("$.accountStatus").value("IN_USE"))
                .andExpect(status().isOk());
    }
    

    @Test
    void successDeleteAccount() throws Exception {
    	// given
    	given(accountService.deleteAccount(anyLong(), anyString()))
    		.willReturn(AccountDto.builder()
    			.userId(1L)
    			.accountNumber("1234567890")
    			.registeredAt(LocalDateTime.now())
    			.unRegisteredAt(LocalDateTime.now())
    			.build());
    	// when
    	
    	// then
        mockMvc.perform(delete("/account")
        			.contentType(MediaType.APPLICATION_JSON)
        			.content(objectMapper.writeValueAsString(
        				new DeleteAccount.Request(3333L, "8987654321")
        			)))
       			.andExpect(status().isOk())
       			.andExpect(jsonPath("$.userId").value(1))
       			.andExpect(jsonPath("$.accountNumber").value("1234567890"))
       			.andDo(print());
    }
    
    @Test
    void successGetAccountsByUserId() throws Exception{
    	// given
    	List<AccountDto> accountDtos = Arrays.asList(
    			AccountDto.builder()
    			.accountNumber("1234567890")
    			.balance(1000L).build(),
    			AccountDto.builder()
    			.accountNumber("1234123412")
    			.balance(2000L).build(),
    			AccountDto.builder()
    			.accountNumber("4321432143")
    			.balance(3000L).build()
    			);
    	given(accountService.getAccountByUserId(anyLong()))
    		.willReturn(accountDtos);
    	// when
    	
    	// then
        mockMvc.perform(get("/account?user_id=1"))
       			.andDo(print())
       			.andExpect(jsonPath("$[0].accountNumber").value("1234567890"))
       			.andExpect(jsonPath("$[0].balance").value(1000))
				.andExpect(jsonPath("$[0].accountNumber").value("1234123412"))
				.andExpect(jsonPath("$[0].balance").value(2000))
	   			.andExpect(jsonPath("$[0].accountNumber").value("4321432143"))
	   			.andExpect(jsonPath("$[0].balance").value(2000));
    }
}