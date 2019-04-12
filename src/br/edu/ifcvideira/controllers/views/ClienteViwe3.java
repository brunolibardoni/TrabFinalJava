package br.edu.ifcvideira.controllers.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.edu.ifcvideira.DAOs.VendasDao;
import br.edu.ifcvideira.beans.Vendas;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClienteViwe3 extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField tfNomeProdutoCarrinho;
	private JTextField tfQuantidadeCarrinho;
	private JTextField tfDataCarrinho;
	private JTextField tfTotalCarrinho;

	VendasDao ved = new VendasDao();
	Vendas ve = new Vendas();
	long time = System.currentTimeMillis();
	Timestamp timestamp = new Timestamp(time);
	
	private List<Object> listaprodutos = new ArrayList<Object>();
	private JTextField tfPreco;
	private JTextField tfNomeCarri;

	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienteViwe3 frame = new ClienteViwe3();
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
	public ClienteViwe3() {
		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent arg0) {
				tfDataCarrinho.setText(""+timestamp);
			}
		});
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 952, 627);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("APLICATIVO COMERCIAL LIBARDONI - ACL");
		label.setFont(new Font("Franklin Gothic Medium Cond", Font.ITALIC, 22));
		label.setBounds(310, 11, 370, 25);
		contentPane.add(label);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 461, 432, 2);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(10, 116, 1, 347);
		contentPane.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(441, 116, 1, 347);
		contentPane.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(10, 116, 432, 2);
		contentPane.add(separator_3);
		
		JLabel lblProdutosDisponveis = new JLabel("PRODUTOS DISPON\u00CDVEIS");
		lblProdutosDisponveis.setBounds(151, 91, 168, 14);
		contentPane.add(lblProdutosDisponveis);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 133, 410, 317);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				
				setarVendas();
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
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(483, 461, 432, 2);
		contentPane.add(separator_4);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setOrientation(SwingConstants.VERTICAL);
		separator_5.setBounds(483, 116, 1, 347);
		contentPane.add(separator_5);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setOrientation(SwingConstants.VERTICAL);
		separator_6.setBounds(914, 116, 1, 347);
		contentPane.add(separator_6);
		
		JSeparator separator_7 = new JSeparator();
		separator_7.setBounds(483, 116, 432, 2);
		contentPane.add(separator_7);
		
		JLabel lblCarrinho = new JLabel("CARRINHO");
		lblCarrinho.setBounds(676, 91, 139, 14);
		contentPane.add(lblCarrinho);
		
		tfNomeProdutoCarrinho = new JTextField();
		tfNomeProdutoCarrinho.setEditable(false);
		tfNomeProdutoCarrinho.setColumns(10);
		tfNomeProdutoCarrinho.setBounds(605, 133, 96, 20);
		contentPane.add(tfNomeProdutoCarrinho);
		
		JLabel lblCdigoDoProduto = new JLabel("Nome do Produto:");
		lblCdigoDoProduto.setBounds(493, 136, 120, 14);
		contentPane.add(lblCdigoDoProduto);
		
		tfQuantidadeCarrinho = new JTextField();
		tfQuantidadeCarrinho.setColumns(10);
		tfQuantidadeCarrinho.setBounds(605, 177, 96, 20);
		contentPane.add(tfQuantidadeCarrinho);
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setBounds(493, 180, 120, 14);
		contentPane.add(lblQuantidade);
		
		JLabel lblData = new JLabel("Data:");
		lblData.setBounds(816, 133, 120, 14);
		contentPane.add(lblData);
		
		tfDataCarrinho = new JTextField();
		tfDataCarrinho.setEditable(false);
		tfDataCarrinho.setColumns(10);
		tfDataCarrinho.setBounds(746, 150, 139, 20);
		contentPane.add(tfDataCarrinho);
		
		tfTotalCarrinho = new JTextField();
		tfTotalCarrinho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
			}
		});
		tfTotalCarrinho.setColumns(10);
		tfTotalCarrinho.setBounds(605, 269, 96, 20);
		contentPane.add(tfTotalCarrinho);
		
		JLabel lblTotal = new JLabel("TOTAL: ");
		lblTotal.setBounds(494, 272, 120, 14);
		contentPane.add(lblTotal);
		
		JButton jbComprarCarrinho = new JButton("COMPRAR");
		jbComprarCarrinho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				double a = Double.parseDouble(tfQuantidadeCarrinho.getText());
				double b =Double.parseDouble(tfPreco.getText());
				
				double total = a*b;
				
				tfTotalCarrinho.setText(String.valueOf(total));
				
			
				ve.setDataVenda(timestamp);
				ve.setDescricao(tfNomeCarri.getText());
				ve.setQuantidade(Integer.parseInt(tfQuantidadeCarrinho.getText()));
				ve.setTotalVenda(Double.parseDouble(tfTotalCarrinho.getText()));
				ve.setDescricao(tfNomeCarri.getText());

				JOptionPane.showMessageDialog(null, "Compra Efetuada!!\n\n"
						+ "Total: "+tfTotalCarrinho.getText()+"\n\n\n"
								+ "Volte Sempre "+tfNomeCarri.getText());
				
				limpar();
				
			}
		});
		jbComprarCarrinho.setBounds(636, 310, 168, 74);
		contentPane.add(jbComprarCarrinho);
		
		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent arg0) {
				atualizarTabProduto();;
				
			}
		});
		
		JButton jbSairCompras = new JButton("SAIR");
		jbSairCompras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		jbSairCompras.setBounds(344, 495, 221, 68);
		contentPane.add(jbSairCompras);
		
		tfPreco = new JTextField();
		tfPreco.setEditable(false);
		tfPreco.setColumns(10);
		tfPreco.setBounds(605, 223, 96, 20);
		contentPane.add(tfPreco);
		
		JLabel lblPreo = new JLabel("Pre\u00E7o Unit\u00E1rio:");
		lblPreo.setBounds(493, 226, 120, 14);
		contentPane.add(lblPreo);
		
		tfNomeCarri = new JTextField();
		tfNomeCarri.setColumns(10);
		tfNomeCarri.setBounds(605, 412, 96, 20);
		contentPane.add(tfNomeCarri);
		
		JLabel lblCliente = new JLabel("Nome Cliente:");
		lblCliente.setBounds(494, 415, 120, 14);
		contentPane.add(lblCliente);
	}
	
	
	public void atualizarTabProduto() {
		try {
			listaprodutos = ved.buscarProdutos();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setNumRows(0);
			
			for(int i=0; i!=listaprodutos.size(); i++) {
				model.addRow((Object[]) listaprodutos.get(i));
			}
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public void limpar(){
		tfNomeCarri.setText(null);
		tfNomeProdutoCarrinho.setText(null);
		tfPreco.setText(null);
		tfTotalCarrinho.setText("0");
		tfQuantidadeCarrinho.setText("0");
	}
	
	
	public void setarVendas() {
		tfNomeProdutoCarrinho.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 1)));
		tfQuantidadeCarrinho.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 2)));
		tfPreco.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 3)));
		

		
		}		
}
