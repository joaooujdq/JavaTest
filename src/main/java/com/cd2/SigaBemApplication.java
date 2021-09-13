package com.cd2;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cd2.models.Frete;
import com.cd2.repositories.FreteDAO;

@SpringBootApplication
public class SigaBemApplication implements CommandLineRunner{
	
	@Autowired
	private FreteDAO freteDAO;
	
	
	public static void main(String[] args) {
		SpringApplication.run(SigaBemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		
		Frete f1 = new Frete(30.75,  "73802427", "73802427","Joao Marcelo");
		Frete f2 = new Frete(23.4,  "73802427", "75804295","Joao");
		Frete f3 = new Frete(12.8,  "73802427", "01001000","Marcelo");
		
		freteDAO.saveAll(Arrays.asList(f1,f2,f3));
	}

}
