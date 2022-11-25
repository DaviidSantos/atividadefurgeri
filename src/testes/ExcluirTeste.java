package testes;

import dao.AmostraDAO;

public class ExcluirTeste {
    public static void main(String[] args) {
        AmostraDAO amostraDAO = new AmostraDAO();
        System.out.println(amostraDAO.excluir(1));
    }
}
