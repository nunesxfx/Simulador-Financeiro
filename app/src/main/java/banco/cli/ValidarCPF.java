package banco.cli;

import java.util.InputMismatchException;

public class ValidarCPF {

     public static boolean ValidarCPF(String CPF){
        if (CPF.equals("00000000000") || CPF.equals("11111111111") || CPF.equals("22222222222") || CPF.equals("33333333333") || CPF.equals("44444444444") || CPF.equals("55555555555")
        ||  CPF.equals("66666666666") || CPF.equals("77777777777") || CPF.equals("88888888888") || CPF.equals("99999999999") || (CPF.length() != 11) ){
            return false;}

        char digito10, digito11;
        int sm, i, r, num, peso;

        try{
            //validar o primeiro digito validador
            sm = 0;
            peso = 10;
            for (i=0 ; i<9 ; i++){
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }
            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)){
                digito10 = '0';
            } else digito10 = (char)(r + 48);

            //validar o segundo digito validador
            sm = 0;
            peso = 11;
            for (i=0 ; i<10 ; i++){
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)){
                digito11 = '0';
            } else digito11 = (char)(r + 48);

            //verificar se todos os digitos calculados batem com os digitos informados
            if ((digito10 == CPF.charAt(9)) && (digito11 == CPF.charAt(10))){
                return(true);
            } else return(false);
        }catch(InputMismatchException erro){
            return false;
        }
    }

    public static String mostrarCPF(String CPF){
        return(CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." + CPF.substring(6, 9) + "-" + CPF.substring(9, 11));
    }
}
