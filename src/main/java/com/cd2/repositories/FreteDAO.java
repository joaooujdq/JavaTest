package com.cd2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cd2.models.Frete;

public interface FreteDAO extends JpaRepository<Frete, Integer>{
	//a função vazia é suficiente para o acesso aos dados

}
