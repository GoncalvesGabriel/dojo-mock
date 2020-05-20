package br.com.dojos.dojomock.repository;

import br.com.dojos.dojomock.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
