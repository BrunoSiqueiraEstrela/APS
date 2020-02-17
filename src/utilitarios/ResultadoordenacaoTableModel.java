/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitarios;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.object.Resultadoordenacao;

/**
 *
 * @author DELTA
 */
public class ResultadoordenacaoTableModel extends AbstractTableModel {

    private static final int COL_CODIGO = 0;
    private static final int COL_METODOORDENACAO = 1;
    private static final int COL_TABELA = 2;
    private static final int COL_COLUNA = 3;
    private static final int COL_REGISTROS = 4;
    private static final int COL_TEMPO = 5;

    List<Resultadoordenacao> linhas;
    private final String[] colunas = new String[]{"CODIGO", "METODO", "TABELA", "COLUNA", "REGISTROS", "TEMPO"};

    public ResultadoordenacaoTableModel(List<Resultadoordenacao> resultadoordenacao) {
        this.linhas = new ArrayList<>(resultadoordenacao);
    }

    public int getRowCount() {
        return linhas.size();
    }

    public int getColumnCount() {
        return colunas.length;
    }

    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    }

    public Class getColumnClass(int columnIndex) {
        if (columnIndex == COL_CODIGO) {
            return Integer.class;
        }
        return String.class;
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int row, int column) {

        Resultadoordenacao m = linhas.get(row);

        switch (column) {
            case COL_CODIGO:
                return m.getCodigo();
            case COL_METODOORDENACAO:
                return m.getMetodoordenacao();
            case COL_TABELA:
                return m.getTabela();
            case COL_COLUNA:
                return m.getColuna();
            case COL_REGISTROS:
                return m.getRegistros();
            case COL_TEMPO:
                return m.getTempo();
            default:
                break;
        }

        return "";
    }

    @Override
    public void setValueAt(Object aValue, int row, int column) {

        Resultadoordenacao u = linhas.get(row);

        switch (column) {
            case COL_CODIGO:
                u.setCodigo((Integer) aValue);
                break;
            case COL_METODOORDENACAO:
                u.setMetodoordenacao((String) aValue);
                break;
            case COL_TABELA:
                u.setTabela((String) aValue);
                break;
            case COL_COLUNA:
                u.setColuna((String) aValue);
                break;
            case COL_REGISTROS:
                u.setRegistros((int) aValue);
                break;
            case COL_TEMPO:
                u.setTempo((int) aValue);
                break;
            default:
                break;
        }
    }

    public Resultadoordenacao getResultadoordenacao(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void addResultadoordenacao(Resultadoordenacao vendaitem) {
        linhas.add(vendaitem);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);

    }

    public void updateResultadoordenacao(int indiceLinha, Resultadoordenacao marca) {
        linhas.set(indiceLinha, marca);
        fireTableRowsUpdated(indiceLinha, indiceLinha);

    }

    public void removeResultadoordenacao(int indiceLinha) {
        linhas.remove(indiceLinha);
        fireTableRowsDeleted(indiceLinha, indiceLinha);

    }
}
