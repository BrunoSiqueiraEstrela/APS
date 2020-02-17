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
import model.dao.ClienteDAO;
import model.object.Venda;

public class VendaDAO {

    Connection con = ConnectionFactory.getConnection();
    PreparedStatement stmt;
    ResultSet rs;

    public List<Venda> Ler() {

        con = ConnectionFactory.getConnection();
        stmt = null;
        rs = null;

        List<Venda> vendas = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM venda");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Venda venda = new Venda();

                venda.setIdvenda(rs.getInt("Idvenda"));
                venda.setFkfuncionario(rs.getInt("Fkfuncionario"));
                venda.setFkcliente(rs.getInt("Fkcliente"));
                venda.setData(rs.getDate("Data"));
                venda.setHora(rs.getDate("Hora"));
                venda.setValor(rs.getBigDecimal("Valor"));
                venda.setDesconto(rs.getBigDecimal("Desconto"));
                venda.setDescontors(rs.getBigDecimal("Descontors"));
                venda.setValorfinal(rs.getBigDecimal("Valorfinal"));
                venda.setTroco(rs.getBigDecimal("Troco"));
                venda.setFkformapagamento(rs.getInt("Fkformapagamento"));
                venda.setObservacao(rs.getString("Observacao"));
                venda.setObservacaocancelamento(rs.getString("Observacaocancelamento"));
                venda.setStatus(rs.getBoolean("status"));
                vendas.add(venda);
            }

        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vendas;

    }

    public List<Venda> LerStatus() {

        con = ConnectionFactory.getConnection();
        stmt = null;
        rs = null;

        List<Venda> vendas = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM venda where status=true");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Venda venda = new Venda();

                venda.setIdvenda(rs.getInt("Idvenda"));
                venda.setFkfuncionario(rs.getInt("Fkfuncionario"));
                venda.setFkcliente(rs.getInt("Fkcliente"));
                venda.setData(rs.getDate("Data"));
                venda.setHora(rs.getDate("Hora"));
                venda.setValor(rs.getBigDecimal("Valor"));
                venda.setDesconto(rs.getBigDecimal("Desconto"));
                venda.setDescontors(rs.getBigDecimal("Descontors"));
                venda.setValorfinal(rs.getBigDecimal("Valorfinal"));
                venda.setTroco(rs.getBigDecimal("Troco"));
                venda.setFkformapagamento(rs.getInt("Fkformapagamento"));
                venda.setObservacao(rs.getString("Observacao"));
                venda.setObservacaocancelamento(rs.getString("Observacaocancelamento"));
                venda.setStatus(rs.getBoolean("status"));
                vendas.add(venda);
            }

        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vendas;

    }

    public int RetornaId() {
        int valor = 0;
        con = ConnectionFactory.getConnection();
        stmt = null;
        rs = null;

        try {

            stmt = con.prepareStatement("SELECT idvenda from venda WHERE"
                    + " idvenda = (SELECT MAX(idvenda) FROM venda)");
            rs = stmt.executeQuery();
            if (rs.next()) {
                valor = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return valor;
    }

    public void adiciona(Venda venda) {
        String sql = "INSERT INTO `bancoaps`.`venda` ("
                + "`Fkfuncionario`, `Fkcliente`, `Data`,"
                + " `Hora`, `Valor`,"
                + " `Desconto`, `Descontors`,"
                + " `Valorfinal`, `Troco`,"
                + " `Fkformapagamento`, `Observacao`,"
                + " `Observacaocancelamento`, `status`)"
                + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {

            stmt = con.prepareStatement(sql);
            stmt.setInt(1, venda.getFkfuncionario());
            stmt.setInt(2, venda.getFkcliente());
            stmt.setDate(3, (java.sql.Date) venda.getData());
            stmt.setDate(4, (java.sql.Date) venda.getHora());
            stmt.setBigDecimal(5, venda.getValor());
            stmt.setBigDecimal(6, venda.getDesconto());
            stmt.setBigDecimal(7, venda.getDescontors());
            stmt.setBigDecimal(8, venda.getValorfinal());
            stmt.setBigDecimal(9, venda.getTroco());
            stmt.setInt(10, venda.getFkformapagamento());
            stmt.setString(11, venda.getObservacao());
            stmt.setString(12, venda.getObservacaocancelamento());
            stmt.setBoolean(13, true);

            stmt.execute();
        } catch (SQLException u) {
            System.out.println(u);
            throw new RuntimeException(u);
        }
    }

    public int Excluir(Venda venda) {
        String sql = "UPDATE venda set status=0 where Idvenda = ?";
        int excluir = 0;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, venda.getIdvenda());
            excluir = stmt.executeUpdate();
            return excluir;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public int Ativar(Venda venda) {
        String sql = "UPDATE venda set status=true where Idvenda = ?";
        int excluir = 0;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, venda.getIdvenda());
            excluir = stmt.executeUpdate();
            return excluir;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public int Alterar(Venda venda) {
        String sql = "UPDATE Produto set Fkfuncionario=?, Fkcliente=?, Data=?, Hora=?, Valor=?, Desconto=?, "
                + "Descontors=?, Valorfinal=?, Troco=?, Fkformapagamento=?, Observacao=?, Observacaocancelamento=?, "
                + "where Idvenda=?";
        int alterar = 0;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, venda.getFkfuncionario());
            stmt.setInt(2, venda.getFkcliente());
            stmt.setDate(3, (java.sql.Date) venda.getData());
            stmt.setDate(4, (java.sql.Date) venda.getHora());
            stmt.setBigDecimal(5, venda.getValor());
            stmt.setBigDecimal(6, venda.getDesconto());
            stmt.setBigDecimal(7, venda.getDescontors());
            stmt.setBigDecimal(8, venda.getValorfinal());
            stmt.setBigDecimal(9, venda.getTroco());
            stmt.setInt(10, venda.getFkformapagamento());
            stmt.setString(11, venda.getObservacao());
            stmt.setString(12, venda.getObservacaocancelamento());
            alterar = stmt.executeUpdate();
            return alterar;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }
}
