package com.example.demo.models.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.example.demo.models.entity.Articulo;
import com.example.demo.models.services.IArticuloService;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ArticuloRestController {
	@Autowired
	private IArticuloService articuloService;
	
	@GetMapping("/articulo")
public List<Articulo> indext(){
	return articuloService.findAll();
	
}
    @GetMapping("/articulo/{id}")
    public Articulo show(@PathVariable Long id) {
    	return articuloService.findById(id);
    }
    
    @PostMapping("/articulo")
    @ResponseStatus(HttpStatus.CREATED)
    public Articulo create(@RequestBody Articulo articulo) {
    	return articuloService.save(articulo);
    }
    
    @PutMapping("/articulo/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Articulo update(@RequestBody Articulo articulo, @PathVariable Long id) {
    	Articulo articuloactual = articuloService.findById(id);
    	articuloactual.setNombre(articulo.getNombre());
    	articuloactual.setCategoria(articulo.getCategoria());
    	return articuloService.save(articuloactual);
    }
    
    @DeleteMapping("/articulo/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
    	articuloService.delete(id);
    }
}
