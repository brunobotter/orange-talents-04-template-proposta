package br.com.bruno.orange.desafioproposta.proposta;

import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.bruno.orange.desafioproposta.validacao.CpfOuCnpj;

public class PropostaRequest {


	@CpfOuCnpj
	private String cpf_cnpj;
	
	@Email
	@NotNull
	@NotBlank
	private String email;
	
	@NotNull
	@NotBlank
	private String nome;
	
	@NotNull
	@NotBlank
	private String endereco;
	
	@NotNull
	@Positive
	private BigDecimal salario;
	
	
	@Deprecated
	public PropostaRequest() {
	}



	public PropostaRequest(String cpf_cnpj, @Email @NotNull @NotBlank String email, @NotNull @NotBlank String nome,
			@NotNull @NotBlank String endereco, @NotNull @NotBlank @Positive BigDecimal salario) {
		this.cpf_cnpj = cpf_cnpj;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
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



	public Proposta toModel() {
		return new Proposta(cpf_cnpj,email, nome, endereco, salario);
	}
	
	
}
