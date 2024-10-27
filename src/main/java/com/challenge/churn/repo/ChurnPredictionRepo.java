package com.challenge.churn.repo;

import com.challenge.churn.model.ChurnPrediction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChurnPredictionRepo extends JpaRepository<ChurnPrediction, Long>{
}
