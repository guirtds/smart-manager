package com.challenge.churn.repo;

import com.challenge.churn.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepo extends JpaRepository<Transacao, Long>{
}
