package com.example.demo.models.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="roles", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id","autorizacion"})})
public class Role implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	private String autorizacion;
		
	private static final long serialVersionUID = 1L;

 
	public Long getId() {
		return Id;
	}


	public void setId(Long id) {
		Id = id;
	}


	public String getAutorizacion() {
		return autorizacion;
	}


	public void setAutorizacion(String autorizacion) {
		this.autorizacion = autorizacion;
	}


	/**
	 * 
	 */

}
