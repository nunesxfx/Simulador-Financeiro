package banco.model;

import java.math.BigDecimal;

// O "extends Conta" significa: "Conta Corrente é uma Conta e herda tudo dela"
public class ContaCorrente extends Conta {
    
    // Característica exclusiva da Conta Corrente
    private BigDecimal limiteChequeEspecial;

    public ContaCorrente(String numero, String titular, String cpf, BigDecimal limiteChequeEspecial) {
        // O "super()" chama o construtor da classe mãe (Conta) para montar o DNA base
        super(numero, titular, cpf);
        this.limiteChequeEspecial = limiteChequeEspecial;
    }

    // A Conta Corrente pode REESCREVER o comportamento de saque
    // para permitir o uso do cheque especial, coisa que a Conta genérica não faz.
    @Override
    public boolean sacar(BigDecimal valor) {
        BigDecimal saldoTotalDisponivel = this.saldo.add(this.limiteChequeEspecial);
        
        if (saldoTotalDisponivel.compareTo(valor) >= 0) {
            this.saldo = this.saldo.subtract(valor);
            return true;
        }
        return false;
    }
}