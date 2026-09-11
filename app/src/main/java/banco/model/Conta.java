package banco.model;

import java.math.BigDecimal;

public abstract class Conta {
    protected String numero; // 'protected' permite que as classes filhas acessem esse dado
    protected String titular;
    protected String cpf;
    protected BigDecimal saldo;

    public Conta(String numero, String titular, String cpf) {
        this.numero = numero;
        this.titular = titular;
        this.cpf = cpf;
        this.saldo = BigDecimal.ZERO; // Toda conta nasce com saldo zerado
    }

    public void depositar(BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) > 0) {
            this.saldo = this.saldo.add(valor);
        }
    }
    
    // O saque básico exige apenas ter saldo suficiente
    public boolean sacar(BigDecimal valor) {
        if (this.saldo.compareTo(valor) >= 0) {
            this.saldo = this.saldo.subtract(valor);
            return true;
        }
        return false;
    }

    public String getNumero() { return numero; }
    public BigDecimal getSaldo() { return saldo; }
}