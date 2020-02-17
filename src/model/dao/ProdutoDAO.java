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
import model.object.Produto;

public class ProdutoDAO {

    Connection con = ConnectionFactory.getConnection();
    PreparedStatement stmt;
    ResultSet rs;

    public List<Produto> Ler() {

        stmt = null;
        rs = null;

        List<Produto> Produtos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM Produto");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Produto produto = new Produto();

                produto.setIdproduto(rs.getInt("Idproduto"));
                produto.setDescricaoproduto(rs.getString("Descricaoproduto"));
                produto.setValorvenda(rs.getBigDecimal("Valorvenda"));
                produto.setValorcustounitario(rs.getBigDecimal("Valorcustounitario"));
                produto.setFksetor(rs.getInt("Fksetor"));
                produto.setFksubgrupo(rs.getInt("Fksubgrupo"));
                produto.setFkmarca(rs.getInt("Fkmarca"));
                produto.setFkgrupo(rs.getInt("Fkgrupo"));
                produto.setStatus(rs.getBoolean("status"));

                Produtos.add(produto);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Produtos;

    }

    public List<Produto> LerStatus() {

        stmt = null;
        rs = null;

        List<Produto> Produtos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM Produto WHERE status=true");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Produto produto = new Produto();

                produto.setIdproduto(rs.getInt("Idproduto"));
                produto.setDescricaoproduto(rs.getString("Descricaoproduto"));
                produto.setValorvenda(rs.getBigDecimal("Valorvenda"));
                produto.setValorcustounitario(rs.getBigDecimal("Valorcustounitario"));
                produto.setFksetor(rs.getInt("Fksetor"));
                produto.setFksubgrupo(rs.getInt("Fksubgrupo"));
                produto.setFkmarca(rs.getInt("Fkmarca"));
                produto.setFkgrupo(rs.getInt("Fkgrupo"));
                produto.setStatus(rs.getBoolean("status"));

                Produtos.add(produto);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Produtos;

    }

    public int RetornaId() {
        int valor = 0;
        con = ConnectionFactory.getConnection();
        stmt = null;
        rs = null;

        try {

            stmt = con.prepareStatement("SELECT idproduto from produto WHERE"
                    + " idproduto = (SELECT MAX(idproduto) FROM produto)");
            rs = stmt.executeQuery();
            if (rs.next()) {
                valor = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return valor;
    }

    public void Adiciona(Produto produto) {
        String sql = "INSERT INTO `bancoaps`.`produto` (`idproduto`, `descricaoproduto`, `valorvenda`, `valorcustounitario`, `fksetor`, `fkgrupo`, `fksubgrupo`, `fkmarca`, `status`) VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, produto.getIdproduto());
            stmt.setString(2, produto.getDescricaoproduto());
            stmt.setBigDecimal(3, produto.getValorvenda());
            stmt.setBigDecimal(4, produto.getValorcustounitario());
            stmt.setInt(5, produto.getFksetor());
            stmt.setInt(6, produto.getFkgrupo());
            stmt.setInt(7, produto.getFksubgrupo());
            stmt.setInt(8, produto.getFkmarca());
            stmt.setBoolean(9, produto.getStatus());
            stmt.execute();
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public int Excluir(Produto produto) {
        String sql = "UPDATE Produto set status=false where idproduto= ?";
        int excluir = 0;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, produto.getIdproduto());
            excluir = stmt.executeUpdate();
            return excluir;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public int Ativar(Produto produto) {
        String sql = "UPDATE Produto set status=true where idproduto= ?";
        int excluir = 0;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, produto.getIdproduto());
            excluir = stmt.executeUpdate();
            return excluir;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public int Alterar(Produto produto) {
        String sql = "UPDATE Produto set descricaoproduto=?, valorvenda=?, valorcustounitario=? "
                + " where Idproduto=?";
        int alterar = 0;
        try {
            stmt = con.prepareStatement(sql);

            stmt.setString(1, produto.getDescricaoproduto());
            stmt.setBigDecimal(2, produto.getValorvenda());
            stmt.setBigDecimal(3, produto.getValorcustounitario());
            stmt.setInt(4, produto.getIdproduto());

            alterar = stmt.executeUpdate();
            return alterar;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public ArrayList<String> LerProduto(String target) {

        con = ConnectionFactory.getConnection();
        stmt = null;
        rs = null;

        ArrayList<String> Target = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT " + target + " FROM Produto");
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
