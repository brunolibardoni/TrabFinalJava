package br.edu.ifcvideira.beans;

import java.sql.Timestamp;

public class Vendas {

	private Timestamp dataVenda;
	private double TotalVenda;
	private String descricao;
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	private int quantidade;
	private int id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public Timestamp getDataVenda() {
		return dataVenda;
	}
	public void setDataVenda(Timestamp dataVenda) {
		this.dataVenda = dataVenda;
	}
	public double getTotalVenda() {
		return TotalVenda;
	}
	public void setTotalVenda(double totalVenda) {
		TotalVenda = totalVenda;
	}
	
	
	
}
