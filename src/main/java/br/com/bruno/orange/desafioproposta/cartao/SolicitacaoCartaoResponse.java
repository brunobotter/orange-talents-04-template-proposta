package br.com.bruno.orange.desafioproposta.cartao;

public class SolicitacaoCartaoResponse {

	private String documento;

	private String nome;

	private String idProposta;

	private String resultadoSolicitacao;

	public SolicitacaoCartaoResponse(String cpfCnpj, String nome, String idProposta, String restricao) {
		super();
		this.documento = cpfCnpj;
		this.nome = nome;
		this.idProposta = idProposta;
		this.resultadoSolicitacao = restricao;
	}


	public String getDocumento() {
		return documento;
	}



	public String getNome() {
		return nome;
	}



	public String getIdProposta() {
		return idProposta;
	}


	public String getResultadoSolicitacao() {
		return resultadoSolicitacao;
	}


	@Override
	public String toString() {
		return "SolicitacaoCartaoResponse [documento=" + documento + ", nome=" + nome + ", idProposta=" + idProposta
				+ ", restricao=" + resultadoSolicitacao + "]";
	}


	
	
}
