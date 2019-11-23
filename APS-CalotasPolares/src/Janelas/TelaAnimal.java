package Janelas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FileDialog;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Database.*;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileSystem;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

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
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

public class TelaAnimal extends JFrame {

	private JPanel contentPane;
	private JTextField edtCod;
	private JTextField edtNComum;
	private JTextField edtHabitat;
	private JTextField edtNCientifico;
	
	JTabbedPane tabPanel,TabCalotas,TabAnimais;
	JPanel TabConsultaAnimais,pnlHeader,lblTitle;
	



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
	public static void LimparTable() {
		
	}
	public TelaAnimal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		tabPanel = new JTabbedPane(JTabbedPane.TOP);
		tabPanel.setBounds(0, 0, 584, 461);
		contentPane.add(tabPanel);

		TabCalotas = new JTabbedPane(JTabbedPane.TOP);
		tabPanel.addTab("Calotas polares", null, TabCalotas, null);

		TabAnimais = new JTabbedPane(JTabbedPane.TOP);
		tabPanel.addTab("Animais", null, TabAnimais, null);

		TabConsultaAnimais = new JPanel();
		TabAnimais.addTab("Consulta", null, TabConsultaAnimais, null);
		TabConsultaAnimais.setLayout(null);

		pnlHeader = new JPanel();
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
		
		JTable cadTable = new javax.swing.JTable(null);
		JTable tableConsulta = new javax.swing.JTable(null);
		
		DefaultTableModel tableModel = (DefaultTableModel) cadTable.getModel();
		DefaultTableModel tableModel2 = (DefaultTableModel) tableConsulta.getModel();
		String[] colunas = { "ID", "Nome Comum", "Nome Cientifico", "Habitat"};
		tableModel.setColumnIdentifiers(colunas);
		tableModel2.setColumnIdentifiers(colunas);

		pnlTable.setLayout(new BorderLayout(0, 0));
		tableConsulta.setModel(tableModel);
		pnlTable.add(new JScrollPane(tableConsulta), BorderLayout.CENTER);
		
		cadTable.setModel(tableModel2);
        
		JButton btnAbrir = new JButton("Abrir");
		btnAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Procurar o arquivo texto
				FileDialog fd = new FileDialog(TelaAnimal.this, "Choose a file", FileDialog.LOAD);
				fd.setDirectory("C:\\");
				fd.setFile("*.txt");
				fd.setVisible(true);
				String dir = fd.getDirectory();
				String filename = fd.getFile();
				if (filename == null) {
					JOptionPane.showMessageDialog(null, "Nenhum Arquivo selecionado");

				} else {

					try {
						DB.connect("banco.db");
						final List<String> lines = LerArquivo.carregarLinhas(dir + filename);
						String[] i2 = lines.toString().split(";");
						ArrayList<Object> array = new ArrayList<Object>();
			
						for(int x =0;x<i2.length;x++) {
							array.add(i2[x]);
						}
						System.out.println(array);
						String Vetor2[] = array.toString().split(",");
						String id, Nome,NomeC,Habitat;
						
						for(int x = 0;x<Vetor2.length;x=x+5) {
							id = Vetor2[x];
							Nome = Vetor2[x+1];
							NomeC = Vetor2[x+2];
							Habitat = Vetor2[x+3];
							String sub = id.substring(0,2); 
							if (sub.equals("[[")) {
								id = id.substring(2);
							}
							Object[] objeto = {id,Nome,NomeC,Habitat};
							tableModel.addRow(objeto);
							tableModel2.addRow(objeto);
							insertDB(id,Nome,NomeC,Habitat);
						}
						 tableConsulta.updateUI();
						 cadTable.updateUI();
						 JOptionPane.showMessageDialog(null, "Arquivo importado com sucesso !");
						
						// notifica o componente de que houve alteração, para que ele atualize
						// considerando agora os novos dados
						
						
					} catch (Exception e) {
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

		JPanel pnlCadTable = new JPanel();
		pnlCadTable.setBounds(0, 169, 564, 226);
		TabCadAnimais.add(pnlCadTable);
		pnlCadTable.setLayout(new BorderLayout(0, 0));

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
				int dialogButton = JOptionPane.showConfirmDialog(null,
						"Realmente deseja apagar todos os dados da tabela ?");
				if (dialogButton == JOptionPane.YES_OPTION) {
                    DB.connect("banco.db");
					for (int x = tableModel.getRowCount() - 1; x >= 0; x--) {
						String id = tableModel.getValueAt(x, 0).toString();
						deleteDB(id);
						tableModel.removeRow(x);
						tableModel2.removeRow(x);
					}
					
					if (dialogButton == JOptionPane.NO_OPTION) {
						JOptionPane.showMessageDialog(null, "Operação Cancelada.");
					}
				}
				tableConsulta.updateUI();
				//cadTable.updateUI();
			}
		});
		btnCadReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dialogButton = JOptionPane.showConfirmDialog(null,
						"Realmente deseja apagar todos os dados da tabela ?");
				if (dialogButton == JOptionPane.YES_OPTION) {
					DB.connect("banco.db");
					for (int x = tableConsulta.getRowCount() - 1; x >= 0; x--) {
						String id = tableModel.getValueAt(x, 0).toString();
						deleteDB(id);
						tableModel.removeRow(x);
						tableModel2.removeRow(x);
					}
					
				}else{
					System.out.println("Cancelada");
					JOptionPane.showMessageDialog(null, "Operação Cancelada.");
				}
				tableConsulta.updateUI();
				cadTable.updateUI();
			}
		});
		
		btnLimpar.setBounds(465, 51, 89, 23);
		pnlHeader.add(btnLimpar);

		JButton btnInc = new JButton("Adicionar");
		btnInc.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {								
				if (edtCod.getText().isEmpty() || edtNComum.getText().isEmpty() || edtNCientifico.getText().isEmpty()
						|| edtHabitat.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Campos em branco. Por favor preencha todos os campos.");

				} else {
					DB.connect("banco.db");
					String id = edtCod.getText();
					String Name = edtNComum.getText();
					String NameC = edtNCientifico.getText();
					String Habitat = edtHabitat.getText();
					
					Object[] objeto = {id,Name,NameC,Habitat};
					tableModel.addRow(objeto);
					tableModel2.addRow(objeto);
					insertDB(id,Name,NameC,Habitat);
					
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
				DB.connect("banco.db");
				int linha = cadTable.getSelectedRow();
				String id = tableModel.getValueAt(linha, 0).toString();
				if(linha < 0) {
					tableModel.removeRow(cadTable.getRowCount()-1);
					tableModel2.removeRow(cadTable.getRowCount()-1);
				}else {
					tableModel.removeRow(linha);
					tableModel2.removeRow(linha);
					
				}
				deleteDB(id);
				cadTable.updateUI();
			}
		});
		btnDel.setMargin(new Insets(2, 2, 2, 2));
		btnDel.setBounds(100, 137, 67, 23);
		pnlCadHeader.add(btnDel);

		btnInc.setMargin(new Insets(2, 2, 2, 2));
		btnInc.setBounds(10, 137, 80, 23);
		pnlCadHeader.add(btnInc);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//LerArquivo.gravar("");
				for(int y =0;y<cadTable.getRowCount();y++){
					String linha = "";
					for(int x =0;x<cadTable.getColumnCount();x++) {
						linha = linha+cadTable.getValueAt(y, x)+";";
						
					}
					LerArquivo.p.insere(linha);
				}
				LerArquivo.gravar();
			}		
		});
		btnSave.setBounds(284, 137, 89, 23);
		pnlCadHeader.add(btnSave);
		
	}
	public void insertDB(String id,String nome, String nomec,String habitat) {
		String query = "insert into Animal ('Id_Animal','NomeComum','NomeCientifico','Habitat') values (";
		query = query + "'" + id + "',";
		query = query + "'" + nome + "',";
		query = query + "'" + nomec + "',";
		query = query + "'" + nomec + "');";
		DB.execQuery(query);
	}
	public void deleteDB(String id) {
		String query = "delete from Animal where Id_Animal = " + "'" + id +"'";
		DB.execQuery(query);
	}
}