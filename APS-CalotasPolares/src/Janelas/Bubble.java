package Janelas;

import Database.BubbleSort;
import Database.DB;
import Database.LerArquivo;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.xml.transform.Result;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Bubble extends JFrame {

	private JPanel contentPane;
	public static JTable BubblePanel;
	public static JScrollPane scrollPane = new JScrollPane();
	public static  String[] vetor;
	public static JLabel lblMiliBubble = new JLabel();
	public static DefaultTableModel modelBubble;

	
	public Bubble() throws SQLException {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 427, 458);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		BubblePanel = new JTable();

		modelBubble = (DefaultTableModel) BubblePanel.getModel();
		String[] coluna = {"Nome Comum"};
		modelBubble.setColumnIdentifiers(coluna);
		Ordene();
		BubblePanel.updateUI();
		JScrollPane scrollPane_1 = new JScrollPane(BubblePanel);
		scrollPane_1.setLocation(0, 26);
		scrollPane_1.setSize(411, 359);
		contentPane.add(scrollPane_1);
		
		JLabel lblBubblesort = new JLabel("BubbleSort");
		lblBubblesort.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBubblesort.setBounds(163, 5, 87, 14);
		contentPane.add(lblBubblesort);
		
		JLabel lblTempoBubble = new JLabel("Tempo: ");
		lblTempoBubble.setBounds(10, 394, 46, 14);
		contentPane.add(lblTempoBubble);
		

		lblMiliBubble.setBounds(55, 394, 156, 14);
		contentPane.add(lblMiliBubble);
		
		JButton btnExportBubble = new JButton("Export");
		btnExportBubble.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for(int y =0;y<BubblePanel.getRowCount();y++){
					String linha = "";
					for(int x =0;x<BubblePanel.getColumnCount();x++) {
						linha = linha+BubblePanel.getValueAt(y, x)+";";
					}
					LerArquivo.p.push(linha);
					LerArquivo.f.push(linha);
				}
				LerArquivo.gravar("BubbleSort");
				
				
			}
		});
		btnExportBubble.setBounds(322, 390, 89, 23);
		contentPane.add(btnExportBubble);
	}
	public static void Ordene(){
		int i = 0;
		BubbleSort b = new BubbleSort();
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
		b.setVetor(vetor);
		String[] aux = b.getVetor();
		lblMiliBubble.setText(b.getTempoExecucao()+" MiliSegundos");

		for(int i2 = 0;i2<aux.length;i2++){

			String aux2 = aux[i2];
			Object[] obj = {aux2};
			modelBubble.addRow(obj);
		}
	}
}

