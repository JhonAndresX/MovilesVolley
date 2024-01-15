package com.example.demo.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.models.dao.IClienteDao;
import com.example.demo.models.entity.Cliente;

@Service
public class ClienteServiceImpl implements IClienteService {
    
	@Autowired
	private IClienteDao ClienteDao;

	@Override
	@Transactional(readOnly = true)
	
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return (List<Cliente>) ClienteDao.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Cliente findById(Long id) {
		// TODO Auto-generated method stub
		return ClienteDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Cliente save(Cliente cliente) {
		// TODO Auto-generated method stub
		return ClienteDao.save(cliente);
	}

	
	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		ClienteDao.deleteById(id);
	}

}
