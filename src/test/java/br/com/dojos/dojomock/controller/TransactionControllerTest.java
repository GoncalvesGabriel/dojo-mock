package br.com.dojos.dojomock.controller;


import br.com.dojos.dojomock.JsonTestHelper;
import br.com.dojos.dojomock.dto.transaction.CreateTransactionDTO;
import br.com.dojos.dojomock.dto.transaction.TransactionDTO;
import br.com.dojos.dojomock.entity.enux.OperationType;
import br.com.dojos.dojomock.services.transaction.TransactionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

@RunWith(MockitoJUnitRunner.class)
public class TransactionControllerTest {

    @Mock
    private TransactionService service;

    @InjectMocks
    private TransactionController controller;

    private MockMvc mockMvc;

    private TransactionDTO transactionDTO;

    @Before
    public void setup(){
        StandaloneMockMvcBuilder standalone = MockMvcBuilders.standaloneSetup(controller);
        mockMvc = standalone.build();
        transactionDTO = transactionDTO.builder().amount(100L).operationType(OperationType.COMPRA_A_VISTA).build();
    }

    @Test
    public void createTransaction() throws Exception {
        CreateTransactionDTO createTransactionDTO = CreateTransactionDTO.builder().amount(100L).operationType(1).build();
        Mockito.when(service.createTransaction(createTransactionDTO)).thenReturn(transactionDTO);

        mockMvc.perform(MockMvcRequestBuilders
            .post("/transactions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(JsonTestHelper.asJsonString(createTransactionDTO)))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("$.amount").value(transactionDTO.getAmount()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operationType").value(OperationType.COMPRA_A_VISTA.toString()));
    }

    @Test
    public void fullUpdate() throws Exception {
        CreateTransactionDTO createTransactionDTO = CreateTransactionDTO.builder().amount(100L).operationType(1).build();
        Mockito.when(service.update(transactionDTO.getId(), createTransactionDTO)).thenReturn(transactionDTO);

        mockMvc.perform(MockMvcRequestBuilders
            .put("/transactions/" + transactionDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .content(JsonTestHelper.asJsonString(createTransactionDTO)))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("$.amount").value(transactionDTO.getAmount()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operationType").value(OperationType.COMPRA_A_VISTA.toString()));
    }

    @Test
    public void update() throws Exception {
        CreateTransactionDTO createTransactionDTO = CreateTransactionDTO.builder().amount(100L).operationType(1).build();
        Mockito.when(service.update(transactionDTO.getId(), createTransactionDTO)).thenReturn(transactionDTO);

        mockMvc.perform(MockMvcRequestBuilders
            .patch("/transactions/" + transactionDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .content(JsonTestHelper.asJsonString(createTransactionDTO)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.amount").value(transactionDTO.getAmount()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operationType").value(OperationType.COMPRA_A_VISTA.toString()));
    }

}