package testes;

import dao.AmostraDAO;
import models.Amostra;

public class AmostraParaJson {
    public static void main(String[] args) {
        AmostraDAO dao = new AmostraDAO();
        Amostra amostra = dao.localizar(2);
        System.out.println(amostra);
        System.out.println(dao.toJson(amostra));
    }
}
