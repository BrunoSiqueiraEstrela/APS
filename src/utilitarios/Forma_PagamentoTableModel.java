
package utilitarios;



import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.object.FormaPagamento;



public class Forma_PagamentoTableModel extends AbstractTableModel {

    private static final int COL_IDFORMAPAGAMENTO = 0;
    private static final int COL_DESCRICAO = 1;
    private static final int COL_STATUS = 2;
  

    List<FormaPagamento> linhas;
    private String[] colunas = new String[]{"CODIGO", "DESCRIÇÃO","STATUS"};

    public Forma_PagamentoTableModel(List<FormaPagamento> clientes) {
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
        if (columnIndex == COL_IDFORMAPAGAMENTO) {
            return Integer.class;
        }
        return String.class;
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Object getValueAt(int row, int column) {

       FormaPagamento m = linhas.get(row);

        if (column == COL_IDFORMAPAGAMENTO) {
            return m.getIdformapagamento();
        } else if (column == COL_DESCRICAO) {
            return m.getDescricao();
        } else if (column == COL_STATUS) {
            return m.getStatus();
        }
        
        return "";
    }

    public void setValueAt(Object aValue, int row, int column) {
        FormaPagamento u = linhas.get(row);
        if (column == COL_IDFORMAPAGAMENTO) {
            u.setIdformapagamento((Integer) aValue);
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

    public FormaPagamento getFormaPagamento(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void addFormaPagamento(FormaPagamento formaPagamento) {
        linhas.add(formaPagamento);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);

    }

    public void updateFormaPagamento(int indiceLinha, FormaPagamento marca) {
        linhas.set(indiceLinha, marca);
        fireTableRowsUpdated(indiceLinha, indiceLinha);

    }

    public void removeFormaPagamento(int indiceLinha) {
        linhas.remove(indiceLinha);
        fireTableRowsDeleted(indiceLinha, indiceLinha);

    }
}
