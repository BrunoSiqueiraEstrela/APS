/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitarios;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.object.FuncionarioCargo;

/**
 *
 * @author DELTA
 */
public class FuncionarioCargoTableModel extends AbstractTableModel {
	
	private static final int COL_IDCARGO = 0;
	private static final int COL_DESCRICAO = 1;
	private static final int COL_STATUS = 2;

	
	List<FuncionarioCargo> linhas;
    private String[] colunas = new String[]{"CODIGO", "DESCRIÇÃO","STATUS"};

    public FuncionarioCargoTableModel(List<FuncionarioCargo> clientes) {
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
        if (columnIndex == COL_IDCARGO) {
            return Integer.class;
        }
        return String.class;
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Object getValueAt(int row, int column) {

    	FuncionarioCargo m = linhas.get(row);

        if (column == COL_IDCARGO) {
            return m.getIdcargo();
        } else if (column == COL_DESCRICAO) {
            return m.getDescricao();
        } else if (column == COL_STATUS) {
            return m.getStatus();
        }
        
        return "";
    }

    public void setValueAt(Object aValue, int row, int column) {
    	
    	FuncionarioCargo u = linhas.get(row);
    	
        if (column == COL_IDCARGO) {
            u.setIdcargo((Integer) aValue);
        } else if (column == COL_DESCRICAO) {
            u.setDescricao(aValue.toString());
        } else if (column == COL_STATUS) {
            if(aValue.toString().equalsIgnoreCase("1")){
            u.setStatus(true);
            }else{
              u.setStatus(false);
            }
        }
    }

    public FuncionarioCargo getFuncionarioCargo(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void addFuncionarioCargo(FuncionarioCargo funcionariocargo) {
        linhas.add(funcionariocargo);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);

    }

    public void updateFuncionarioCargo(int indiceLinha, FuncionarioCargo marca) {
        linhas.set(indiceLinha, marca);
        fireTableRowsUpdated(indiceLinha, indiceLinha);

    }

    public void removeFuncionarioCargo(int indiceLinha) {
        linhas.remove(indiceLinha);
        fireTableRowsDeleted(indiceLinha, indiceLinha);

    }
}
