package br.com.bruno.orange.desafioproposta.proposta;

import java.math.BigDecimal;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.bruno.orange.desafioproposta.cartao.Cartao;
import br.com.bruno.orange.desafioproposta.cartao.RestricaoCartao;
import br.com.bruno.orange.desafioproposta.cartao.SolicitacaoCartaoRequest;
import br.com.bruno.orange.desafioproposta.validacao.CpfOuCnpj;

@Entity
public class Proposta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@CpfOuCnpj
	private String cpfCnpj;

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

	@Enumerated(EnumType.STRING)
	private RestricaoCartao restricao;

	@OneToOne(cascade =CascadeType.MERGE)
	private Cartao cartao;

	@Deprecated
	public Proposta() {
	}
	
	public Proposta(String cpfCnpj, @Email @NotNull @NotBlank String email, @NotNull @NotBlank String nome,
			@NotNull @NotBlank String endereco, @NotNull @Positive BigDecimal salario) {
		super();
		this.cpfCnpj = cpfCnpj;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}

	public Proposta(String cpfCnpj, @Email @NotNull @NotBlank String email, @NotNull @NotBlank String nome,
			@NotNull @NotBlank String endereco, @NotNull @Positive BigDecimal salario, RestricaoCartao restricao) {
		super();
		this.cpfCnpj = cpfCnpj;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
		this.restricao = restricao;
	}


	public Long getId() {
		return id;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
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

	public RestricaoCartao getRestricao() {
		return restricao;
	}

	public SolicitacaoCartaoRequest toModelCartao() {
		return new SolicitacaoCartaoRequest(cpfCnpj, nome, id.toString());
	}

	public void adicionaRestricao(RestricaoCartao elegivel) {
		this.restricao = elegivel;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public void adicionaCartao(Cartao cartao){
		this.cartao = cartao;
	}

}
