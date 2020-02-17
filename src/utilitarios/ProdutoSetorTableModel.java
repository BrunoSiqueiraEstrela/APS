/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitarios;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.object.ProdutoSetor;

/**
 *
 * @author DELTA
 */
public class ProdutoSetorTableModel extends AbstractTableModel {
    
	private static final int COL_IDSETOR = 0;
	private static final int COL_NOME = 1;
	private static final int COL_STATUS = 2;
	
	
	List<ProdutoSetor> linhas;
    private String[] colunas = new String[]{"CODIGO","NOME","STATUS"};

    public ProdutoSetorTableModel(List<ProdutoSetor> clientes) {
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
        if (columnIndex == COL_IDSETOR) {
            return Integer.class;
        }
        return String.class;
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Object getValueAt(int row, int column) {

    	ProdutoSetor m = linhas.get(row);

        if (column == COL_IDSETOR) {
            return m.getIdsetor();
        }else if (column == COL_NOME) {
            return m.getNome();
        }else if (column == COL_STATUS) {
            return m.getStatus();
        }
        
        return "";
    }

    public void setValueAt(Object aValue, int row, int column) {
    	
    	ProdutoSetor u = linhas.get(row);
        
    	if (column == COL_IDSETOR) {
            u.setIdsetor((Integer) aValue);
        }else if (column == COL_NOME) {
            u.setNome(aValue.toString());
        }else if (column == COL_STATUS) {
            if(aValue.toString().equalsIgnoreCase("1")){
            u.setStatus(true);
            }else{
              u.setStatus(false);
            }
        }
    }

    public ProdutoSetor getProdutoSetor(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void addProdutoSetor(ProdutoSetor produtosetor) {
        linhas.add(produtosetor);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);

    }

    public void updateProdutoSetor(int indiceLinha, ProdutoSetor marca) {
        linhas.set(indiceLinha, marca);
        fireTableRowsUpdated(indiceLinha, indiceLinha);

    }

    public void removeProdutoSetor(int indiceLinha) {
        linhas.remove(indiceLinha);
        fireTableRowsDeleted(indiceLinha, indiceLinha);

    }
}
