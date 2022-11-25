package testes;

import dao.AmostraDAO;
import models.Amostra;

public class AmostraGetTeste {
    public static void main(String[] args) {
        AmostraDAO dao = new AmostraDAO();
        Amostra p = dao.localizar(3);
        if(p!= null) {
            System.out.println(p);
        }
        else {
            System.out.println("Produto não encontrado!");
        }
    }
}
