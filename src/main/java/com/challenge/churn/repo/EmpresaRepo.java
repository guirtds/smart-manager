package com.challenge.churn.repo;

import com.challenge.churn.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepo extends JpaRepository<Empresa, Long>{
}
