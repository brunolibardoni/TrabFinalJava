package br.edu.ifcvideira.controllers.views;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import br.edu.ifcvideira.DAOs.CadastroProdutoDao;
import br.edu.ifcvideira.beans.CadastroProduto;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public class ClienteViwe2 extends JFrame {

	private JPanel contentPane;
	private JTextField tfNomeProduto;
	private JTextField tfQuantidadeProduto;
	private JTextField tfPrecoProduto;
	private JTextField tfTipoProduto;
	private JTextField tfCodigoProduto;
	private JTextField tfBuscarCodigoProduto;
	private JTextField tfBuscarNomeProduto;
	private JTable table;
	

	
	CadastroProdutoDao cpd = new CadastroProdutoDao();
	CadastroProduto cp = new CadastroProduto();
	
	private List<Object> cadastroproduto = new ArrayList<Object>();

	long time = System.currentTimeMillis();
	Timestamp timestamp = new Timestamp(time);
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					ClienteViwe2 frame = new ClienteViwe2();
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
	public ClienteViwe2() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1018, 602);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("APLICATIVO COMERCIAL LIBARDONI - ACL");
		label.setFont(new Font("Franklin Gothic Medium Cond", Font.ITALIC, 22));
		label.setBounds(356, 11, 370, 25);
		contentPane.add(label);
		
		JLabel lblNomeDoProduto = new JLabel("Nome do Produto:");
		lblNomeDoProduto.setBounds(28, 90, 109, 23);
		contentPane.add(lblNomeDoProduto);
		
		tfNomeProduto = new JTextField();
		tfNomeProduto.setColumns(10);
		tfNomeProduto.setBounds(162, 91, 86, 20);
		contentPane.add(tfNomeProduto);
		
		tfQuantidadeProduto = new JTextField();
		tfQuantidadeProduto.setColumns(10);
		tfQuantidadeProduto.setBounds(162, 136, 86, 20);
		contentPane.add(tfQuantidadeProduto);
		
		tfPrecoProduto = new JTextField();
		tfPrecoProduto.setColumns(10);
		tfPrecoProduto.setBounds(162, 186, 86, 20);
		contentPane.add(tfPrecoProduto);
		
		tfTipoProduto = new JTextField();
		tfTipoProduto.setColumns(10);
		tfTipoProduto.setBounds(162, 236, 86, 20);
		contentPane.add(tfTipoProduto);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(28, 235, 124, 23);
		contentPane.add(lblTipo);
		
		JLabel lblQuantidadeDoProduto = new JLabel("Quantidade:");
		lblQuantidadeDoProduto.setBounds(28, 135, 109, 23);
		contentPane.add(lblQuantidadeDoProduto);
		
		JLabel lblPreo = new JLabel("Pre\u00E7o:");
		lblPreo.setBounds(28, 185, 124, 23);
		contentPane.add(lblPreo);
		
		tfCodigoProduto = new JTextField();
		tfCodigoProduto.setEditable(false);
		tfCodigoProduto.setColumns(10);
		tfCodigoProduto.setBounds(401, 89, 82, 20);
		contentPane.add(tfCodigoProduto);
		
		JLabel label_5 = new JLabel("C\u00D3DIGO:");
		label_5.setBounds(342, 87, 80, 25);
		contentPane.add(label_5);
		
		JButton jbLimparProduto = new JButton("LIMPAR");
		jbLimparProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				limpar();
				
			}
		});
		jbLimparProduto.setBounds(342, 132, 141, 23);
		contentPane.add(jbLimparProduto);
		
		JButton jbExcluirProduto = new JButton("EXCLUIR");
		jbExcluirProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if (table.getSelectedRow()!= -1) {
					Object[] exclusao = {"Prosseguir", "Cancelar"};
					if(JOptionPane.showOptionDialog(null, "Deseja proseguir com a exclusão do registro?\n"
							+ "Código: "+table.getValueAt(table.getSelectedRow(), 0) + "\n"
							+ "Nome: "+table.getValueAt(table.getSelectedRow(), 1), null,
							JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, exclusao, exclusao[0]) == 0){
						try {
					
						cp.setId(Integer.parseInt(tfCodigoProduto.getText()));
						
						cpd.deletarProduto(cp);
						
						atualizarTabProduto();
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
		jbExcluirProduto.setBounds(342, 182, 141, 23);
		contentPane.add(jbExcluirProduto);
		
		JButton jbAlterarProduto = new JButton("ALTERAR");
		jbAlterarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if (table.getSelectedRow() != -1) {
					try {
												
						cp.setId(Integer.parseInt(tfCodigoProduto.getText()));
						cp.setNomeProduto(tfNomeProduto.getText());
						cp.setQuantidadeProduto(Integer.parseInt(tfQuantidadeProduto.getText()));
						cp.setPrecoProduto(Double.parseDouble(tfPrecoProduto.getText()));
						cp.setDescricao(tfTipoProduto.getText());
						
						cpd.AlterarProdutos(cp);
						
						atualizarTabProduto();
						
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
		jbAlterarProduto.setBounds(342, 232, 141, 23);
		contentPane.add(jbAlterarProduto);
		
		JButton jbCadastrarProduto = new JButton("CADASTRAR");
		
		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent arg0) {
				atualizarTabProduto();;
				limpar();
			}
		});
		
		
		jbCadastrarProduto.addActionListener(new ActionListener() {
			
			
			// CADASTRANDO PRODUTO //
			
			
			public void actionPerformed(ActionEvent e) {
				try {
					
					cp.setNomeProduto(tfNomeProduto.getText());
					cp.setPrecoProduto(Double.parseDouble(tfPrecoProduto.getText()));
					cp.setQuantidadeProduto(Integer.parseInt(tfQuantidadeProduto.getText()));
					cp.setDescricao(tfTipoProduto.getText());
					System.out.println(timestamp);

					
					cpd.CadastarProduto(cp);
					
				}catch (Exception f) {
					JOptionPane.showMessageDialog(null, f.getMessage());
				}
				
				JOptionPane.showMessageDialog(null, "Produto Cadastrado!");
				limpar();
				atualizarTabProduto();
			}
		});
		jbCadastrarProduto.setBounds(233, 291, 141, 68);
		contentPane.add(jbCadastrarProduto);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 72, 506, 2);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 419, 506, 2);
		contentPane.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(515, 74, 1, 347);
		contentPane.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(10, 74, 1, 347);
		contentPane.add(separator_3);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(559, 419, 432, 2);
		contentPane.add(separator_4);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setOrientation(SwingConstants.VERTICAL);
		separator_5.setBounds(559, 74, 1, 347);
		contentPane.add(separator_5);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setOrientation(SwingConstants.VERTICAL);
		separator_6.setBounds(990, 74, 1, 347);
		contentPane.add(separator_6);
		
		JSeparator separator_7 = new JSeparator();
		separator_7.setBounds(559, 74, 432, 2);
		contentPane.add(separator_7);
		
		JLabel lblBuscarProduto = new JLabel("BUSCAR PRODUTO");
		lblBuscarProduto.setBounds(737, 90, 109, 14);
		contentPane.add(lblBuscarProduto);
		
		JLabel label_2 = new JLabel("C\u00F3digo: ");
		label_2.setBounds(570, 130, 46, 14);
		contentPane.add(label_2);
		
		tfBuscarCodigoProduto = new JTextField();
		tfBuscarCodigoProduto.addCaretListener(new CaretListener() {
			
			
			public void caretUpdate(CaretEvent e) {
				
				TableRowSorter<TableModel> filtrar = null;
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				filtrar = new TableRowSorter<TableModel>(model);
				table.setRowSorter(filtrar);
				
				if(tfBuscarCodigoProduto.getText().length() == 0) {
					filtrar.setRowFilter(null);
				}else {
					filtrar.setRowFilter(RowFilter.regexFilter("(?i)" + tfBuscarCodigoProduto.getText(), 0));
				}
			}
				
		});
		tfBuscarCodigoProduto.setColumns(10);
		tfBuscarCodigoProduto.setBounds(648, 127, 96, 20);
		contentPane.add(tfBuscarCodigoProduto);
		
		tfBuscarNomeProduto = new JTextField();
		tfBuscarNomeProduto.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				
				TableRowSorter<TableModel> filtrar = null;
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				filtrar = new TableRowSorter<TableModel>(model);
				table.setRowSorter(filtrar);
				
				if(tfBuscarNomeProduto.getText().length() == 0) {
					filtrar.setRowFilter(null);
				}else {
					filtrar.setRowFilter(RowFilter.regexFilter("(?i)" + tfBuscarNomeProduto.getText(), 1));
				}
		
				
				
			}
		});
		tfBuscarNomeProduto.setColumns(10);
		tfBuscarNomeProduto.setBounds(858, 127, 96, 20);
		contentPane.add(tfBuscarNomeProduto);
		
		JLabel label_3 = new JLabel("Nome:");
		label_3.setBounds(780, 130, 46, 14);
		contentPane.add(label_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(570, 208, 410, 200);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
		
			public void mousePressed(MouseEvent e) {
				setaCamporDaTabelaProduo();
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Nome", "Quantidade", "Pre\u00E7o", "Tipo"
			}
		));
		scrollPane.setViewportView(table);
		
		JLabel lblProdutosCadastrados = new JLabel("Produtos Cadastrados");
		lblProdutosCadastrados.setBounds(570, 189, 131, 14);
		contentPane.add(lblProdutosCadastrados);
		
		JButton jbSairProduto = new JButton("SAIR");
		jbSairProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});
		
		jbSairProduto.setBounds(420, 473, 221, 68);
		contentPane.add(jbSairProduto);
		
	}
	
	public void atualizarTabProduto() {
		try {
			cadastroproduto = cpd.buscarProdutos();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setNumRows(0);
			
			for(int i=0; i!=cadastroproduto.size(); i++) {
				model.addRow((Object[]) cadastroproduto.get(i));
			}
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	
	public void limpar(){
		tfNomeProduto.setText(null);
		tfPrecoProduto.setText(null);
		tfQuantidadeProduto.setText(null);
		tfTipoProduto.setText(null);
	try {
		tfCodigoProduto.setText((String.valueOf(cpd.RetornarProxCdg())));
	}catch (Exception e) {
		JOptionPane.showMessageDialog(null, e.getMessage());
	}
}
	
	public void setaCamporDaTabelaProduo() {
	tfCodigoProduto.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 0)));
	tfNomeProduto.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 1)));
	tfQuantidadeProduto.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 2)));
	tfPrecoProduto.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 3)));
	tfTipoProduto.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 4)));
	
	}		
	
	
	
	
	
	
}
