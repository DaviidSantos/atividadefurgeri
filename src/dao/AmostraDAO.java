package dao;

import models.Amostra;
import services.BD;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import com.google.gson.Gson;

public class AmostraDAO {
    private String sql, men;
    private BD bd;

    public AmostraDAO() {
        bd = new BD();
    }

    /**
     * Realiza a gravação de uma amostra no banco de dados
     * @param amostra A amostra a ser salva
     * @return Uma mensagem informando o ocorrido
     */
    public String salvar(Amostra amostra){
        sql = "INSERT INTO tb_amostra(nome_amostra,id_solicitacao_de_analise, status_amostra, data_de_entrada, tipo_amostra, nota_fiscal, validade) VALUES(?, ?, ?, ?, ?, ?, ?)";
        try{
            bd.getConnection();
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setString(1, amostra.getNomeAmostra());
            bd.st.setInt(2, amostra.getNumSolicitacaoDeAnalise());
            bd.st.setString(3, amostra.getStatusAmostra());
            bd.st.setDate(4,amostra.getDataDeEntrada());
            bd.st.setString(5, amostra.getTipo());
            bd.st.setString(6, amostra.getNotaFiscal());
            bd.st.setDate(7, amostra.getValidade());
            bd.st.executeUpdate();
            men = "Amostra " + amostra.getNomeAmostra() + " inserida com sucesso!";
        } catch (Exception e){
            men = "Erro ao cadastrar amostra\n" + e.toString();
        }
        finally {
            bd.close();
        }

        return men;
    }

    /**
     * Realiza a alteração do Status da Amostra
     * @param amostra Objeto amostra com Id da Amostra a ser alterada e o novo status
     * @return Uma mensagem informando o ocorrido
     */
    public String alterarStatusAmostra(Amostra amostra){
        sql = "UPDATE tb_amostra SET status_amostra = ? WHERE id_amostra = ?";
        try{
            bd.getConnection();
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setString(1, amostra.getStatusAmostra());
            bd.st.setInt(2, amostra.getNumAmostra());
            bd.st.executeUpdate();
            men = "Amostra atualizada com sucesso!";
        } catch (SQLException e) {
            men = "Amostra não encontrada";
        }
        finally {
            bd.close();
        }
        return men;
    }

    /**
     * Exclui uma amostra a partir de seu número
     * @param numAmostra Número da amostra a ser excluída
     * @return Uma mensagem informando o ocorrido
     */
    public String excluir(int numAmostra){
       sql = "DELETE FROM tb_amostra WHERE id_amostra = ?";
       try {
           bd.getConnection();
           bd.st = bd.con.prepareStatement(sql);
           bd.st.setInt(1, numAmostra);
           bd.st.executeUpdate();
           men = "Amostra deletada com sucesso!";
       } catch (SQLException e) {
           men = "Erro ao deletar amostra\n" +  e.toString();
       }
       return men;
    }

    /**
     * Localiza uma amostra a partir de seu número
     * @param numAmostra Número da amostra a ser localizada
     * @return A amostra na forma de um objeto ou null caso não encontrado
     */
    public Amostra localizar(int numAmostra){
        sql = "SELECT * FROM tb_amostra WHERE id_amostra = ?";
        Amostra amostra = null;
        try{
            bd.getConnection();
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setInt(1, numAmostra);
            bd.rs = bd.st.executeQuery();
            if (bd.rs.next()){
                amostra = new Amostra();
                amostra.setNumAmostra(bd.rs.getInt("id_amostra"));
                amostra.setNomeAmostra(bd.rs.getString("nome_amostra"));
                amostra.setNumSolicitacaoDeAnalise(bd.rs.getInt("id_solicitacao_de_analise"));
                amostra.setStatusAmostra(bd.rs.getString("status_amostra"));
                amostra.setDataDeEntrada(bd.rs.getDate("data_de_entrada"));
                amostra.setNotaFiscal(bd.rs.getString("nota_fiscal"));
                amostra.setTipo(bd.rs.getString("tipo_amostra"));
                amostra.setValidade(bd.rs.getDate("validade"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            bd.close();
        }

        return amostra;
    }

    /**
     * Método que transforma um objeto para um Json
     * @param amostra Amostra a ser transformada para Json
     * @return Json da Amostra
     */
    public String toJson(Amostra amostra){
        Gson g = new Gson();
        String amostraJson = g.toJson(amostra);
        return amostraJson;
    }

    /**
     * Método que exporta as amostras para um CSV
     * @param arquivo caminho do arquivo a ser exportado
     * @return quantidade de amostras exportadas
     */
    public int exportarCsv(String arquivo){
        int qtde = 0;
        bd.getConnection();
        sql = "select * from tb_amostra";
        try {
            PrintWriter pw = new PrintWriter(arquivo);
            pw.println("id_amostra;data_de_entrada;nome_amostra;nota_fiscal;status_amostra;tipo_amostra;validade;id_solicitacao_de_analise");
            bd.st = bd.con.prepareStatement(sql);
            bd.rs = bd.st.executeQuery();
            while(bd.rs.next()) { //ler registro e inserir no arquivo
                String linha =
                        bd.rs.getString(1)+";"+
                                bd.rs.getString(2)+";"+
                                bd.rs.getString(3)+";"+
                                bd.rs.getString(4)+";"+
                                bd.rs.getString(5)+";"+
                                bd.rs.getString(6)+";"+
                                bd.rs.getString(7)+";"+
                                bd.rs.getString(8)+";";
                linha = linha.replace(".", ",");
                pw.println(linha);
                qtde++;
            }
            pw.close();
        }
        catch(SQLException e) {
            System.out.println("SQL: " + e);
        }catch(FileNotFoundException e) {
            System.out.println("Arquivo: " + e);
        }
        finally {
            bd.close();
        }

        return qtde;
    }

    /**
     * Importa um arquivo CSV e salva na base de dados
     * @param file Caminho do arquivo a ser importado
     * @return quantos registros foram importados
     */
    public int importarCSV(String file) {
        int qtde = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            br.readLine(); //le o cabeçalho
            String linha="";
            while((linha = br.readLine())!=null) {
                //ler a linha e gravar no banco
                //System.out.println(linha);
                String[] dados = linha.split(";");
                Amostra amostra = new Amostra();
                amostra.setNumAmostra(Integer.parseInt(dados[0]));
                amostra.setNomeAmostra(dados[2]);
                amostra.setNumSolicitacaoDeAnalise(Integer.parseInt(dados[7]));
                amostra.setStatusAmostra(dados[4]);
                amostra.setDataDeEntrada(Date.valueOf(dados[1]));
                amostra.setNotaFiscal(dados[3]);
                amostra.setTipo(dados[5]);
                System.out.println(dados[6]);
                amostra.setValidade(Date.valueOf(dados[6]));
                System.out.println(amostra);
                salvar(amostra);
                qtde++;
            }
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
        return qtde;
    }
}
