package com.example.demo.models.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Proveedor")
public class Proveedor implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idproveedor;
	
	private String nombrep;
	private String apellidop;
	private String telefono;
	private String empresa;
    private String imagenPath; 

	
	public String getImagenPath() {
		return imagenPath;
	}

	public void setImagenPath(String imagenPath) {
		this.imagenPath = imagenPath;
	}

	public Long getIdproveedor() {
		return idproveedor;
	}

	public void setIdproveedor(Long idproveedor) {
		this.idproveedor = idproveedor;
	}

	public String getNombrep() {
		return nombrep;
	}

	public void setNombrep(String nombrep) {
		this.nombrep = nombrep;
	}

	public String getApellidop() {
		return apellidop;
	}

	public void setApellidop(String apellidop) {
		this.apellidop = apellidop;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	

}
