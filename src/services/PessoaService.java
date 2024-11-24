package services;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PessoaService {
    public static void validarCpf(String cpf) {
        if (cpf.length() != 11) {
            throw new IllegalArgumentException("-> CPF deve conter 11 dígitos");
        }
    }

    public static void validarNumero(String numero) {
        if (numero.length() != 8) {
            throw new IllegalArgumentException("-> Número deve conter 8 dígitos");
        }
    }

    public static boolean validarDataNascimento(String dataNasc) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");  
        sdf.setLenient(false);
        
        try {
            sdf.parse(dataNasc);  
            int anoNascimento = Integer.parseInt(dataNasc.split("/")[0]);
            
            int anoAtual = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
            if (anoNascimento > anoAtual || anoNascimento < 1900) {
                System.out.println("Erro: Ano de nascimento inválido.");
                return false;
            }
            
            return true;
        } catch (ParseException e) {
            System.out.println("Erro: Data de nascimento inválida.");
            return false;
        }
    }
}
