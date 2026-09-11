package banco.service;

import banco.model.Conta;
import banco.model.ContaCorrente;
import banco.repository.ContaRepository;

import java.math.BigDecimal;
import java.util.UUID;

public class ContaService {

    // O Service conhece o Repository, mas não sabe se ele salva em memória ou arquivo
    private final ContaRepository repository;

    // Injeção de Dependência: recebemos o Repository pelo construtor
    public ContaService(ContaRepository repository) {
        this.repository = repository;
    }

    public Conta criarContaCorrente(String titular, String cpf, BigDecimal limiteChequeEspecial) {
        
        // Geração do número da conta
        String numeroConta = UUID.randomUUID().toString().substring(0, 6).toUpperCase();

        // Criando o modelo de conta
        Conta novaConta = new ContaCorrente(numeroConta, titular, cpf, limiteChequeEspecial);

        // Armazenando a conta
        repository.salvar(novaConta);

        // 4. RETORNO: Devolvemos o objeto para que o Menu possa exibir o número gerado ao usuário
        return novaConta;
    }
}