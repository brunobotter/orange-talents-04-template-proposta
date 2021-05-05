package br.com.bruno.orange.desafioproposta.proposta;

import java.math.BigDecimal;

public class PropostaResponse {
	
	@Deprecated
	public PropostaResponse() {

	}
	
	public PropostaResponse(Proposta proposta) {
		this.cpf_cnpj = proposta.getCpfCnpj();
		this.id = proposta.getId();
		this.email = proposta.getEmail();
		this.endereco = proposta.getEndereco();
		this.salario = proposta.getSalario();
		this.nome = proposta.getNome();
	}
	
	private Long id;

	private String cpf_cnpj;
	

	private String email;
	

	private String nome;
	
	private String endereco;
	
	private BigDecimal salario;

	public Long getId() {
		return id;
	}

	public String getCpf_cnpj() {
		return cpf_cnpj;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public BigDecimal getSalario() {
		return salario;
	}
	
	
}
