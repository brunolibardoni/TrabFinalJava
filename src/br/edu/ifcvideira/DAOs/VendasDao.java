package br.edu.ifcvideira.DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.edu.ifcvideira.beans.Vendas;
import br.edu.ifcvideira.utils.Conexao;

public class VendasDao {

	
	
	
	
	
	// COMEÇO BUSCAR PRODUTOS //
	
		public List<Object> buscarProdutos() throws SQLException, Exception{
			List<Object> produtos = new ArrayList<Object>();

			try {
				String sql = "SELECT * FROM produto";
				java.sql.Statement state = Conexao.conectar().createStatement();
				ResultSet rs = state.executeQuery(sql);
				
				while(rs.next()) {
					Object[] row = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)};
					produtos.add(row);

				}
				state.close();
			}catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			
			}
			
			return produtos;
		}

	// ------------------------ FIM BUSCAR PRODUTOS -----------------------------//
		
		
		// COMEÇO INTO VENDAS // 
		
		public void Vender(Vendas ve) throws SQLException,Exception{
			try {
				String sql = "INSERT INTO vendas (data_vendas, total_vendas, quantidade, descricao) VALUES (?,?,?,?)";
				java.sql.PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
				sqlPrep.setTimestamp(1, ve.getDataVenda());
				sqlPrep.setDouble(2, ve.getTotalVenda());
				sqlPrep.setInt(3, ve.getQuantidade());
				sqlPrep.setString(4, ve.getDescricao());
				sqlPrep.execute();
			}catch(SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
		
		
		// ------------------------ FIM INTO VENDER  -----------------------------//

		
		public List<Object> buscarCli() throws SQLException, Exception{
			List<Object> cli = new ArrayList<Object>();

			try {
				String sql = "SELECT nome from cadastro_cliente where id=?";
				
				
				java.sql.Statement state = Conexao.conectar().createStatement();
				ResultSet rs = state.executeQuery(sql);
				
				while(rs.next()) {
					Object[] row = {rs.getString(1)};
					cli.add(row);

				}
				state.close();
			}catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			
			}
			
			return cli;
		}
		
}
