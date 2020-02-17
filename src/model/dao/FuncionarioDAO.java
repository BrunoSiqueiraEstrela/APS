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
import model.object.Funcionario;

public class FuncionarioDAO {

    Connection con = ConnectionFactory.getConnection();
    PreparedStatement stmt;
    ResultSet rs;

    public List<Funcionario> Ler() {

        con = ConnectionFactory.getConnection();
        stmt = null;
        rs = null;

        List<Funcionario> funcionarios = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM funcionario");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Funcionario funcionario = new Funcionario();

                funcionario.setIdfuncionario(rs.getInt("Idfuncionario"));
                funcionario.setFkcargo(rs.getInt("Fkcargo"));
                funcionario.setNome(rs.getString("Nome"));
                funcionario.setDatanascimento(rs.getDate("Datanascimento"));
                funcionario.setCpf(rs.getString("Cpf"));
                funcionario.setRg(rs.getString("Rg"));
                funcionario.setSexo(rs.getString("Sexo"));
                funcionario.setEmail(rs.getString("Email"));
                funcionario.setTelefone(rs.getString("Telefone"));
                funcionario.setCelular(rs.getString("Celular"));
                funcionario.setDatacadastro(rs.getDate("Datacadastro"));
                funcionario.setStatus(rs.getBoolean("status"));
                funcionarios.add(funcionario);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 

        return funcionarios;

    }
    
    public List<Funcionario> LerStatus() {

        con = ConnectionFactory.getConnection();
        stmt = null;
        rs = null;

        List<Funcionario> funcionarios = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM funcionario WHERE status=true");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Funcionario funcionario = new Funcionario();

                funcionario.setIdfuncionario(rs.getInt("Idfuncionario"));
                funcionario.setFkcargo(rs.getInt("Fkcargo"));
                funcionario.setNome(rs.getString("Nome"));
                funcionario.setDatanascimento(rs.getDate("Datanascimento"));
                funcionario.setCpf(rs.getString("Cpf"));
                funcionario.setRg(rs.getString("Rg"));
                funcionario.setSexo(rs.getString("Sexo"));
                funcionario.setEmail(rs.getString("Email"));
                funcionario.setTelefone(rs.getString("Telefone"));
                funcionario.setCelular(rs.getString("Celular"));
                funcionario.setDatacadastro(rs.getDate("Datacadastro"));
                funcionario.setStatus(rs.getBoolean("status"));
                funcionarios.add(funcionario);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 

        return funcionarios;

    }

    public int RetornaId() {
        int valor = 0;
        con = ConnectionFactory.getConnection();
        stmt = null;
        rs = null;

        try {

            stmt = con.prepareStatement("SELECT idfuncionario from funcionario WHERE"
                    + " idfuncionario = (SELECT MAX(idfuncionario) FROM funcionario)");
            rs = stmt.executeQuery();
            if (rs.next()) {
                valor = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return valor;
    }

    public void Adiciona(Funcionario funcionario) {
        String sql = "INSERT INTO `bancoaps`.`funcionario` ("
                + "`Idfuncionario`, `fkcargo`, `nome`,"
                + " `datanascimento`, `cpf`,"
                + " `rg`, `sexo`,"
                + " `email`, `telefone`,"
                + " `Celular`, `Datacadastro`,"
                + " `status`)"
                + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        try {

            stmt = con.prepareStatement(sql);
            stmt.setInt(1, funcionario.getIdfuncionario());
            stmt.setInt(2, funcionario.getFkcargo());
            stmt.setString(3, funcionario.getNome());
            stmt.setDate(4,  funcionario.getDatanascimento());
            stmt.setString(5, funcionario.getCpf());
            stmt.setString(6, funcionario.getRg());
            stmt.setString(7, funcionario.getSexo());
            stmt.setString(8, funcionario.getEmail());
            stmt.setString(9, funcionario.getTelefone());
            stmt.setString(10, funcionario.getCelular());
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            
            stmt.setDate(11, date);
            stmt.setBoolean(12, true);

            stmt.execute();
        } catch (SQLException u) {
            System.out.println(u);
            throw new RuntimeException(u);
        }
    }

    public int Excluir(Funcionario funcionario) {
        String sql = "UPDATE funcionario SET status=false WHERE idfuncionario = ?";
        int excluir = 0;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, funcionario.getIdfuncionario());
            excluir = stmt.executeUpdate();
            return excluir;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }
        public int Ativar(Funcionario funcionario) {
        String sql = "UPDATE funcionario SET status=true WHERE idfuncionario = ?";
        int excluir = 0;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, funcionario.getIdfuncionario());
            excluir = stmt.executeUpdate();
            return excluir;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public int Alterar(Funcionario funcionario) {
        String sql = "UPDATE funcionario SET fkcargo=?, nome=?, datanascimento=?, cpf=?, rg=?, sexo=?, "
                + "email=?, telefone=?, celular=?  where idfuncionario=?";
        int alterar = 0;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, funcionario.getFkcargo());
            System.out.println( funcionario.getNome());
            stmt.setString(2, funcionario.getNome());
            stmt.setDate(3, (java.sql.Date) funcionario.getDatanascimento());
            stmt.setString(4, funcionario.getCpf());
            stmt.setString(5, funcionario.getRg());
            stmt.setString(6, funcionario.getSexo());
            stmt.setString(7, funcionario.getEmail());
            stmt.setString(8, funcionario.getTelefone());
            stmt.setString(9, funcionario.getCelular());
            stmt.setInt(10, funcionario.getIdfuncionario());
            alterar = stmt.executeUpdate();
            return alterar;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }
            public ArrayList<String> LerFuncionario(String target) {

        con = ConnectionFactory.getConnection();
        stmt = null;
        rs = null;

        ArrayList<String> Target = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT " + target + " FROM funcionario");
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
