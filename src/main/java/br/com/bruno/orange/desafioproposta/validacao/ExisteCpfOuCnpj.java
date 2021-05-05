package br.com.bruno.orange.desafioproposta.validacao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.server.ResponseStatusException;

import br.com.bruno.orange.desafioproposta.proposta.Proposta;
import br.com.bruno.orange.desafioproposta.proposta.PropostaRepository;
import br.com.bruno.orange.desafioproposta.proposta.PropostaRequest;

@Component
public class ExisteCpfOuCnpj implements Validator{

	@Autowired
	private PropostaRepository repository;

	@Override
	public boolean supports(Class<?> clazz) {
		return PropostaRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(errors.hasErrors()) {
			return;
		}

		PropostaRequest request = (PropostaRequest) target;

		Optional<Proposta> proposta = repository.findByCpfCnpj(request.getCpf_cnpj());

		if(proposta.isPresent()) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "CPF OU CNPJ já cadastrado");
		}

	}

}
