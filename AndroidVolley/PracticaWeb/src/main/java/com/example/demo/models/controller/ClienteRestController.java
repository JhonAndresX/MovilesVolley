package com.example.demo.models.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.entity.Cliente;
import com.example.demo.models.services.IClienteService;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ClienteRestController {
	@Autowired
	private IClienteService clienteService;
	
	@GetMapping("/clientes")
public List<Cliente> indext(){
	return clienteService.findAll();
	
}
    @GetMapping("/clientes/{id}")
    public Cliente show(@PathVariable Long id) {
    	return clienteService.findById(id);
    }
    
    @PostMapping("/clientes")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente create(@RequestBody Cliente cliente) {
    	return clienteService.save(cliente);
    }
    
    @PutMapping("/clientes/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente update(@RequestBody Cliente cliente, @PathVariable Long id) {
    	Cliente clienteactual = clienteService.findById(id);
    	clienteactual.setNombre(cliente.getNombre());
    	clienteactual.setApellido(cliente.getApellido());
    	clienteactual.setEmail(cliente.getEmail());
    	return clienteService.save(clienteactual);
    }
    
    @DeleteMapping("/clientes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
    	clienteService.delete(id);
    }
}
