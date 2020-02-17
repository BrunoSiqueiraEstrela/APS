/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitarios;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import model.object.ProdutoGrupo;

/**
 *
 * @author DELTA
 */
public class ProdutoGrupoTableModel extends AbstractTableModel  {
    
	private static final int COL_IDGRUPO = 0;
	private static final int COL_FKSETOR = 1;
	private static final int COL_NOME = 2;
	private static final int COL_STATUS = 3;
	
	
	List<ProdutoGrupo> linhas;
    private String[] colunas = new String[]{"CODIGO","SETOR","NOME","STATUS"};

    public ProdutoGrupoTableModel(List<ProdutoGrupo> clientes) {
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
        if (columnIndex == COL_IDGRUPO) {
            return Integer.class;
        }
        return String.class;
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Object getValueAt(int row, int column) {

    	ProdutoGrupo m = linhas.get(row);

        if (column == COL_IDGRUPO) {
            return m.getIdgrupo();
        } else if (column == COL_FKSETOR) {
            return m.getFksetor();
        }else if (column == COL_NOME) {
            return m.getNome();
        }else if (column == COL_STATUS) {
            return m.getStatus();
        }
        
        return "";
    }

    public void setValueAt(Object aValue, int row, int column) {
    	
    	ProdutoGrupo u = linhas.get(row);
        
    	if (column == COL_IDGRUPO) {
            u.setIdgrupo((Integer) aValue);
        } else if (column == COL_FKSETOR) {
            u.setFksetor((int) aValue);
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

    public ProdutoGrupo getFuncionario(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void addProdutoGrupo(ProdutoGrupo produtogrupo) {
        linhas.add(produtogrupo);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);

    }

    public void updateProdutoGrupo(int indiceLinha, ProdutoGrupo marca) {
        linhas.set(indiceLinha, marca);
        fireTableRowsUpdated(indiceLinha, indiceLinha);

    }

    public void removeProdutoGrupo(int indiceLinha) {
        linhas.remove(indiceLinha);
        fireTableRowsDeleted(indiceLinha, indiceLinha);

    }
}
