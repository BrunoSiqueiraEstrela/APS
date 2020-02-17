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

import model.object.FuncionarioCargo;

public class FuncionarioCargoDAO {

    Connection con = ConnectionFactory.getConnection();
    PreparedStatement stmt;
    ResultSet rs;

    public List<FuncionarioCargo> Ler() {

        con = ConnectionFactory.getConnection();
        stmt = null;
        rs = null;

        List<FuncionarioCargo> FuncionarioCargos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM funcionario_cargo");
            rs = stmt.executeQuery();

            while (rs.next()) {

                FuncionarioCargo FuncionarioCargo = new FuncionarioCargo();

                FuncionarioCargo.setIdcargo(rs.getInt("idcargo"));
                FuncionarioCargo.setDescricao(rs.getString("descricao"));
                FuncionarioCargo.setStatus(rs.getBoolean("status"));
                FuncionarioCargos.add(FuncionarioCargo);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioCargoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return FuncionarioCargos;

    }

    public List<FuncionarioCargo> LerStatus() {

        con = ConnectionFactory.getConnection();
        stmt = null;
        rs = null;

        List<FuncionarioCargo> FuncionarioCargos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM funcionario_cargo WHERE status=true");
            rs = stmt.executeQuery();

            while (rs.next()) {

                FuncionarioCargo FuncionarioCargo = new FuncionarioCargo();

                FuncionarioCargo.setIdcargo(rs.getInt("idcargo"));
                FuncionarioCargo.setDescricao(rs.getString("descricao"));
                FuncionarioCargo.setStatus(rs.getBoolean("status"));
                FuncionarioCargos.add(FuncionarioCargo);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioCargoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return FuncionarioCargos;

    }

    public List<FuncionarioCargo> LerFk(FuncionarioCargo funcionarioCargo) {

        con = ConnectionFactory.getConnection();
        stmt = null;
        rs = null;

        List<FuncionarioCargo> FuncionarioCargos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM funcionario_cargo WHERE idcargo=? ");
            stmt.setInt(1, funcionarioCargo.getIdcargo());
            rs = stmt.executeQuery();

            while (rs.next()) {

                FuncionarioCargo FuncionarioCargo = new FuncionarioCargo();

                FuncionarioCargo.setIdcargo(rs.getInt("idcargo"));
                FuncionarioCargo.setDescricao(rs.getString("descricao"));
                FuncionarioCargo.setStatus(rs.getBoolean("status"));
                FuncionarioCargos.add(FuncionarioCargo);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioCargoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return FuncionarioCargos;

    }

    public int RetornaId() {
        int valor = 0;
        con = ConnectionFactory.getConnection();
        stmt = null;
        rs = null;

        try {

            stmt = con.prepareStatement("SELECT idcargo from funcionario_cargo WHERE"
                    + " idcargo = (SELECT MAX(idcargo) FROM funcionario_cargo)");
            rs = stmt.executeQuery();
            if (rs.next()) {
                valor = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return valor;
    }

    public void Adiciona(FuncionarioCargo FuncionarioCargo) {
        String sql = "INSERT INTO `bancoaps`.`funcionario_cargo` (`idcargo`, `Descricao`, `status`)"
                + " VALUES (?,?,?)";
        try {

            stmt = con.prepareStatement(sql);
            stmt.setInt(1, FuncionarioCargo.getIdcargo());
            stmt.setString(2, FuncionarioCargo.getDescricao());
            stmt.setBoolean(3, true);

            stmt.execute();
        } catch (SQLException u) {
            System.out.println(u);
            throw new RuntimeException(u);
        }
    }

    public int Excluir(FuncionarioCargo FuncionarioCargo) {
        String sql = "UPDATE funcionario_cargo set status=false where idcargo = ?";
        int excluir = 0;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, FuncionarioCargo.getIdcargo());
            excluir = stmt.executeUpdate();
            return excluir;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public int Ativar(FuncionarioCargo FuncionarioCargo) {
        String sql = "UPDATE funcionario_cargo set status=true where idcargo = ?";
        int excluir = 0;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, FuncionarioCargo.getIdcargo());
            excluir = stmt.executeUpdate();
            return excluir;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public int Alterar(FuncionarioCargo FuncionarioCargo) {
        String sql = "UPDATE funcionario_cargo set Descricao = ? " + "where idcargo = ?";
        int alterar = 0;
        try {

            stmt = con.prepareStatement(sql);
            stmt.setString(1, FuncionarioCargo.getDescricao());
            stmt.setInt(2, FuncionarioCargo.getIdcargo());

            alterar = stmt.executeUpdate();
            return alterar;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public ArrayList<String> LerFuncionarioCargo(String target) {

        con = ConnectionFactory.getConnection();
        stmt = null;
        rs = null;

        ArrayList<String> Target = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT " + target + " FROM funcionario_cargo");
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
