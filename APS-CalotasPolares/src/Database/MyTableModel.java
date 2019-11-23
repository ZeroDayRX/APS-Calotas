package Database;

import javax.swing.table.DefaultTableModel;

public class MyTableModel extends DefaultTableModel {

/*  public MyTableModel(Object[] dados) {
        super(dados, new String[] {"Id_Animal", "Nome Comum", "Nome Científico","Habitat"});
    }
*/
    // usaremos este método para atualizar os dados da JTable
    public void setDados(Object[] dados) {
        dataVector = super.convertToVector(dados);
    }

    private final Class[] types = new Class[] {java.lang.String.class, java.lang.String.class, java.lang.String.class,java.lang.String.class};
    private final boolean[] canEdit = new boolean[] {false, false, false};

    @Override
    public Class getColumnClass(final int columnIndex) {
        return types[columnIndex];
    }

    @Override
    public boolean isCellEditable(final int rowIndex, final int columnIndex) {
        return canEdit[columnIndex];
    }

}