package banco.repository;

import banco.model.Conta;
import java.lang.reflect.Field;
import java.util.*;

public class ContaRepository {
    // Este HashMap é o "banco de dados" em memória.
    private final Map<String, Conta> bancoDeDados = new HashMap<>();

    // Método para salvar a conta que o usuário acabou de criar
    public void salvar(Conta conta) {
        bancoDeDados.put(obterNumero(conta), conta);
    }

    private String obterNumero(Conta conta) {
        try {
            Field campo = Conta.class.getDeclaredField("numero");
            campo.setAccessible(true);
            Object numero = campo.get(conta);
            if (numero == null) {
                throw new IllegalArgumentException("O número da conta não pode ser nulo");
            }
            return numero.toString();
        } catch (ReflectiveOperationException e) {
            throw new IllegalArgumentException("Não foi possível obter o número da conta", e);
        }
    }

    // Método para buscar uma conta quando o usuário for fazer uma transferência
    // Retorna um Optional para evitar o temido NullPointerException caso a conta não exista
    public Optional<Conta> buscarPorNumero(String numero) {
        return Optional.ofNullable(bancoDeDados.get(numero));
    }
    
    // Método para listar todas as contas (útil para um menu de administrador, por exemplo)
    public Map<String, Conta> listarTodas() {
        return bancoDeDados;
    }
}