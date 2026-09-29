package banco.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

// Dica de POO: Usar um Enum para definir os tipos de transação 
// evita erros de digitação (como usar strings "saque" ou "SAQ")
enum TipoTransacao {
    DEPOSITO,
    SAQUE
}

public class Transacao {
    
    // Atributos de uma transação real ( future feature pix )
    private BigDecimal valor;
    private LocalDateTime dataHora;
    private TipoTransacao tipoDeTransacao;
    
    // A transação precisa saber em qual conta vai operar
    private Conta contaAlvo; 

    // Inicializador da transaçã
    public Transacao(TipoTransacao tipo, BigDecimal valor, Conta contaAlvo) {
        this.tipoDeTransacao = tipo;
        this.valor = valor;
        this.contaAlvo = contaAlvo;
        this.dataHora = LocalDateTime.now(); // A data e hora da transação servirá para registro e histórico
    }

    // Método responsável por efetivar a transação na conta.
    /**
     * Processa a transação.
     * @return boolean indicando se a transação foi bem-sucedida.
     */
    public boolean processar() {
        // 1. Como verificar se esta transação é do tipo DEPOSITO ou SAQUE?
        TipoTransacao tipoTransacao = this.tipoDeTransacao;
        switch (tipoTransacao) {
            case DEPOSITO:
                contaAlvo.depositar(valor);
                return true;
            case SAQUE:
                if (!contaAlvo.sacar(valor)){
                    return false; // Falha de saque pois não há saldo suficiente
                } else {
                    return true; // Sucesso no saque
                }
            default:
                return false; // Tipo de transação desconhecido
        }
    }
    // Inserir os Getters e Setters conforme necessário para acessar os atributos privados da classe Transacao 
}