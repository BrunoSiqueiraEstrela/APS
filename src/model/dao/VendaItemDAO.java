package model.dao;

import connection.ConnectionFactory;
import java.math.BigDecimal;
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

import model.object.VendaItem;

public class VendaItemDAO {

    Connection con = ConnectionFactory.getConnection();
    PreparedStatement stmt;
    ResultSet rs;

    public List<VendaItem> Ler() {

        con = ConnectionFactory.getConnection();
        stmt = null;
        rs = null;

        List<VendaItem> VendaItens = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM venda_item");
            rs = stmt.executeQuery();

            while (rs.next()) {

                VendaItem vendaitem = new VendaItem();

                vendaitem.setIditemvenda(rs.getInt("iditemvenda"));
                vendaitem.setFkvenda(rs.getInt("fkvenda"));
                vendaitem.setFkproduto(rs.getInt("fkproduto"));
                vendaitem.setValorcusto(rs.getBigDecimal("valorcusto"));
                vendaitem.setValorvenda(rs.getBigDecimal("valorvenda"));
                vendaitem.setQuantidade(rs.getBigDecimal("quantidade"));
                vendaitem.setValorfinalitem(rs.getBigDecimal("valorfinalitem"));
                vendaitem.setStatus(rs.getBoolean("status"));
                VendaItens.add(vendaitem);
            }

        } catch (SQLException ex) {
            Logger.getLogger(VendaItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return VendaItens;

    }

    public List<VendaItem> LerChave(Venda venda) {

        con = ConnectionFactory.getConnection();
        stmt = null;
        rs = null;

        List<VendaItem> VendaItens = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM venda_item WHERE fkvenda=?");
            stmt.setInt(1, venda.getIdvenda());
            rs = stmt.executeQuery();

            while (rs.next()) {

                VendaItem vendaitem = new VendaItem();

                vendaitem.setIditemvenda(rs.getInt("iditemvenda"));
                vendaitem.setFkvenda(rs.getInt("fkvenda"));
                vendaitem.setFkproduto(rs.getInt("fkproduto"));
                vendaitem.setValorcusto(rs.getBigDecimal("valorcusto"));
                vendaitem.setValorvenda(rs.getBigDecimal("valorvenda"));
                vendaitem.setQuantidade(rs.getBigDecimal("quantidade"));
                vendaitem.setValorfinalitem(rs.getBigDecimal("valorfinalitem"));
                vendaitem.setStatus(rs.getBoolean("status"));
                VendaItens.add(vendaitem);
            }

        } catch (SQLException ex) {
            Logger.getLogger(VendaItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return VendaItens;

    }

    public BigDecimal Valor(Venda venda) {

        con = ConnectionFactory.getConnection();
        stmt = null;
        rs = null;
        BigDecimal fi = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM venda_item WHERE fkvenda=?");
            stmt.setInt(1, venda.getIdvenda());
            rs = stmt.executeQuery();

            while (rs.next()) {

                fi.add( rs.getBigDecimal("valorfinalitem"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(VendaItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return fi;

    }

    public List<VendaItem> LerStatus() {

        con = ConnectionFactory.getConnection();
        stmt = null;
        rs = null;

        List<VendaItem> VendaItens = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM venda_item WHERE status=trues");
            rs = stmt.executeQuery();

            while (rs.next()) {

                VendaItem vendaitem = new VendaItem();

                vendaitem.setIditemvenda(rs.getInt("iditemvenda"));
                vendaitem.setFkvenda(rs.getInt("fkvenda"));
                vendaitem.setFkproduto(rs.getInt("fkproduto"));
                vendaitem.setValorcusto(rs.getBigDecimal("valorcusto"));
                vendaitem.setValorvenda(rs.getBigDecimal("valorvenda"));
                vendaitem.setQuantidade(rs.getBigDecimal("quantidade"));
                vendaitem.setValorfinalitem(rs.getBigDecimal("valorfinalitem"));
                vendaitem.setStatus(rs.getBoolean("status"));
                VendaItens.add(vendaitem);
            }

        } catch (SQLException ex) {
            Logger.getLogger(VendaItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return VendaItens;

    }

    public int RetornaId() {
        int valor = 0;
        con = ConnectionFactory.getConnection();
        stmt = null;
        rs = null;

        try {

            stmt = con.prepareStatement("SELECT iditemvenda from venda_item WHERE"
                    + " iditemvenda = (SELECT MAX(iditemvenda) FROM venda_item)");
            rs = stmt.executeQuery();
            if (rs.next()) {
                valor = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return valor;
    }

    public void Adiciona(VendaItem VendaItem) {
        String sql = "INSERT INTO `bancoaps`.`venda_item` ("
                + "`fkvenda`, `fkproduto`, `valorcusto`,"
                + " `valorvenda`, `quantidade`,"
                + " `valorfinalitem`, `status`)"
                + " VALUES (?,?,?,?,?,?,?,?)";
        try {

            stmt = con.prepareStatement(sql);
            stmt.setInt(1, VendaItem.getFkvenda());
            stmt.setInt(2, VendaItem.getFkproduto());
            stmt.setBigDecimal(3, VendaItem.getValorcusto());
            stmt.setBigDecimal(4, VendaItem.getValorvenda());
            stmt.setBigDecimal(5, VendaItem.getQuantidade());
            stmt.setBigDecimal(6, VendaItem.getValorfinalitem());
            stmt.setBoolean(7, true);

            stmt.execute();
        } catch (SQLException u) {
            System.out.println(u);
            throw new RuntimeException(u);
        }
    }

    public int Excluir(VendaItem vendaItem) {
        String sql = "UPDATE venda_item set status=0 where iditemvenda = ?";
        int excluir = 0;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, vendaItem.getIditemvenda());
            excluir = stmt.executeUpdate();
            return excluir;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public int Ativar(VendaItem vendaItem) {
        String sql = "UPDATE venda_item set status=0 where iditemvenda = ?";
        int excluir = 0;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, vendaItem.getIditemvenda());
            excluir = stmt.executeUpdate();
            return excluir;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public int Alterar(VendaItem VendaItem) {
        String sql = "UPDATE venda_item set fkvenda, fkproduto, valorcusto, valorvenda, quantidade, valorfinalitem,"
                + "where Idvenda=?";
        int alterar = 0;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, VendaItem.getFkvenda());
            stmt.setInt(2, VendaItem.getFkproduto());
            stmt.setBigDecimal(3, VendaItem.getValorcusto());
            stmt.setBigDecimal(4, VendaItem.getValorvenda());
            stmt.setBigDecimal(5, VendaItem.getQuantidade());
            stmt.setBigDecimal(6, VendaItem.getValorfinalitem());
            alterar = stmt.executeUpdate();
            return alterar;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }
}
