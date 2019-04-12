package br.edu.ifcvideira.DAOs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JOptionPane;

import com.mysql.fabric.xmlrpc.base.Array;

import br.edu.ifcvideira.beans.CadastroCliente;
import br.edu.ifcvideira.beans.CadastroProduto;
import br.edu.ifcvideira.beans.Vendas;
import br.edu.ifcvideira.utils.Conexao;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CadastroClienteDao {

//------------------------------------------------------------//	
	
	// COMEÇO CADASTRO CLIENTE//
	
		public void CadastrarCliente(CadastroCliente cc) throws SQLException, Exception{
			try {
				String sql = "INSERT INTO cadastro_cliente (nome, cpf, telefone, cidade) VALUES (?,?,?,?)";
				java.sql.PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
				sqlPrep.setString(1, cc.getNome());
				sqlPrep.setString(2, cc.getCpf());
				sqlPrep.setString(3, cc.getTelefone());
				sqlPrep.setString(4, cc.getCidade());
				sqlPrep.execute();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
		
		// FIM CADASTRO CLIENTE //
		
// -----------------------------------------------------------------------//
		
// COMEÇO BUSCAR CLIENTES //
		
		public List<Object> buscarClientes() throws SQLException, Exception{
			List<Object> clientes = new ArrayList<Object>();
			try {
			String sql = "SELECT * FROM cadastro_cliente";	
			java.sql.Statement state = Conexao.conectar().createStatement();
			ResultSet rs = state.executeQuery(sql);
			
			
			while (rs.next()) {
				Object[] linha = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)};
				clientes.add(linha);
			}
			state.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
			return clientes;
	}
		
// -------------------------- FIM BUSCAR CLIENTES -------------------- //
		
		
		// COMEÇO ALTERAR CLIENTES //
		
		public void AlterarCliente (CadastroCliente cc) throws Exception{
			try {
				String sql = "UPDATE cadastro_cliente SET nome=?, cpf=?, telefone=?, cidade=? WHERE id=?";
				PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
				sqlPrep.setString(1, cc.getNome());
				sqlPrep.setString(2, cc.getCpf());
				sqlPrep.setString(3, cc.getTelefone());
				sqlPrep.setString(4, cc.getCidade());
				sqlPrep.setInt(5, cc.getId());
				sqlPrep.execute();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
		
// ------------------------ FINAL ALTERAR CLIENTES ------------------------- //
		
		
		// COMEÇO DELETAR CLIENTES //
		
		public void deletarCliente(CadastroCliente cc) throws Exception{
			try {
				String sql = "DELETE FROM cadastro_cliente WHERE id =? ";
				PreparedStatement sqlPrep = (PreparedStatement) Conexao.conectar().prepareStatement(sql);
				sqlPrep.setInt(1, cc.getId());
				sqlPrep.execute();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
		
		
// --------------------------- FIM DELETAR CLIENTES ------------------------//
		
		
		// RETORNAR PRÓXIMA CÓDIGO CLIENTE //
		
		public int RetornarProxCdg() throws Exception{
			try {
				String sql ="SELECT id+1 AS id FROM cadastro_cliente";
				PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
				ResultSet as = sqlPrep.executeQuery();
				
				if(as.next()) {
					return as.getInt("id");
				}else {
					return 1;
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,e.getMessage());
				return 1;
			}
		}

		
		
}

