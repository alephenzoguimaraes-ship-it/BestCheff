package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import JDBC.JdbcConnection;
import model.beans.ProdutoBeans;

public class ProdutoDao {
	private PreparedStatement pstm;
	private ResultSet rs;
	private JdbcConnection conexao = new JdbcConnection();
	private final String listarTodos = "SELECT * FROM PRODUTO";
	private final String buscarProdNome = "SELECT * FROM PRODUTO r where upper(r.DESCRICAO_PRODUTO) like upper('%' || ? || '%');";
	
	public ArrayList<ProdutoBeans> listarProdutos() {
		ArrayList<ProdutoBeans> produtos = new ArrayList<ProdutoBeans>();
		try {
			pstm = conexao.conectar().prepareStatement(listarTodos);
			rs = pstm.executeQuery();
			while(rs.next()) {
				String cod = rs.getString("COD_PRODUTO");
				String descricao = rs.getString("DESCRICAO_PRODUTO");
				double preco = rs.getDouble("VLR_VENDA_PRODUTO");
				double qtde = rs.getDouble("ESTOQUE_PRODUTO");
				produtos.add(new ProdutoBeans(cod, descricao, preco, qtde));
			}
			return produtos;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			conexao.desconectar();
		}
	}
	
	public ArrayList<ProdutoBeans> buscarProdutoNome(String nome) {
		ArrayList<ProdutoBeans> produtos = new ArrayList<ProdutoBeans>();
		try {
			pstm = conexao.conectar().prepareStatement(buscarProdNome);
			pstm.setString(1, nome.toUpperCase());
			rs = pstm.executeQuery();
			while(rs.next()) {
				String cod = rs.getString("COD_PRODUTO");
				String descricao = rs.getString("DESCRICAO_PRODUTO");
				double preco = rs.getDouble("VLR_VENDA_PRODUTO");
				double qtde = rs.getDouble("ESTOQUE_PRODUTO");
				produtos.add(new ProdutoBeans(cod, descricao, preco, qtde));
			}
			return produtos;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			conexao.desconectar();
		}
	}
}
