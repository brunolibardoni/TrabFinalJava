package br.edu.ifcvideira.DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import br.edu.ifcvideira.beans.CadastroCliente;
import br.edu.ifcvideira.beans.CadastroProduto;
import br.edu.ifcvideira.utils.Conexao;

public class CadastroProdutoDao {

	// COMEÇO CADASTRO PRODUTO // 
	public void CadastarProduto(CadastroProduto cp) throws SQLException, Exception{
		try {
			String sql = "INSERT INTO produto (nome_produto, quantidade_produto, preco_produto, descricao) VALUES(?,?,?,?)";
			java.sql.PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			sqlPrep.setString(1, cp.getNomeProduto());
			sqlPrep.setInt(2, cp.getQuantidadeProduto());
			sqlPrep.setDouble(3, cp.getPrecoProduto());
			sqlPrep.setString(4, cp.getDescricao());
			sqlPrep.execute();
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
		// ------------------------ FIM CADASTRO PRODUTO --------------------- //

	

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
	
	
	// COMEÇO ALTERAR PRODUTOS // 
	
	public void AlterarProdutos(CadastroProduto cp) throws Exception{
		try {
			String sql = "UPDATE produto SET nome_produto=?, quantidade_produto=?, preco_produto=?, descricao=? WHERE id=?";
			PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			sqlPrep.setString(1, cp.getNomeProduto());
			sqlPrep.setInt(2, cp.getQuantidadeProduto());
			sqlPrep.setDouble(3, cp.getPrecoProduto());
			sqlPrep.setString(4, cp.getDescricao());
			sqlPrep.setInt(5, cp.getId());
			sqlPrep.execute();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
// ----------------- FINAL ALTERAR PRODUTOS -----------------//
	
	// COMEÇO DELETAR PRODUTOS //
	
	public void deletarProduto(CadastroProduto cp) throws Exception{
		try {
			String sql = "DELETE FROM produto WHERE id =? ";
			PreparedStatement sqlPrep = (PreparedStatement) Conexao.conectar().prepareStatement(sql);
			sqlPrep.setInt(1, cp.getId());
			sqlPrep.execute();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
// --------------- FIM DELETAR PRODUTOS ---------------- //
	
	
	//RETORNAR PRÓXIMO CÓDIGO PRODUTO //
	
	public int RetornarProxCdg() throws Exception{
		try {
			String sql = "SELECT id+1 AS id FROM produto";
			PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			ResultSet as = sqlPrep.executeQuery();
			
			if(as.next()) {
				return as.getInt("id");
				
			}else {
				return 1;
			}
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return 1;
		}
	}
	
	
// --------------- FIM RETORNAR PROX CODIGO PRODUTO -------------- //
	
	
	
	
}
