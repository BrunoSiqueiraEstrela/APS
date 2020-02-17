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

import model.object.ProdutoMarca;

public class ProdutoMarcaDAO {

    Connection con = ConnectionFactory.getConnection();
    PreparedStatement stmt;
    ResultSet rs;

    public List<ProdutoMarca> Ler() {

        con = ConnectionFactory.getConnection();
        stmt = null;
        rs = null;

        List<ProdutoMarca> ProdutoMarcas = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM produto_marca");
            rs = stmt.executeQuery();

            while (rs.next()) {

                ProdutoMarca produtomarca = new ProdutoMarca();

                produtomarca.setIdmarca(rs.getInt("Idmarca"));
                produtomarca.setNome(rs.getString("Nome"));
                produtomarca.setStatus(rs.getBoolean("status"));
                ProdutoMarcas.add(produtomarca);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoMarcaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ProdutoMarcas;

    }

    public List<ProdutoMarca> LerStatus() {

        con = ConnectionFactory.getConnection();
        stmt = null;
        rs = null;

        List<ProdutoMarca> ProdutoMarcas = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM produto_marca WHERE status=true");
            rs = stmt.executeQuery();

            while (rs.next()) {

                ProdutoMarca produtomarca = new ProdutoMarca();

                produtomarca.setIdmarca(rs.getInt("Idmarca"));
                produtomarca.setNome(rs.getString("Nome"));
                produtomarca.setStatus(rs.getBoolean("status"));
                ProdutoMarcas.add(produtomarca);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoMarcaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ProdutoMarcas;

    }

    public int RetornaId() {
        int valor = 0;
        con = ConnectionFactory.getConnection();
        stmt = null;
        rs = null;

        try {

            stmt = con.prepareStatement("SELECT idmarca from produto_marca WHERE"
                    + " idmarca = (SELECT MAX(idmarca) FROM produto_marca)");
            rs = stmt.executeQuery();
            if (rs.next()) {
                valor = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return valor;
    }

    public void Adiciona(ProdutoMarca ProdutoMarca) {
        String sql = "INSERT INTO `bancoaps`.`produto_marca` (`Idmarca`, `Nome`, `status`)" + " VALUES (?,?,?)";
        try {

            stmt = con.prepareStatement(sql);
            stmt.setInt(1, ProdutoMarca.getIdmarca());
            stmt.setString(2, ProdutoMarca.getNome());
            stmt.setBoolean(3, true);

            stmt.execute();
        } catch (SQLException u) {
            System.out.println(u);
            throw new RuntimeException(u);
        }
    }

    public int Excluir(ProdutoMarca ProdutoMarca) {
        String sql = "UPDATE produto_marca set status=0 where Idmarca = ?";
        int excluir = 0;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, ProdutoMarca.getIdmarca());
            excluir = stmt.executeUpdate();
            return excluir;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public int Ativar(ProdutoMarca ProdutoMarca) {
        String sql = "UPDATE produto_marca set status=true where Idmarca = ?";
        int excluir = 0;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, ProdutoMarca.getIdmarca());
            excluir = stmt.executeUpdate();
            return excluir;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public int Alterar(ProdutoMarca ProdutoMarca) {
        String sql = "UPDATE produto_marca set Nome = ? where Idmarca=?";
        int alterar = 0;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, ProdutoMarca.getNome());
            stmt.setInt(2, ProdutoMarca.getIdmarca());
            alterar = stmt.executeUpdate();
            return alterar;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public ArrayList<String> LerProdutoMarca(String target) {

        con = ConnectionFactory.getConnection();
        stmt = null;
        rs = null;

        ArrayList<String> Target = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT " + target + " FROM produto_marca");
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
