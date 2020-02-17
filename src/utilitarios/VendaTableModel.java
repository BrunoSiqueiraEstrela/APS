/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitarios;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.object.Venda;

/**
 *
 * @author DELTA
 */
public class VendaTableModel extends AbstractTableModel {
    
	
	private static final int COL_IDVENDA = 0;
	private static final int COL_FKFUNCIONARIO = 1;
	private static final int COL_FKCLIENTE = 2;
	private static final int COL_DATA = 3;
	private static final int COL_HORA = 4;
	private static final int COL_VALOR = 5;
	private static final int COL_DESCONTO = 6;
	private static final int COL_DESCONTORS = 7;
	private static final int COL_VALORFINAL = 8;
	private static final int COL_TROCO = 9;
	private static final int COL_FKFORM = 10;
	private static final int COL_OBSEVACAO = 11;
	private static final int COL_OBSEVACAOCANCELAMENTO = 12; 
	private static final int COL_STATUS = 13;
	
	
	List<Venda> linhas;
    private String[] colunas = new String[]{"IdVenda","Funcion�rio","Cliente","Data","Hora","Valor","Desconto","Descontors","Valor Final",
    										"Troca","Forma de Pagamento","Observ��o","Observ��o de Cancelamento","Status"};
    

    public VendaTableModel(List<Venda> clientes) {
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
        if (columnIndex == COL_IDVENDA) {
            return Integer.class;
        }
        return String.class;
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Object getValueAt(int row, int column) {

    	Venda m = linhas.get(row);

        if (column == COL_IDVENDA) {
            return m.getIdvenda();
        }else if (column == COL_FKFUNCIONARIO) {
            return m.getFkfuncionario();
        }else if (column == COL_FKCLIENTE) {
            return m.getFkcliente();
        }else if (column == COL_DATA) {
            return m.getData();
        }else if (column == COL_HORA) {
            return m.getHora();
        }else if (column == COL_VALOR) {
            return m.getValor();
        }else if (column == COL_DESCONTO) {
            return m.getDesconto();
        }else if (column == COL_DESCONTORS) {
            return m.getDescontors();
        }else if (column == COL_VALORFINAL) {
            return m.getValorfinal();
        }else if (column == COL_TROCO) {
            return m.getTroco();
        }else if (column == COL_FKFORM) {
            return m.getFkformapagamento();
        }else if (column == COL_OBSEVACAO) {
            return m.getObservacao();
        }else if (column == COL_OBSEVACAOCANCELAMENTO) {
            return m.getObservacaocancelamento();
        }else if (column == COL_STATUS) {
            return m.getStatus();
        
        }
        return "";
    }

    public void setValueAt(Object aValue, int row, int column) {
    	
    	Venda u = linhas.get(row);
        
    	if (column == COL_IDVENDA) {
            u.setIdvenda((Integer) aValue);
        }else if (column == COL_FKFUNCIONARIO) {
            u.setFkfuncionario((Integer) aValue);
        }else if (column == COL_FKCLIENTE) {
            u.setFkcliente((Integer) aValue);
        }else if (column == COL_DATA) {
            u.setData((Date) aValue);
        }else if (column == COL_HORA) {
            u.setHora((Date) aValue);
        }else if (column == COL_VALOR) {
            u.setValor((BigDecimal) aValue);
        }else if (column == COL_DESCONTO) {
            u.setDesconto((BigDecimal) aValue);
        }else if (column == COL_DESCONTORS) {
            u.setDescontors((BigDecimal) aValue);
        }else if (column == COL_VALORFINAL) {
            u.setValorfinal((BigDecimal) aValue);
        }else if (column == COL_TROCO) {
            u.setTroco((BigDecimal) aValue);
        }else if (column == COL_FKFORM) {
            u.setFkformapagamento((Integer) aValue);
        }else if (column == COL_OBSEVACAO) {
            u.setObservacao(aValue.toString());
        }else if (column == COL_OBSEVACAOCANCELAMENTO) {
            u.setObservacaocancelamento(aValue.toString());
        }else if (column == COL_STATUS) {
            if(aValue.toString().equalsIgnoreCase("1")){
            u.setStatus(true);
            }else{
              u.setStatus(false);
            }
        }
    }

    public Venda getVenda(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void addVenda(Venda venda) {
        linhas.add(venda);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);

    }

    public void updateVenda(int indiceLinha, Venda marca) {
        linhas.set(indiceLinha, marca);
        fireTableRowsUpdated(indiceLinha, indiceLinha);

    }

    public void removeVenda(int indiceLinha) {
        linhas.remove(indiceLinha);
        fireTableRowsDeleted(indiceLinha, indiceLinha);

    }
}
