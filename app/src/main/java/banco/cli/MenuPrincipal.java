package banco.cli;

import banco.service.ContaService;
import java.math.BigDecimal;
import banco.model.*;

public class MenuPrincipal
{
    private final EntradaSaidaConsole io;
    private final ContaService contaService;
    private String titular;
    private Conta conta;

        public MenuPrincipal(ContaService contaService, EntradaSaidaConsole io) {
        this.contaService = contaService;
        this.io = io;
    }

    public void iniciar(){
        criarNovaConta();
        exibirMenu();
    }

    private void criarNovaConta(){
        io.exibirMensagem("\n--- CRIACAO DE NOVA CONTA ---");

        String cpf = "";
        boolean cpfValido = false;

        while(!cpfValido)
        {
        cpf = io.lerTexto("Digite o CPF do titular (sem pontuacao): ");
            if (ValidarCPF.ValidarCPF(cpf)){
                cpfValido = true;
                io.exibirMensagem("CPF validado com sucesso!");
            }else io.exibirMensagem("Erro: CPF invalido! Por favor, tente novamente");
        }

        this.titular = io.lerTexto("Digite o nome completo do titular: ");
        String cpfFormatado = ValidarCPF.mostrarCPF(cpf);
        BigDecimal limiteChequeEspecial = BigDecimal.valueOf(io.lerValorFinanceiro("Digite o limite do cheque especial: "));

        try 
        {
            // Atualize a chamada dos métodos passando todas as variáveis (nome, cpf e limite):
            this.conta = contaService.criarContaCorrente(titular, cpf, limiteChequeEspecial); 
                
            io.exibirMensagem("=====================================");
            io.exibirMensagem("\nConta criada com sucesso para: " + titular);
            io.exibirMensagem("Documento registrado: " + cpfFormatado);
            io.exibirMensagem("Limite do cheque especial: " + limiteChequeEspecial);
            io.exibirMensagem("Saldo inicial: R$00,00");
            io.exibirMensagem("Data de abertura: " + java.time.LocalDate.now());
            io.exibirMensagem("Numero da conta: " + conta.getNumero());
            io.exibirMensagem("=====================================");
                
        } catch(Exception e) 
        {
            io.exibirMensagem("Erro ao criar a conta no sistema: " + e.getMessage());
        }
    }

    private void exibirMenu() {
        boolean rodando = true;
        io.exibirMensagem("--- Bem-vindo ao sistema " + titular + " ---");
        while(rodando){
            io.exibirMensagem("Saldo atual: R$" + conta.getSaldo());
            io.exibirMensagem("1. Realizar operacoes na conta");
            io.exibirMensagem("2. Realizar operacoes no Mercado Financeiro");
            io.exibirMensagem("3. Sair");
            int opcao = io.lerInteiro("Escolha uma opcao: ");
            io.exibirMensagem("--------------------------------");

            switch(opcao){
                case 1:
                    io.exibirMensagem("Operacoes da conta:");
                    io.exibirMensagem("Saldo atual: R$" + conta.getSaldo());
                    io.exibirMensagem("1. Depositar");
                    io.exibirMensagem("2. Sacar");
                    int operacaoConta = io.lerInteiro("Escolha uma operacao: ");
                    io.exibirMensagem("--------------------------------");
                    switch(operacaoConta){
                        case 1:

                            double depositoDigitado = io.lerValorFinanceiro("Digite o valor do deposito: R$");
                            BigDecimal valorConvertido = BigDecimal.valueOf(depositoDigitado);
                        
                            Transacao novoDeposito = new Transacao(Transacao.TipoTransacao.DEPOSITO, valorConvertido, conta); //serve para gerar um extrato de transferencia
                            novoDeposito.processar();

                            break;
                            
                        case 2:

                            double saqueDigitado = io.lerValorFinanceiro("Digite o valor do saque: R$");
                            BigDecimal valorConvertidoo = BigDecimal.valueOf(saqueDigitado);

                            Transacao novoSaque = new Transacao(Transacao.TipoTransacao.SAQUE, valorConvertidoo, conta);
                            novoSaque.processar();

                            break;
                             
                        default:
                            io.exibirMensagem("Opcao invalida! Tente novamente.");
                    }
                    break;
                case 2:
                    io.exibirMensagem("Operacoes no Mercado Financeiro:");
                    io.exibirMensagem("--------------------------------");
                    break;
                case 3:
                    io.exibirMensagem("Saindo do sistema...");
                    rodando = false;
                    break;
                default:
                    io.exibirMensagem("Opcao invalida! Tente novamente.");
            }
        }
    }
}