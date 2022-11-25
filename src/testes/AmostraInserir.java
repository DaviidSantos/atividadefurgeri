package testes;

import dao.AmostraDAO;
import models.Amostra;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;

public class AmostraInserir {
    public static void main(String[] args) {
        Amostra amostra = new Amostra("Dipirona", 2, "Aguardando Análise", Date.valueOf(LocalDate.now()), "Comprimido", "123456789", Date.valueOf("2022-11-25"));
        AmostraDAO amostraDAO = new AmostraDAO();
        System.out.println(amostraDAO.salvar(amostra));
    }
}
