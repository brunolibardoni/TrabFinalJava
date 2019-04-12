package br.edu.ifcvideira.DAOs;

import java.awt.Window;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import br.edu.ifcvideira.beans.Usuarios;
import br.edu.ifcvideira.controllers.views.ClienteViwe;
import br.edu.ifcvideira.utils.Conexao;


public class LoginDao {

	ClienteViwe cvl = new ClienteViwe();
	public void recebeDados(Usuarios usu) throws SQLException{
		int flag=0;
		
		try{
		String sql = "Select nome_usuario, senha_usuario, id FROM usuarios where nome_usuario=?";
		
		java.sql.PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
		
		
		
		int contador = 1;
		sqlPrep.setString(contador++, usu.getNome_usuario());
		
		ResultSet rs = sqlPrep.executeQuery();
		
		while (rs.next()){
				
				String senha = rs.getString("senha_usuario");
				
				if (usu.getSenha_usuario().equals(senha)){
					usu.setNome_usuario(rs.getString("nome_usuario"));
					usu.setId(rs.getInt("id"));
					JOptionPane.showMessageDialog(null, "Logando..."); // tela principal
					cvl.setVisible(true);
					

					
				}else{
					JOptionPane.showMessageDialog(null, "Senha incorreta");
				}
				flag=1;
		}
		
		if (flag==0){
			JOptionPane.showMessageDialog(null, "Dados de login inexistente");
		}
	} catch(Exception e) {
		JOptionPane.showMessageDialog(null, "Dados de login inexistente");
		
	}
		
	}
	
	
}
