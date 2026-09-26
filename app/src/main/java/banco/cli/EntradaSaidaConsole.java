package banco.cli;

import java.util.*;

public class EntradaSaidaConsole {
    private final Scanner scanner;

    public EntradaSaidaConsole(){
        this.scanner = new Scanner(System.in);
    }

    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    public String lerTexto(String prompt){
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public int lerInteiro(String prompt){
        while(true) {
            try {
                System.out.print(prompt);
                int valor = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do scanner
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Erro: Por favor, digite um valor inteiro valido.");
                scanner.nextLine(); // Limpar o buffer do scanner
            }
        }
    }

    public double lerValorFinanceiro(String prompt){
        while(true) {
            try {
                System.out.print(prompt);
                double valor = scanner.nextDouble();
                scanner.nextLine(); // Limpar o buffer do scanner
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Erro: Por favor, digite um valor numérico valido.");
                scanner.nextLine(); // Limpar o buffer do scanner
            }
        }
    }

    public void fecharScanner() {
        if(scanner != null) {
            scanner.close();
        }
    }
}
