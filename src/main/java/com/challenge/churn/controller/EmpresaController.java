package com.challenge.churn.controller;

import com.challenge.churn.model.Empresa;
import com.challenge.churn.repo.EmpresaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmpresaController {

    @Autowired
    private EmpresaRepo empresaRepo;

    @GetMapping(value = "/empresas")
    public String listEmpresas(Model model) {
        List<Empresa> empresas = empresaRepo.findAll();
        model.addAttribute("empresas", empresas);
        return "empresas";
    }

    @GetMapping(value = "/empresa/{id}")
    public String getEmpresa(@PathVariable long id, Model model){
        Empresa empresa = empresaRepo.findById(id).orElse(null);
        if (empresa != null) {
            model.addAttribute("empresa", empresa);
            return "empresa-detail";
        } else {
            return "404";
        }
    }

    @GetMapping(value = "/empresa/create")
    public String createEmpresaForm(Model model) {
        model.addAttribute("empresa", new Empresa());
        return "empresa-form";
    }

    @PostMapping(value = "/empresa/create")
    public String saveEmpresa(@ModelAttribute Empresa empresa){
        empresaRepo.save(empresa);
        return "redirect:/empresas";
    }

    @GetMapping(value = "/empresa/update/{id}")
    public String updateEmpresaForm(@PathVariable long id, Model model) {
        Empresa empresa = empresaRepo.findById(id).orElse(null);
        if (empresa != null) {
            model.addAttribute("empresa", empresa);
            return "empresa-form";
        } else {
            return "404";
        }
    }

    @PostMapping(value = "/empresa/update/{id}")
    public String updateEmpresa(@PathVariable long id, @ModelAttribute Empresa empresa){
        Empresa updatedEmpresa = empresaRepo.findById(id).orElse(null);
        if (updatedEmpresa != null) {
            updatedEmpresa.setNome(empresa.getNome());
            updatedEmpresa.setEndereco(empresa.getEndereco());
            empresaRepo.save(updatedEmpresa);
            return "redirect:/empresas";
        } else {
            return "404";
        }
    }

    @GetMapping(value = "/empresa/delete/{id}")
    public String deleteEmpresa(@PathVariable long id){
        Empresa deletedEmpresa = empresaRepo.findById(id).orElse(null);
        if (deletedEmpresa != null) {
            empresaRepo.delete(deletedEmpresa);
            return "redirect:/empresas";
        } else {
            return "404";
        }
    }
}
