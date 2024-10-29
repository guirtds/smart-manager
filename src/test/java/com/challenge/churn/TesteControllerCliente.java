package com.challenge.churn.controller;

import com.challenge.churn.model.Cliente;
import com.challenge.churn.model.Empresa;
import com.challenge.churn.repo.ClienteRepo;
import com.challenge.churn.repo.EmpresaRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClienteController.class)
public class TesteControllerCliente {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteRepo clienteRepo;

    @MockBean
    private EmpresaRepo empresaRepo;

    @Mock
    private Model model;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListClientes() throws Exception {
        when(clienteRepo.findAll()).thenReturn(Arrays.asList(new Cliente()));

        mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk())
                .andExpect(view().name("clientes"))
                .andExpect(model().attributeExists("clientes"));
    }

    @Test
    public void testGetCliente_NotFound() throws Exception {
        when(clienteRepo.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/cliente/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("404"));
    }

    @Test
    public void testCreateClienteForm() throws Exception {
        when(empresaRepo.findAll()).thenReturn(Arrays.asList(new Empresa()));

        mockMvc.perform(get("/cliente/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("cliente-form"))
                .andExpect(model().attributeExists("cliente"))
                .andExpect(model().attributeExists("empresas"));
    }

    @Test
    public void testSaveCliente() throws Exception {
        Empresa empresa = new Empresa();
        empresa.setId(1L);

        Cliente cliente = new Cliente();
        cliente.setEmpresa(empresa);

        when(empresaRepo.findById(1L)).thenReturn(Optional.of(empresa));

        mockMvc.perform(post("/cliente/create")
                        .flashAttr("cliente", cliente))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/clientes"));

        verify(clienteRepo, times(1)).save(any(Cliente.class));
    }
}
