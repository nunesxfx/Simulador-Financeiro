package banco.cli;

import banco.service.ContaService;

import java.math.BigDecimal;

import banco.cli.ValidarCPF;

public class MenuPrincipal {
    private final EntradaSaidaConsole io;
    private final ContaService contaService;

        public MenuPrincipal(ContaService contaService, EntradaSaidaConsole io) {
        this.contaService = contaService;
        this.io = io;
    }

    public void iniciar(){
        boolean rodando = true;

        io.exibirMensagem("=== Bem-Vindo ao Simulador Bancario ===");

        while(rodando){
            io.exibirMensagem("\n1- Criar Conta");
            io.exibirMensagem("2- Realizar Transferencia");
            io.exibirMensagem("3- Sair");

            String opcao = io.lerTexto("Escolha uma opção: ");

            switch (opcao) {
                case "1":
                    criarNovaConta();
                    break;
            }
        }
    }

    private void criarNovaConta(){
        io.exibirMensagem("\n--- CRIACAO DE NOVA CONTA ---");

        String cpf = "";
        boolean cpfValido = false;

        while(!cpfValido){
            cpf = io.lerTexto("Digite o CPF do titular (sem pontuacao): ");

            if (ValidarCPF.ValidarCPF(cpf)){
                cpfValido = true;
                io.exibirMensagem("CPF validado com sucesso!");
            }else io.exibirMensagem("Erro: CPF invalido! Por favor, tente novamente");
        }

    String nomeTitular = io.lerTexto("Digite o nome completo do titular: ");
        BigDecimal limiteCheque = BigDecimal.valueOf(io.lerValorFinanceiro("Digite o limite do cheque especial: "));

        try {
            // 2. Atualize a chamada do método passando as 3 variáveis (nome, cpf e limite):
            contaService.criarContaCorrente(nomeTitular, cpf, limiteCheque); 
            
            io.exibirMensagem("\n Conta criada com sucesso para: " + nomeTitular);
            io.exibirMensagem("Documento registrado: " + cpf);
            
        } catch(Exception e) {
            io.exibirMensagem("Erro ao criar a conta no sistema: " + e.getMessage());
        }
    }
}
