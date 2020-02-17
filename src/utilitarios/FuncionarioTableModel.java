/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitarios;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.object.Funcionario;

/**
 *
 * @author DELTA
 */
public class FuncionarioTableModel extends AbstractTableModel  {
    
	private static final int COL_IDFUNCIONARIO = 0;
	private static final int COL_FKCARGO = 1;
	private static final int COL_NOME = 2;
	private static final int COL_DATANASCIMENTO = 3;
	private static final int COL_CPF = 4;
	private static final int COL_RG = 5;
	private static final int COL_SEXO = 6;
	private static final int COL_EMAIL = 7;
	private static final int COL_TELEFONE = 8;
	private static final int COL_CELULAR = 9;
	private static final int COL_DATACADASTRO = 10;
	private static final int COL_STATUS = 11;
	
	
	List<Funcionario> linhas;
    private String[] colunas = new String[]{"CODIGO","CARGO","NOME","Data de Nascimento","CPF","RG","Sexo","Email","Telefone",
    										"Celular","Data de Cadastro","Status"};
    

    public FuncionarioTableModel(List<Funcionario> clientes) {
        this.linhas = new ArrayList<>(clientes);
    }

        @Override
    public int getRowCount() {
        return linhas.size();
    }

        @Override
    public int getColumnCount() {
        return colunas.length;
    }

        @Override
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    }

        @Override
    public Class getColumnClass(int columnIndex) {
        if (columnIndex == COL_IDFUNCIONARIO) {
            return Integer.class;
        }
        return String.class;
    }

        @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

        @Override
    public Object getValueAt(int row, int column) {

    	Funcionario m = linhas.get(row);

            switch (column) {
                case COL_IDFUNCIONARIO:
                    return m.getIdfuncionario();
                case COL_FKCARGO:
                    return m.getFkcargo();
                case COL_NOME:
                    return m.getNome();
                case COL_DATANASCIMENTO:
                    return m.getDatanascimento();
                case COL_CPF:
                    return m.getCpf();
                case COL_RG:
                    return m.getRg();
                case COL_SEXO:
                    return m.getSexo();
                case COL_EMAIL:
                    return m.getEmail();
                case COL_TELEFONE:
                    return m.getTelefone();
                case COL_CELULAR:
                    return m.getCelular();
                case COL_DATACADASTRO:
                    return m.getDatacadastro();
                case COL_STATUS:
                    return m.getStatus();
                default:
                    break;
            }
        return "";
    }

        @Override
    public void setValueAt(Object aValue, int row, int column) {
    	
    	Funcionario u = linhas.get(row);
        
            switch (column) {
                case COL_IDFUNCIONARIO:
                    u.setIdfuncionario((Integer) aValue);
                    break;
                case COL_FKCARGO:
                    u.setFkcargo((int)aValue);
                    break;
                case COL_NOME:
                    u.setNome(aValue.toString());
                    break;
                case COL_DATANASCIMENTO:
                    u.setDatanascimento((java.sql.Date) aValue);
                    break;
                case COL_CPF:
                    u.setCpf(aValue.toString());
                    break;
                case COL_RG:
                    u.setRg(aValue.toString());
                    break;
                case COL_SEXO:
                    u.setSexo(aValue.toString());
                    break;
                case COL_EMAIL:
                    u.setEmail(aValue.toString());
                    break;
                case COL_TELEFONE:
                    u.setTelefone(aValue.toString());
                    break;
                case COL_CELULAR:
                    u.setCelular(aValue.toString());
                    break;
                case COL_DATACADASTRO:
                    u.setDatacadastro((java.sql.Date) aValue);
                    break;
                case COL_STATUS:
                    if(aValue.toString().equalsIgnoreCase("1")){
                        u.setStatus(true);
                    }else{
                        u.setStatus(false);
                    }       break;
                default:
                    break;
            }
    }

    public Funcionario getFuncionario(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void addFuncionario(Funcionario funcionario) {
        linhas.add(funcionario);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);

    }

    public void updateFuncionario(int indiceLinha, Funcionario marca) {
        linhas.set(indiceLinha, marca);
        fireTableRowsUpdated(indiceLinha, indiceLinha);

    }

    public void removeFuncionario(int indiceLinha) {
        linhas.remove(indiceLinha);
        fireTableRowsDeleted(indiceLinha, indiceLinha);

    }
	
}
