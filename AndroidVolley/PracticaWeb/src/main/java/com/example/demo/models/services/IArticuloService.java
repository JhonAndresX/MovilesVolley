package com.example.demo.models.services;

import java.util.List;

import com.example.demo.models.entity.Articulo;

public interface IArticuloService {
	public List<Articulo> findAll();

	public Articulo save(Articulo articulo);

	public Articulo findById(Long id);

	public void delete (Long id);
	
}
