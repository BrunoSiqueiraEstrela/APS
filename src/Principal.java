
import controller.Controle;
import java.sql.SQLException;
import model.dao.ClienteDAO;
import model.dao.FormaPagamentoDAO;
import model.dao.FuncionarioCargoDAO;
import model.dao.FuncionarioDAO;
import model.dao.ProdutoDAO;
import model.dao.ProdutoGrupoDAO;
import model.dao.ProdutoMarcaDAO;
import model.dao.ProdutoSetorDAO;
import model.dao.ProdutoSubGrupoDAO;
import model.dao.ResultadoordenacaoDAO;
import model.dao.VendaDAO;
import model.dao.VendaItemDAO;
import model.object.Cliente;
import model.object.FormaPagamento;
import model.object.Funcionario;
import model.object.FuncionarioCargo;
import model.object.Produto;
import model.object.ProdutoGrupo;
import model.object.ProdutoMarca;
import model.object.ProdutoSetor;
import model.object.ProdutoSubGrupo;
import model.object.Resultadoordenacao;
import model.object.Venda;
import model.object.VendaItem;
import sorts.*;
import views.UserInterface;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author DELTA
 */
public class Principal {

    public static void main(String args[]) throws ClassNotFoundException,
            SQLException {

        Cliente c = new Cliente();
        FormaPagamento fp = new FormaPagamento();
        Funcionario f = new Funcionario();
        FuncionarioCargo fc = new FuncionarioCargo();
        Produto p = new Produto();
        ProdutoGrupo pg = new ProdutoGrupo();
        ProdutoMarca pm = new ProdutoMarca();
        ProdutoSetor ps = new ProdutoSetor();
        ProdutoSubGrupo psg = new ProdutoSubGrupo();
        Venda v = new Venda();
        VendaItem vi = new VendaItem();
        Resultadoordenacao ro = new Resultadoordenacao();

        ClienteDAO clienteDao = new ClienteDAO();
        FormaPagamentoDAO formaPagamentoDao = new FormaPagamentoDAO();
        FuncionarioCargoDAO funcionarioCargoDAO = new FuncionarioCargoDAO();
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        ProdutoGrupoDAO produtoGrupoDAO = new ProdutoGrupoDAO();
        ProdutoMarcaDAO produtoMarcaDAO = new ProdutoMarcaDAO();
        ProdutoSetorDAO produtoSetorDAO = new ProdutoSetorDAO();
        ProdutoSubGrupoDAO produtoSubGrupoDAO = new ProdutoSubGrupoDAO();
        VendaDAO vendaDAO = new VendaDAO();
        VendaItemDAO vendaItemDAO = new VendaItemDAO();
        ResultadoordenacaoDAO resultadoordenacaoDAO = new ResultadoordenacaoDAO();

     
        UserInterface UI = new UserInterface(); 
        
        BubbleSort  b = new BubbleSort();
        QuickSort q = new QuickSort();
        SelectSort s = new SelectSort();
        CocktailSort ctail = new CocktailSort();
        
        Controle controle = new Controle(
                UI,
                b,q,s,ctail,
                c, fp, f, fc, p, pg, pm, ps, psg, v, vi,ro,
                clienteDao, formaPagamentoDao, funcionarioCargoDAO, funcionarioDAO, produtoDAO,
                produtoGrupoDAO, produtoMarcaDAO, produtoSetorDAO, produtoSubGrupoDAO, vendaDAO,
                vendaItemDAO,resultadoordenacaoDAO
        );
         UI.setTitle("Home");
        UI.setVisible(true);

    }
}
