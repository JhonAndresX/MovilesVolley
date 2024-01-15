package com.example.demo.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.entity.Articulo;

public interface IArticuloDao  extends CrudRepository<Articulo,Long> {
 
}
