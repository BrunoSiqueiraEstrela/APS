package utilitarios;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.object.Cliente;

public class ClienteTableModel extends AbstractTableModel {

    private static final int COL_IDCLIENTE = 0;
    private static final int COL_NOME = 1;
    private static final int COL_CPF = 2;
    private static final int COL_EMAIL = 3;
    private static final int COL_DATACADASTRO = 4;
    private static final int COL_STATUS = 5;

    List<Cliente> linhas;
    private String[] colunas = new String[]{"CODIGO","NOME","CPF","EMAIL","DATA DE CADASTRO","STATUS"};

    public ClienteTableModel(List<Cliente> clientes) {
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

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (linhas.isEmpty()) {
            return Object.class;
        }
        return getValueAt(0, columnIndex).getClass();
    }
//    public Class<?> getColumnClass(int columnIndex) {
//        if (columnIndex == COL_IDCLIENTE) {
//            return Integer.class;
//        }
//        return String.class;
//    }
    
    



    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Object getValueAt(int row, int column) {

        Cliente m = linhas.get(row);

        if (column == COL_IDCLIENTE) {
            return m.getIdcliente();
        } else if (column == COL_NOME) {
            return m.getNome();
        } else if (column == COL_CPF) {
            return m.getCpf();
        } else if (column == COL_EMAIL) {
            return m.getEmail();
        } else if (column == COL_DATACADASTRO) {
            return m.getDatacadastro();
        } else if (column == COL_STATUS) {
            return m.getStatus();
        }

        return "";
    }

    public void setValueAt(Object aValue, int row, int column) {
        Cliente u = linhas.get(row);
        if (column == COL_IDCLIENTE) {
            u.setIdcliente((Integer) aValue);
        } else if (column == COL_NOME) {
            u.setNome(aValue.toString());
        } else if (column == COL_CPF) {
            u.setCpf(aValue.toString());
        } else if (column == COL_EMAIL) {
            u.setEmail(aValue.toString());
        } else if (column == COL_DATACADASTRO) {
            u.setDatacadastro((java.sql.Date) aValue);
        } else if (column == COL_STATUS) {
            if (aValue.toString().equalsIgnoreCase("1")) {
                u.setStatus(true);
            } else {
                u.setStatus(false);
            }
        }
    }

    public Cliente getCliente(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void addCliente(Cliente Cliente) {
        linhas.add(Cliente);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);

    }

    public void updateCliente(int indiceLinha, Cliente marca) {
        linhas.set(indiceLinha, marca);
        fireTableRowsUpdated(indiceLinha, indiceLinha);

    }

    public void removeCliente(int indiceLinha) {
        linhas.remove(indiceLinha);
        fireTableRowsDeleted(indiceLinha, indiceLinha);

    }
}
