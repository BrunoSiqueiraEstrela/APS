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

import model.object.ProdutoSetor;

public class ProdutoSetorDAO {

    Connection con = ConnectionFactory.getConnection();
    PreparedStatement stmt;
    ResultSet rs;

    public List<ProdutoSetor> Ler() {

        con = ConnectionFactory.getConnection();
        stmt = null;
        rs = null;

        List<ProdutoSetor> ProdutosSetores = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM produto_setor");
            rs = stmt.executeQuery();

            while (rs.next()) {

                ProdutoSetor produtosetor = new ProdutoSetor();

                produtosetor.setIdsetor(rs.getInt("Idsetor"));
                produtosetor.setNome(rs.getString("Nome"));
                produtosetor.setStatus(rs.getBoolean("status"));
                ProdutosSetores.add(produtosetor);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoSetor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ProdutosSetores;

    }

    public List<ProdutoSetor> LerStatus() {

        con = ConnectionFactory.getConnection();
        stmt = null;
        rs = null;

        List<ProdutoSetor> ProdutosSetores = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM produto_setor WHERE status=true");
            rs = stmt.executeQuery();

            while (rs.next()) {

                ProdutoSetor produtosetor = new ProdutoSetor();

                produtosetor.setIdsetor(rs.getInt("Idsetor"));
                produtosetor.setNome(rs.getString("Nome"));
                produtosetor.setStatus(rs.getBoolean("status"));
                ProdutosSetores.add(produtosetor);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoSetor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ProdutosSetores;

    }

    public int RetornaId() {
        int valor = 0;
        con = ConnectionFactory.getConnection();
        stmt = null;
        rs = null;

        try {

            stmt = con.prepareStatement("SELECT idsetor FROM produto_setor WHERE"
                    + " idsetor = (SELECT MAX(idsetor) FROM produto_setor)");
            rs = stmt.executeQuery();
            if (rs.next()) {
                valor = rs.getInt(1);
                System.out.println(valor);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return valor;
    }

    public void Adiciona(ProdutoSetor ProdutoSetor) {
        String sql = "INSERT INTO `bancoaps`.`produto_setor` (`Idsetor`, `Nome`, `status`)"
                + "VALUES (?,?,?)";
        try {

            stmt = con.prepareStatement(sql);
            stmt.setInt(1, ProdutoSetor.getIdsetor());
            stmt.setString(2, ProdutoSetor.getNome());
            stmt.setBoolean(3, true);
            System.out.println("SAVAR");
            stmt.execute();
        } catch (SQLException u) {
            System.out.println(u);
            throw new RuntimeException(u);
        }
    }

    public int Excluir(ProdutoSetor ProdutoSetor) {
        String sql = "UPDATE produto_setor set status=0 where idsetor = ?";
        int excluir = 0;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, ProdutoSetor.getIdsetor());
            excluir = stmt.executeUpdate();
            return excluir;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public int Ativar(ProdutoSetor ProdutoSetor) {
        String sql = "UPDATE produto_setor set status=true where idsetor = ?";
        int excluir = 0;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, ProdutoSetor.getIdsetor());
            excluir = stmt.executeUpdate();
            return excluir;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public int Alterar(ProdutoSetor ProdutoSetor) {
        String sql = "UPDATE produto_setor set Nome = ? where idsetor=?";
        int alterar = 0;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, ProdutoSetor.getNome());
            stmt.setInt(2, ProdutoSetor.getIdsetor());

            alterar = stmt.executeUpdate();
            return alterar;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public ArrayList<String> LerProdutoSetor(String target) {

        con = ConnectionFactory.getConnection();
        stmt = null;
        rs = null;

        ArrayList<String> Target = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT " + target + " FROM produto_setor");
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
