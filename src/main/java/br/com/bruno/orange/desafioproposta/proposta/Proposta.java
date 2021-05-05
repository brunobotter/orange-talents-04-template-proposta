package br.com.bruno.orange.desafioproposta.proposta;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.bruno.orange.desafioproposta.validacao.CpfOuCnpj;

@Entity
public class Proposta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

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
	public Proposta() {
	}

	public Proposta(String cpf_cnpj2, @Email @NotNull @NotBlank String email2, @NotNull @NotBlank String nome2,
			@NotNull @NotBlank String endereco2, @NotNull @Positive BigDecimal salario2) {
		this.cpf_cnpj = cpf_cnpj2;
		this.email = email2;
		this.nome = nome2;
		this.endereco = endereco2;
		this.salario = salario2;
	}

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
