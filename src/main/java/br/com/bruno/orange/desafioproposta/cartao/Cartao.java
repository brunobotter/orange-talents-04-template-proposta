package br.com.bruno.orange.desafioproposta.cartao;

import br.com.bruno.orange.desafioproposta.biometria.Biometria;
import br.com.bruno.orange.desafioproposta.bloqueio.Bloqueio;
import br.com.bruno.orange.desafioproposta.proposta.Proposta;
import br.com.bruno.orange.desafioproposta.viagem.Viagem;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroCartao;

    @OneToOne(mappedBy = "cartao")
    private Proposta proposta;

    @OneToMany(mappedBy = "cartao")
    private List<Biometria> biometrias;

    @Enumerated(EnumType.STRING)
    private BloqueioCartao statusCartao;

    @OneToMany(cascade = CascadeType.MERGE)
    private Set<Viagem> viagem = new HashSet<>();

    @Deprecated
    public Cartao() {
    }

    public Cartao(Proposta proposta) {
        this.proposta = proposta;
    }


    public Cartao(String id, Proposta proposta) {
        this.numeroCartao = id;
        this.proposta = proposta;
    }

    public Long getId() {
        return id;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public Proposta getProposta() {
        return proposta;
    }



    public void adicionaBloqueio() {
        this.statusCartao = BloqueioCartao.BLOQUEADO;
    }

    public void adicionaViagem(Viagem viagem){
        this.viagem.add(viagem);
    }

    public BloqueioCartao getStatusCartao() {
        return statusCartao;
    }

    public Set<Viagem> getViagem() {
        return viagem;
    }
}
