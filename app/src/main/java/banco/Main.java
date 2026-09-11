package banco;

import banco.cli.*;
import banco.service.*;
import banco.repository.*;

public class Main {
    public static void main(String[] args) {

        // 1. Camada de Dados (O Cofre)
        ContaRepository contaRepository = new ContaRepository();

        // 2. Camada de Negócios
        ContaService contaService = new ContaService(contaRepository);

        // 3. Ferramentas de Interface
        EntradaSaidaConsole io = new EntradaSaidaConsole();

        // 4. Camada de Interface / Controlador (O Maestro)
        MenuPrincipal menu = new MenuPrincipal(contaService, io);

        // 5. Início da Execução
        menu.iniciar();
    }
}