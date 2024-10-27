package com.challenge.churn.controller;

import com.challenge.churn.model.Transacao;
import com.challenge.churn.repo.TransacaoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TransacaoController {

    @Autowired
    private TransacaoRepo transacaoRepo;

    @GetMapping(value = "/transacoes")
    public String listTransacoes(Model model) {
        List<Transacao> transacoes = transacaoRepo.findAll();
        model.addAttribute("transacoes", transacoes);
        return "transacoes";
    }

    @GetMapping(value = "/transacao/{id}")
    public String getTransacao(@PathVariable long id, Model model) {
        Transacao transacao = transacaoRepo.findById(id).orElse(null);
        if (transacao != null) {
            model.addAttribute("transacao", transacao);
            return "transacao-detail";
        } else {
            return "404";
        }
    }

    @GetMapping(value = "/transacao/create")
    public String createTransacaoForm(Model model) {
        model.addAttribute("transacao", new Transacao());
        return "transacao-form";
    }

    @PostMapping(value = "/transacao/create")
    public String saveTransacao(@ModelAttribute Transacao transacao) {
        transacaoRepo.save(transacao);
        return "redirect:/transacoes";
    }

    @GetMapping(value = "/transacao/edit/{id}")
    public String editTransacaoForm(@PathVariable long id, Model model) {
        Transacao transacao = transacaoRepo.findById(id).orElse(null);
        if (transacao != null) {
            model.addAttribute("transacao", transacao);
            return "transacao-form";
        } else {
            return "404";
        }
    }

    @PostMapping(value = "/transacao/update/{id}")
    public String updateTransacao(@PathVariable long id, @ModelAttribute Transacao transacao) {
        Transacao existingTransacao = transacaoRepo.findById(id).orElse(null);
        if (existingTransacao != null) {
            existingTransacao.setCliente(transacao.getCliente());
            existingTransacao.setValor(transacao.getValor());
            existingTransacao.setData(transacao.getData());
            transacaoRepo.save(existingTransacao);
            return "redirect:/transacoes";
        } else {
            return "404";
        }
    }

    @GetMapping(value = "/transacao/delete/{id}")
    public String deleteTransacao(@PathVariable long id) {
        Transacao transacao = transacaoRepo.findById(id).orElse(null);
        if (transacao != null) {
            transacaoRepo.delete(transacao);
        }
        return "redirect:/transacoes";
    }
}
