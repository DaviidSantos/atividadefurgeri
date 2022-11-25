package testes;

import dao.AmostraDAO;

public class ExportarCSVTeste {
    public static void main(String[] args) {
        AmostraDAO dao = new AmostraDAO();
        System.out.println(dao.exportarCsv("C:\\Users\\55199\\Documents\\test\\teste.csv"));
    }
}
