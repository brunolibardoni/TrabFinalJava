package br.edu.ifcvideira.controllers.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.RowFilter;

import java.awt.Component;
import javax.swing.Box;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.xml.soap.Text;

import com.mysql.fabric.xmlrpc.base.Array;

import br.edu.ifcvideira.DAOs.CadastroClienteDao;
import br.edu.ifcvideira.beans.CadastroCliente;

import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import java.awt.Window.Type;

public class ClienteViwe extends JFrame {

	long time = System.currentTimeMillis();
	Timestamp timestamp = new Timestamp(time);
	
	private List<Object> cadastrocliente = new ArrayList<Object>();

	CadastroClienteDao ccd = new CadastroClienteDao();
	CadastroCliente cc = new CadastroCliente();
	
	ClienteViwe2 cv2 = new ClienteViwe2();
	ClienteViwe3 cv3 = new ClienteViwe3();
	
	private JPanel contentPane;
	private JTextField tfNomeCliente;
	private JTextField tfCpfCliente;
	private JTextField tfTelefoneCliente;
	private JTextField tfCidadeCliente;
	private JTextField tfCodigoCliente;
	private JTextField tfBuscarCodigo;
	private JTextField tfBuscarNome;
	private JTable table;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienteViwe frame = new ClienteViwe();
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
	public ClienteViwe() {
		setTitle("Aplicativo Comercial Libardoni - ACL");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1016, 625);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAplicativoComercialLibardoni = new JLabel("APLICATIVO COMERCIAL LIBARDONI - ACL");
		lblAplicativoComercialLibardoni.setFont(new Font("Franklin Gothic Medium Cond", Font.ITALIC, 22));
		lblAplicativoComercialLibardoni.setBounds(330, 11, 370, 25);
		contentPane.add(lblAplicativoComercialLibardoni);
		
		JButton jbCadastroCliente = new JButton("CADASTRO CLIENTE");
		jbCadastroCliente.setBounds(83, 64, 157, 23);
		contentPane.add(jbCadastroCliente);
		
		JButton jbCadastroProduto = new JButton("CADASTRO PRODUTO");
		jbCadastroProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				cv2.setVisible(true);
			}
			
		});
		jbCadastroProduto.setBounds(416, 64, 162, 23);
		contentPane.add(jbCadastroProduto);
		
		JButton jbCompras = new JButton("COMPRAS");
		jbCompras.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				cv3.setVisible(true);
				
			}
		});
		
		
		jbCompras.setBounds(755, 64, 141, 23);
		contentPane.add(jbCompras);
		
		JLabel lblNomeDoCliente = new JLabel("Nome do Cliente:");
		lblNomeDoCliente.setBounds(20, 135, 109, 23);
		contentPane.add(lblNomeDoCliente);
		
		tfNomeCliente = new JTextField();
		tfNomeCliente.setBounds(154, 136, 86, 20);
		contentPane.add(tfNomeCliente);
		tfNomeCliente.setColumns(10);
		
		JLabel lblCpfDoCliente = new JLabel("CPF do Cliente:");
		lblCpfDoCliente.setBounds(20, 180, 91, 23);
		contentPane.add(lblCpfDoCliente);
		
		tfCpfCliente = new JTextField();
		tfCpfCliente.setColumns(10);
		tfCpfCliente.setBounds(154, 181, 86, 20);
		contentPane.add(tfCpfCliente);
		
		JLabel lblTelefoneDoCliente = new JLabel("Telefone do Cliente:");
		lblTelefoneDoCliente.setBounds(20, 230, 124, 23);
		contentPane.add(lblTelefoneDoCliente);
		
		tfTelefoneCliente = new JTextField();
		tfTelefoneCliente.setColumns(10);
		tfTelefoneCliente.setBounds(154, 231, 86, 20);
		contentPane.add(tfTelefoneCliente);
		
		tfCidadeCliente = new JTextField();
		tfCidadeCliente.setColumns(10);
		tfCidadeCliente.setBounds(154, 281, 86, 20);
		contentPane.add(tfCidadeCliente);
		
		JLabel lblCidadeDoCliente = new JLabel("Cidade do Cliente: ");
		lblCidadeDoCliente.setBounds(20, 280, 124, 23);
		contentPane.add(lblCidadeDoCliente);
		
		JLabel lblCdigo = new JLabel("C\u00D3DIGO:");
		lblCdigo.setBounds(320, 135, 80, 25);
		contentPane.add(lblCdigo);
		
		tfCodigoCliente = new JTextField();
		tfCodigoCliente.setEditable(false);
		tfCodigoCliente.setBounds(379, 137, 82, 20);
		contentPane.add(tfCodigoCliente);
		tfCodigoCliente.setColumns(10);
		
		
		
		
		// EXCLUSÃO
		
		JButton jbExcluir = new JButton("EXCLUIR");
		jbExcluir.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if (table.getSelectedRow()!= -1) {
					Object[] exclusao = {"Prosseguir", "Cancelar"};
					if(JOptionPane.showOptionDialog(null, "Deseja proseguir com a exclusão do registro?\n"
							+ "Código: "+table.getValueAt(table.getSelectedRow(), 0) + "\n"
							+ "Nome: "+table.getValueAt(table.getSelectedRow(), 1), null,
							JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, exclusao, exclusao[0]) == 0){
						try {
					
						cc.setId(Integer.parseInt(tfCodigoCliente.getText()));
						
						ccd.deletarCliente(cc);
						
						atualizarTab();
						limpar();
						
					}catch (Exception f) {
						JOptionPane.showMessageDialog(null, f.getMessage());
					}

				}
		
			} else {
				JOptionPane.showMessageDialog(null, "Nenhuma linha selecionada");
				}
			}
		});
		
		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent arg0) {
				atualizarTab();
				limpar();
			}
		});
		
		
		// ALTERAR //
		
		jbExcluir.setBounds(320, 230, 141, 23);
		contentPane.add(jbExcluir);
		
		JButton jbAlterar = new JButton("ALTERAR");
		jbAlterar.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				
				if (table.getSelectedRow() != -1) {
					try {
												
						cc.setId(Integer.parseInt(tfCodigoCliente.getText()));
						cc.setNome(tfNomeCliente.getText());
						cc.setCpf(tfCpfCliente.getText());
						cc.setTelefone(tfTelefoneCliente.getText());
						cc.setCidade(tfCidadeCliente.getText());
						
						ccd.AlterarCliente(cc);
						
						atualizarTab();
						
						JOptionPane.showMessageDialog(null, "Registro Alterado com Sucesso!");
						
						limpar();
						
					}catch (Exception b) {
						JOptionPane.showMessageDialog(null, b.getMessage());
				
					}
				}else {
					JOptionPane.showMessageDialog(null, "Nenhuma linha selecionada");
				}
				
				
				
			}
		});
		jbAlterar.setBounds(320, 280, 141, 23);
		contentPane.add(jbAlterar);
		
		JButton jbLimpar = new JButton("LIMPAR");
		jbLimpar.addActionListener(new ActionListener() {
			
			
			
			
		
			// LIMPAR //
			
			public void actionPerformed(ActionEvent e) {
				limpar();
				
			}
			
		});
		
		
		
		
		jbLimpar.setBounds(320, 180, 141, 23);
		contentPane.add(jbLimpar);
		
		JButton jbCadastrar = new JButton("CADASTRAR");
		jbCadastrar.addActionListener(new ActionListener() {
			
			
			
			
			
			// ATRIBUINDO VALORES NO CADASTROCLIENTE
	
			
			public void actionPerformed(ActionEvent arg0) {
				
			try {
				
				cc.setNome(tfNomeCliente.getText());
				cc.setCpf(tfCpfCliente.getText());
				cc.setTelefone(tfTelefoneCliente.getText());
				cc.setCidade(tfCidadeCliente.getText());
				System.out.println(timestamp);
				
				//CHAMANDO NO BANCO DE DADOS
			
				ccd.CadastrarCliente(cc);
			}catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			
			JOptionPane.showMessageDialog(null, "Cliente Cadastrado!");
			limpar();
			atualizarTab();
			
		}
	});
		
		
		
		
		
		
		jbCadastrar.setBounds(197, 336, 141, 68);
		contentPane.add(jbCadastrar);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 111, 506, 2);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(530, 395, 0, -282);
		contentPane.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 456, 506, 2);
		contentPane.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(515, 111, 1, 347);
		contentPane.add(separator_3);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setOrientation(SwingConstants.VERTICAL);
		separator_4.setBounds(10, 111, 1, 347);
		contentPane.add(separator_4);
		
		JLabel lblNewLabel = new JLabel("BUSCAR CLIENTE");
		lblNewLabel.setBounds(736, 124, 109, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblCdigo_1 = new JLabel("C\u00F3digo: ");
		lblCdigo_1.setBounds(569, 164, 46, 14);
		contentPane.add(lblCdigo_1);
		
		tfBuscarCodigo = new JTextField();
		tfBuscarCodigo.addCaretListener(new CaretListener() {
			
			
			public void caretUpdate(CaretEvent e) {
				
				TableRowSorter<TableModel> filtrar = null;
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				filtrar = new TableRowSorter<TableModel>(model);
				table.setRowSorter(filtrar);
				
				if(tfBuscarCodigo.getText().length() == 0) {
					filtrar.setRowFilter(null);
				}else {
					filtrar.setRowFilter(RowFilter.regexFilter(tfBuscarCodigo.getText(), 0));
				}
				
			}
		});
		
		
		
		tfBuscarCodigo.setBounds(647, 161, 96, 20);
		contentPane.add(tfBuscarCodigo);
		tfBuscarCodigo.setColumns(10);
		
		tfBuscarNome = new JTextField();
		tfBuscarNome.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				
				TableRowSorter<TableModel> filtrar = null;
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				filtrar = new TableRowSorter<TableModel>(model);
				table.setRowSorter(filtrar);
				
				if(tfBuscarNome.getText().length() == 0) {
					filtrar.setRowFilter(null);
				}else {
					filtrar.setRowFilter(RowFilter.regexFilter("(?i)" + tfBuscarNome.getText(), 1));
				}
			}
		
			
		});
		tfBuscarNome.setColumns(10);
		tfBuscarNome.setBounds(857, 161, 96, 20);
		contentPane.add(tfBuscarNome);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(779, 164, 46, 14);
		contentPane.add(lblNome);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(569, 245, 410, 200);
		contentPane.add(scrollPane_1);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				setaCamposDaTabela();
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Nome", "CPF", "Telefone", "Cidade"
			}
		));
		scrollPane_1.setViewportView(table);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(1084, 395, -94, -282);
		contentPane.add(separator_5);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setBounds(558, 456, 432, 2);
		contentPane.add(separator_6);
		
		JSeparator separator_7 = new JSeparator();
		separator_7.setOrientation(SwingConstants.VERTICAL);
		separator_7.setBounds(1069, 111, -79, 347);
		contentPane.add(separator_7);
		
		JSeparator separator_8 = new JSeparator();
		separator_8.setOrientation(SwingConstants.VERTICAL);
		separator_8.setBounds(558, 111, 1, 347);
		contentPane.add(separator_8);
		
		JSeparator separator_9 = new JSeparator();
		separator_9.setOrientation(SwingConstants.VERTICAL);
		separator_9.setBounds(989, 111, 1, 347);
		contentPane.add(separator_9);
		
		JSeparator separator_10 = new JSeparator();
		separator_10.setBounds(558, 111, 432, 2);
		contentPane.add(separator_10);
		
		JLabel lblNewLabel_1 = new JLabel("Cliente Cadastrados:");
		lblNewLabel_1.setBounds(569, 220, 131, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton jbSair = new JButton("SAIR");
		jbSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		jbSair.setBounds(424, 485, 221, 68);
		contentPane.add(jbSair);
	}
	
	
	
	
	
	
	public void atualizarTab() {
		try {
			cadastrocliente = ccd.buscarClientes();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setNumRows(0);
			
			for(int a=0; a!=cadastrocliente.size(); a++) {
				model.addRow((Object[]) cadastrocliente.get(a));
			}
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	
	
	
	
	
	public void limpar() {
		tfNomeCliente.setText(null);
		tfCidadeCliente.setText(null);
		tfCpfCliente.setText(null);
		tfTelefoneCliente.setText(null);
	try {
		tfCodigoCliente.setText(String.valueOf(ccd.RetornarProxCdg()));
		
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	
	
	public void setaCamposDaTabela() {
	tfCodigoCliente.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 0)));
	tfNomeCliente.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 1)));
	tfCpfCliente.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 2)));
	tfTelefoneCliente.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 3)));
	tfCidadeCliente.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 4)));
		
	}
	
	
	
	
	
}




