package testes;

import dao.AmostraDAO;
import models.Amostra;

public class AmostraAtualizarStatus {
    public static void main(String[] args) {
        Amostra amostra = new Amostra(2, "Análise Finalizada");
        AmostraDAO amostraDAO = new AmostraDAO();
        System.out.println(amostraDAO.alterarStatusAmostra(amostra));
    }
}
