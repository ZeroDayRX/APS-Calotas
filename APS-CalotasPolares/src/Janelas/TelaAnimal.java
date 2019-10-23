package Janelas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FileDialog;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

import Database.LerArquivo;
import Database.MyTableModel;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaAnimal extends JFrame {
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAnimal frame = new TelaAnimal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaAnimal() {
		//setBounds(new Rectangle(0, 0, 600, 500));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		

		JTabbedPane tabPanel = new JTabbedPane(JTabbedPane.TOP);
		tabPanel.setBounds(0, 0, 584, 461);
		contentPane.add(tabPanel);

		JPanel pnl1 = new JPanel();
		tabPanel.addTab("Consulta", null, pnl1, null);
		pnl1.setLayout(null);

		JPanel pnlHeader = new JPanel();
		pnlHeader.setBounds(0, 0, 579, 86);
		pnl1.add(pnlHeader);
		pnlHeader.setLayout(null);

		JLabel lblTitle = new JLabel("Animais");
		lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(123, 11, 327, 29);
		pnlHeader.add(lblTitle);

		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//
			}
		});
		btnNovo.setBounds(133, 51, 89, 23);
		pnlHeader.add(btnNovo);

		JPanel pnl2 = new JPanel();
		tabPanel.addTab("Cadastro", null, pnl2, null);

		JPanel pnlTable = new JPanel();
		pnlTable.setBounds(0, 86, 569, 347);
		pnl1.add(pnlTable);

		pnlTable.setLayout(new BorderLayout(0, 0));
	    JTable table = new javax.swing.JTable(null);
	    final Object[][] dados = null;
	    final MyTableModel myTableModel = new MyTableModel(dados);
		table.setModel(myTableModel);
		pnlTable.add(new JScrollPane(table));
		

		JButton btnAbrir = new JButton("Abrir");
		btnAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Procurar o arquivo texto
				FileDialog fd = new FileDialog(TelaAnimal.this, "Choose a file", FileDialog.LOAD);
				fd.setDirectory("C:\\");
				fd.setVisible(true);
				String dir = fd.getDirectory();
				String filename = fd.getFile();
				if (filename == null) {
					JOptionPane.showMessageDialog(null, "Nenhum Arquivo selecionado");

				} else {

					try {
						FileReader f = new FileReader(dir + filename);
						BufferedReader lerArq = new BufferedReader(f);
						final List<String> lines = LerArquivo.carregarLinhas(dir + filename);
                        int i2 = lines.size();
                        final Object[][] dados = new Object[i2][4];
						for (int i = 0; i < lines.size(); i++) {							
						    final String[] data = LerArquivo.lerDados(";", lines.get(i));
						    dados[i][0] = data[0];
						    dados[i][1] = data[1];
						    dados[i][2] = data[2];
						    dados[i][3] = data[3];
						   
						}
						final MyTableModel tableModel = (MyTableModel) table.getModel();
						tableModel.setDados(dados);
					    // notifica o componente de que houve alteração, para que ele atualize considerando agora os novos dados
					    table.updateUI();

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
		});
		btnAbrir.setBounds(22, 52, 89, 23);
		pnlHeader.add(btnAbrir);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
								
			}
		});
		btnLimpar.setBounds(465, 51, 89, 23);
		pnlHeader.add(btnLimpar);
	}
}
