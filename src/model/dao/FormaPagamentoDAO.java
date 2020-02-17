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

import model.object.FormaPagamento;

public class FormaPagamentoDAO {

    Connection con = ConnectionFactory.getConnection();
    PreparedStatement stmt;
    ResultSet rs;

    public List<FormaPagamento> Ler() {

        con = ConnectionFactory.getConnection();
        stmt = null;
        rs = null;

        List<FormaPagamento> FormaPagamentos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM forma_pagamento");
            rs = stmt.executeQuery();

            while (rs.next()) {

                FormaPagamento formapagamento = new FormaPagamento();

                formapagamento.setIdformapagamento(rs.getInt("Idformapagamento"));
                formapagamento.setDescricao(rs.getString("descricao"));
                formapagamento.setStatus(rs.getBoolean("status"));
                FormaPagamentos.add(formapagamento);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FormaPagamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return FormaPagamentos;

    }

    public List<FormaPagamento> LerStatus() {

        con = ConnectionFactory.getConnection();
        stmt = null;
        rs = null;

        List<FormaPagamento> FormaPagamentos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM forma_pagamento WHERE status=true");
            rs = stmt.executeQuery();

            while (rs.next()) {

                FormaPagamento formapagamento = new FormaPagamento();

                formapagamento.setIdformapagamento(rs.getInt("idformapagamento"));
                formapagamento.setDescricao(rs.getString("descricao"));
                formapagamento.setStatus(rs.getBoolean("status"));
                FormaPagamentos.add(formapagamento);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FormaPagamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return FormaPagamentos;

    }

    public int RetornaId() {
        int valor = 0;
        con = ConnectionFactory.getConnection();
        stmt = null;
        rs = null;

        try {

            stmt = con.prepareStatement("SELECT idformapagamento from forma_pagamento WHERE"
                    + " idformapagamento = (SELECT MAX(idformapagamento) FROM forma_pagamento)");
            rs = stmt.executeQuery();
            if (rs.next()) {
                valor = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return valor;
    }

    public void Adiciona(FormaPagamento FormaPagamento) {
        String sql = "INSERT INTO `bancoaps`.`forma_pagamento` (`descricao`,`status`)"
                + " VALUES (?,?)";
        try {

            stmt = con.prepareStatement(sql);
            stmt.setString(1, FormaPagamento.getDescricao());
            stmt.setBoolean(2, true);

            stmt.execute();
        } catch (SQLException u) {
            System.out.println(u);
            throw new RuntimeException(u);
        }
    }

    public int Excluir(FormaPagamento FormaPagamento) {
        String sql = "UPDATE forma_pagamento set status=false where Idformapagamento = ?";
        int excluir = 0;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, FormaPagamento.getIdformapagamento());
            excluir = stmt.executeUpdate();
            return excluir;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public int Ativar(FormaPagamento FormaPagamento) {
        String sql = "UPDATE forma_pagamento set status=true where Idformapagamento = ?";
        int excluir = 0;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, FormaPagamento.getIdformapagamento());
            excluir = stmt.executeUpdate();
            return excluir;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }
        public int Alterar(FormaPagamento FormaPagamento) {
        String sql = "UPDATE forma_pagamento set descricao=? where Idformapagamento=?";
        int alterar = 0;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, FormaPagamento.getDescricao());
            stmt.setInt(2, FormaPagamento.getIdformapagamento());

            alterar = stmt.executeUpdate();
            return alterar;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

        public ArrayList<String> LerFormaPagamento(String target) {

        con = ConnectionFactory.getConnection();
        stmt = null;
        rs = null;

        ArrayList<String> Target = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT " + target + " FROM forma_pagamento");
            rs = stmt.executeQuery();

            while (rs.next()) {
                 Target.add(rs.getString(target));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Target;
    }
}
