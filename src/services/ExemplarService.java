package services;

public class ExemplarService {
    public static void validarDisp(String disp) {
        if (!disp.equals("disp") && !disp.equals("indisp")) {
            throw new IllegalArgumentException("-> Disponibilidade deve ser 'dips' para disponível ou 'indips' para indisponível.");
        }
    }
}
