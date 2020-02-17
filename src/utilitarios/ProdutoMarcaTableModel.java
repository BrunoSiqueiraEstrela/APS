/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitarios;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import model.object.ProdutoMarca;

/**
 *
 * @author DELTA
 */
public class ProdutoMarcaTableModel extends AbstractTableModel  {
    
	private static final int COL_IDMARCA = 0;
	private static final int COL_NOME = 1;
	private static final int COL_STATUS = 2;
	
	
	List<ProdutoMarca> linhas;
    private String[] colunas = new String[]{"CODIGO","NOME","STATUS"};

    public ProdutoMarcaTableModel(List<ProdutoMarca> clientes) {
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
        if (columnIndex == COL_IDMARCA) {
            return Integer.class;
        }
        return String.class;
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Object getValueAt(int row, int column) {

    	ProdutoMarca m = linhas.get(row);

        if (column == COL_IDMARCA) {
            return m.getIdmarca();
        }else if (column == COL_NOME) {
            return m.getNome();
        }else if (column == COL_STATUS) {
            return m.getStatus();
        }
        
        return "";
    }

    public void setValueAt(Object aValue, int row, int column) {
    	
    	ProdutoMarca u = linhas.get(row);
        
    	if (column == COL_IDMARCA) {
            u.setIdmarca((Integer) aValue);
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

    public ProdutoMarca getProdutoMarca(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void addProdutoMarca(ProdutoMarca produtomarca) {
        linhas.add(produtomarca);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);

    }

    public void updateProdutoMarca(int indiceLinha, ProdutoMarca marca) {
        linhas.set(indiceLinha, marca);
        fireTableRowsUpdated(indiceLinha, indiceLinha);

    }

    public void removeProdutoMarca(int indiceLinha) {
        linhas.remove(indiceLinha);
        fireTableRowsDeleted(indiceLinha, indiceLinha);

    }
}
