package model.beans.Produto;

import java.util.Objects;

// TODO: Auto-generated Javadoc
/**
 * The Class ProdutoBeans.
 */
public class ProdutoBeans {
	
	/** The cod produto. */
	private String codProduto;
	
	/** The descricao produto. */
	private String descricaoProduto;
	
	/** The preco venda produto. */
	private double precoVendaProduto;
	
	/** The qtde. */
	private double qtde;
	
	/**
	 * Instantiates a new produto beans.
	 *
	 * @param codProduto the cod produto
	 * @param descricaoProduto the descricao produto
	 * @param precoVendaProduto the preco venda produto
	 * @param qtde the qtde
	 */
	public ProdutoBeans(String codProduto, String descricaoProduto, double precoVendaProduto, double qtde) {
		super();
		this.codProduto = codProduto;
		this.descricaoProduto = descricaoProduto;
		this.precoVendaProduto = precoVendaProduto;
		this.qtde = qtde;
	}
	
	/**
	 * Instantiates a new produto beans.
	 */
	public ProdutoBeans() {
		super();
	}

	/**
	 * Gets the cod produto.
	 *
	 * @return the cod produto
	 */
	public String getCodProduto() {
		return codProduto;
	}

	/**
	 * Sets the cod produto.
	 *
	 * @param codProduto the new cod produto
	 */
	public void setCodProduto(String codProduto) {
		this.codProduto = codProduto;
	}

	/**
	 * Gets the descricao produto.
	 *
	 * @return the descricao produto
	 */
	public String getDescricaoProduto() {
		return descricaoProduto;
	}

	/**
	 * Sets the descricao produto.
	 *
	 * @param descricaoProduto the new descricao produto
	 */
	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}

	/**
	 * Gets the preco venda produto.
	 *
	 * @return the preco venda produto
	 */
	public double getPrecoVendaProduto() {
		return precoVendaProduto;
	}

	/**
	 * Sets the preco venda produto.
	 *
	 * @param precoVendaProduto the new preco venda produto
	 */
	public void setPrecoVendaProduto(double precoVendaProduto) {
		this.precoVendaProduto = precoVendaProduto;
	}

	/**
	 * Gets the qtde.
	 *
	 * @return the qtde
	 */
	public double getQtde() {
		return qtde;
	}

	/**
	 * Sets the qtde.
	 *
	 * @param qtde the new qtde
	 */
	public void setQtde(double qtde) {
		this.qtde = qtde;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(codProduto, descricaoProduto, precoVendaProduto, qtde);
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProdutoBeans other = (ProdutoBeans) obj;
		return Objects.equals(codProduto, other.codProduto) && Objects.equals(descricaoProduto, other.descricaoProduto)
				&& Double.doubleToLongBits(precoVendaProduto) == Double.doubleToLongBits(other.precoVendaProduto)
				&& Double.doubleToLongBits(qtde) == Double.doubleToLongBits(other.qtde);
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "ProdutoBeans [codProduto=" + codProduto + ", descricaoProduto=" + descricaoProduto
				+ ", precoVendaProduto=" + precoVendaProduto + ", qtde=" + qtde + "]";
	}
}