package com.challenge.churn.controller;

import com.challenge.churn.model.ChurnPrediction;
import com.challenge.churn.model.Cliente;
import com.challenge.churn.repo.ChurnPredictionRepo;
import com.challenge.churn.repo.ClienteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ChurnPredictionController {

    @Autowired
    private ChurnPredictionRepo churnPredictionRepo;

    @Autowired
    private ClienteRepo clienteRepo;

    @GetMapping(value = "/churn-predictions")
    public String listChurnPredictions(Model model) {
        List<ChurnPrediction> churnPredictions = churnPredictionRepo.findAll();
        model.addAttribute("churnPredictions", churnPredictions);
        return "churn-predictions";
    }

    @GetMapping(value = "/churn-prediction/{id}")
    public String getChurnPrediction(@PathVariable long id, Model model) {
        ChurnPrediction churnPrediction = churnPredictionRepo.findById(id).orElse(null);
        if (churnPrediction != null) {
            model.addAttribute("churnPrediction", churnPrediction);
            return "churn-prediction-detail";
        } else {
            return "404";
        }
    }

    @GetMapping(value = "/churn-prediction/create")
    public String createChurnPredictionForm(Model model) {
        model.addAttribute("churnPrediction", new ChurnPrediction());
        List<Cliente> clientes = clienteRepo.findAll(); // Obtém a lista de clientes
        model.addAttribute("clientes", clientes);
        return "churn-prediction-form";
    }

    @PostMapping(value = "/churn-prediction/create")
    public String saveChurnPrediction(@ModelAttribute ChurnPrediction churnPrediction) {
        churnPredictionRepo.save(churnPrediction);
        return "redirect:/churn-predictions";
    }

    @GetMapping(value = "/churn-prediction/edit/{id}")
    public String editChurnPredictionForm(@PathVariable long id, Model model) {
        ChurnPrediction churnPrediction = churnPredictionRepo.findById(id).orElse(null);
        if (churnPrediction != null) {
            model.addAttribute("churnPrediction", churnPrediction);
            List<Cliente> clientes = clienteRepo.findAll(); // Obtém a lista de clientes
            model.addAttribute("clientes", clientes);
            return "churn-prediction-form";
        } else {
            return "404";
        }
    }

    @PostMapping(value = "/churn-prediction/update/{id}")
    public String updateChurnPrediction(@PathVariable long id, @ModelAttribute ChurnPrediction churnPrediction) {
        ChurnPrediction existingChurnPrediction = churnPredictionRepo.findById(id).orElse(null);
        if (existingChurnPrediction != null) {
            existingChurnPrediction.setCliente(churnPrediction.getCliente());
            existingChurnPrediction.setProbabilidadeChurn(churnPrediction.getProbabilidadeChurn());
            existingChurnPrediction.setDataPredicao(churnPrediction.getDataPredicao());
            churnPredictionRepo.save(existingChurnPrediction);
            return "redirect:/churn-predictions";
        } else {
            return "404";
        }
    }

    @GetMapping(value = "/churn-prediction/delete/{id}")
    public String deleteChurnPrediction(@PathVariable long id) {
        ChurnPrediction churnPrediction = churnPredictionRepo.findById(id).orElse(null);
        if (churnPrediction != null) {
            churnPredictionRepo.delete(churnPrediction);
        }
        return "redirect:/churn-predictions";
    }
}
