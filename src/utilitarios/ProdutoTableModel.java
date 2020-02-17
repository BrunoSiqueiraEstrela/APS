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
import model.object.Produto;

/**
 *
 * @author DELTA
 */
public class ProdutoTableModel extends AbstractTableModel {
    
	private static final int COL_IDPRODUTO = 0;
	private static final int COL_DESCRICAO = 1;
	private static final int COL_VALOR = 2;
	private static final int COL_CUSTOUNITARIO = 3;
	private static final int COL_FKSETOR = 4;
	private static final int COL_FKGRUPO = 5;
	private static final int COL_FKSUBGRUPO = 6;
	private static final int COL_FKMARCA = 7;
	private static final int COL_STATUS = 8;
	
	
	List<Produto> linhas;
    private String[] colunas = new String[]{"CODIGO","DESCRIÇÃO","VALOR","CUSTO","SETOR","GRUPO","SUB GRUPO","MARCA","STATUS"};

    public ProdutoTableModel(List<Produto> clientes) {
        this.linhas = new ArrayList<>(clientes);
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
        if (columnIndex == COL_IDPRODUTO) {
            return Integer.class;
        }
        return String.class;
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Object getValueAt(int row, int column) {

    	Produto m = linhas.get(row);

        if (column == COL_IDPRODUTO) {
            return m.getIdproduto();
        }else if (column == COL_DESCRICAO) {
            return m.getDescricaoproduto();
        }else if (column == COL_VALOR) {
            return m.getValorvenda();
        }else if (column == COL_CUSTOUNITARIO) {
            return m.getValorcustounitario();
        }else if (column == COL_FKSETOR) {
            return m.getFksetor();
        }else if (column == COL_FKGRUPO) {
            return m.getFkgrupo();
        }else if (column == COL_FKSUBGRUPO) {
            return m.getFksubgrupo();
        }else if (column == COL_FKMARCA) {
            return m.getFkmarca();
        }else if (column == COL_STATUS) {
            return m.getStatus();
        }
        
        return "";
    }

    public void setValueAt(Object aValue, int row, int column) {
    	
    	Produto u = linhas.get(row);
        
    	if (column == COL_IDPRODUTO) {
            u.setIdproduto((Integer) aValue);
        }else if (column == COL_DESCRICAO) {
            u.setDescricaoproduto(aValue.toString());
        }else if (column == COL_VALOR) {
            u.setValorvenda((BigDecimal) aValue);
        }else if (column == COL_CUSTOUNITARIO) {
            u.setValorcustounitario((BigDecimal) aValue);
        }else if (column == COL_FKSETOR) {
            u.setFksetor((Integer) aValue);
        }else if (column == COL_FKGRUPO) {
            u.setFkgrupo((Integer) aValue);
        }else if (column == COL_FKSUBGRUPO) {
            u.setFksubgrupo((Integer) aValue);
        }else if (column == COL_FKMARCA) {
            u.setFkmarca((Integer) aValue);
        }else if (column == COL_STATUS) {
            if(aValue.toString().equalsIgnoreCase("1")){
            u.setStatus(true);
            }else{
              u.setStatus(false);
            }
        }
    }

    public Produto getProduto(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void addProduto(Produto produto) {
        linhas.add(produto);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);

    }

    public void updateProduto(int indiceLinha, Produto marca) {
        linhas.set(indiceLinha, marca);
        fireTableRowsUpdated(indiceLinha, indiceLinha);

    }

    public void removeProduto(int indiceLinha) {
        linhas.remove(indiceLinha);
        fireTableRowsDeleted(indiceLinha, indiceLinha);

    }
}
