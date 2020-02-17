package controller;

import connection.ConnectionFactory;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import sorts.*;
import utilitarios.*;
import model.object.*;
import model.dao.*;
import views.UserInterface;

public class Controle {

    public int selecionado = 0;
    public int estado = 0;

    public UserInterface c_UI;

    public Cliente c_c;
    public FormaPagamento c_fp;
    public Funcionario c_f;
    public FuncionarioCargo c_fc;
    public Produto c_p;
    public ProdutoGrupo c_pg;
    public ProdutoMarca c_pm;
    public ProdutoSetor c_ps;
    public ProdutoSubGrupo c_psg;
    public Venda c_v;
    public VendaItem c_vi;
    public Resultadoordenacao c_ro;

    public ClienteDAO c_ClienteDAO;
    public FormaPagamentoDAO c_FormaPagamentoDAO;
    public FuncionarioDAO c_FuncionarioDAO;
    public FuncionarioCargoDAO c_FuncionarioCargoDAO;
    public ProdutoDAO c_ProdutoDAO;
    public ProdutoGrupoDAO c_ProdutoGrupoDAO;
    public ProdutoMarcaDAO c_ProdutoMarcaDAO;
    public ProdutoSetorDAO c_ProdutoSetorDAO;
    public ProdutoSubGrupoDAO c_ProdutoSubGrupoDAO;
    public VendaDAO c_VendaDAO;
    public VendaItemDAO c_VendaItemDAO;
    public ResultadoordenacaoDAO c_resultadoordenacaoDAO;

    public QuickSort c_QuickSort;
    public SelectSort c_SelectSort;
    public BubbleSort c_BubbleSort;
    public CocktailSort c_CocktailSort;

    public Controle(
            UserInterface UI,
            BubbleSort b,
            QuickSort q,
            SelectSort s,
            CocktailSort ctail,
            Cliente c,
            FormaPagamento fp,
            Funcionario f,
            FuncionarioCargo fc,
            Produto p,
            ProdutoGrupo pg,
            ProdutoMarca pm,
            ProdutoSetor ps,
            ProdutoSubGrupo psg,
            Venda v,
            VendaItem vi,
            Resultadoordenacao ro,
            ClienteDAO clientedao,
            FormaPagamentoDAO formaPagamentoDAO,
            FuncionarioCargoDAO funcionarioCargoDAO,
            FuncionarioDAO funcionarioDAO,
            ProdutoDAO produtoDAO,
            ProdutoGrupoDAO produtoGrupoDAO,
            ProdutoMarcaDAO produtoMarcaDAO,
            ProdutoSetorDAO produtoSetorDAO,
            ProdutoSubGrupoDAO produtoSubGrupoDAO,
            VendaDAO vendaDAO,
            VendaItemDAO vendaItemDAO,
            ResultadoordenacaoDAO resultadoordenacaoDAO
    )
            throws ClassNotFoundException, SQLException {
        c_UI = UI;

        c_QuickSort = q;
        c_SelectSort = s;
        c_BubbleSort = b;
        c_CocktailSort = ctail;

        c_c = c;
        c_fp = fp;
        c_f = f;
        c_fc = fc;
        c_p = p;
        c_pg = pg;
        c_pm = pm;
        c_ps = ps;
        c_psg = psg;
        c_v = v;
        c_vi = vi;
        c_ro = ro;

        c_ClienteDAO = clientedao;
        c_FormaPagamentoDAO = formaPagamentoDAO;
        c_FuncionarioDAO = funcionarioDAO;
        c_FuncionarioCargoDAO = funcionarioCargoDAO;
        c_ProdutoDAO = produtoDAO;
        c_ProdutoGrupoDAO = produtoGrupoDAO;
        c_ProdutoMarcaDAO = produtoMarcaDAO;
        c_ProdutoSetorDAO = produtoSetorDAO;
        c_ProdutoSubGrupoDAO = produtoSubGrupoDAO;
        c_VendaDAO = vendaDAO;
        c_VendaItemDAO = vendaItemDAO;
        c_resultadoordenacaoDAO = resultadoordenacaoDAO;

        //Adicionando Os listener nas interfaces
        c_UI.addNovoListener(new Novo());
        c_UI.addSalvarListener(new Salvar());
        c_UI.addRemoverListener(new Remover());
        c_UI.addModificarListener(new Modificar());
        c_UI.addLimparListener(new Limpar());
        c_UI.addCancelarListener(new Cancelar());
        c_UI.addRstatusListener(new Estatus());
        c_UI.addAtivarListener(new Ativar());
        c_UI.addSelecaoListener(new Selecao());
        c_UI.addSortListener(new Sort());
        c_UI.addResetListener(new Reset());
        c_UI.jTabela.addMouseListener(new PopulaCampos());
        c_UI.AddImprimirListener(new Imprimir());
        c_UI.jBcliente.addMouseListener(new Efeito());
        c_UI.jBformaPagamento.addMouseListener(new Efeito());
        c_UI.jBcargos.addMouseListener(new Efeito());
        c_UI.jBfuncionario.addMouseListener(new Efeito());
        c_UI.jBhome.addMouseListener(new Efeito());
        c_UI.jBmarca.addMouseListener(new Efeito());
        c_UI.jBproduto.addMouseListener(new Efeito());
        c_UI.jBsetor.addMouseListener(new Efeito());
        c_UI.jBsub.addMouseListener(new Efeito());
        c_UI.jBgrupo.addMouseListener(new Efeito());
        c_UI.jBreport.addMouseListener(new Efeito());

    }

    //Cliente = 1
    //Venda = X
    //formaPagamento = 3
    //funcionarioCargo = 4
    //produto = 5
    //produtoGrupo = 6
    //produtoMarca = 7
    //MprodutoSetor = 8
    //jMprodutoSubGrupo = 9 
    //jMfuncionario = 10
    //jMrelatorio = 12
    //venda = 13
    //vendaItem = 14
    Action LimparAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {

            Limpar();
            LimparCampos();
        }
    };

    //Muda o estado para inserção
    class Novo implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == c_UI.jBnovo) {
                estado = 2;
                Estado();

            }
        }

    }

    class Efeito extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {

            c_UI.jTabela.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "pressed");
            c_UI.jTabela.getActionMap().put("pressed", LimparAction);

            if (e.getSource() == c_UI.jBcliente) {
                setColor(c_UI.jBcliente);
                resetColor(c_UI.jBfuncionario);
                resetColor(c_UI.jBcargos);
                resetColor(c_UI.jBformaPagamento);
                resetColor(c_UI.jBhome);
                resetColor(c_UI.jBsub);
                resetColor(c_UI.jBreport);
                resetColor(c_UI.jBproduto);
                resetColor(c_UI.jBsetor);
                resetColor(c_UI.jBmarca);
                resetColor(c_UI.jBgrupo);
                c_UI.in_cliente.setOpaque(true);
                c_UI.in_cargo.setOpaque(false);
                c_UI.in_funcionario.setOpaque(false);
                c_UI.in_home.setOpaque(false);
                c_UI.in_sub.setOpaque(false);
                c_UI.in_forma.setOpaque(false);
                c_UI.in_report.setOpaque(false);
                c_UI.in_produto.setOpaque(false);
                c_UI.in_grupo.setOpaque(false);
                c_UI.in_marca.setOpaque(false);
                c_UI.in_setor.setOpaque(false);

                selecionado = 1;
                c_c.setIdcliente(null);
                c_c.setCpf(null);
                c_c.setDatacadastro(null);
                c_c.setEmail(null);
                c_c.setStatus(null);

                AtualizaTabela();
                CardLayout cl = (CardLayout) c_UI.jPrincipal.getLayout();
                cl.show(c_UI.jPrincipal, "jP2");

                CardLayout c2 = (CardLayout) c_UI.jPdados.getLayout();
                c2.show(c_UI.jPdados, "jPcliente");

                estado = 1;
                Estado();
                c_UI.setTitle("Cliente");

            }
            if (e.getSource() == c_UI.jBfuncionario) {
                resetColor(c_UI.jBcliente);
                setColor(c_UI.jBfuncionario);
                resetColor(c_UI.jBcargos);
                resetColor(c_UI.jBformaPagamento);
                resetColor(c_UI.jBhome);
                resetColor(c_UI.jBsub);
                resetColor(c_UI.jBreport);
                resetColor(c_UI.jBproduto);
                resetColor(c_UI.jBsetor);
                resetColor(c_UI.jBmarca);
                resetColor(c_UI.jBgrupo);
                c_UI.in_cliente.setOpaque(false);
                c_UI.in_cargo.setOpaque(false);
                c_UI.in_funcionario.setOpaque(true);
                c_UI.in_home.setOpaque(false);
                c_UI.in_sub.setOpaque(false);
                c_UI.in_forma.setOpaque(false);
                c_UI.in_report.setOpaque(false);
                c_UI.in_produto.setOpaque(false);
                c_UI.in_grupo.setOpaque(false);
                c_UI.in_marca.setOpaque(false);

                selecionado = 10;

                AtualizaTabela();
                ChavesFK();
                CardLayout cl = (CardLayout) c_UI.jPrincipal.getLayout();
                cl.show(c_UI.jPrincipal, "jP2");

                CardLayout c2 = (CardLayout) c_UI.jPdados.getLayout();
                c2.show(c_UI.jPdados, "jPfuncionario");

                estado = 1;
                Estado();
                c_UI.setTitle("Funcionario");
            }

            if (e.getSource() == c_UI.jBcargos) {
                resetColor(c_UI.jBcliente);
                resetColor(c_UI.jBfuncionario);
                setColor(c_UI.jBcargos);
                resetColor(c_UI.jBformaPagamento);
                resetColor(c_UI.jBhome);
                resetColor(c_UI.jBsub);
                resetColor(c_UI.jBreport);
                resetColor(c_UI.jBproduto);
                resetColor(c_UI.jBsetor);
                resetColor(c_UI.jBmarca);
                resetColor(c_UI.jBgrupo);
                c_UI.in_cliente.setOpaque(false);
                c_UI.in_cargo.setOpaque(true);
                c_UI.in_funcionario.setOpaque(false);
                c_UI.in_home.setOpaque(false);
                c_UI.in_sub.setOpaque(false);
                c_UI.in_forma.setOpaque(false);
                c_UI.in_report.setOpaque(false);
                c_UI.in_produto.setOpaque(false);
                c_UI.in_grupo.setOpaque(false);
                c_UI.in_marca.setOpaque(false);
                c_UI.in_setor.setOpaque(false);

                selecionado = 4;

                AtualizaTabela();
                CardLayout cl = (CardLayout) c_UI.jPrincipal.getLayout();
                cl.show(c_UI.jPrincipal, "jP2");

                CardLayout c2 = (CardLayout) c_UI.jPdados.getLayout();
                c2.show(c_UI.jPdados, "jPfuncionarioCargo");

                estado = 1;
                Estado();
                c_UI.setTitle("Cargos");

            }

            if (e.getSource() == c_UI.jBformaPagamento) {
                resetColor(c_UI.jBcliente);
                resetColor(c_UI.jBfuncionario);
                resetColor(c_UI.jBcargos);
                setColor(c_UI.jBformaPagamento);
                resetColor(c_UI.jBhome);
                resetColor(c_UI.jBsub);
                resetColor(c_UI.jBreport);
                resetColor(c_UI.jBproduto);
                resetColor(c_UI.jBsetor);
                resetColor(c_UI.jBmarca);
                resetColor(c_UI.jBgrupo);
                c_UI.in_cliente.setOpaque(false);
                c_UI.in_cargo.setOpaque(false);
                c_UI.in_funcionario.setOpaque(false);
                c_UI.in_home.setOpaque(false);
                c_UI.in_sub.setOpaque(false);
                c_UI.in_forma.setOpaque(true);
                c_UI.in_report.setOpaque(false);
                c_UI.in_produto.setOpaque(false);
                c_UI.in_grupo.setOpaque(false);
                c_UI.in_marca.setOpaque(false);
                c_UI.in_setor.setOpaque(false);

                selecionado = 3;

                c_fp.setIdformapagamento(null);
                c_fp.setDescricao(null);

                AtualizaTabela();
                CardLayout cl = (CardLayout) c_UI.jPrincipal.getLayout();
                cl.show(c_UI.jPrincipal, "jP2");

                CardLayout c2 = (CardLayout) c_UI.jPdados.getLayout();
                c2.show(c_UI.jPdados, "jPformaPagamento");

                c_UI.setTitle("Forma de Pagamento");
                estado = 1;
                Estado();

            }

            if (e.getSource() == c_UI.jBhome) {
                resetColor(c_UI.jBcliente);
                resetColor(c_UI.jBfuncionario);
                resetColor(c_UI.jBcargos);
                resetColor(c_UI.jBformaPagamento);
                setColor(c_UI.jBhome);
                resetColor(c_UI.jBsub);
                resetColor(c_UI.jBreport);
                resetColor(c_UI.jBproduto);
                resetColor(c_UI.jBsetor);
                resetColor(c_UI.jBmarca);
                resetColor(c_UI.jBgrupo);
                c_UI.in_cliente.setOpaque(false);
                c_UI.in_cargo.setOpaque(false);
                c_UI.in_funcionario.setOpaque(false);
                c_UI.in_home.setOpaque(true);
                c_UI.in_sub.setOpaque(false);
                c_UI.in_forma.setOpaque(false);
                c_UI.in_report.setOpaque(false);
                c_UI.in_produto.setOpaque(false);
                c_UI.in_grupo.setOpaque(false);
                c_UI.in_marca.setOpaque(false);
                c_UI.in_setor.setOpaque(false);

                CardLayout cl = (CardLayout) c_UI.jPrincipal.getLayout();
                cl.show(c_UI.jPrincipal, "jP1");
                c_UI.setTitle("Home");

            }

            if (e.getSource() == c_UI.jBsub) {
                resetColor(c_UI.jBcliente);
                resetColor(c_UI.jBfuncionario);
                resetColor(c_UI.jBcargos);
                resetColor(c_UI.jBformaPagamento);
                resetColor(c_UI.jBhome);
                setColor(c_UI.jBsub);
                resetColor(c_UI.jBreport);
                resetColor(c_UI.jBproduto);
                resetColor(c_UI.jBsetor);
                resetColor(c_UI.jBmarca);
                resetColor(c_UI.jBgrupo);
                c_UI.in_cliente.setOpaque(false);
                c_UI.in_cargo.setOpaque(false);
                c_UI.in_funcionario.setOpaque(false);
                c_UI.in_home.setOpaque(false);
                c_UI.in_sub.setOpaque(true);
                c_UI.in_forma.setOpaque(false);
                c_UI.in_report.setOpaque(false);
                c_UI.in_produto.setOpaque(false);
                c_UI.in_grupo.setOpaque(false);
                c_UI.in_marca.setOpaque(false);
                c_UI.in_setor.setOpaque(false);

                selecionado = 9;

                AtualizaTabela();
                ChavesFK();

                CardLayout c2 = (CardLayout) c_UI.jPrincipal.getLayout();
                c2.show(c_UI.jPrincipal, "jP2");

                CardLayout cl = (CardLayout) c_UI.jPdados.getLayout();
                cl.show(c_UI.jPdados, "jPprodutoSubGrupo");

                c_UI.setTitle("Sub Grupo");
                estado = 1;
                Estado();

            }

            if (e.getSource() == c_UI.jBreport) {
                resetColor(c_UI.jBcliente);
                resetColor(c_UI.jBfuncionario);
                resetColor(c_UI.jBcargos);
                resetColor(c_UI.jBformaPagamento);
                resetColor(c_UI.jBhome);
                resetColor(c_UI.jBsub);
                setColor(c_UI.jBreport);
                resetColor(c_UI.jBproduto);
                resetColor(c_UI.jBsetor);
                resetColor(c_UI.jBmarca);
                resetColor(c_UI.jBgrupo);
                c_UI.in_cliente.setOpaque(false);
                c_UI.in_cargo.setOpaque(false);
                c_UI.in_funcionario.setOpaque(false);
                c_UI.in_home.setOpaque(false);
                c_UI.in_sub.setOpaque(false);
                c_UI.in_forma.setOpaque(false);
                c_UI.in_report.setOpaque(true);
                c_UI.in_produto.setOpaque(false);
                c_UI.in_grupo.setOpaque(false);
                c_UI.in_marca.setOpaque(false);
                c_UI.in_setor.setOpaque(false);

                selecionado = 12;

                CardLayout cl = (CardLayout) c_UI.jPrincipal.getLayout();
                cl.show(c_UI.jPrincipal, "jP3");
                AtualizaTabela();
                c_UI.setTitle("Report");

            }

            if (e.getSource() == c_UI.jBproduto) {
                resetColor(c_UI.jBcliente);
                resetColor(c_UI.jBfuncionario);
                resetColor(c_UI.jBcargos);
                resetColor(c_UI.jBformaPagamento);
                resetColor(c_UI.jBhome);
                resetColor(c_UI.jBsub);
                resetColor(c_UI.jBreport);
                setColor(c_UI.jBproduto);
                resetColor(c_UI.jBsetor);
                resetColor(c_UI.jBmarca);
                resetColor(c_UI.jBgrupo);
                c_UI.in_cliente.setOpaque(false);
                c_UI.in_cargo.setOpaque(false);
                c_UI.in_funcionario.setOpaque(false);
                c_UI.in_home.setOpaque(false);
                c_UI.in_sub.setOpaque(false);
                c_UI.in_forma.setOpaque(false);
                c_UI.in_report.setOpaque(false);
                c_UI.in_produto.setOpaque(true);
                c_UI.in_grupo.setOpaque(false);
                c_UI.in_marca.setOpaque(false);
                c_UI.in_setor.setOpaque(false);

                CardLayout c2 = (CardLayout) c_UI.jPrincipal.getLayout();
                c2.show(c_UI.jPrincipal, "jP2");

                c_UI.jCBgrupoProduto.removeAllItems();
                c_UI.jCBsubGrupoProduto.removeAllItems();
                c_UI.jCBsetorProduto.removeAllItems();

                selecionado = 5;
                ChavesFK();
                AtualizaTabela();

                CardLayout cl = (CardLayout) c_UI.jPdados.getLayout();
                cl.show(c_UI.jPdados, "jPproduto");
                c_UI.setTitle("Produto");
                estado = 1;
                Estado();
                c_UI.setVisible(true);
            }

            if (e.getSource() == c_UI.jBsetor) {
                resetColor(c_UI.jBcliente);
                resetColor(c_UI.jBfuncionario);
                resetColor(c_UI.jBcargos);
                resetColor(c_UI.jBformaPagamento);
                resetColor(c_UI.jBhome);
                resetColor(c_UI.jBsub);
                resetColor(c_UI.jBreport);
                resetColor(c_UI.jBproduto);
                setColor(c_UI.jBsetor);
                resetColor(c_UI.jBmarca);
                resetColor(c_UI.jBgrupo);
                c_UI.in_cliente.setOpaque(false);
                c_UI.in_cargo.setOpaque(false);
                c_UI.in_funcionario.setOpaque(false);
                c_UI.in_home.setOpaque(false);
                c_UI.in_sub.setOpaque(false);
                c_UI.in_forma.setOpaque(false);
                c_UI.in_report.setOpaque(false);
                c_UI.in_produto.setOpaque(false);
                c_UI.in_grupo.setOpaque(false);
                c_UI.in_marca.setOpaque(false);
                c_UI.in_setor.setOpaque(true);

                selecionado = 8;

                CardLayout c2 = (CardLayout) c_UI.jPrincipal.getLayout();
                c2.show(c_UI.jPrincipal, "jP2");

                AtualizaTabela();
                ChavesFK();
                CardLayout cl = (CardLayout) c_UI.jPdados.getLayout();
                cl.show(c_UI.jPdados, "jPprodutoSetor");
                c_UI.setTitle("Setor");
                estado = 1;
                Estado();
                c_UI.setVisible(true);

            }

            if (e.getSource() == c_UI.jBmarca) {
                resetColor(c_UI.jBcliente);
                resetColor(c_UI.jBfuncionario);
                resetColor(c_UI.jBcargos);
                resetColor(c_UI.jBformaPagamento);
                resetColor(c_UI.jBhome);
                resetColor(c_UI.jBsub);
                resetColor(c_UI.jBreport);
                resetColor(c_UI.jBproduto);
                resetColor(c_UI.jBsetor);
                setColor(c_UI.jBmarca);
                resetColor(c_UI.jBgrupo);
                c_UI.in_cliente.setOpaque(false);
                c_UI.in_cargo.setOpaque(false);
                c_UI.in_funcionario.setOpaque(false);
                c_UI.in_home.setOpaque(false);
                c_UI.in_sub.setOpaque(false);
                c_UI.in_forma.setOpaque(false);
                c_UI.in_report.setOpaque(false);
                c_UI.in_produto.setOpaque(false);
                c_UI.in_grupo.setOpaque(false);
                c_UI.in_marca.setOpaque(true);
                c_UI.in_setor.setOpaque(false);

                CardLayout c2 = (CardLayout) c_UI.jPrincipal.getLayout();
                c2.show(c_UI.jPrincipal, "jP2");

                selecionado = 7;
                ChavesFK();
                AtualizaTabela();

                CardLayout cl = (CardLayout) c_UI.jPdados.getLayout();
                cl.show(c_UI.jPdados, "jPprodutoMarca");
                c_UI.setTitle("Marca");
                estado = 1;
                Estado();
                c_UI.setVisible(true);

            }

            if (e.getSource() == c_UI.jBgrupo) {
                resetColor(c_UI.jBcliente);
                resetColor(c_UI.jBfuncionario);
                resetColor(c_UI.jBcargos);
                resetColor(c_UI.jBformaPagamento);
                resetColor(c_UI.jBhome);
                resetColor(c_UI.jBsub);
                resetColor(c_UI.jBreport);
                resetColor(c_UI.jBproduto);
                resetColor(c_UI.jBsetor);
                resetColor(c_UI.jBmarca);
                setColor(c_UI.jBgrupo);
                c_UI.in_cliente.setOpaque(false);
                c_UI.in_cargo.setOpaque(false);
                c_UI.in_funcionario.setOpaque(false);
                c_UI.in_home.setOpaque(false);
                c_UI.in_sub.setOpaque(false);
                c_UI.in_forma.setOpaque(false);
                c_UI.in_report.setOpaque(false);
                c_UI.in_produto.setOpaque(false);
                c_UI.in_grupo.setOpaque(true);
                c_UI.in_marca.setOpaque(false);
                c_UI.in_setor.setOpaque(false);

                CardLayout c2 = (CardLayout) c_UI.jPrincipal.getLayout();
                c2.show(c_UI.jPrincipal, "jP2");

                selecionado = 6;

                AtualizaTabela();
                ChavesFK();
                CardLayout cl = (CardLayout) c_UI.jPdados.getLayout();
                cl.show(c_UI.jPdados, "jPprodutoGrupo");
                c_UI.setTitle("Grupo");
                estado = 1;
                Estado();
                c_UI.setVisible(true);

            }

        }

    }

    void setColor(JPanel panel) {
        panel.setBackground(new Color(0, 51, 102));
    }

    void resetColor(JPanel panel) {
        panel.setBackground(new Color(255, 140, 0));
    }

    //Da o commit para adcionar e modificar
    class Salvar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == c_UI.jBsalvar) {

                /*Tabela Cliente*/
                switch (selecionado) {
                    case 1:
                        if (c_UI.jTnomeCliente.getText().isEmpty()
                                | c_UI.jTcpfCliente.getText().isEmpty()
                                | c_UI.jTemailCliente.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Todos os campos precisam ser preenchido ");

                        } else if (estado == 2) {

                            try {
                                c_ClienteDAO.Adiciona(setCliente());
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            }

                        } else if (estado == 3) {

                            try {
                                c_ClienteDAO.Alterar(setCliente());
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            }

                        }
                        AtualizaTabela();
                        estado = 1;
                        Estado();
                        break;
                    case 3:
                        if (c_UI.jTidForma.getText().isEmpty() | c_UI.jTdescricaoForma.getText().isEmpty()) {

                            JOptionPane.showMessageDialog(null, "Todos os campos precisam ser preenchido ");

                        } else {

                            if (estado == 2) {

                                try {
                                    c_FormaPagamentoDAO.Adiciona(setFormaPagamento());
                                } catch (SQLException ex) {
                                    Logger.getLogger(Controle.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            }

                            if (estado == 3) {

                                try {
                                    c_FormaPagamentoDAO.Alterar(setFormaPagamento());
                                } catch (SQLException ex) {
                                    Logger.getLogger(Controle.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            }
                            AtualizaTabela();
                            estado = 1;
                            Estado();
                        }
                        break;
                    case 4:
                        if (c_UI.jTidCargo.getText().isEmpty()
                                | c_UI.jTdescricaoCargo.getText().isEmpty()) {

                            JOptionPane.showMessageDialog(null, "Todos os campos precisam ser preenchido ");

                        } else {

                            if (estado == 2) {

                                try {
                                    c_FuncionarioCargoDAO.Adiciona(setFuncionarioCargo());
                                } catch (SQLException ex) {
                                    Logger.getLogger(Controle.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            }

                            if (estado == 3) {

                                try {
                                    c_FuncionarioCargoDAO.Alterar(setFuncionarioCargo());
                                } catch (SQLException ex) {
                                    Logger.getLogger(Controle.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            }

                            AtualizaTabela();
                            estado = 1;
                            Estado();
                        }
                        break;
                    case 5:
                        if (c_UI.jTidProduto.getText().isEmpty() | c_UI.jTdescricaoProduto.getText().isEmpty()
                                | c_UI.jTvalorVendaProduto.getText().isEmpty() | c_UI.jTvalorCustoUnitarioProduto.getText().isEmpty()
                                | c_UI.jCBsetorProduto.getSelectedItem() == null | c_UI.jCBgrupoProduto.getSelectedItem() == null
                                | c_UI.jCBsubGrupoProduto.getSelectedItem() == null | c_UI.jCBmarcaProduto.getSelectedItem() == null) {

                            JOptionPane.showMessageDialog(null, "Todos os campos precisam ser preenchido ");

                        } else if (estado == 2) {

                            try {
                                c_ProdutoDAO.Adiciona(setProduto());
                            } catch (SQLException ex) {
                                Logger.getLogger(Controle.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            AtualizaTabela();
                            estado = 1;
                            Estado();

                        }
                        if (estado == 3) {

                            try {
                                c_ProdutoDAO.Alterar(setProduto());
                            } catch (SQLException ex) {
                                Logger.getLogger(Controle.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            AtualizaTabela();
                            estado = 1;
                            Estado();

                        }
                        break;
                    case 6:
                        if (c_UI.jTidGrupo.getText().isEmpty() | c_UI.jTnomeGrupo.getText().isEmpty()
                                | c_UI.jCBsetorGrupo.getSelectedItem() == null) {

                            JOptionPane.showMessageDialog(null, "Todos os campos precisam ser preenchido ");

                        } else {

                            if (estado == 2) {

                                try {
                                    c_ProdutoGrupoDAO.Adiciona(setProdutoGrupo());
                                } catch (SQLException ex) {
                                    Logger.getLogger(Controle.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            }

                            if (estado == 3) {

                                try {
                                    c_ProdutoGrupoDAO.Alterar(setProdutoGrupo());
                                } catch (SQLException ex) {
                                    Logger.getLogger(Controle.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            }

                            AtualizaTabela();
                            estado = 1;
                            Estado();
                        }
                        break;
                    case 7:
                        if (c_UI.jTidMarca.getText().isEmpty() | c_UI.jTnomeMarca.getText().isEmpty()) {

                            JOptionPane.showMessageDialog(null, "Todos os campos precisam ser preenchido ");

                        } else {
                            if (estado == 2) {

                                try {
                                    c_ProdutoMarcaDAO.Adiciona(setProdutoMarca());
                                } catch (SQLException ex) {
                                    Logger.getLogger(Controle.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            }
                            if (estado == 3) {

                                try {
                                    c_ProdutoMarcaDAO.Alterar(setProdutoMarca());
                                } catch (SQLException ex) {
                                    Logger.getLogger(Controle.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            }

                            AtualizaTabela();
                            estado = 1;
                            Estado();
                        }
                        break;
                    case 8:
                        if (c_UI.jTnomeSetor.getText().isEmpty()) {

                            JOptionPane.showMessageDialog(null, "Todos os campos precisam ser preenchido ");

                        } else {

                            if (estado == 2) {

                                try {
                                    c_ProdutoSetorDAO.Adiciona(setProdutoSetor());
                                } catch (SQLException ex) {
                                    Logger.getLogger(Controle.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            }
                            if (estado == 3) {

                                try {
                                    System.out.println("Alterar");
                                    c_ProdutoSetorDAO.Alterar(setProdutoSetor());
                                } catch (SQLException ex) {
                                    Logger.getLogger(Controle.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            }

                            AtualizaTabela();
                            estado = 1;
                            Estado();
                        }
                        break;
                    case 9:
                        if (c_UI.jTidSub.getText().isEmpty() | c_UI.jTnomeSub.getText().isEmpty()
                                | c_UI.jCBsub.getSelectedItem() == null) {

                            JOptionPane.showMessageDialog(null, "Todos os campos precisam ser preenchido ");

                        } else {

                            if (estado == 2) {

                                try {
                                    c_ProdutoSubGrupoDAO.Adiciona(setProdutoSubGrupo());
                                } catch (SQLException ex) {
                                    Logger.getLogger(Controle.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            }

                            if (estado == 3) {

                                try {
                                    c_ProdutoSubGrupoDAO.Alterar(setProdutoSubGrupo());
                                } catch (SQLException ex) {
                                    Logger.getLogger(Controle.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            }

                            AtualizaTabela();
                            estado = 1;
                            Estado();
                        }
                        break;
                    case 10:
                        if (c_UI.jTidFuncionario.getText().isEmpty() | c_UI.jTnomeFuncionario.getText().isEmpty()
                                | c_UI.jCBcargoFuncionario.getSelectedItem() == null | c_UI.jTcpfFuncionario.getText().isEmpty()
                                | c_UI.jTrgFuncionario.getText().isEmpty() | c_UI.jTsexoFuncionario.getText().isEmpty()
                                | c_UI.jTemailFuncionario.getText().isEmpty() | c_UI.jTtelefoneFuncionario.getText().isEmpty()
                                | c_UI.jTcelularFuncionario.getText().isEmpty()) {

                            JOptionPane.showMessageDialog(null, "Todos os campos precisam ser preenchido ");

                        } else {

                            if (estado == 2) {

                                try {
                                    c_FuncionarioDAO.Adiciona(setFuncionario());
                                } catch (SQLException ex) {
                                    Logger.getLogger(Controle.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            }

                            if (estado == 3) {

                                try {
                                    c_FuncionarioDAO.Alterar(setFuncionario());
                                } catch (SQLException ex) {
                                    Logger.getLogger(Controle.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            }
                            AtualizaTabela();
                            estado = 1;
                            Estado();
                        }
                        break;
                    default:
                        break;
                }

            }
        }
    }

    //Muda o status para Falso
    class Remover implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == c_UI.jBremover) {

                switch (selecionado) {
                    case 1:
                        try {
                            c_ClienteDAO.Excluir(setCliente());
                        } catch (SQLException ex) {
                            Logger.getLogger(Controle.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    case 3:
                        try {
                            c_FormaPagamentoDAO.Excluir(setFormaPagamento());
                        } catch (SQLException ex) {
                            Logger.getLogger(Controle.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    case 4:
                        try {
                            c_FuncionarioCargoDAO.Excluir(setFuncionarioCargo());
                        } catch (SQLException ex) {
                            Logger.getLogger(Controle.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    case 5:
                        try {
                            c_ProdutoDAO.Excluir(setProduto());
                        } catch (SQLException ex) {
                            Logger.getLogger(Controle.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    case 6:
                        try {
                            c_ProdutoGrupoDAO.Excluir(setProdutoGrupo());
                        } catch (SQLException ex) {
                            Logger.getLogger(Controle.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    case 7:
                        try {
                            c_ProdutoMarcaDAO.Excluir(setProdutoMarca());
                        } catch (SQLException ex) {
                            Logger.getLogger(Controle.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    case 8:
                        try {
                            c_ProdutoSetorDAO.Excluir(setProdutoSetor());
                        } catch (SQLException ex) {
                            Logger.getLogger(Controle.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    case 9:
                        try {
                            c_ProdutoSubGrupoDAO.Excluir(setProdutoSubGrupo());
                        } catch (SQLException ex) {
                            Logger.getLogger(Controle.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    case 10:
                        try {
                            c_FuncionarioDAO.Excluir(setFuncionario());
                        } catch (SQLException ex) {
                            Logger.getLogger(Controle.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    default:
                        break;
                }
                AtualizaTabela();

            }
        }

    }

    //Cancela e volta pra Estado Normal
    class Cancelar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == c_UI.jBcancelar) {

                LimparCampos();
                estado = 1;
                Estado();

            }
        }

    }

    //Muda o estado para Modificar caso uma linha esta selecionado
    class Modificar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            estado = 3;
            if (e.getSource() == c_UI.jBmodificar && c_UI.jTabela.getSelectedRow() != -1) {
                Estado();
            }
        }
    }

    //Ativar o status
    class Ativar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == c_UI.jBativar) {

                switch (selecionado) {
                    case 1:
                        try {
                            c_ClienteDAO.Ativar(setCliente());
                        } catch (SQLException ex) {
                            Logger.getLogger(Controle.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        AtualizaTabela();
                        break;
                    case 3:
                        c_fp.setIdformapagamento(Integer.parseInt(c_UI.jTidForma.getText()));
                        c_FormaPagamentoDAO.Ativar(c_fp);
                        AtualizaTabela();
                        break;
                    case 4:
                        c_fc.setIdcargo(Integer.parseInt(c_UI.jTidCargo.getText()));
                        c_FuncionarioCargoDAO.Ativar(c_fc);
                        AtualizaTabela();
                        break;
                    case 5:
                        c_p.setIdproduto(Integer.parseInt(c_UI.jTidProduto.getText()));
                        c_ProdutoDAO.Ativar(c_p);
                        AtualizaTabela();
                        break;
                    case 6:
                        c_pg.setIdgrupo(Integer.parseInt(c_UI.jTidGrupo.getText()));
                        c_ProdutoGrupoDAO.Ativar(c_pg);
                        AtualizaTabela();
                        break;
                    case 7:
                        c_pm.setIdmarca(Integer.parseInt(c_UI.jTidMarca.getText()));
                        c_ProdutoMarcaDAO.Ativar(c_pm);
                        AtualizaTabela();
                        break;
                    case 8:
                        c_ps.setIdsetor(Integer.parseInt(c_UI.jTidSetor.getText()));
                        c_ProdutoSetorDAO.Ativar(c_ps);
                        AtualizaTabela();
                        break;
                    case 9:
                        c_psg.setIdsubgrupo(Integer.parseInt(c_UI.jTidSub.getText()));
                        c_ProdutoSubGrupoDAO.Ativar(c_psg);
                        AtualizaTabela();
                        break;
                    case 10:
                        c_f.setIdfuncionario(Integer.parseInt(c_UI.jTidFuncionario.getText()));
                        c_FuncionarioDAO.Ativar(c_f);
                        AtualizaTabela();
                        break;
                    default:
                        break;
                }
            }
        }
    }

    //Limpa os campos
    class Limpar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            LimparCampos();

        }
    }

    //Exibir status true e false ou só true
    class Estatus implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("1");
            //so True
            switch (selecionado) {
                case 1:
                    if (c_UI.jRstatus.isSelected() == true) {

                        List<Cliente> dados = c_ClienteDAO.LerStatus();
                        ClienteTableModel ModeloTabela = new ClienteTableModel(dados);
                        c_UI.jTabela.setModel(ModeloTabela);

                    }   //False ou True
                    if (c_UI.jRstatus.isSelected() == false) {

                        AtualizaTabela();

                    }
                    break;
                case 3:
                    if (c_UI.jRstatus.isSelected() == true) {

                        List<FormaPagamento> dados = c_FormaPagamentoDAO.LerStatus();
                        Forma_PagamentoTableModel ModeloTabela = new Forma_PagamentoTableModel(dados);
                        c_UI.jTabela.setModel(ModeloTabela);

                    }   //False ou True
                    if (c_UI.jRstatus.isSelected() == false) {

                        AtualizaTabela();

                    }
                    break;
                case 4:
                    if (c_UI.jRstatus.isSelected() == true) {

                        List<FuncionarioCargo> dados = c_FuncionarioCargoDAO.LerStatus();
                        FuncionarioCargoTableModel ModeloTabela = new FuncionarioCargoTableModel(dados);
                        c_UI.jTabela.setModel(ModeloTabela);

                    }   //False ou True
                    if (c_UI.jRstatus.isSelected() == false) {

                        AtualizaTabela();

                    }
                    break;
                case 5:
                    if (c_UI.jRstatus.isSelected() == true) {

                        List<Produto> dados = c_ProdutoDAO.LerStatus();
                        ProdutoTableModel ModeloTabela = new ProdutoTableModel(dados);
                        c_UI.jTabela.setModel(ModeloTabela);

                    }   //False ou True
                    if (c_UI.jRstatus.isSelected() == false) {

                        AtualizaTabela();

                    }
                    break;
                case 6:
                    if (c_UI.jRstatus.isSelected() == true) {

                        List<ProdutoGrupo> dados = c_ProdutoGrupoDAO.LerStatus();
                        ProdutoGrupoTableModel ModeloTabela = new ProdutoGrupoTableModel(dados);
                        c_UI.jTabela.setModel(ModeloTabela);

                    }   //False ou True
                    if (c_UI.jRstatus.isSelected() == false) {

                        AtualizaTabela();

                    }
                    break;
                case 7:
                    if (c_UI.jRstatus.isSelected() == true) {

                        List<ProdutoMarca> dados = c_ProdutoMarcaDAO.LerStatus();
                        ProdutoMarcaTableModel ModeloTabela = new ProdutoMarcaTableModel(dados);
                        c_UI.jTabela.setModel(ModeloTabela);

                    }   //False ou True
                    if (c_UI.jRstatus.isSelected() == false) {

                        AtualizaTabela();

                    }
                    break;
                case 8:
                    if (c_UI.jRstatus.isSelected() == true) {

                        List<ProdutoSetor> dados = c_ProdutoSetorDAO.LerStatus();
                        ProdutoSetorTableModel ModeloTabela = new ProdutoSetorTableModel(dados);
                        c_UI.jTabela.setModel(ModeloTabela);

                    }   //False ou True
                    if (c_UI.jRstatus.isSelected() == false) {

                        AtualizaTabela();

                    } else if (selecionado == 9) {
                        if (c_UI.jRstatus.isSelected() == true) {

                            List<ProdutoSubGrupo> dados = c_ProdutoSubGrupoDAO.LerStatus();
                            ProdutoSubgrupoTableModel ModeloTabela = new ProdutoSubgrupoTableModel(dados);
                            c_UI.jTabela.setModel(ModeloTabela);

                        }
                        //False ou True
                        if (selecionado == 9 && c_UI.jRstatus.isSelected() == false) {

                            AtualizaTabela();

                        } else if (selecionado == 10) {

                            if (c_UI.jRstatus.isSelected() == true) {

                                List<Funcionario> dados = c_FuncionarioDAO.LerStatus();
                                FuncionarioTableModel ModeloTabela = new FuncionarioTableModel(dados);
                                c_UI.jTabela.setModel(ModeloTabela);

                            }
                            //False ou True
                            if (c_UI.jRstatus.isSelected() == false) {

                                AtualizaTabela();

                            }
                        }

                    }
                    break;
                default:
                    break;
            }

        }
    }

    //Popula campos do textField com as linhas da Tabela
    class PopulaCampos extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent evt) {

            if (estado == 1 && c_UI.jTabela.getSelectedRow() > -1) {

                c_UI.jBmodificar.setEnabled(true);
            }

            if (estado == 1) {

                switch (selecionado) {
                    case 1: {
                        String id = "" + c_UI.jTabela.getValueAt(c_UI.jTabela.getSelectedRow(), 0);
                        c_UI.jTidCliente.setText(id);
                        String nome = "" + c_UI.jTabela.getValueAt(c_UI.jTabela.getSelectedRow(), 1);
                        c_UI.jTnomeCliente.setText(nome);
                        String cpf = "" + c_UI.jTabela.getValueAt(c_UI.jTabela.getSelectedRow(), 2);
                        c_UI.jTcpfCliente.setText(cpf);
                        String email = "" + c_UI.jTabela.getValueAt(c_UI.jTabela.getSelectedRow(), 3);
                        c_UI.jTemailCliente.setText(email);
                        break;
                    }
                    case 3: {
                        String id = "" + c_UI.jTabela.getValueAt(c_UI.jTabela.getSelectedRow(), 0);
                        c_UI.jTidForma.setText(id);
                        String nome = "" + c_UI.jTabela.getValueAt(c_UI.jTabela.getSelectedRow(), 1);
                        c_UI.jTdescricaoForma.setText(nome);
                        break;
                    }
                    case 4: {
                        String id = "" + c_UI.jTabela.getValueAt(c_UI.jTabela.getSelectedRow(), 0);
                        c_UI.jTidCargo.setText(id);
                        String nome = "" + c_UI.jTabela.getValueAt(c_UI.jTabela.getSelectedRow(), 1);
                        c_UI.jTdescricaoCargo.setText(nome);
                        break;
                    }
                    case 5: {
                        String id = "" + c_UI.jTabela.getValueAt(c_UI.jTabela.getSelectedRow(), 0);
                        c_UI.jTidProduto.setText(id);
                        String nome = "" + c_UI.jTabela.getValueAt(c_UI.jTabela.getSelectedRow(), 1);
                        c_UI.jTdescricaoProduto.setText(nome);
                        String valorVenda = "" + c_UI.jTabela.getValueAt(c_UI.jTabela.getSelectedRow(), 2);
                        c_UI.jTvalorVendaProduto.setText(valorVenda);
                        String valorUni = "" + c_UI.jTabela.getValueAt(c_UI.jTabela.getSelectedRow(), 3);
                        c_UI.jTvalorCustoUnitarioProduto.setText(valorUni);
                        ProdutoSetor PS = new ProdutoSetor();
                        PS.setIdsetor((Integer) c_UI.jTabela.getValueAt(c_UI.jTabela.getSelectedRow(), 4));
                        c_UI.jCBsetorProduto.setSelectedItem(PS);
                        ProdutoGrupo PG = new ProdutoGrupo();
                        PG.setIdgrupo((Integer) c_UI.jTabela.getValueAt(c_UI.jTabela.getSelectedRow(), 5));
                        c_UI.jCBgrupoProduto.setSelectedItem(PG);
                        ProdutoSubGrupo PSG = new ProdutoSubGrupo();
                        PSG.setIdsubgrupo((Integer) c_UI.jTabela.getValueAt(c_UI.jTabela.getSelectedRow(), 6));
                        c_UI.jCBsubGrupoProduto.setSelectedItem(PSG);
                        ProdutoMarca PM = new ProdutoMarca();
                        PM.setIdmarca((Integer) c_UI.jTabela.getValueAt(c_UI.jTabela.getSelectedRow(), 7));
                        c_UI.jCBmarcaProduto.setSelectedItem(PM);
                        break;
                    }
                    case 6: {
                        String id = "" + c_UI.jTabela.getValueAt(c_UI.jTabela.getSelectedRow(), 0);
                        c_UI.jTidGrupo.setText(id);
                        ProdutoSetor PS = new ProdutoSetor();
                        PS.setIdsetor((Integer) c_UI.jTabela.getValueAt(c_UI.jTabela.getSelectedRow(), 1));
                        c_UI.jCBsetorGrupo.setSelectedItem(PS);
                        String Nome = "" + c_UI.jTabela.getValueAt(c_UI.jTabela.getSelectedRow(), 2);
                        c_UI.jTnomeGrupo.setText(Nome);
                        break;
                    }
                    case 7: {
                        String id = "" + c_UI.jTabela.getValueAt(c_UI.jTabela.getSelectedRow(), 0);
                        c_UI.jTidMarca.setText(id);
                        String nome = "" + c_UI.jTabela.getValueAt(c_UI.jTabela.getSelectedRow(), 1);
                        c_UI.jTnomeMarca.setText(nome);
                        break;
                    }
                    case 8: {
                        String id = "" + c_UI.jTabela.getValueAt(c_UI.jTabela.getSelectedRow(), 0);
                        c_UI.jTidSetor.setText(id);
                        String nome = "" + c_UI.jTabela.getValueAt(c_UI.jTabela.getSelectedRow(), 1);
                        c_UI.jTnomeSetor.setText(nome);
                        break;
                    }
                    case 9: {
                        String id = "" + c_UI.jTabela.getValueAt(c_UI.jTabela.getSelectedRow(), 0);
                        c_UI.jTidSub.setText(id);
                        ProdutoGrupo PG = new ProdutoGrupo();
                        PG.setIdgrupo((Integer) c_UI.jTabela.getValueAt(c_UI.jTabela.getSelectedRow(), 1));
                        c_UI.jCBsub.setSelectedItem(PG);
                        String nome = "" + c_UI.jTabela.getValueAt(c_UI.jTabela.getSelectedRow(), 2);
                        c_UI.jTnomeSub.setText(nome);
                        break;
                    }
                    case 10: {
                        String id = "" + c_UI.jTabela.getValueAt(c_UI.jTabela.getSelectedRow(), 0);
                        c_UI.jTidFuncionario.setText(id);
                        FuncionarioCargo FC = new FuncionarioCargo();
                        FC.setIdcargo((Integer) c_UI.jTabela.getValueAt(c_UI.jTabela.getSelectedRow(), 1));
                        c_UI.jCBcargoFuncionario.setSelectedItem(FC);
                        String nome = "" + c_UI.jTabela.getValueAt(c_UI.jTabela.getSelectedRow(), 2);
                        c_UI.jTnomeFuncionario.setText(nome);
                        Date data = (Date) c_UI.jTabela.getValueAt(c_UI.jTabela.getSelectedRow(), 3);
                        c_UI.jDateChoosernacimento.setDate(data);
                        String cpf = "" + c_UI.jTabela.getValueAt(c_UI.jTabela.getSelectedRow(), 4);
                        c_UI.jTcpfFuncionario.setText(cpf);
                        String rg = "" + c_UI.jTabela.getValueAt(c_UI.jTabela.getSelectedRow(), 5);
                        c_UI.jTrgFuncionario.setText(rg);
                        String sexo = "" + c_UI.jTabela.getValueAt(c_UI.jTabela.getSelectedRow(), 6);
                        c_UI.jTsexoFuncionario.setText(sexo);
                        String email = "" + c_UI.jTabela.getValueAt(c_UI.jTabela.getSelectedRow(), 7);
                        c_UI.jTemailFuncionario.setText(email);
                        String telefone = "" + c_UI.jTabela.getValueAt(c_UI.jTabela.getSelectedRow(), 8);
                        c_UI.jTtelefoneFuncionario.setText(telefone);
                        String celular = "" + c_UI.jTabela.getValueAt(c_UI.jTabela.getSelectedRow(), 9);
                        c_UI.jTcelularFuncionario.setText(celular);
                        break;
                    }
                    default:
                        break;
                }
            }
        }
    }

    //Imprimir o relatorio
    class Imprimir implements ActionListener {

        File desktopDir = new File(System.getProperty("user.home"), "Desktop");

        private Connection connection;

        public Imprimir() throws ClassNotFoundException {
            this.connection = ConnectionFactory.getConnection();
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                JasperPrint jp = JasperFillManager.fillReport("src/relatorios/Ordenacao.jasper", null, connection);
                JasperViewer jw = new JasperViewer(jp, false);
                jw.getDefaultCloseOperation();
                jw.setVisible(true);
                JasperExportManager.exportReportToPdfFile(jp, desktopDir.getPath() + "/Ordenacao.pdf");
            } catch (JRException e1) {
                e1.printStackTrace();
            }

        }
    }

    class Selecao implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == c_UI.jCBsetorProduto) {

                c_UI.jCBsubGrupoProduto.removeAllItems();

                ProdutoSetor FS = (ProdutoSetor) c_UI.jCBsetorProduto.getSelectedItem();

                for (ProdutoGrupo p : c_ProdutoGrupoDAO.LerFk(FS)) {

                    c_UI.jCBgrupoProduto.addItem(p);

                }

            }

            if (e.getSource() == c_UI.jCBgrupoProduto) {

                ProdutoGrupo FG = (ProdutoGrupo) c_UI.jCBgrupoProduto.getSelectedItem();

                for (ProdutoSubGrupo p : c_ProdutoSubGrupoDAO.LerFk(FG)) {

                    c_UI.jCBsubGrupoProduto.addItem(p);

                }

            }

        }

    }

    class Sort implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String Metodo, coluna, tabela;
            long tempo;
            ArrayList<String> dados;
            switch (selecionado) {
                case 1:
                    if (estado == 1) {
                        tabela = "Cliente";
                        Metodo = "Quicksort";

                        coluna = "nome";
                        dados = c_ClienteDAO.LerCliente(coluna);
                        tempo = c_QuickSort.quickSort(dados);
                        System.out.println(tempo);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "cpf";
                        dados = c_ClienteDAO.LerCliente(coluna);
                        tempo = c_QuickSort.quickSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "email";
                        dados = c_ClienteDAO.LerCliente(coluna);
                        tempo = c_QuickSort.quickSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "datacadastro";
                        dados = c_ClienteDAO.LerCliente(coluna);
                        tempo = c_QuickSort.quickSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "status";
                        dados = c_ClienteDAO.LerCliente(coluna);
                        tempo = c_QuickSort.quickSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        Metodo = "SelectSort";

                        coluna = "nome";
                        dados = c_ClienteDAO.LerCliente(coluna);
                        tempo = c_SelectSort.selectSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "cpf";
                        dados = c_ClienteDAO.LerCliente(coluna);
                        tempo = c_SelectSort.selectSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "email";
                        dados = c_ClienteDAO.LerCliente(coluna);
                        tempo = c_SelectSort.selectSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "datacadastro";
                        dados = c_ClienteDAO.LerCliente(coluna);
                        tempo = c_SelectSort.selectSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "status";
                        dados = c_ClienteDAO.LerCliente(coluna);
                        tempo = c_SelectSort.selectSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        Metodo = "BubbleSort";

                        coluna = "nome";
                        dados = c_ClienteDAO.LerCliente(coluna);
                        tempo = c_BubbleSort.bubbleSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "cpf";
                        dados = c_ClienteDAO.LerCliente(coluna);
                        tempo = c_BubbleSort.bubbleSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "email";
                        dados = c_ClienteDAO.LerCliente(coluna);
                        tempo = c_BubbleSort.bubbleSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "datacadastro";
                        dados = c_ClienteDAO.LerCliente(coluna);
                        tempo = c_BubbleSort.bubbleSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "status";
                        dados = c_ClienteDAO.LerCliente(coluna);
                        tempo = c_BubbleSort.bubbleSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        Metodo = "CocktailSort";

                        coluna = "nome";
                        dados = c_ClienteDAO.LerCliente(coluna);
                        tempo = c_CocktailSort.cocktailSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "cpf";
                        dados = c_ClienteDAO.LerCliente(coluna);
                        tempo = c_CocktailSort.cocktailSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "email";
                        dados = c_ClienteDAO.LerCliente(coluna);
                        tempo = c_CocktailSort.cocktailSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "datacadastro";
                        dados = c_ClienteDAO.LerCliente(coluna);
                        tempo = c_CocktailSort.cocktailSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "status";
                        dados = c_ClienteDAO.LerCliente(coluna);
                        tempo = c_CocktailSort.cocktailSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);
                    }
                    break;
                case 3:
                    if (estado == 1) {
                        tabela = "forma_pagamento";

                        Metodo = "Quicksort";

                        coluna = "descricao";
                        dados = c_FormaPagamentoDAO.LerFormaPagamento(coluna);
                        tempo = c_QuickSort.quickSort(dados);
                        System.out.println(tempo);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "status";
                        dados = c_FormaPagamentoDAO.LerFormaPagamento(coluna);
                        tempo = c_QuickSort.quickSort(dados);
                        System.out.println(tempo);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        Metodo = "SelectSort";

                        coluna = "descricao";
                        dados = c_FormaPagamentoDAO.LerFormaPagamento(coluna);
                        tempo = c_SelectSort.selectSort(dados);
                        System.out.println(tempo);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "status";
                        dados = c_FormaPagamentoDAO.LerFormaPagamento(coluna);
                        tempo = c_SelectSort.selectSort(dados);
                        System.out.println(tempo);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        Metodo = "BubbleSort";

                        coluna = "descricao";
                        dados = c_FormaPagamentoDAO.LerFormaPagamento(coluna);
                        tempo = c_BubbleSort.bubbleSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "status";
                        dados = c_FormaPagamentoDAO.LerFormaPagamento(coluna);
                        tempo = c_BubbleSort.bubbleSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        Metodo = "CocktailSort";

                        coluna = "descricao";
                        dados = c_FormaPagamentoDAO.LerFormaPagamento(coluna);
                        tempo = c_CocktailSort.cocktailSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "status";
                        dados = c_FormaPagamentoDAO.LerFormaPagamento(coluna);
                        tempo = c_CocktailSort.cocktailSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);
                    }
                    break;
                case 4:
                    if (estado == 1) {
                        tabela = "funcionario_cargo";

                        Metodo = "Quicksort";

                        coluna = "descricao";
                        dados = c_FuncionarioCargoDAO.LerFuncionarioCargo(coluna);
                        tempo = c_QuickSort.quickSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "status";
                        dados = c_FuncionarioCargoDAO.LerFuncionarioCargo(coluna);
                        tempo = c_QuickSort.quickSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        Metodo = "SelectSort";

                        coluna = "descricao";
                        dados = c_FuncionarioCargoDAO.LerFuncionarioCargo(coluna);
                        tempo = c_SelectSort.selectSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "status";
                        dados = c_FuncionarioCargoDAO.LerFuncionarioCargo(coluna);
                        tempo = c_SelectSort.selectSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        Metodo = "BubbleSort";

                        coluna = "descricao";
                        dados = c_FuncionarioCargoDAO.LerFuncionarioCargo(coluna);
                        tempo = c_BubbleSort.bubbleSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "status";
                        dados = c_FuncionarioCargoDAO.LerFuncionarioCargo(coluna);
                        tempo = c_BubbleSort.bubbleSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        Metodo = "CocktailSort";

                        coluna = "descricao";
                        dados = c_FuncionarioCargoDAO.LerFuncionarioCargo(coluna);
                        tempo = c_CocktailSort.cocktailSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "status";
                        dados = c_FuncionarioCargoDAO.LerFuncionarioCargo(coluna);
                        tempo = c_CocktailSort.cocktailSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);
                    }
                    break;
                case 5:
                    if (estado == 1) {
                        tabela = "Produto";

                        Metodo = "Quicksort";

                        coluna = "Descricaoproduto";
                        dados = c_ProdutoDAO.LerProduto(coluna);
                        tempo = c_QuickSort.quickSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Valorvenda";
                        dados = c_ProdutoDAO.LerProduto(coluna);
                        tempo = c_QuickSort.quickSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Valorcustounitario";
                        dados = c_ProdutoDAO.LerProduto(coluna);
                        tempo = c_QuickSort.quickSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Fksetor";
                        dados = c_ProdutoDAO.LerProduto(coluna);
                        tempo = c_QuickSort.quickSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Fksubgrupo";
                        dados = c_ProdutoDAO.LerProduto(coluna);
                        tempo = c_QuickSort.quickSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Fkmarca";
                        dados = c_ProdutoDAO.LerProduto(coluna);
                        tempo = c_QuickSort.quickSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Fkgrupo";
                        dados = c_ProdutoDAO.LerProduto(coluna);
                        tempo = c_QuickSort.quickSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "status";
                        dados = c_ProdutoDAO.LerProduto(coluna);
                        tempo = c_QuickSort.quickSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        Metodo = "SelectSort";

                        coluna = "Descricaoproduto";
                        dados = c_ProdutoDAO.LerProduto(coluna);
                        tempo = c_SelectSort.selectSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Valorvenda";
                        dados = c_ProdutoDAO.LerProduto(coluna);
                        tempo = c_SelectSort.selectSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Valorcustounitario";
                        dados = c_ProdutoDAO.LerProduto(coluna);
                        tempo = c_SelectSort.selectSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Fksetor";
                        dados = c_ProdutoDAO.LerProduto(coluna);
                        tempo = c_SelectSort.selectSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Fksubgrupo";
                        dados = c_ProdutoDAO.LerProduto(coluna);
                        tempo = c_SelectSort.selectSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Fkmarca";
                        dados = c_ProdutoDAO.LerProduto(coluna);
                        tempo = c_SelectSort.selectSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Fkgrupo";
                        dados = c_ProdutoDAO.LerProduto(coluna);
                        tempo = c_SelectSort.selectSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "status";
                        dados = c_ProdutoDAO.LerProduto(coluna);
                        tempo = c_SelectSort.selectSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        Metodo = "BubbleSort";

                        coluna = "Descricaoproduto";
                        dados = c_ProdutoDAO.LerProduto(coluna);
                        tempo = c_BubbleSort.bubbleSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Valorvenda";
                        dados = c_ProdutoDAO.LerProduto(coluna);
                        tempo = c_BubbleSort.bubbleSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Valorcustounitario";
                        dados = c_ProdutoDAO.LerProduto(coluna);
                        tempo = c_BubbleSort.bubbleSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Fksetor";
                        dados = c_ProdutoDAO.LerProduto(coluna);
                        tempo = c_BubbleSort.bubbleSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Fksubgrupo";
                        dados = c_ProdutoDAO.LerProduto(coluna);
                        tempo = c_BubbleSort.bubbleSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Fkmarca";
                        dados = c_ProdutoDAO.LerProduto(coluna);
                        tempo = c_BubbleSort.bubbleSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Fkgrupo";
                        dados = c_ProdutoDAO.LerProduto(coluna);
                        tempo = c_BubbleSort.bubbleSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "status";
                        dados = c_ProdutoDAO.LerProduto(coluna);
                        tempo = c_BubbleSort.bubbleSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        Metodo = "CocktailSort";

                        coluna = "Descricaoproduto";
                        dados = c_ProdutoDAO.LerProduto(coluna);
                        tempo = c_CocktailSort.cocktailSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Valorvenda";
                        dados = c_ProdutoDAO.LerProduto(coluna);
                        tempo = c_CocktailSort.cocktailSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Valorcustounitario";
                        dados = c_ProdutoDAO.LerProduto(coluna);
                        tempo = c_CocktailSort.cocktailSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Fksetor";
                        dados = c_ProdutoDAO.LerProduto(coluna);
                        tempo = c_CocktailSort.cocktailSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Fksubgrupo";
                        dados = c_ProdutoDAO.LerProduto(coluna);
                        tempo = c_CocktailSort.cocktailSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Fkmarca";
                        dados = c_ProdutoDAO.LerProduto(coluna);
                        tempo = c_CocktailSort.cocktailSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Fkgrupo";
                        dados = c_ProdutoDAO.LerProduto(coluna);
                        tempo = c_CocktailSort.cocktailSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "status";
                        dados = c_ProdutoDAO.LerProduto(coluna);
                        tempo = c_CocktailSort.cocktailSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);
                    }
                    break;
                case 6:
                    if (estado == 1) {
                        tabela = "produto_grupo";

                        Metodo = "Quicksort";

                        coluna = "fksetor";
                        dados = c_ProdutoGrupoDAO.LerProdutoGrupo(coluna);
                        tempo = c_QuickSort.quickSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "nome";
                        dados = c_ProdutoGrupoDAO.LerProdutoGrupo(coluna);
                        tempo = c_QuickSort.quickSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "status";
                        dados = c_ProdutoGrupoDAO.LerProdutoGrupo(coluna);
                        tempo = c_QuickSort.quickSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        Metodo = "SelectSort";

                        coluna = "fksetor";
                        dados = c_ProdutoGrupoDAO.LerProdutoGrupo(coluna);
                        tempo = c_SelectSort.selectSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "nome";
                        dados = c_ProdutoGrupoDAO.LerProdutoGrupo(coluna);
                        tempo = c_SelectSort.selectSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "status";
                        dados = c_ProdutoGrupoDAO.LerProdutoGrupo(coluna);
                        tempo = c_SelectSort.selectSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        Metodo = "BubbleSort";

                        coluna = "fksetor";
                        dados = c_ProdutoGrupoDAO.LerProdutoGrupo(coluna);
                        tempo = c_BubbleSort.bubbleSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "nome";
                        dados = c_ProdutoGrupoDAO.LerProdutoGrupo(coluna);
                        tempo = c_BubbleSort.bubbleSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "status";
                        dados = c_ProdutoGrupoDAO.LerProdutoGrupo(coluna);
                        tempo = c_BubbleSort.bubbleSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        Metodo = "CocktailSort";

                        coluna = "fksetor";
                        dados = c_ProdutoGrupoDAO.LerProdutoGrupo(coluna);
                        tempo = c_CocktailSort.cocktailSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "nome";
                        dados = c_ProdutoGrupoDAO.LerProdutoGrupo(coluna);
                        tempo = c_CocktailSort.cocktailSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "status";
                        dados = c_ProdutoGrupoDAO.LerProdutoGrupo(coluna);
                        tempo = c_CocktailSort.cocktailSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);
                    }
                    break;
                case 7:
                    if (estado == 1) {
                        tabela = "produto_marca";

                        Metodo = "Quicksort";

                        coluna = "Nome";
                        dados = c_ProdutoMarcaDAO.LerProdutoMarca(coluna);
                        tempo = c_QuickSort.quickSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "status";
                        dados = c_ProdutoMarcaDAO.LerProdutoMarca(coluna);
                        tempo = c_QuickSort.quickSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        Metodo = "SelectSort";

                        coluna = "Nome";
                        dados = c_ProdutoMarcaDAO.LerProdutoMarca(coluna);
                        tempo = c_SelectSort.selectSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "status";
                        dados = c_ProdutoMarcaDAO.LerProdutoMarca(coluna);
                        tempo = c_SelectSort.selectSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        Metodo = "BubbleSort";

                        coluna = "Nome";
                        dados = c_ProdutoMarcaDAO.LerProdutoMarca(coluna);
                        tempo = c_BubbleSort.bubbleSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "status";
                        dados = c_ProdutoMarcaDAO.LerProdutoMarca(coluna);
                        tempo = c_BubbleSort.bubbleSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        Metodo = "CocktailSort";

                        coluna = "Nome";
                        dados = c_ProdutoMarcaDAO.LerProdutoMarca(coluna);
                        tempo = c_CocktailSort.cocktailSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "status";
                        dados = c_ProdutoMarcaDAO.LerProdutoMarca(coluna);
                        tempo = c_CocktailSort.cocktailSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);
                    }
                    break;
                case 8:
                    if (estado == 1) {
                        tabela = "produto_setor";

                        Metodo = "Quicksort";

                        coluna = "Nome";
                        dados = c_ProdutoSetorDAO.LerProdutoSetor(coluna);
                        tempo = c_QuickSort.quickSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "status";
                        dados = c_ProdutoSetorDAO.LerProdutoSetor(coluna);
                        tempo = c_QuickSort.quickSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        Metodo = "SelectSort";

                        coluna = "Nome";
                        dados = c_ProdutoSetorDAO.LerProdutoSetor(coluna);
                        tempo = c_SelectSort.selectSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "status";
                        dados = c_ProdutoSetorDAO.LerProdutoSetor(coluna);
                        tempo = c_SelectSort.selectSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        Metodo = "BubbleSort";

                        coluna = "Nome";
                        dados = c_ProdutoSetorDAO.LerProdutoSetor(coluna);
                        tempo = c_BubbleSort.bubbleSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "status";
                        dados = c_ProdutoSetorDAO.LerProdutoSetor(coluna);
                        tempo = c_BubbleSort.bubbleSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        Metodo = "CocktailSort";

                        coluna = "Nome";
                        dados = c_ProdutoSetorDAO.LerProdutoSetor(coluna);
                        tempo = c_CocktailSort.cocktailSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "status";
                        dados = c_ProdutoSetorDAO.LerProdutoSetor(coluna);
                        tempo = c_CocktailSort.cocktailSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);
                    }
                    break;
                case 9:
                    if (estado == 1) {
                        tabela = "produto_subgrupo";

                        Metodo = "Quicksort";

                        coluna = "fkgrupo";
                        dados = c_ProdutoSubGrupoDAO.LerProdutoSubGrupo(coluna);
                        tempo = c_QuickSort.quickSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Nome";
                        dados = c_ProdutoSubGrupoDAO.LerProdutoSubGrupo(coluna);
                        tempo = c_QuickSort.quickSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "status";
                        dados = c_ProdutoSubGrupoDAO.LerProdutoSubGrupo(coluna);
                        tempo = c_QuickSort.quickSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        Metodo = "SelectSort";

                        coluna = "fkgrupo";
                        dados = c_ProdutoSubGrupoDAO.LerProdutoSubGrupo(coluna);
                        tempo = c_SelectSort.selectSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Nome";
                        dados = c_ProdutoSubGrupoDAO.LerProdutoSubGrupo(coluna);
                        tempo = c_SelectSort.selectSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "status";
                        dados = c_ProdutoSubGrupoDAO.LerProdutoSubGrupo(coluna);
                        tempo = c_SelectSort.selectSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        Metodo = "BubbleSort";

                        coluna = "fkgrupo";
                        dados = c_ProdutoSubGrupoDAO.LerProdutoSubGrupo(coluna);
                        tempo = c_BubbleSort.bubbleSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Nome";
                        dados = c_ProdutoSubGrupoDAO.LerProdutoSubGrupo(coluna);
                        tempo = c_BubbleSort.bubbleSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "status";
                        dados = c_ProdutoSubGrupoDAO.LerProdutoSubGrupo(coluna);
                        tempo = c_BubbleSort.bubbleSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        Metodo = "CocktailSort";

                        coluna = "fkgrupo";
                        dados = c_ProdutoSubGrupoDAO.LerProdutoSubGrupo(coluna);
                        tempo = c_CocktailSort.cocktailSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Nome";
                        dados = c_ProdutoSubGrupoDAO.LerProdutoSubGrupo(coluna);
                        tempo = c_CocktailSort.cocktailSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "status";
                        dados = c_ProdutoSubGrupoDAO.LerProdutoSubGrupo(coluna);
                        tempo = c_CocktailSort.cocktailSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);
                    }
                    break;
                case 10:
                    if (estado == 1) {
                        tabela = "funcionario";

                        Metodo = "Quicksort";

                        coluna = "Fkcargo";
                        dados = c_FuncionarioDAO.LerFuncionario(coluna);
                        tempo = c_QuickSort.quickSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Nome";
                        dados = c_FuncionarioDAO.LerFuncionario(coluna);
                        tempo = c_QuickSort.quickSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Datanascimento";
                        dados = c_FuncionarioDAO.LerFuncionario(coluna);
                        tempo = c_QuickSort.quickSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Cpf";
                        dados = c_FuncionarioDAO.LerFuncionario(coluna);
                        tempo = c_QuickSort.quickSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Rg";
                        dados = c_FuncionarioDAO.LerFuncionario(coluna);
                        tempo = c_QuickSort.quickSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "sexo";
                        dados = c_FuncionarioDAO.LerFuncionario(coluna);
                        tempo = c_QuickSort.quickSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Email";
                        dados = c_FuncionarioDAO.LerFuncionario(coluna);
                        tempo = c_QuickSort.quickSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Telefone";
                        dados = c_FuncionarioDAO.LerFuncionario(coluna);
                        tempo = c_QuickSort.quickSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Celular";
                        dados = c_FuncionarioDAO.LerFuncionario(coluna);
                        tempo = c_QuickSort.quickSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Datacadastro";
                        dados = c_FuncionarioDAO.LerFuncionario(coluna);
                        tempo = c_QuickSort.quickSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "status";
                        dados = c_FuncionarioDAO.LerFuncionario(coluna);
                        tempo = c_QuickSort.quickSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        Metodo = "SelectSort";

                        coluna = "Fkcargo";
                        dados = c_FuncionarioDAO.LerFuncionario(coluna);
                        tempo = c_SelectSort.selectSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Nome";
                        dados = c_FuncionarioDAO.LerFuncionario(coluna);
                        tempo = c_SelectSort.selectSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Datanascimento";
                        dados = c_FuncionarioDAO.LerFuncionario(coluna);
                        tempo = c_SelectSort.selectSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Cpf";
                        dados = c_FuncionarioDAO.LerFuncionario(coluna);
                        tempo = c_SelectSort.selectSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Rg";
                        dados = c_FuncionarioDAO.LerFuncionario(coluna);
                        tempo = c_SelectSort.selectSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Sexo";
                        dados = c_FuncionarioDAO.LerFuncionario(coluna);
                        tempo = c_SelectSort.selectSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Email";
                        dados = c_FuncionarioDAO.LerFuncionario(coluna);
                        tempo = c_SelectSort.selectSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Telefone";
                        dados = c_FuncionarioDAO.LerFuncionario(coluna);
                        tempo = c_SelectSort.selectSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Celular";
                        dados = c_FuncionarioDAO.LerFuncionario(coluna);
                        tempo = c_SelectSort.selectSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Datacadastro";
                        dados = c_FuncionarioDAO.LerFuncionario(coluna);
                        tempo = c_SelectSort.selectSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "status";
                        dados = c_FuncionarioDAO.LerFuncionario(coluna);
                        tempo = c_SelectSort.selectSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        Metodo = "BubbleSort";

                        coluna = "Fkcargo";
                        dados = c_FuncionarioDAO.LerFuncionario(coluna);
                        tempo = c_BubbleSort.bubbleSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Nome";
                        dados = c_FuncionarioDAO.LerFuncionario(coluna);
                        tempo = c_BubbleSort.bubbleSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Datanascimento";
                        dados = c_FuncionarioDAO.LerFuncionario(coluna);
                        tempo = c_BubbleSort.bubbleSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Cpf";
                        dados = c_FuncionarioDAO.LerFuncionario(coluna);
                        tempo = c_BubbleSort.bubbleSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Rg";
                        dados = c_FuncionarioDAO.LerFuncionario(coluna);
                        tempo = c_BubbleSort.bubbleSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Sexo";
                        dados = c_FuncionarioDAO.LerFuncionario(coluna);
                        tempo = c_BubbleSort.bubbleSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Email";
                        dados = c_FuncionarioDAO.LerFuncionario(coluna);
                        tempo = c_BubbleSort.bubbleSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Telefone";
                        dados = c_FuncionarioDAO.LerFuncionario(coluna);
                        tempo = c_BubbleSort.bubbleSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Celular";
                        dados = c_FuncionarioDAO.LerFuncionario(coluna);
                        tempo = c_BubbleSort.bubbleSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Datacadastro";
                        dados = c_FuncionarioDAO.LerFuncionario(coluna);
                        tempo = c_BubbleSort.bubbleSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "status";
                        dados = c_FuncionarioDAO.LerFuncionario(coluna);
                        tempo = c_BubbleSort.bubbleSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        Metodo = "CocktailSort";

                        coluna = "Fkcargo";
                        dados = c_FuncionarioDAO.LerFuncionario(coluna);
                        tempo = c_CocktailSort.cocktailSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Nome";
                        dados = c_FuncionarioDAO.LerFuncionario(coluna);
                        tempo = c_CocktailSort.cocktailSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Datanascimento";
                        dados = c_FuncionarioDAO.LerFuncionario(coluna);
                        tempo = c_CocktailSort.cocktailSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Cpf";
                        dados = c_FuncionarioDAO.LerFuncionario(coluna);
                        tempo = c_CocktailSort.cocktailSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Rg";
                        dados = c_FuncionarioDAO.LerFuncionario(coluna);
                        tempo = c_CocktailSort.cocktailSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Sexo";
                        dados = c_FuncionarioDAO.LerFuncionario(coluna);
                        tempo = c_CocktailSort.cocktailSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Email";
                        dados = c_FuncionarioDAO.LerFuncionario(coluna);
                        tempo = c_CocktailSort.cocktailSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Telefone";
                        dados = c_FuncionarioDAO.LerFuncionario(coluna);
                        tempo = c_CocktailSort.cocktailSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Celular";
                        dados = c_FuncionarioDAO.LerFuncionario(coluna);
                        tempo = c_CocktailSort.cocktailSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "Datacadastro";
                        dados = c_FuncionarioDAO.LerFuncionario(coluna);
                        tempo = c_CocktailSort.cocktailSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);

                        coluna = "status";
                        dados = c_FuncionarioDAO.LerFuncionario(coluna);
                        tempo = c_CocktailSort.cocktailSort(dados);
                        c_resultadoordenacaoDAO.AdicionaRelatorio(Metodo, coluna, tabela, dados.size(), tempo);
                    }
                    break;
                default:
                    break;
            }

        }

    }

    class Reset implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            c_resultadoordenacaoDAO.Delete();
            List<Resultadoordenacao> dados = c_resultadoordenacaoDAO.Ler();

            ResultadoordenacaoTableModel ModeloTabela = new ResultadoordenacaoTableModel(dados);
            c_UI.jTabelaResultado.setModel(ModeloTabela);
        }

    }

    public void Limpar() {

        c_UI.jTabela.getSelectionModel().clearSelection();
        c_UI.jBmodificar.setEnabled(false);

    }

    //Atualiza tabela
    public void AtualizaTabela() {
        switch (selecionado) {
            case 1: {
                List<Cliente> dados = c_ClienteDAO.Ler();
                ClienteTableModel ModeloTabela = new ClienteTableModel(dados);
                c_UI.jTabela.setModel(ModeloTabela);
                break;
            } //        else if (selecionado == 2) {
            case 3: {
                List<FormaPagamento> dados = c_FormaPagamentoDAO.Ler();
                Forma_PagamentoTableModel ModeloTabela = new Forma_PagamentoTableModel(dados);
                c_UI.jTabela.setModel(ModeloTabela);
                break;
            }
            case 4: {
                List<FuncionarioCargo> dados = c_FuncionarioCargoDAO.Ler();
                FuncionarioCargoTableModel ModeloTabela = new FuncionarioCargoTableModel(dados);
                c_UI.jTabela.setModel(ModeloTabela);
                break;
            }
            case 5: {
                List<Produto> dados = c_ProdutoDAO.Ler();
                ProdutoTableModel ModeloTabela = new ProdutoTableModel(dados);
                c_UI.jTabela.setModel(ModeloTabela);
                break;
            }
            case 6: {
                List<ProdutoGrupo> dados = c_ProdutoGrupoDAO.Ler();
                ProdutoGrupoTableModel ModeloTabela = new ProdutoGrupoTableModel(dados);
                c_UI.jTabela.setModel(ModeloTabela);
                break;
            }
            case 7: {
                List<ProdutoMarca> dados = c_ProdutoMarcaDAO.Ler();
                ProdutoMarcaTableModel ModeloTabela = new ProdutoMarcaTableModel(dados);
                c_UI.jTabela.setModel(ModeloTabela);
                break;
            }
            case 8: {
                List<ProdutoSetor> dados = c_ProdutoSetorDAO.Ler();
                ProdutoSetorTableModel ModeloTabela = new ProdutoSetorTableModel(dados);
                c_UI.jTabela.setModel(ModeloTabela);
                break;
            }
            case 9: {
                List<ProdutoSubGrupo> dados = c_ProdutoSubGrupoDAO.Ler();
                ProdutoSubgrupoTableModel ModeloTabela = new ProdutoSubgrupoTableModel(dados);
                c_UI.jTabela.setModel(ModeloTabela);
                break;
            }
            case 10: {
                List<Funcionario> dados = c_FuncionarioDAO.Ler();
                FuncionarioTableModel ModeloTabela = new FuncionarioTableModel(dados);
                c_UI.jTabela.setModel(ModeloTabela);
                break;
            }
            case 12: {
                List<Resultadoordenacao> dados = c_resultadoordenacaoDAO.Ler();

                ResultadoordenacaoTableModel ModeloTabela = new ResultadoordenacaoTableModel(dados);
                c_UI.jTabelaResultado.setModel(ModeloTabela);
                break;
            }
            default:
                break;
        }
    }

    //Limpa os campos TextField
    public void LimparCampos() {
        switch (selecionado) {
            case 1:
                if (estado == 1) {
                    c_UI.jTidCliente.setText("");
                    c_UI.jTnomeCliente.setText("");
                    c_UI.jTcpfCliente.setText("");
                    c_UI.jTemailCliente.setText("");
                }
                if (estado == 2) {

                    c_UI.jTnomeCliente.setText("");
                    c_UI.jTcpfCliente.setText("");
                    c_UI.jTemailCliente.setText("");
                }
                if (estado == 3) {

                    c_UI.jTnomeCliente.setText("");
                    c_UI.jTcpfCliente.setText("");
                    c_UI.jTemailCliente.setText("");
                }
                break;
            case 3:
                if (estado == 1) {

                    c_UI.jTdescricaoForma.setText("");

                }
                if (estado == 2) {

                    c_UI.jTdescricaoForma.setText("");
                }
                if (estado == 3) {

                    c_UI.jTdescricaoForma.setText("");

                }
                break;
            case 4:
                if (estado == 1) {

                    c_UI.jTdescricaoCargo.setText("");

                }
                if (estado == 2) {

                    c_UI.jTdescricaoCargo.setText("");
                }
                if (estado == 3) {

                    c_UI.jTdescricaoCargo.setText("");

                }
                break;
            case 5:
                if (estado == 1) {

                    c_UI.jTdescricaoProduto.setText("");
                    c_UI.jTvalorVendaProduto.setText("");
                    c_UI.jTvalorCustoUnitarioProduto.setText("");
                    c_UI.jCBsetorProduto.setSelectedItem("");
                    c_UI.jCBgrupoProduto.setSelectedItem("");
                    c_UI.jCBsubGrupoProduto.setSelectedItem("");
                    c_UI.jCBmarcaProduto.setSelectedItem("");

                }
                if (estado == 2) {

                    c_UI.jTdescricaoProduto.setText("");
                    c_UI.jTvalorVendaProduto.setText("");
                    c_UI.jTvalorCustoUnitarioProduto.setText("");
                    c_UI.jCBsetorProduto.setSelectedItem("");
                    c_UI.jCBgrupoProduto.setSelectedItem("");
                    c_UI.jCBsubGrupoProduto.setSelectedItem("");
                    c_UI.jCBmarcaProduto.setSelectedItem("");
                }
                if (estado == 3) {

                    c_UI.jTdescricaoProduto.setText("");
                    c_UI.jTvalorVendaProduto.setText("");
                    c_UI.jTvalorCustoUnitarioProduto.setText("");
                    c_UI.jCBsetorProduto.setSelectedItem("");
                    c_UI.jCBgrupoProduto.setSelectedItem("");
                    c_UI.jCBsubGrupoProduto.setSelectedItem("");
                    c_UI.jCBmarcaProduto.setSelectedItem("");

                }
                break;
            case 6:
                if (estado == 1) {

                    c_UI.jTnomeGrupo.setText("");
                    c_UI.jCBsetorGrupo.setSelectedItem(null);

                }
                if (estado == 2) {

                    c_UI.jTnomeGrupo.setText("");
                    c_UI.jCBsetorGrupo.setSelectedItem(null);

                }
                if (estado == 3) {

                    c_UI.jTnomeGrupo.setText("");
                    c_UI.jCBsetorGrupo.setSelectedItem(null);

                }
                break;
            case 7:
                if (estado == 1) {

                    c_UI.jTnomeMarca.setText("");

                }
                if (estado == 2) {

                    c_UI.jTnomeMarca.setText("");

                }
                if (estado == 3) {

                    c_UI.jTnomeMarca.setText("");

                }
                break;
            case 8:
                if (estado == 1) {
                    c_UI.jTidSetor.setText("");
                    c_UI.jTnomeSetor.setText("");

                }
                if (estado == 2) {

                    c_UI.jTnomeSetor.setText("");

                }
                if (estado == 3) {

                    c_UI.jTnomeSetor.setText("");

                }
                break;
            case 9:
                if (estado == 1) {

                    c_UI.jTnomeSub.setText("");
                    c_UI.jCBgrupoProduto.setSelectedItem("");

                }
                if (estado == 2) {

                    c_UI.jTnomeSub.setText("");
                    c_UI.jCBgrupoProduto.setSelectedItem("");

                }
                if (estado == 3) {

                    c_UI.jTnomeSub.setText("");
                    c_UI.jCBgrupoProduto.setSelectedItem("");

                }
                break;
            case 10:
                if (estado == 1) {
                    c_UI.jTidFuncionario.setText("");
                    c_UI.jTnomeFuncionario.setText("");
                    c_UI.jCBcargoFuncionario.setSelectedItem(null);
                    c_UI.jDateChoosernacimento.setDate(null);
                    c_UI.jTcpfFuncionario.setText("");
                    c_UI.jTrgFuncionario.setText("");
                    c_UI.jTsexoFuncionario.setText("");
                    c_UI.jTemailFuncionario.setText("");
                    c_UI.jTtelefoneFuncionario.setText("");
                    c_UI.jTcelularFuncionario.setText("");

                }
                if (estado == 2) {
                    c_UI.jDateChoosernacimento.setEnabled(false);
                    c_UI.jTnomeFuncionario.setText("");

                    c_UI.jTcpfFuncionario.setText("");
                    c_UI.jTrgFuncionario.setText("");
                    c_UI.jTsexoFuncionario.setText("");
                    c_UI.jTemailFuncionario.setText("");
                    c_UI.jTtelefoneFuncionario.setText("");
                    c_UI.jTcelularFuncionario.setText("");

                }
                if (estado == 3) {
                    c_UI.jDateChoosernacimento.setEnabled(false);
                    c_UI.jTnomeFuncionario.setText("");

                    c_UI.jTcpfFuncionario.setText("");
                    c_UI.jTrgFuncionario.setText("");
                    c_UI.jTsexoFuncionario.setText("");
                    c_UI.jTemailFuncionario.setText("");
                    c_UI.jTtelefoneFuncionario.setText("");
                    c_UI.jTcelularFuncionario.setText("");

                }
                break;
            default:
                break;
        }
    }

    //EDITABLE recebe int i para verificar se sera Editavel ou não| 1 = true | 0 = false |
    public void Editavel(int i) {

        if (selecionado == 1) {
            if (i == 1) {

                c_UI.jTnomeCliente.setEditable(true);
                c_UI.jTcpfCliente.setEditable(true);
                c_UI.jTemailCliente.setEditable(true);
            }
            if (i == 0) {

                c_UI.jTnomeCliente.setEditable(false);
                c_UI.jTcpfCliente.setEditable(false);
                c_UI.jTemailCliente.setEditable(false);

            }

        }

        /*Tabela Venda*/
        if (selecionado == 3) {
            if (i == 1) {
                c_UI.jTdescricaoForma.setEditable(true);
            }
            if (i == 0) {
                c_UI.jTdescricaoForma.setEditable(false);

            }
        }

        if (selecionado == 4) {
            if (i == 1) {
                c_UI.jTdescricaoCargo.setEditable(true);
            }
            if (i == 0) {
                c_UI.jTdescricaoCargo.setEditable(false);

            }
        }

        if (selecionado == 5) {
            if (i == 1) {

                c_UI.jTdescricaoProduto.setEditable(true);
                c_UI.jTvalorVendaProduto.setEditable(true);
                c_UI.jTvalorCustoUnitarioProduto.setEditable(true);
                c_UI.jCBsetorProduto.setEnabled(true);
                c_UI.jCBgrupoProduto.setEnabled(true);
                c_UI.jCBsubGrupoProduto.setEnabled(true);
                c_UI.jCBmarcaProduto.setEnabled(true);
            }
            if (i == 0) {

                c_UI.jTdescricaoProduto.setEditable(false);
                c_UI.jTvalorVendaProduto.setEditable(false);
                c_UI.jTvalorCustoUnitarioProduto.setEditable(false);
                c_UI.jCBsetorProduto.setEnabled(false);
                c_UI.jCBgrupoProduto.setEnabled(false);
                c_UI.jCBsubGrupoProduto.setEnabled(false);
                c_UI.jCBmarcaProduto.setEnabled(false);
            }
        }

        if (selecionado == 6) {
            if (i == 1) {

                c_UI.jTnomeGrupo.setEditable(true);
                c_UI.jCBsetorGrupo.setEnabled(true);
            }
            if (i == 0) {

                c_UI.jTnomeGrupo.setEditable(false);
                c_UI.jCBsetorGrupo.setEnabled(false);
            }
        }

        if (selecionado == 7) {
            if (i == 1) {

                c_UI.jTnomeMarca.setEditable(true);
            }
            if (i == 0) {

                c_UI.jTnomeMarca.setEditable(false);
            }
        }

        if (selecionado == 8) {
            if (i == 1) {

                c_UI.jTnomeSetor.setEditable(true);
            }
            if (i == 0) {

                c_UI.jTnomeSetor.setEditable(false);
            }
        }

        if (selecionado == 9) {
            if (i == 1) {

                c_UI.jTnomeSub.setEditable(true);
                c_UI.jCBsubGrupoProduto.setEnabled(true);

            }
            if (i == 0) {

                c_UI.jTnomeSub.setEditable(false);
                c_UI.jCBsubGrupoProduto.setEnabled(false);

            }
        }

        if (selecionado == 10) {
            if (i == 1) {

                c_UI.jTnomeFuncionario.setEditable(true);
                c_UI.jCBcargoFuncionario.setEnabled(true);
                c_UI.jDateChoosernacimento.setEnabled(true);
                c_UI.jTcpfFuncionario.setEditable(true);
                c_UI.jTrgFuncionario.setEditable(true);
                c_UI.jTsexoFuncionario.setEditable(true);
                c_UI.jTemailFuncionario.setEditable(true);
                c_UI.jTtelefoneFuncionario.setEditable(true);
                c_UI.jTcelularFuncionario.setEditable(true);

            }
            if (i == 0) {

                c_UI.jTnomeFuncionario.setEditable(false);
                c_UI.jCBcargoFuncionario.setEnabled(false);
                c_UI.jDateChoosernacimento.setEnabled(false);
                c_UI.jTcpfFuncionario.setEditable(false);
                c_UI.jTrgFuncionario.setEditable(false);
                c_UI.jTsexoFuncionario.setEditable(false);
                c_UI.jTemailFuncionario.setEditable(false);
                c_UI.jTtelefoneFuncionario.setEditable(false);
                c_UI.jTcelularFuncionario.setEditable(false);

            }
        }

    }

    //Insere os valores do combo Box
    public void ChavesFK() {

        if (selecionado == 5) {

            for (ProdutoSetor p : c_ProdutoSetorDAO.Ler()) {
                c_UI.jCBsetorProduto.addItem(p);
            }

            for (ProdutoMarca p : c_ProdutoMarcaDAO.Ler()) {
                c_UI.jCBmarcaProduto.addItem(p);
            }

        }

        if (selecionado == 6) {

            for (ProdutoSetor p : c_ProdutoSetorDAO.Ler()) {
                System.out.println(p);
                c_UI.jCBsetorGrupo.addItem(p);

            }
        }

        if (selecionado == 9) {

            for (ProdutoGrupo p : c_ProdutoGrupoDAO.Ler()) {
                System.out.println(p);
                c_UI.jCBsub.addItem(p);

            }
        }

        if (selecionado == 10) {

            for (FuncionarioCargo p : c_FuncionarioCargoDAO.Ler()) {
                System.out.println(p);
                c_UI.jCBcargoFuncionario.addItem(p);
            }
        }
    }

    //Estado para os botões
    //Estado 1 = Inicial
    //Estado 2 = Insert
    //Estado 3 = Modificação
    public void Estado() {

        switch (selecionado) {
            /*Tabela Venda*/
            case 1:
                switch (estado) {
                    case 1:
                        c_UI.jTabela.getSelectionModel().clearSelection();
                        Editavel(0);
                        LimparCampos();
                        c_UI.jTidCliente.setText("");
                        c_UI.jBmodificar.setEnabled(false);
                        c_UI.jBremover.setEnabled(true);
                        c_UI.jBativar.setEnabled(true);
                        c_UI.jBnovo.setEnabled(true);
                        c_UI.jBsalvar.setEnabled(false);
                        c_UI.jBlimpar.setEnabled(false);
                        c_UI.jBcancelar.setEnabled(false);
                        break;
                    case 2:
                        c_UI.jTabela.setRowSelectionAllowed(false);
                        LimparCampos();
                        Editavel(1);
                        c_UI.jTidCliente.setText("" + (c_ClienteDAO.RetornaId() + 1));
                        c_UI.jBmodificar.setEnabled(false);
                        c_UI.jBremover.setEnabled(false);
                        c_UI.jBativar.setEnabled(false);
                        c_UI.jBnovo.setEnabled(false);
                        c_UI.jBsalvar.setEnabled(true);
                        c_UI.jBlimpar.setEnabled(true);
                        c_UI.jBcancelar.setEnabled(true);
                        break;
                    case 3:
                        Editavel(1);
                        c_UI.jBmodificar.setEnabled(false);
                        c_UI.jBremover.setEnabled(false);
                        c_UI.jBativar.setEnabled(false);
                        c_UI.jBnovo.setEnabled(false);
                        c_UI.jBlimpar.setEnabled(true);
                        c_UI.jBcancelar.setEnabled(true);
                        c_UI.jBsalvar.setEnabled(true);
                        break;
                    default:
                        break;
                }
                break;
            case 3:
                switch (estado) {
                    case 1:
                        LimparCampos();
                        c_UI.jTabela.getSelectionModel().clearSelection();
                        Editavel(0);
                        c_UI.jTidForma.setText("");
                        c_UI.jBmodificar.setEnabled(false);
                        c_UI.jBremover.setEnabled(true);
                        c_UI.jBativar.setEnabled(true);
                        c_UI.jBnovo.setEnabled(true);
                        c_UI.jBsalvar.setEnabled(false);
                        c_UI.jBlimpar.setEnabled(false);
                        c_UI.jBcancelar.setEnabled(false);
                        break;
                    case 2:
                        LimparCampos();
                        Editavel(1);
                        c_UI.jTidForma.setText("" + (c_FormaPagamentoDAO.RetornaId() + 1));
                        c_UI.jBmodificar.setEnabled(false);
                        c_UI.jBremover.setEnabled(false);
                        c_UI.jBativar.setEnabled(false);
                        c_UI.jBnovo.setEnabled(false);
                        c_UI.jBsalvar.setEnabled(true);
                        c_UI.jBlimpar.setEnabled(true);
                        c_UI.jBcancelar.setEnabled(true);
                        break;
                    case 3:
                        Editavel(1);
                        String id = "" + c_UI.jTabela.getValueAt(c_UI.jTabela.getSelectedRow(), 0);
                        c_UI.jTidForma.setText(id);
                        c_UI.jBmodificar.setEnabled(false);
                        c_UI.jBremover.setEnabled(false);
                        c_UI.jBativar.setEnabled(false);
                        c_UI.jBnovo.setEnabled(false);
                        c_UI.jBlimpar.setEnabled(true);
                        c_UI.jBcancelar.setEnabled(true);
                        c_UI.jBsalvar.setEnabled(true);
                        break;
                    default:
                        break;
                }
                break;
            case 4:
                switch (estado) {
                    case 1:
                        LimparCampos();
                        c_UI.jTabela.getSelectionModel().clearSelection();
                        Editavel(0);
                        c_UI.jTidCargo.setText("");
                        c_UI.jBmodificar.setEnabled(false);
                        c_UI.jBremover.setEnabled(true);
                        c_UI.jBativar.setEnabled(true);
                        c_UI.jBnovo.setEnabled(true);
                        c_UI.jBsalvar.setEnabled(false);
                        c_UI.jBlimpar.setEnabled(false);
                        c_UI.jBcancelar.setEnabled(false);
                        break;
                    case 2:
                        LimparCampos();
                        Editavel(1);
                        c_UI.jTidCargo.setText("" + (c_FuncionarioCargoDAO.RetornaId() + 1));
                        c_UI.jBmodificar.setEnabled(false);
                        c_UI.jBremover.setEnabled(false);
                        c_UI.jBativar.setEnabled(false);
                        c_UI.jBnovo.setEnabled(false);
                        c_UI.jBsalvar.setEnabled(true);
                        c_UI.jBlimpar.setEnabled(true);
                        c_UI.jBcancelar.setEnabled(true);
                        break;
                    case 3:
                        Editavel(1);
                        String id = "" + c_UI.jTabela.getValueAt(c_UI.jTabela.getSelectedRow(), 0);
                        c_UI.jTidCargo.setText(id);
                        c_UI.jBmodificar.setEnabled(false);
                        c_UI.jBremover.setEnabled(false);
                        c_UI.jBativar.setEnabled(false);
                        c_UI.jBnovo.setEnabled(false);
                        c_UI.jBlimpar.setEnabled(true);
                        c_UI.jBcancelar.setEnabled(true);
                        c_UI.jBsalvar.setEnabled(true);
                        break;
                    default:
                        break;
                }
                break;
            case 5:
                switch (estado) {
                    case 1:
                        LimparCampos();
                        c_UI.jTabela.getSelectionModel().clearSelection();
                        Editavel(0);
                        c_UI.jTidProduto.setText("");
                        c_UI.jBmodificar.setEnabled(false);
                        c_UI.jBremover.setEnabled(true);
                        c_UI.jBativar.setEnabled(true);
                        c_UI.jBnovo.setEnabled(true);
                        c_UI.jBsalvar.setEnabled(false);
                        c_UI.jBlimpar.setEnabled(false);
                        c_UI.jBcancelar.setEnabled(false);
                        break;
                    case 2:
                        LimparCampos();
                        Editavel(1);
                        c_UI.jTidProduto.setText("" + (c_ProdutoDAO.RetornaId() + 1));
                        c_UI.jBmodificar.setEnabled(false);
                        c_UI.jBremover.setEnabled(false);
                        c_UI.jBativar.setEnabled(false);
                        c_UI.jBnovo.setEnabled(false);
                        c_UI.jBsalvar.setEnabled(true);
                        c_UI.jBlimpar.setEnabled(true);
                        c_UI.jBcancelar.setEnabled(true);
                        break;
                    case 3:
                        Editavel(1);
                        String id = "" + c_UI.jTabela.getValueAt(c_UI.jTabela.getSelectedRow(), 0);
                        c_UI.jTidProduto.setText(id);
                        c_UI.jBmodificar.setEnabled(false);
                        c_UI.jBremover.setEnabled(false);
                        c_UI.jBativar.setEnabled(false);
                        c_UI.jBnovo.setEnabled(false);
                        c_UI.jBlimpar.setEnabled(true);
                        c_UI.jBcancelar.setEnabled(true);
                        c_UI.jBsalvar.setEnabled(true);
                        break;
                    default:
                        break;
                }
                break;
            case 6:
                switch (estado) {
                    case 1:
                        LimparCampos();
                        c_UI.jTabela.getSelectionModel().clearSelection();
                        Editavel(0);
                        c_UI.jTidGrupo.setText("");
                        c_UI.jBmodificar.setEnabled(false);
                        c_UI.jBremover.setEnabled(true);
                        c_UI.jBativar.setEnabled(true);
                        c_UI.jBnovo.setEnabled(true);
                        c_UI.jBsalvar.setEnabled(false);
                        c_UI.jBlimpar.setEnabled(false);
                        c_UI.jBcancelar.setEnabled(false);
                        break;
                    case 2:
                        LimparCampos();
                        Editavel(1);
                        c_UI.jTidGrupo.setText("" + (c_ProdutoGrupoDAO.RetornaId() + 1));
                        c_UI.jBmodificar.setEnabled(false);
                        c_UI.jBremover.setEnabled(false);
                        c_UI.jBativar.setEnabled(false);
                        c_UI.jBnovo.setEnabled(false);
                        c_UI.jBsalvar.setEnabled(true);
                        c_UI.jBlimpar.setEnabled(true);
                        c_UI.jBcancelar.setEnabled(true);
                        break;
                    case 3:
                        Editavel(1);
                        String id = "" + c_UI.jTabela.getValueAt(c_UI.jTabela.getSelectedRow(), 0);
                        c_UI.jBmodificar.setEnabled(false);
                        c_UI.jBremover.setEnabled(false);
                        c_UI.jBativar.setEnabled(false);
                        c_UI.jBnovo.setEnabled(false);
                        c_UI.jBlimpar.setEnabled(true);
                        c_UI.jBcancelar.setEnabled(true);
                        c_UI.jBsalvar.setEnabled(true);
                        break;
                    default:
                        break;
                }
                break;
            case 7:
                switch (estado) {
                    case 1:
                        LimparCampos();
                        c_UI.jTabela.getSelectionModel().clearSelection();
                        Editavel(0);
                        c_UI.jTidMarca.setText("");
                        c_UI.jBmodificar.setEnabled(false);
                        c_UI.jBremover.setEnabled(true);
                        c_UI.jBativar.setEnabled(true);
                        c_UI.jBnovo.setEnabled(true);
                        c_UI.jBsalvar.setEnabled(false);
                        c_UI.jBlimpar.setEnabled(false);
                        c_UI.jBcancelar.setEnabled(false);
                        break;
                    case 2:
                        LimparCampos();
                        Editavel(1);
                        c_UI.jTidMarca.setText("" + (c_ProdutoMarcaDAO.RetornaId() + 1));
                        c_UI.jBmodificar.setEnabled(false);
                        c_UI.jBremover.setEnabled(false);
                        c_UI.jBativar.setEnabled(false);
                        c_UI.jBnovo.setEnabled(false);
                        c_UI.jBsalvar.setEnabled(true);
                        c_UI.jBlimpar.setEnabled(true);
                        c_UI.jBcancelar.setEnabled(true);
                        break;
                    case 3:
                        Editavel(1);
                        String id = "" + c_UI.jTabela.getValueAt(c_UI.jTabela.getSelectedRow(), 0);
                        c_UI.jBmodificar.setEnabled(false);
                        c_UI.jBremover.setEnabled(false);
                        c_UI.jBativar.setEnabled(false);
                        c_UI.jBnovo.setEnabled(false);
                        c_UI.jBlimpar.setEnabled(true);
                        c_UI.jBcancelar.setEnabled(true);
                        c_UI.jBsalvar.setEnabled(true);
                        break;
                    default:
                        break;
                }
                break;
            case 8:
                switch (estado) {
                    case 1:
                        LimparCampos();
                        c_UI.jTabela.getSelectionModel().clearSelection();
                        Editavel(0);
                        c_UI.jTidSetor.setText("");
                        c_UI.jBmodificar.setEnabled(false);
                        c_UI.jBremover.setEnabled(true);
                        c_UI.jBativar.setEnabled(true);
                        c_UI.jBnovo.setEnabled(true);
                        c_UI.jBsalvar.setEnabled(false);
                        c_UI.jBlimpar.setEnabled(false);
                        c_UI.jBcancelar.setEnabled(false);
                        break;
                    case 2:
                        LimparCampos();
                        Editavel(1);
                        c_UI.jTidSetor.setText("" + (c_ProdutoSetorDAO.RetornaId() + 1));
                        c_UI.jBmodificar.setEnabled(false);
                        c_UI.jBremover.setEnabled(false);
                        c_UI.jBativar.setEnabled(false);
                        c_UI.jBnovo.setEnabled(false);
                        c_UI.jBsalvar.setEnabled(true);
                        c_UI.jBlimpar.setEnabled(true);
                        c_UI.jBcancelar.setEnabled(true);
                        break;
                    case 3:
                        Editavel(1);
                        String id = "" + c_UI.jTabela.getValueAt(c_UI.jTabela.getSelectedRow(), 0);
                        c_UI.jTidSetor.setText(id);
                        c_UI.jBmodificar.setEnabled(false);
                        c_UI.jBremover.setEnabled(false);
                        c_UI.jBativar.setEnabled(false);
                        c_UI.jBnovo.setEnabled(false);
                        c_UI.jBlimpar.setEnabled(true);
                        c_UI.jBcancelar.setEnabled(true);
                        c_UI.jBsalvar.setEnabled(true);
                        break;
                    default:
                        break;
                }
                break;
            case 9:
                switch (estado) {
                    case 1:
                        LimparCampos();
                        c_UI.jTabela.getSelectionModel().clearSelection();
                        Editavel(0);
                        c_UI.jTidSub.setText("");
                        c_UI.jBmodificar.setEnabled(false);
                        c_UI.jBremover.setEnabled(true);
                        c_UI.jBativar.setEnabled(true);
                        c_UI.jBnovo.setEnabled(true);
                        c_UI.jBsalvar.setEnabled(false);
                        c_UI.jBlimpar.setEnabled(false);
                        c_UI.jBcancelar.setEnabled(false);
                        break;
                    case 2:
                        LimparCampos();
                        Editavel(1);
                        c_UI.jTidSub.setText("" + (c_ProdutoSubGrupoDAO.RetornaId() + 1));
                        c_UI.jBmodificar.setEnabled(false);
                        c_UI.jBremover.setEnabled(false);
                        c_UI.jBativar.setEnabled(false);
                        c_UI.jBnovo.setEnabled(false);
                        c_UI.jBsalvar.setEnabled(true);
                        c_UI.jBlimpar.setEnabled(true);
                        c_UI.jBcancelar.setEnabled(true);
                        break;
                    case 3:
                        Editavel(1);
                        String id = "" + c_UI.jTabela.getValueAt(c_UI.jTabela.getSelectedRow(), 0);
                        c_UI.jTidSub.setText(id);
                        c_UI.jBmodificar.setEnabled(false);
                        c_UI.jBremover.setEnabled(false);
                        c_UI.jBativar.setEnabled(false);
                        c_UI.jBnovo.setEnabled(false);
                        c_UI.jBlimpar.setEnabled(true);
                        c_UI.jBcancelar.setEnabled(true);
                        c_UI.jBsalvar.setEnabled(true);
                        break;
                    default:
                        break;
                }
                break;
            case 10:
                switch (estado) {
                    case 1:
                        LimparCampos();
                        c_UI.jTabela.getSelectionModel().clearSelection();
                        Editavel(0);
                        c_UI.jTidFuncionario.setText("");
                        c_UI.jBmodificar.setEnabled(false);
                        c_UI.jBremover.setEnabled(true);
                        c_UI.jBativar.setEnabled(true);
                        c_UI.jBnovo.setEnabled(true);
                        c_UI.jBsalvar.setEnabled(false);
                        c_UI.jBlimpar.setEnabled(false);
                        c_UI.jBcancelar.setEnabled(false);
                        break;
                    case 2:
                        LimparCampos();
                        Editavel(1);
                        c_UI.jTidFuncionario.setText("" + (c_FuncionarioDAO.RetornaId() + 1));
                        c_UI.jBmodificar.setEnabled(false);
                        c_UI.jBremover.setEnabled(false);
                        c_UI.jBativar.setEnabled(false);
                        c_UI.jBnovo.setEnabled(false);
                        c_UI.jBsalvar.setEnabled(true);
                        c_UI.jBlimpar.setEnabled(true);
                        c_UI.jBcancelar.setEnabled(true);
                        break;
                    case 3:
                        Editavel(1);
                        String id = "" + c_UI.jTabela.getValueAt(c_UI.jTabela.getSelectedRow(), 0);
                        c_UI.jTidFuncionario.setText(id);
                        c_UI.jBmodificar.setEnabled(false);
                        c_UI.jBremover.setEnabled(false);
                        c_UI.jBativar.setEnabled(false);
                        c_UI.jBnovo.setEnabled(false);
                        c_UI.jBlimpar.setEnabled(true);
                        c_UI.jBcancelar.setEnabled(true);
                        c_UI.jBsalvar.setEnabled(true);
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }

    }

    public Cliente setCliente() throws SQLException {

        c_c.setIdcliente(Integer.parseInt(c_UI.jTidCliente.getText()));
        c_c.setNome(c_UI.jTnomeCliente.getText());
        c_c.setCpf(c_UI.jTcpfCliente.getText());
        c_c.setEmail(c_UI.jTemailCliente.getText());

        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);

        c_c.setDatacadastro(date);
        c_c.setStatus(Boolean.TRUE);

        return c_c;

    }

    public FormaPagamento setFormaPagamento() throws SQLException {

        c_fp.setIdformapagamento(Integer.parseInt(c_UI.jTidForma.getText()));
        c_fp.setDescricao(c_UI.jTdescricaoForma.getText());

        c_fp.setStatus(Boolean.TRUE);

        return c_fp;

    }

    public FuncionarioCargo setFuncionarioCargo() throws SQLException {

        c_fc.setIdcargo(Integer.parseInt(c_UI.jTidCargo.getText()));
        c_fc.setDescricao(c_UI.jTdescricaoCargo.getText());

        c_fc.setStatus(Boolean.TRUE);

        return c_fc;

    }

    public Produto setProduto() throws SQLException {

        BigDecimal big1 = new BigDecimal(c_UI.jTvalorVendaProduto.getText());
        BigDecimal big2 = new BigDecimal(c_UI.jTvalorCustoUnitarioProduto.getText());

        c_p.setIdproduto(Integer.parseInt(c_UI.jTidProduto.getText()));
        c_p.setDescricaoproduto(c_UI.jTdescricaoProduto.getText());
        c_p.setValorvenda(big1);
        c_p.setValorcustounitario(big2);

        ProdutoSetor PS = (ProdutoSetor) c_UI.jCBsetorProduto.getSelectedItem();

        c_p.setFksetor(PS.getIdsetor());

        ProdutoGrupo PG = (ProdutoGrupo) c_UI.jCBgrupoProduto.getSelectedItem();

        c_p.setFkgrupo(PG.getIdgrupo());

        ProdutoSubGrupo PSG = (ProdutoSubGrupo) c_UI.jCBsubGrupoProduto.getSelectedItem();

        c_p.setFksubgrupo(PSG.getIdsubgrupo());

        ProdutoMarca PM = (ProdutoMarca) c_UI.jCBmarcaProduto.getSelectedItem();

        c_p.setFkmarca(PM.getIdmarca());

        c_p.setStatus(Boolean.TRUE);

        return c_p;

    }

    public ProdutoGrupo setProdutoGrupo() throws SQLException {

        c_pg.setIdgrupo(Integer.parseInt(c_UI.jTidGrupo.getText()));
        c_pg.setNome(c_UI.jTnomeGrupo.getText());

        ProdutoSetor PS = (ProdutoSetor) c_UI.jCBsetorGrupo.getSelectedItem();

        c_pg.setFksetor(PS.getIdsetor());

        c_pg.setStatus(Boolean.TRUE);

        return c_pg;

    }

    public ProdutoMarca setProdutoMarca() throws SQLException {

        c_pm.setIdmarca(Integer.parseInt(c_UI.jTidMarca.getText()));
        c_pm.setNome(c_UI.jTnomeMarca.getText());
        c_pm.setStatus(Boolean.TRUE);

        return c_pm;
    }

    public ProdutoSetor setProdutoSetor() throws SQLException {

        c_ps.setIdsetor(Integer.parseInt(c_UI.jTidSetor.getText()));
        c_ps.setNome(c_UI.jTnomeSetor.getText());
        c_ps.setStatus(Boolean.TRUE);

        return c_ps;
    }

    public ProdutoSubGrupo setProdutoSubGrupo() throws SQLException {

        c_psg.setIdsubgrupo(Integer.parseInt(c_UI.jTidSub.getText()));
        c_psg.setNome(c_UI.jTnomeSub.getText());

        ProdutoGrupo PG = (ProdutoGrupo) c_UI.jCBsub.getSelectedItem();

        c_psg.setFkgrupo(PG.getIdgrupo());

        c_psg.setStatus(Boolean.TRUE);
        return c_psg;

    }

    public Funcionario setFuncionario() throws SQLException {

        c_f.setIdfuncionario(Integer.parseInt(c_UI.jTidFuncionario.getText()));
        c_f.setNome(c_UI.jTnomeFuncionario.getText());

        FuncionarioCargo FC = (FuncionarioCargo) c_UI.jCBcargoFuncionario.getSelectedItem();

        c_f.setFkcargo(FC.getIdcargo());

        c_f.setCpf(c_UI.jTcpfFuncionario.getText());
        c_f.setRg(c_UI.jTrgFuncionario.getText());
        c_f.setSexo(c_UI.jTsexoFuncionario.getText());
        c_f.setEmail(c_UI.jTemailFuncionario.getText());
        c_f.setTelefone(c_UI.jTtelefoneFuncionario.getText());
        c_f.setCelular(c_UI.jTcelularFuncionario.getText());

        java.util.Date dateu = c_UI.jDateChoosernacimento.getDate();

        java.sql.Date sDate = new java.sql.Date(dateu.getTime());

        c_f.setDatanascimento(sDate);

        c_f.setStatus(Boolean.TRUE);
        return c_f;

    }

    public Venda setVenda() throws SQLException {

        c_v.setIdvenda(Integer.parseInt(c_UI.jTidVenda.getText()));

        Funcionario f = (Funcionario) c_UI.jCBfuncionarioVenda.getSelectedItem();

        Cliente c = (Cliente) c_UI.jCBclienteVenda.getSelectedItem();

        FormaPagamento fp = (FormaPagamento) c_UI.jCBformaPagamentoVenda.getSelectedItem();

        c_v.setFkcliente(c.getIdcliente());
        c_v.setFkformapagamento(fp.getIdformapagamento());
        c_v.setFkfuncionario(f.getIdfuncionario());

        List<VendaItem> v = c_VendaItemDAO.LerChave(c_v);

        c_v.setValor(c_VendaItemDAO.Valor(c_v));

        BigDecimal big1 = new BigDecimal(c_UI.jTdescontoVenda.getText());

        c_v.setDesconto(big1);

        c_v.setValorfinal(big1.subtract(c_v.getValor()));

        return c_v;
    }

    public VendaItem setVendaItem() throws SQLException {

        c_vi.setIditemvenda(Integer.parseInt(c_UI.jTidItem.getText()));
        c_vi.setFkvenda(Integer.parseInt(c_UI.jTidVenda.getText()));

        Produto p = (Produto) c_UI.jCBprodutoItem.getSelectedItem();

        c_vi.setFkproduto(p.getIdproduto());
        c_vi.setValorcusto(p.getValorcustounitario());
        c_vi.setValorvenda(p.getValorvenda());

        BigDecimal big1 = new BigDecimal(c_UI.jTquantidadeItem.getText());
        c_vi.setQuantidade(big1);
        BigDecimal c = c_vi.getValorcusto();
        BigDecimal v = c_vi.getValorvenda();
        BigDecimal q = c_vi.getQuantidade();
        BigDecimal ValorF = (v.subtract(c)).multiply(q);
        c_vi.setValorfinalitem(ValorF);
        return c_vi;
    }

}
