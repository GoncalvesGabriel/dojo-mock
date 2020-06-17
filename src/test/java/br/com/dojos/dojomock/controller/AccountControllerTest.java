package br.com.dojos.dojomock.controller;

import br.com.dojos.dojomock.JsonTestHelper;
import br.com.dojos.dojomock.dto.account.AccountDTO;
import br.com.dojos.dojomock.dto.account.CreateAccountDTO;
import br.com.dojos.dojomock.services.account.AccountService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

@RunWith(MockitoJUnitRunner.class)
public class AccountControllerTest {

    @Mock private AccountService service;

    @InjectMocks
    AccountController accountController;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        StandaloneMockMvcBuilder standalone = MockMvcBuilders.standaloneSetup(accountController);
        mockMvc = standalone.build();
    }

    @Test
    public void createTest() throws Exception {
        CreateAccountDTO createAccountDTO = CreateAccountDTO.builder().documentNumber("LTN137").build();
        AccountDTO accountDTO = AccountDTO.builder().documentNumber("LTN137").id(1L).build();
        Mockito.when(service.createAccount(createAccountDTO)).thenReturn(accountDTO);

        mockMvc.perform(MockMvcRequestBuilders
            .post("/accounts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(JsonTestHelper.asJsonString(createAccountDTO)))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("$.documentNumber").value(accountDTO.getDocumentNumber()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(accountDTO.getId()));

    }

}