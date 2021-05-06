package br.com.bruno.orange.desafioproposta.proposta;

import java.math.BigDecimal;
import java.util.Optional;

public class PropostaResponse {

	private Long idProposta;
	private String documento;
	private String nome;
	private String endereco;
	private BigDecimal salario;
	private String status;

	
	@Deprecated
	public PropostaResponse() {

	}

	public PropostaResponse(Long idProposta, String documento, String nome, String endereco, BigDecimal salario, String status) {
		this.idProposta = idProposta;
		this.documento = documento;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
		this.status = status;
	}

	public PropostaResponse(Optional<Proposta> proposta) {
		this.idProposta = proposta.get().getId();
		this.documento = proposta.get().getCpfCnpj();
		this.nome = proposta.get().getNome();
		this.endereco = proposta.get().getEndereco();
		this.salario = proposta.get().getSalario();
		this.status = proposta.get().getRestricao().toString();
	}

	public Long getIdProposta() {
		return idProposta;
	}

	public String getDocumento() {
		return documento;
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

	public String getStatus() {
		return status;
	}
}
