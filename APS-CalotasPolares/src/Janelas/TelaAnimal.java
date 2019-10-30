package Janelas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FileDialog;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Database.*;

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
import javax.swing.Box;
import java.awt.Insets;
import javax.swing.JTextField;

public class TelaAnimal extends JFrame {
	
	private JPanel contentPane;
	private JTextField edtCod;
	private JTextField edtNComum;
	private JTextField edtHabitat;
	private JTextField edtNCientifico;
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		

		JTabbedPane tabPanel = new JTabbedPane(JTabbedPane.TOP);
		tabPanel.setBounds(0, 0, 584, 461);
		contentPane.add(tabPanel);
	    final Object[][] dados = null;
	    final MyTableModel myTableModel = new MyTableModel(dados);
		
		JTabbedPane TabCalotas = new JTabbedPane(JTabbedPane.TOP);
		tabPanel.addTab("Calotas polares", null, TabCalotas, null);
		
		JTabbedPane TabAnimais = new JTabbedPane(JTabbedPane.TOP);
		tabPanel.addTab("Animais", null, TabAnimais, null);
		
				JPanel TabConsultaAnimais = new JPanel();
				TabAnimais.addTab("Consulta", null, TabConsultaAnimais, null);
				TabConsultaAnimais.setLayout(null);
				
						JPanel pnlHeader = new JPanel();
						pnlHeader.setBounds(0, 0, 562, 86);
						TabConsultaAnimais.add(pnlHeader);
						pnlHeader.setLayout(null);
						
								JLabel lblTitle = new JLabel("Consulta de Animais");
								lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
								lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
								lblTitle.setBounds(121, 11, 327, 29);
								pnlHeader.add(lblTitle);
																												
												JPanel pnlTable = new JPanel();
												pnlTable.setBounds(0, 86, 569, 347);
												TabConsultaAnimais.add(pnlTable);
												
														pnlTable.setLayout(new BorderLayout(0, 0));
														JTable tableConsulta = new javax.swing.JTable(null);
														tableConsulta.setModel(myTableModel);
														pnlTable.add(new JScrollPane(tableConsulta), BorderLayout.CENTER);
														
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
						final MyTableModel tableModel = (MyTableModel) tableConsulta.getModel();
						tableModel.setDados(dados);
					    // notifica o componente de que houve altera��o, para que ele atualize considerando agora os novos dados
					    tableConsulta.updateUI();

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
		});
		btnAbrir.setBounds(22, 52, 89, 23);
		pnlHeader.add(btnAbrir);
		
		JPanel TabCadAnimais = new JPanel();
		TabAnimais.addTab("Cadastro", null, TabCadAnimais, null);
		TabCadAnimais.setLayout(null);
		
		JPanel pnlCadHeader = new JPanel();
		pnlCadHeader.setLayout(null);
		pnlCadHeader.setBounds(0, 0, 562, 171);
		TabCadAnimais.add(pnlCadHeader);
		
		JLabel lblCadastroDeAnimais = new JLabel("Cadastro de Animais");
		lblCadastroDeAnimais.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastroDeAnimais.setFont(new Font("Arial", Font.BOLD, 24));
		lblCadastroDeAnimais.setBounds(121, 10, 327, 29);
		pnlCadHeader.add(lblCadastroDeAnimais);
		
		JButton btnCadReset = new JButton("Limpar");
		btnCadReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dialogButton = JOptionPane.showConfirmDialog(null, "Realmente deseja apagar todos os dados da tabela ?");
	            if(dialogButton == JOptionPane.YES_OPTION) {
	            	for(int x = tableConsulta.getRowCount() - 1; x >=0; x--) {
	            		((MyTableModel) tableConsulta.getModel()).removeRow(x);
	            	}
	            if(dialogButton == JOptionPane.NO_OPTION) {
	                  JOptionPane.showMessageDialog(null, "Opera��o Cancelada.");
	                }
	              }
				tableConsulta.updateUI();
			}
		});
		btnCadReset.setBounds(430, 137, 89, 23);
		pnlCadHeader.add(btnCadReset);
		
		JLabel lblNewLabel = new JLabel("Id_Animal");
		lblNewLabel.setBounds(23, 50, 83, 14);
		pnlCadHeader.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nome Comum");
		lblNewLabel_1.setBounds(23, 92, 86, 14);
		pnlCadHeader.add(lblNewLabel_1);
		
		edtCod = new JTextField();
		edtCod.setBounds(23, 67, 86, 20);
		pnlCadHeader.add(edtCod);
		edtCod.setColumns(10);
		
		edtNComum = new JTextField();
		edtNComum.setColumns(10);
		edtNComum.setBounds(23, 108, 174, 20);
		pnlCadHeader.add(edtNComum);
		
		edtHabitat = new JTextField();
		edtHabitat.setColumns(10);
		edtHabitat.setBounds(219, 108, 227, 20);
		pnlCadHeader.add(edtHabitat);
		
		edtNCientifico = new JTextField();
		edtNCientifico.setColumns(10);
		edtNCientifico.setBounds(219, 67, 227, 20);
		pnlCadHeader.add(edtNCientifico);
		
		JLabel lblNomeCientfico = new JLabel("Nome Cient\u00EDfico");
		lblNomeCientfico.setBounds(219, 50, 123, 14);
		pnlCadHeader.add(lblNomeCientfico);
		
		JLabel lblHabitat = new JLabel("Habitat");
		lblHabitat.setBounds(219, 92, 87, 14);
		pnlCadHeader.add(lblHabitat);
		
		JButton btnPilha = new JButton("Pilha");
		btnPilha.setMargin(new Insets(2, 2, 2, 2));
		btnPilha.setBounds(212, 137, 67, 23);
		pnlCadHeader.add(btnPilha);
		
		JButton btnFila = new JButton("Fila");
		btnFila.setMargin(new Insets(2, 2, 2, 2));
		btnFila.setBounds(289, 137, 67, 23);
		pnlCadHeader.add(btnFila);
		
		JPanel pnlCadTable = new JPanel();
		pnlCadTable.setBounds(0, 169, 564, 226);
		TabCadAnimais.add(pnlCadTable);
		pnlCadTable.setLayout(new BorderLayout(0, 0));
		JTable cadTable = new javax.swing.JTable(null);
		cadTable.setModel(myTableModel);
		pnlCadTable.add(new JScrollPane(cadTable), BorderLayout.CENTER);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TabAnimais.setSelectedIndex(1);
			}
		});
		btnNovo.setBounds(133, 51, 89, 23);
		pnlHeader.add(btnNovo);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				final MyTableModel tableModel = (MyTableModel) tableConsulta.getModel();
				tableModel.setDados(new Object[0][4]);		
				tableConsulta.updateUI();
			}
		});
		btnLimpar.setBounds(465, 51, 89, 23);
		pnlHeader.add(btnLimpar);
		
		JButton btnInc = new JButton("Adicionar");
		btnInc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(edtCod.getText().isEmpty() || edtNComum.getText().isEmpty() || edtNCientifico.getText().isEmpty() || edtHabitat.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Campos em branco. Por favor preencha todos os campos.");
					
				}else {
					int i = cadTable.getRowCount();
					final Object[][] dados = new Object[i+1][4];										
					    dados[i][0] = edtCod.getText();
					    dados[i][1] = edtNComum.getText();
					    dados[i][2] = edtNCientifico.getText();
					    dados[i][3] = edtHabitat.getText();
					
					final MyTableModel tableModel = (MyTableModel) cadTable.getModel();
					tableModel.setDados(dados);
				    // notifica o componente de que houve altera��o, para que ele atualize considerando agora os novos dados
					cadTable.updateUI();
					edtCod.setText(null);
					edtNComum.setText(null);
					edtNCientifico.setText(null);
					edtHabitat.setText(null);
				}

			}
		});
		
		JButton btnDel = new JButton("Deletar");
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((MyTableModel) cadTable.getModel()).removeRow(cadTable.getSelectedRow());
				cadTable.updateUI();	
			}
		});
		btnDel.setMargin(new Insets(2, 2, 2, 2));
		btnDel.setBounds(100, 137, 67, 23);
		pnlCadHeader.add(btnDel);
		
		btnInc.setMargin(new Insets(2, 2, 2, 2));
		btnInc.setBounds(10, 137, 80, 23);
		pnlCadHeader.add(btnInc);
						
	}
}
