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
import model.object.Cliente;

public class ClienteDAO {

    Connection con = ConnectionFactory.getConnection();
    PreparedStatement stmt;
    ResultSet rs;

    public List<Cliente> Ler() {

        con = ConnectionFactory.getConnection();
        stmt = null;
        rs = null;

        List<Cliente> clientes = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM cliente");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Cliente cliente = new Cliente();

                cliente.setIdcliente(rs.getInt("idcliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setEmail(rs.getString("email"));
                cliente.setDatacadastro(rs.getDate("datacadastro"));
                cliente.setStatus(rs.getBoolean("status"));
                clientes.add(cliente);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return clientes;

    }

    public List<Cliente> LerStatus() {

        con = ConnectionFactory.getConnection();
        stmt = null;
        rs = null;

        List<Cliente> clientes = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM cliente WHERE status=true");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Cliente cliente = new Cliente();

                cliente.setIdcliente(rs.getInt("idcliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setEmail(rs.getString("email"));
                cliente.setDatacadastro(rs.getDate("datacadastro"));
                cliente.setStatus(rs.getBoolean("status"));
                clientes.add(cliente);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return clientes;

    }
    
//      cliente.setIdcliente(rs.getInt("idcliente"));
//                cliente.setNome(rs.getString("nome"));
//                cliente.setCpf(rs.getString("cpf"));
//                cliente.setEmail(rs.getString("email"));
//                cliente.setDatacadastro(rs.getDate("datacadastro"));
    
    public ArrayList<String> LerNome() {

        con = ConnectionFactory.getConnection();
        stmt = null;
        rs = null;

        ArrayList<String> Nome = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT nome FROM cliente");
            rs = stmt.executeQuery();

            while (rs.next()) {

                 Nome.add(rs.getString("nome"));
          
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Nome;

    }

    public int RetornaId() {
        int valor = 0;
        con = ConnectionFactory.getConnection();
        stmt = null;
        rs = null;

        try {

            stmt = con.prepareStatement("SELECT idcliente FROM cliente WHERE idcliente = (SELECT MAX(idcliente) FROM cliente)");
            rs = stmt.executeQuery();
            if (rs.next()) {
                valor = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return valor;
    }

    public void Adiciona(Cliente cliente) {
        String sql = "INSERT INTO `bancoaps`.`cliente` ("
                + "`nome`, `cpf`, `email`,"
                + " `datacadastro`, `status`)"
                + " VALUES (?,?,?,?,?)";
        try {

            stmt = con.prepareStatement(sql);

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getEmail());
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            stmt.setDate(4, date);
            stmt.setBoolean(5, true);

            stmt.execute();
        } catch (SQLException u) {
            System.out.println(u);
            throw new RuntimeException(u);
        }
    }

    public int Excluir(Cliente cliente) {
        String sql = "UPDATE cliente set status=false where idcliente = ?";
        int excluir = 0;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, cliente.getIdcliente());
            excluir = stmt.executeUpdate();
            return excluir;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public int Ativar(Cliente cliente) {
        String sql = "UPDATE cliente set status=true where idcliente = ?";
        int excluir = 0;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, cliente.getIdcliente());
            excluir = stmt.executeUpdate();
            return excluir;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }

    public int Alterar(Cliente cliente) {
        String sql = "UPDATE cliente set nome=?, cpf=?, email=? where idcliente=?";
        int alterar = 0;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getEmail());
            stmt.setInt(4, cliente.getIdcliente());

            alterar = stmt.executeUpdate();
            return alterar;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
    }
    
    public ArrayList<String> LerCliente(String target) {

        con = ConnectionFactory.getConnection();
        stmt = null;
        rs = null;

        ArrayList<String> Target = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT " + target + " FROM cliente");
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
