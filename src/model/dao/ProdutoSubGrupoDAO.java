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

import model.object.ProdutoSubGrupo;

public class ProdutoSubGrupoDAO {

    Connection con = ConnectionFactory.getConnection();
    PreparedStatement stmt;
    ResultSet rs;

    public List<ProdutoSubGrupo> Ler() {

        con = ConnectionFactory.getConnection();
        stmt = null;
        rs = null;

        List<ProdutoSubGrupo> produtosubgrupos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM produto_subgrupo");
            rs = stmt.executeQuery();

            while (rs.next()) {

                ProdutoSubGrupo produtosubgrupo = new ProdutoSubGrupo();

                produtosubgrupo.setIdsubgrupo(rs.getInt("idsubgrupo"));
                produtosubgrupo.setFkgrupo(rs.getInt("fkgrupo"));
                produtosubgrupo.setNome(rs.getString("Nome"));
                produtosubgrupo.setStatus(rs.getBoolean("status"));
                produtosubgrupos.add(produtosubgrupo);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoSubGrupoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return produtosubgrupos;

    }

    public List<ProdutoSubGrupo> LerStatus() {

        con = ConnectionFactory.getConnection();
        stmt = null;
        rs = null;

        List<ProdutoSubGrupo> produtosubgrupos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM produto_subgrupo WHERE status=true");
            rs = stmt.executeQuery();

            while (rs.next()) {

                ProdutoSubGrupo produtosubgrupo = new ProdutoSubGrupo();

                produtosubgrupo.setIdsubgrupo(rs.getInt("idsubgrupo"));
                produtosubgrupo.setFkgrupo(rs.getInt("fkgrupo"));
                produtosubgrupo.setNome(rs.getString("Nome"));
                produtosubgrupo.setStatus(rs.getBoolean("status"));
                produtosubgrupos.add(produtosubgrupo);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoSubGrupoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return produtosubgrupos;

    }
    
    public List<ProdutoSubGrupo> LerFk(ProdutoGrupo produtoGrupo) {

        con = ConnectionFactory.getConnection();
        stmt = null;
        rs = null;

        List<ProdutoSubGrupo> produtosubgrupos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM produto_subgrupo where fkgrupo=?");
            stmt.setInt(1, produtoGrupo.getIdgrupo());
            rs = stmt.executeQuery();
            
            while (rs.next()) {

                ProdutoSubGrupo produtosubgrupo = new ProdutoSubGrupo();

                produtosubgrupo.setIdsubgrupo(rs.getInt("idsubgrupo"));
                produtosubgrupo.setFkgrupo(rs.getInt("fkgrupo"));
                produtosubgrupo.setNome(rs.getString("Nome"));
                produtosubgrupo.setStatus(rs.getBoolean("status"));
                produtosubgrupos.add(produtosubgrupo);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoSubGrupoDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.print(ex);
        }

        return produtosubgrupos;

    }

    public int RetornaId() {
        int valor = 0;
        con = ConnectionFactory.getConnection();
        stmt = null;
        rs = null;

        try {

            stmt = con.prepareStatement("SELECT idsubgrupo from produto_subgrupo WHERE"
                    + " idsubgrupo = (SELECT MAX(idsubgrupo) FROM produto_subgrupo)");
            rs = stmt.executeQuery();
            if (rs.next()) {
                valor = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return valor;
    }

    public void Adiciona(ProdutoSubGrupo ProdutoSubgrupo) {
        String sql = "INSERT INTO `bancoaps`.`produto_subgrupo` ("
                + "`fkgrupo`,`Nome`,`status`)"
                + " VALUES (?,?,?)";
        try {

            stmt = con.prepareStatement(sql);
            stmt.setInt(1, ProdutoSubgrupo.getFkgrupo());
            stmt.setString(2, ProdutoSubgrupo.getNome());
            stmt.setBoolean(3, true);

            stmt.execute();
        } catch (SQLException u) {
            System.out.println(u);
            throw new RuntimeException(u);
        }
    }

    public int Excluir(ProdutoSubGrupo ProdutoSubgrupo) {
        String sql = "UPDATE produto_subgrupo set status=0 where idsubgrupo = ?";
        int excluir = 0;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, ProdutoSubgrupo.getIdsubgrupo());
            excluir = stmt.executeUpdate();
            return excluir;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public int Ativar(ProdutoSubGrupo ProdutoSubgrupo) {
        String sql = "UPDATE produto_subgrupo set status=true where idsubgrupo = ?";
        int excluir = 0;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, ProdutoSubgrupo.getIdsubgrupo());
            excluir = stmt.executeUpdate();
            return excluir;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public int Alterar(ProdutoSubGrupo ProdutoSubgrupo) {
        String sql = "UPDATE produto_subgrupo set idsubgrupo = ?, Nome=?"
                + "where idsubgrupo = ?";
        int alterar = 0;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, ProdutoSubgrupo.getFkgrupo());
            stmt.setString(2, ProdutoSubgrupo.getNome());
            stmt.setInt(3, ProdutoSubgrupo.getIdsubgrupo());

            alterar = stmt.executeUpdate();
            return alterar;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }
            public ArrayList<String> LerProdutoSubGrupo(String target) {

        con = ConnectionFactory.getConnection();
        stmt = null;
        rs = null;

        ArrayList<String> Target = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT " + target + " FROM produto_subgrupo");
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
