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
import model.object.VendaItem;

/**
 *
 * @author DELTA
 */
public class VendaItemTableModel extends AbstractTableModel {
    
	private static final int COL_IDITEMVENDA = 0;
	private static final int COL_FKVENDA = 1;
	private static final int COL_FKPRODUTO = 2;
	private static final int COL_VALORCUSTO = 3;
	private static final int COL_VALORVENDA = 4;
	private static final int COL_QUANTIDADE = 5;
	private static final int COL_VALORFINAL = 6;
	private static final int COL_STATUS = 7;
	
	
	List<VendaItem> linhas;
    private String[] colunas = new String[]{"CODIGO","Venda","Produto","Valor de Custo","Valor Venda","Quantidade","Valor Final","Status"};

    public VendaItemTableModel(List<VendaItem> clientes) {
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
        if (columnIndex == COL_IDITEMVENDA) {
            return Integer.class;
        }
        return String.class;
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Object getValueAt(int row, int column) {

    	VendaItem m = linhas.get(row);

        if (column == COL_IDITEMVENDA) {
            return m.getIditemvenda();
        }else if (column == COL_FKVENDA) {
            return m.getFkvenda();
        }else if (column == COL_FKPRODUTO) {
            return m.getFkproduto();
        }else if (column == COL_VALORCUSTO) {
            return m.getValorcusto();
        }else if (column == COL_VALORVENDA) {
            return m.getValorvenda();
        }else if (column == COL_QUANTIDADE) {
            return m.getQuantidade();
        }else if (column == COL_VALORFINAL) {
            return m.getValorfinalitem();
        }else if (column == COL_STATUS) {
            return m.getStatus();
        }
        
        return "";
    }

    public void setValueAt(Object aValue, int row, int column) {
    	
    	VendaItem u = linhas.get(row);
        
    	if (column == COL_IDITEMVENDA) {
            u.setIditemvenda((Integer) aValue);
        }else if (column == COL_FKVENDA) {
            u.setFkvenda((Integer) aValue);
        }else if (column == COL_FKPRODUTO) {
            u.setFkproduto((Integer) aValue);
        }else if (column == COL_VALORCUSTO) {
            u.setValorcusto((BigDecimal) aValue);
        }else if (column == COL_VALORVENDA) {
            u.setValorvenda((BigDecimal) aValue);
        }else if (column == COL_QUANTIDADE) {
            u.setQuantidade((BigDecimal) aValue);
        }else if (column == COL_VALORFINAL) {
            u.setValorfinalitem((BigDecimal) aValue);
        }else if (column == COL_STATUS) {
            if(aValue.toString().equalsIgnoreCase("1")){
            u.setStatus(true);
            }else{
              u.setStatus(false);
            }
        }
    }

    public VendaItem getVendaItem(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void addVendaItem(VendaItem vendaitem) {
        linhas.add(vendaitem);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);

    }

    public void updateVendaItem(int indiceLinha, VendaItem marca) {
        linhas.set(indiceLinha, marca);
        fireTableRowsUpdated(indiceLinha, indiceLinha);

    }

    public void removeVendaItem(int indiceLinha) {
        linhas.remove(indiceLinha);
        fireTableRowsDeleted(indiceLinha, indiceLinha);

    }
}
