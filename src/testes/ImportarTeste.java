package testes;

import dao.AmostraDAO;

public class ImportarTeste {
    public static void main(String[] args) {
        AmostraDAO dao = new AmostraDAO();
        System.out.println(dao.importarCSV("C:\\Users\\55199\\Documents\\test\\teste.csv"));
    }
}
