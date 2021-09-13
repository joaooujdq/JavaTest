package com.cd2.models;



import java.io.Serializable;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.IOException;

import org.json.JSONObject;

@Entity
@Table(name = "Fretes")
public class Frete implements Serializable{

	private static final long serialVersionUID = 1L;
	//tabela hash

		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id_Frete")
		private Integer codigo;
		
		@Column(name = "nomeDestinatario", nullable = false)
		private String nomeDestinatario;
		
		@Column(name = "peso", nullable = false)
		private double peso;
		
		@Column(name = "vlTotalFrete")
		private double vlTotalFrete;
		
		@Column(name = "cepOrigem", nullable = false, length = 8)
		private String cepOrigem;
		
		@Column(name = "cepDestino", nullable = false, length = 8)
		private String cepDestino;
		
		@Column(name = "dataPrevisaoEntrega", nullable = false, length = 8)
		private LocalDate dataPrevisaoEntrega;
		
		@Column(name = "dataConsulta", length = 8)
		private LocalDate dataConsulta;
	
		
		public Frete() {
			
		}

		public Frete(  double peso,  String cepOrigem,
				String cepDestino, String nomeDestinatario) throws IOException, InterruptedException {
			
			this.nomeDestinatario = nomeDestinatario;
			this.peso = peso;
			this.cepOrigem = cepOrigem;
			this.cepDestino = cepDestino;
			this.vlTotalFrete = (int) peso; //1kg 1 real
			
			LocalDate agora = LocalDate.now();
			this.dataConsulta = agora;

			HttpClient client = HttpClient.newHttpClient();
			HttpRequest requestCepOrigem = HttpRequest.newBuilder().uri(URI.create("https://viacep.com.br/ws/" + cepOrigem +"/json/")).build();
			HttpResponse<String> responseCepOrigem = client.send(requestCepOrigem, HttpResponse.BodyHandlers.ofString());
			JSONObject jsonCepOrigem = new JSONObject(responseCepOrigem.body());
			

			
			HttpRequest requestCepDestino = HttpRequest.newBuilder().uri(URI.create("https://viacep.com.br/ws/" + cepDestino +"/json/")).build();
			HttpResponse<String> responseCepDestino = client.send(requestCepDestino, HttpResponse.BodyHandlers.ofString());
			JSONObject jsonCepDestino = new JSONObject(responseCepDestino.body());
			
			

			if(jsonCepOrigem.opt("ddd").equals(jsonCepDestino.opt("ddd"))) {
				vlTotalFrete = vlTotalFrete * 0.5;
				this.dataPrevisaoEntrega = agora.plusDays(1);
			} else if(jsonCepOrigem.opt("uf").equals(jsonCepDestino.opt("uf"))){
				vlTotalFrete = vlTotalFrete * 0.25; //75% de desconto
				this.dataPrevisaoEntrega = agora.plusDays(3);
			}else {
				this.dataPrevisaoEntrega = agora.plusDays(10);
			}

		
			
		}
		
		
		

		public Integer getCodigo() {
			return codigo;
		}

		public void setCodigo(Integer codigo) {
			this.codigo = codigo;
		}

		public String getNomeDestinatario() {
			return nomeDestinatario;
		}

		public void setNomeDestinatario(String nomeDestinatario) {
			this.nomeDestinatario = nomeDestinatario;
		}

		public double getPeso() {
			return peso;
		}

		public void setPeso(float peso) {
			this.peso = peso;
		}

		public double getVlTotalFrete() {
			return vlTotalFrete;
		}

		public void setVlTotalFrete(double vlTotalFrete) {
			this.vlTotalFrete = vlTotalFrete;
		}

		public String getCepOrigem() {
			return cepOrigem;
		}

		public void setCepOrigem(String cepOrigem) {
			this.cepOrigem = cepOrigem;
		}

		public String getCepDestino() {
			return cepDestino;
		}

		public void setCepDestinatario(String cepDestino) {
			this.cepDestino = cepDestino;
		}

		public LocalDate getDataPrevisaoEntrega() {
			return dataPrevisaoEntrega;
		}

		public void setDataPrevisaoEntrega(LocalDate dataPrevisaoEntrega) {
			this.dataPrevisaoEntrega = dataPrevisaoEntrega;
		}

		public LocalDate getDataConsulta() {
			return dataConsulta;
		}

		public void setDataConsulta(LocalDate dataConsulta) {
			this.dataConsulta = dataConsulta;
		}
		


		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Frete other = (Frete) obj;
			if (codigo == null) {
				if (other.codigo != null)
					return false;
			} else if (!codigo.equals(other.codigo))
				return false;
			return true;
		}
		
		
		
		
}
