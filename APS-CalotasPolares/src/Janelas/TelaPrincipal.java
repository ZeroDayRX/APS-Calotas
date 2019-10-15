package Janelas;

import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.EventQueue;
import java.awt.FileDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class TelaPrincipal extends JFrame {

	private JPanel PanelPrincipal, PanelAnimais;
	private JMenuBar menubar;
	// Procurar o arquivo texto
	/*
	FileDialog fd = new FileDialog(this, "Choose a file", FileDialog.LOAD);
	 fd.setDirectory("C:\\"); 
	 fd.setFile("*.txt"); 
	 fd.setVisible(true);
	 String dir = fd.getDirectory();
	 String filename = fd.getFile();
	 if (filename == null) {
		 System.out.println("You cancelled the choice");
		 
	 }else {
		 System.out.println("You chose " + filename);
		 System.out.println("Dir: " + dir);
		 
	 }
	 */
	

	 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
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
	public TelaPrincipal() {
		setResizable(false);
		setTitle("APS Calotas Polares");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		PanelPrincipal = new JPanel();
		PanelAnimais = new JPanel();
		menubar = new JMenuBar();
		PanelPrincipal.setBackground(Color.WHITE);
		setJMenuBar(menubar);

		JMenu MenuImport = new JMenu("Importar");
		JMenu MenuFile = new JMenu("Opcoes");
		menubar.add(MenuImport);
		menubar.add(MenuFile);

		JMenuItem MenuItemCalotas = new JMenuItem("Calotas Polares");
		MenuItemCalotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelPrincipal.setBackground(Color.BLACK);


			}
		});
		MenuFile.add(MenuItemCalotas);

		JMenuItem MenuItemAnimais = new JMenuItem("Animais Do Artico");
		MenuFile.add(MenuItemAnimais);
		setContentPane(PanelPrincipal);
		PanelPrincipal.setLayout(null);
	}
}
