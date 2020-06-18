package br.com.dojos.dojomock.controller;

import br.com.dojos.dojomock.dto.transaction.CreateTransactionDTO;
import br.com.dojos.dojomock.dto.transaction.TransactionDTO;
import br.com.dojos.dojomock.services.transaction.TransactionService;
import java.net.URI;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private TransactionService transactionService;

    @Autowired
    TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<TransactionDTO> createAccount(@RequestBody CreateTransactionDTO createTransactionDTO) {
        TransactionDTO transaction = transactionService.createTransaction(createTransactionDTO);
        URI uri = getUri(transaction);
        return ResponseEntity.created(uri).body(transaction);
    }

    private URI getUri(TransactionDTO transaction) {
        return URI.create(String.format("/transactions/%s", transaction.getId()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionDTO> fullUpdate(@PathParam("id") Long id, @RequestBody CreateTransactionDTO createTransactionDTO) {
        TransactionDTO transactionDTO = transactionService.update(id, createTransactionDTO);
        return ResponseEntity.created(getUri(transactionDTO)).body(transactionDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TransactionDTO> update(@PathParam("id") Long id, @RequestBody CreateTransactionDTO createTransactionDTO) {
        TransactionDTO transactionDTO = transactionService.update(id, createTransactionDTO);
        return ResponseEntity.ok(transactionDTO);
    }
}
