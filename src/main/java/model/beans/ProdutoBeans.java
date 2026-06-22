package model.beans;

import java.util.Objects;

public class ProdutoBeans {
	private String codProduto;
	private String descricaoProduto;
	private double precoVendaProduto;
	private double qtde;
	
	public ProdutoBeans(String codProduto, String descricaoProduto, double precoVendaProduto, double qtde) {
		super();
		this.codProduto = codProduto;
		this.descricaoProduto = descricaoProduto;
		this.precoVendaProduto = precoVendaProduto;
		this.qtde = qtde;
	}
	
	public ProdutoBeans() {
		super();
	}

	public String getCodProduto() {
		return codProduto;
	}

	public void setCodProduto(String codProduto) {
		this.codProduto = codProduto;
	}

	public String getDescricaoProduto() {
		return descricaoProduto;
	}

	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}

	public double getPrecoVendaProduto() {
		return precoVendaProduto;
	}

	public void setPrecoVendaProduto(double precoVendaProduto) {
		this.precoVendaProduto = precoVendaProduto;
	}

	public double getQtde() {
		return qtde;
	}

	public void setQtde(double qtde) {
		this.qtde = qtde;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codProduto, descricaoProduto, precoVendaProduto, qtde);
	}

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

	@Override
	public String toString() {
		return "ProdutoBeans [codProduto=" + codProduto + ", descricaoProduto=" + descricaoProduto
				+ ", precoVendaProduto=" + precoVendaProduto + ", qtde=" + qtde + "]";
	}
}