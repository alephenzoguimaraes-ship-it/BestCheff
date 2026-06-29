package model.dao.Produto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import JDBC.JdbcConnection;
import model.beans.Produto.ProdutoBeans;

// TODO: Auto-generated Javadoc
/**
 * The Class ProdutoDao.
 */
public class ProdutoDao {
	
	/** The pstm. */
	private PreparedStatement pstm;
	
	/** The rs. */
	private ResultSet rs;
	
	/** The conexao. */
	private JdbcConnection conexao = new JdbcConnection();
	
	/** The listar todos. */
	private final String listarTodos = "SELECT * FROM PRODUTO";
	
	/** The buscar prod nome. */
	private final String buscarProdNome = "SELECT * FROM PRODUTO r where upper(r.DESCRICAO_PRODUTO) like upper('%' || ? || '%');";
	
	/**
	 * Listar produtos.
	 *
	 * @return the array list
	 */
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
	
	/**
	 * Buscar produto nome.
	 *
	 * @param nome the nome
	 * @return the array list
	 */
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
