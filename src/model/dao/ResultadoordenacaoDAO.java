/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.object.Resultadoordenacao;

/**
 *
 * @author DELTA
 */
public class ResultadoordenacaoDAO {

    Connection con = ConnectionFactory.getConnection();
    PreparedStatement stmt;
    ResultSet rs;

    public List<Resultadoordenacao> Ler() {

        con = ConnectionFactory.getConnection();
        stmt = null;
        rs = null;

        List<Resultadoordenacao> resultadoordenacaos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM  bancoaps.resultadoordenacao");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Resultadoordenacao pesultadoordenacao = new Resultadoordenacao();

                pesultadoordenacao.setCodigo(rs.getInt("codigo"));
                pesultadoordenacao.setMetodoordenacao(rs.getString("metodoordenacao"));
                pesultadoordenacao.setTabela(rs.getString("tabela"));
                pesultadoordenacao.setColuna(rs.getString("coluna"));
                pesultadoordenacao.setRegistros(rs.getInt("registros"));
                pesultadoordenacao.setTempo(rs.getInt("tempo"));
                resultadoordenacaos.add(pesultadoordenacao);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resultadoordenacaos;

    }

    public void AdicionaRelatorio(String Metodoordenacao, String Coluna, String tabela, int Registros, long Tempo) {
        String sql = "INSERT INTO `bancoaps`.`resultadoordenacao` ("
                + " `metodoordenacao`, `tabela`,"
                + " `coluna`, `registros`,`tempo`)"
                + " VALUES (?,?,?,?,?)";
        try {

            stmt = con.prepareStatement(sql);

            stmt.setString(1, Metodoordenacao);
            stmt.setString(2, tabela);
            stmt.setString(3, Coluna);
            stmt.setInt(4, Registros);
            stmt.setLong(5, Tempo);

            stmt.execute();
        } catch (SQLException u) {
            System.out.println(u);
            throw new RuntimeException(u);
        }
    }



    public void Delete() {
        try {
            String sql = "DELETE FROM resultadoordenacao";

            stmt = con.prepareStatement(sql);
            stmt.execute();

        } catch (SQLException u) {
            System.out.println(u);
            throw new RuntimeException(u);
        }

    }
}
