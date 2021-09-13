package com.cd2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cd2.models.Frete;
import com.cd2.repositories.FreteDAO;

@RestController
@RequestMapping("/sigabem/fretes")
public class FreteController {
	
	@Autowired //injeção de dependencias
	private FreteDAO freteDAO;
	
	@GetMapping
	public List<Frete> buscarTodos() {
		
		return freteDAO.findAll();
	} //endpoint Get para retornar todos os registros 
}
