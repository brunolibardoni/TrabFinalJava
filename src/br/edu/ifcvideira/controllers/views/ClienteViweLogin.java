package br.edu.ifcvideira.controllers.views;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.edu.ifcvideira.DAOs.LoginDao;
import br.edu.ifcvideira.beans.Usuarios;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class ClienteViweLogin extends JFrame {

	private JPanel contentPane;
	private JTextField tfUsuario;
	Usuarios us = new Usuarios();
	LoginDao ld = new LoginDao();
	
	
	ClienteViwe clv = new ClienteViwe();
	/**
	 * @wbp.nonvisual location=-45,289
	 * 
	 * 
	 */
	private final JComboBox comboBox = new JComboBox();
	private JPasswordField tfSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienteViweLogin frame = new ClienteViweLogin();
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
	public ClienteViweLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 403, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAplicativoComercialLibardoni = new JLabel("Login");
		lblAplicativoComercialLibardoni.setFont(new Font("Tahoma", Font.BOLD, 37));
		lblAplicativoComercialLibardoni.setBounds(10, 11, 339, 92);
		contentPane.add(lblAplicativoComercialLibardoni);
		
		JLabel lblUsuario = new JLabel("USUARIO:");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUsuario.setBounds(111, 114, 70, 14);
		contentPane.add(lblUsuario);
		
		tfUsuario = new JTextField();
		tfUsuario.setBounds(196, 114, 153, 20);
		contentPane.add(tfUsuario);
		tfUsuario.setColumns(10);
		
		JLabel lblSenha = new JLabel("SENHA:");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSenha.setBounds(127, 154, 70, 14);
		contentPane.add(lblSenha);
		
		JButton btnNewButton = new JButton("Entrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				us.setNome_usuario(tfUsuario.getText());
				us.setSenha_usuario(new String (tfSenha.getPassword()));
				
				try {
					ld.recebeDados(us);
					dispose();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(218, 216, 131, 48);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ClienteViweLogin.class.getResource("/br/edu/ifcvideira/img/Nuvola_apps_kgpg.png")));
		lblNewLabel.setBounds(10, 228, 153, 150);
		contentPane.add(lblNewLabel);
		
		tfSenha = new JPasswordField();
		tfSenha.setBounds(196, 152, 153, 20);
		contentPane.add(tfSenha);
	}
}
