package Janelas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Database.BubbleSort;
import Database.DB;
import Database.LerArquivo;
import Database.SelectionSort;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Selection extends JFrame {

	private JPanel contentPane;
	public static JTable SelectionPanel;
	public static JScrollPane scrollPane = new JScrollPane();
	public static  String[] vetor;
	public static JLabel lblMiliSelection = new JLabel();
	public static DefaultTableModel modelBubble;

	public Selection() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 427, 458);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		SelectionPanel = new JTable();

		modelBubble = (DefaultTableModel) SelectionPanel.getModel();
		String[] coluna = {"Nome Comum"};
		modelBubble.setColumnIdentifiers(coluna);
		Ordene();
		SelectionPanel.updateUI();
		JScrollPane scrollPane_1 = new JScrollPane(SelectionPanel);
		scrollPane_1.setLocation(0, 26);
		scrollPane_1.setSize(411, 359);
		contentPane.add(scrollPane_1);
		
		JLabel lblSelectionSort = new JLabel("SelectionSort");
		lblSelectionSort.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSelectionSort.setBounds(163, 5, 111, 14);
		contentPane.add(lblSelectionSort);
		
		JLabel lblTempoSelection = new JLabel("Tempo: ");
		lblTempoSelection.setBounds(10, 394, 46, 14);
		contentPane.add(lblTempoSelection);
		

		lblMiliSelection.setBounds(55, 394, 156, 14);
		contentPane.add(lblMiliSelection);
		
		JButton btnExportSelection = new JButton("Export");
		btnExportSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int y =0;y<SelectionPanel.getRowCount();y++){
					String linha = "";
					for(int x =0;x<SelectionPanel.getColumnCount();x++) {
						linha = linha+SelectionPanel.getValueAt(y, x)+";";
					}
					LerArquivo.p.push(linha);
					LerArquivo.f.push(linha);
				}
				LerArquivo.gravar("SelectionSort");
				
				
			}
			
		});
		btnExportSelection.setBounds(322, 390, 89, 23);
		contentPane.add(btnExportSelection);
	}

	public static void Ordene(){
		int i = 0;
		SelectionSort s = new SelectionSort();
		DB.connect("banco.db");
		ResultSet rs = DB.query("select count(Id_Animal) as num from Animal");
		ResultSet rs2 = DB.query("select * from Animal");
		try{
			vetor = new String[rs.getInt("num")];
			while(rs2.next()){
				String nome = rs2.getString("NomeComum");
				vetor[i] = nome.toLowerCase().trim();
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String[] aux = s.selectionSort(vetor);
		lblMiliSelection.setText(s.getTempoExecucao()+" MiliSegundos");

		for(int i2 = 0;i2<aux.length;i2++){

			String aux2 = aux[i2];
			Object[] obj = {aux2};
			modelBubble.addRow(obj);
		}
	}

}
