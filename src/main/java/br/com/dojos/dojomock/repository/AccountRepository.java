package br.com.dojos.dojomock.repository;


import br.com.dojos.dojomock.entity.Account;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByDocumentNumber(String documentNumber);

}
