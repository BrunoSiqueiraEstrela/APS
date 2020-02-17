/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitarios;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.object.ProdutoSubGrupo;

/**
 *
 * @author DELTA
 */
public class ProdutoSubgrupoTableModel extends AbstractTableModel  {
    
	private static final int COL_IDSUBGRUPO = 0;
	private static final int COL_FKGRUPO = 1;
	private static final int COL_NOME = 2;
	private static final int COL_STATUS = 3;
	
	
	List<ProdutoSubGrupo> linhas;
    private String[] colunas = new String[]{"CODIGO","GRUPO","NOME","STATUS"};

    public ProdutoSubgrupoTableModel(List<ProdutoSubGrupo> clientes) {
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
        if (columnIndex == COL_IDSUBGRUPO) {
            return Integer.class;
        }
        return String.class;
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Object getValueAt(int row, int column) {

    	ProdutoSubGrupo m = linhas.get(row);

        if (column == COL_IDSUBGRUPO) {
            return m.getIdsubgrupo();
        }else if (column == COL_FKGRUPO) {
            return m.getFkgrupo();
        }else if (column == COL_NOME) {
            return m.getNome();
        }else if (column == COL_STATUS) {
            return m.getStatus();
        }
        
        return "";
    }

    public void setValueAt(Object aValue, int row, int column) {
    	
    	ProdutoSubGrupo u = linhas.get(row);
        
    	if (column == COL_IDSUBGRUPO) {
            u.setIdsubgrupo((Integer) aValue);
        }else if (column == COL_FKGRUPO) {
            u.setFkgrupo((int) aValue);
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

    public ProdutoSubGrupo getProdutoSubgrupo(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void addProdutoSubgrupo(ProdutoSubGrupo produtosubgrupo) {
        linhas.add(produtosubgrupo);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);

    }

    public void updateProdutoSubgrupo(int indiceLinha, ProdutoSubGrupo marca) {
        linhas.set(indiceLinha, marca);
        fireTableRowsUpdated(indiceLinha, indiceLinha);

    }

    public void removeProdutoSubgrupo(int indiceLinha) {
        linhas.remove(indiceLinha);
        fireTableRowsDeleted(indiceLinha, indiceLinha);

    }
}
