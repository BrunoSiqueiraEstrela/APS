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

import model.object.ProdutoGrupo;
import model.object.ProdutoSetor;

public class ProdutoGrupoDAO {

    Connection con = ConnectionFactory.getConnection();
    PreparedStatement stmt;
    ResultSet rs;

    public List<ProdutoGrupo> Ler() {

        con = ConnectionFactory.getConnection();
        stmt = null;
        rs = null;

        List<ProdutoGrupo> ProdutoGrupos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM produto_grupo");
            rs = stmt.executeQuery();

            while (rs.next()) {

                ProdutoGrupo produtogrupo = new ProdutoGrupo();

                produtogrupo.setIdgrupo(rs.getInt("idgrupo"));
                produtogrupo.setFksetor(rs.getInt("fksetor"));
                produtogrupo.setNome(rs.getString("nome"));
                produtogrupo.setStatus(rs.getBoolean("status"));
                ProdutoGrupos.add(produtogrupo);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoGrupoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ProdutoGrupos;

    }

    public List<ProdutoGrupo> LerStatus() {

        con = ConnectionFactory.getConnection();
        stmt = null;
        rs = null;

        List<ProdutoGrupo> ProdutoGrupos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM produto_grupo WHERE status=true");
            rs = stmt.executeQuery();

            while (rs.next()) {

                ProdutoGrupo produtogrupo = new ProdutoGrupo();

                produtogrupo.setIdgrupo(rs.getInt("idgrupo"));
                produtogrupo.setFksetor(rs.getInt("fksetor"));
                produtogrupo.setNome(rs.getString("nome"));
                produtogrupo.setStatus(rs.getBoolean("status"));
                ProdutoGrupos.add(produtogrupo);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoGrupoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ProdutoGrupos;

    }

    public List<ProdutoGrupo> LerFk(ProdutoSetor produtoSetor) {

        con = ConnectionFactory.getConnection();
        stmt = null;
        rs = null;

        List<ProdutoGrupo> ProdutoGrupos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM produto_grupo where fksetor=?");
            stmt.setInt(1, produtoSetor.getIdsetor());
            rs = stmt.executeQuery();

            while (rs.next()) {

                ProdutoGrupo produtogrupo = new ProdutoGrupo();

                produtogrupo.setIdgrupo(rs.getInt("idgrupo"));
                produtogrupo.setFksetor(rs.getInt("fksetor"));
                produtogrupo.setNome(rs.getString("nome"));
                produtogrupo.setStatus(rs.getBoolean("status"));
                ProdutoGrupos.add(produtogrupo);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoGrupoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ProdutoGrupos;

    }

    public int RetornaId() {
        int valor = 0;
        con = ConnectionFactory.getConnection();
        stmt = null;
        rs = null;

        try {

            stmt = con.prepareStatement("SELECT idgrupo from produto_grupo WHERE"
                    + " idgrupo = (SELECT MAX(idgrupo) FROM produto_grupo)");
            rs = stmt.executeQuery();
            if (rs.next()) {
                valor = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return valor;
    }

    public void Adiciona(ProdutoGrupo ProdutoGrupo) {
        String sql = "INSERT INTO `bancoaps`.`produto_grupo` ("
                + "`fksetor`, `nome`,`status`)"
                + " VALUES (?,?,?)";
        try {

            stmt = con.prepareStatement(sql);
            stmt.setInt(1, ProdutoGrupo.getFksetor());
            stmt.setString(2, ProdutoGrupo.getNome());
            stmt.setBoolean(3, true);

            stmt.execute();
        } catch (SQLException u) {
            System.out.println(u);
            throw new RuntimeException(u);
        }
    }

    public int Excluir(ProdutoGrupo ProdutoGrupo) {
        String sql = "UPDATE produto_grupo set status=0 where idgrupo = ?";
        int excluir = 0;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, ProdutoGrupo.getIdgrupo());
            excluir = stmt.executeUpdate();
            return excluir;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public int Ativar(ProdutoGrupo ProdutoGrupo) {
        String sql = "UPDATE produto_grupo set status=true where idgrupo = ?";
        int excluir = 0;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, ProdutoGrupo.getIdgrupo());
            excluir = stmt.executeUpdate();
            return excluir;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public int Alterar(ProdutoGrupo ProdutoGrupo) {
        String sql = "UPDATE produto_grupo set fksetor = ?, nome = ? "
                + "where idgrupo = ?";
        int alterar = 0;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, ProdutoGrupo.getFksetor());
            stmt.setString(2, ProdutoGrupo.getNome());
            stmt.setInt(3, ProdutoGrupo.getIdgrupo());
            alterar = stmt.executeUpdate();
            return alterar;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public ArrayList<String> LerProdutoGrupo(String target) {

        con = ConnectionFactory.getConnection();
        stmt = null;
        rs = null;

        ArrayList<String> Target = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT " + target + " FROM produto_grupo");
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
