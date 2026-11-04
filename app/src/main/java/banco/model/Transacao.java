package banco.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

// Enum para definir os tipos de transação 


public class Transacao {

    public enum TipoTransacao {
        DEPOSITO,
        SAQUE
    }
    
    // Atributos de uma transação real ( future feature pix )
    private BigDecimal valor;
    private LocalDateTime dataHora;
    private TipoTransacao tipoDeTransacao;
    
    // A transação precisa saber em qual conta vai operar
    private Conta contaAlvo; 

    // Inicializador da transação
    public Transacao(TipoTransacao tipo, BigDecimal valor, Conta contaAlvo) {
        this.tipoDeTransacao = tipo;
        this.valor = valor;
        this.contaAlvo = contaAlvo; // imaginar que a conta alvo sera numerica em todos os casos
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
                if (valor.compareTo(BigDecimal.ZERO) <= 0) {
                    return false;
                } else{
                    contaAlvo.depositar(valor);
                    return true;
                }
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

    //get para mostrar o valor da transação
    public BigDecimal getValor() {return valor;}

    //get para mostrar a data da transação
    public LocalDateTime getDataEHora() {return dataHora;}

    //get para mostrar qual conta sera enviada o dinheiro
    public Conta getContaAlvo() {return contaAlvo;}

}