package com.challenge.churn.repo;

import com.challenge.churn.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepo extends JpaRepository<Cliente, Long>{
}
