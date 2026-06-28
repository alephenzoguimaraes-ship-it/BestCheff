package model.beans;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Objects;

// TODO: Auto-generated Javadoc
/**
 * The Class ComandaDetBeans.
 */
public class ComandaDetBeans {
	
	/** The id comanda detalhe. */
	private long idComandaDetalhe;
	
	/** The id comanda. */
	private long idComanda;
	
	/** The id bloco comanda. */
	private int idBlocoComanda;
	
	/** The id produto. */
	private String idProduto;
	
	/** The descricaoProduto. */
	private String descricaoProduto;
	
	/** The qtde comanda detalhe. */
	private double qtdeComandaDetalhe;
	
	/** The vlr unitario comanda detalhe. */
	private double vlrUnitarioComandaDetalhe;
	
	/** The vlr total comanda detalhe. */
	private double vlrTotalComandaDetalhe;
	
	/** The vlr desconto comanda detalhe. */
	private double vlrDescontoComandaDetalhe;
	
	/** The vlr acrescimo comanda detalhe. */
	private double vlrAcrescimoComandaDetalhe;
	
	/** The vlr tot final comanda detalhe. */
	private double vlrTotFinalComandaDetalhe;
	
	/** The data comanda. */
	private java.sql.Date dataComanda;
	
	/** The hora comanda. */
	private java.sql.Time horaComanda;
	
	/** The id funcionario. */
	private int idFuncionario;
	
	/** The gravacao. */
	private java.sql.Timestamp gravacao;
	
	/** The comanda detalhe item. */
	private int comandaDetalheItem;

	/**
	 * Instantiates a new comanda det beans.
	 *
	 * @param idComandaDetalhe the id comanda detalhe
	 * @param idComanda the id comanda
	 * @param idBlocoComanda the id bloco comanda
	 * @param idProduto the id produto
	 * @param descricaoProduto the descricao produto
	 * @param qtdeComandaDetalhe the qtde comanda detalhe
	 * @param vlrUnitarioComandaDetalhe the vlr unitario comanda detalhe
	 * @param vlrTotalComandaDetalhe the vlr total comanda detalhe
	 * @param vlrDescontoComandaDetalhe the vlr desconto comanda detalhe
	 * @param vlrAcrescimoComandaDetalhe the vlr acrescimo comanda detalhe
	 * @param vlrTotFinalComandaDetalhe the vlr tot final comanda detalhe
	 * @param dataComanda the data comanda
	 * @param horaComanda the hora comanda
	 * @param idFuncionario the id funcionario
	 * @param gravacao the gravacao
	 * @param comandaDetalheItem the comanda detalhe item
	 */
	public ComandaDetBeans(long idComandaDetalhe, long idComanda, int idBlocoComanda, String idProduto,
			String descricaoProduto, double qtdeComandaDetalhe, double vlrUnitarioComandaDetalhe,
			double vlrTotalComandaDetalhe, double vlrDescontoComandaDetalhe, double vlrAcrescimoComandaDetalhe,
			double vlrTotFinalComandaDetalhe, Date dataComanda, Time horaComanda, int idFuncionario, Timestamp gravacao,
			int comandaDetalheItem) {
		this.idComandaDetalhe = idComandaDetalhe;
		this.idComanda = idComanda;
		this.idBlocoComanda = idBlocoComanda;
		this.idProduto = idProduto;
		this.descricaoProduto = descricaoProduto;
		this.qtdeComandaDetalhe = qtdeComandaDetalhe;
		this.vlrUnitarioComandaDetalhe = vlrUnitarioComandaDetalhe;
		this.vlrTotalComandaDetalhe = vlrTotalComandaDetalhe;
		this.vlrDescontoComandaDetalhe = vlrDescontoComandaDetalhe;
		this.vlrAcrescimoComandaDetalhe = vlrAcrescimoComandaDetalhe;
		this.vlrTotFinalComandaDetalhe = vlrTotFinalComandaDetalhe;
		this.dataComanda = dataComanda;
		this.horaComanda = horaComanda;
		this.idFuncionario = idFuncionario;
		this.gravacao = gravacao;
		this.comandaDetalheItem = comandaDetalheItem;
	}

	/**
	 * Instantiates a new comanda det beans.
	 */
	public ComandaDetBeans() {
		super();
	}

	/**
	 * Gets the id comanda detalhe.
	 *
	 * @return the id comanda detalhe
	 */
	public long getIdComandaDetalhe() {
		return idComandaDetalhe;
	}

	/**
	 * Sets the id comanda detalhe.
	 *
	 * @param idComandaDetalhe the new id comanda detalhe
	 */
	public void setIdComandaDetalhe(long idComandaDetalhe) {
		this.idComandaDetalhe = idComandaDetalhe;
	}

	/**
	 * Gets the id comanda.
	 *
	 * @return the id comanda
	 */
	public long getIdComanda() {
		return idComanda;
	}

	/**
	 * Sets the id comanda.
	 *
	 * @param idComanda the new id comanda
	 */
	public void setIdComanda(long idComanda) {
		this.idComanda = idComanda;
	}

	/**
	 * Gets the id bloco comanda.
	 *
	 * @return the id bloco comanda
	 */
	public int getIdBlocoComanda() {
		return idBlocoComanda;
	}

	/**
	 * Sets the id bloco comanda.
	 *
	 * @param idBlocoComanda the new id bloco comanda
	 */
	public void setIdBlocoComanda(int idBlocoComanda) {
		this.idBlocoComanda = idBlocoComanda;
	}

	/**
	 * Gets the id produto.
	 *
	 * @return the id produto
	 */
	public String getIdProduto() {
		return idProduto;
	}

	/**
	 * Sets the id produto.
	 *
	 * @param idProduto the new id produto
	 */
	public void setIdProduto(String idProduto) {
		this.idProduto = idProduto;
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
	 * Gets the qtde comanda detalhe.
	 *
	 * @return the qtde comanda detalhe
	 */
	public double getQtdeComandaDetalhe() {
		return qtdeComandaDetalhe;
	}

	/**
	 * Sets the qtde comanda detalhe.
	 *
	 * @param qtdeComandaDetalhe the new qtde comanda detalhe
	 */
	public void setQtdeComandaDetalhe(double qtdeComandaDetalhe) {
		this.qtdeComandaDetalhe = qtdeComandaDetalhe;
	}

	/**
	 * Gets the vlr unitario comanda detalhe.
	 *
	 * @return the vlr unitario comanda detalhe
	 */
	public double getVlrUnitarioComandaDetalhe() {
		return vlrUnitarioComandaDetalhe;
	}

	/**
	 * Sets the vlr unitario comanda detalhe.
	 *
	 * @param vlrUnitarioComandaDetalhe the new vlr unitario comanda detalhe
	 */
	public void setVlrUnitarioComandaDetalhe(double vlrUnitarioComandaDetalhe) {
		this.vlrUnitarioComandaDetalhe = vlrUnitarioComandaDetalhe;
	}

	/**
	 * Gets the vlr total comanda detalhe.
	 *
	 * @return the vlr total comanda detalhe
	 */
	public double getVlrTotalComandaDetalhe() {
		return vlrTotalComandaDetalhe;
	}

	/**
	 * Sets the vlr total comanda detalhe.
	 *
	 * @param vlrTotalComandaDetalhe the new vlr total comanda detalhe
	 */
	public void setVlrTotalComandaDetalhe(double vlrTotalComandaDetalhe) {
		this.vlrTotalComandaDetalhe = vlrTotalComandaDetalhe;
	}

	/**
	 * Gets the vlr desconto comanda detalhe.
	 *
	 * @return the vlr desconto comanda detalhe
	 */
	public double getVlrDescontoComandaDetalhe() {
		return vlrDescontoComandaDetalhe;
	}

	/**
	 * Sets the vlr desconto comanda detalhe.
	 *
	 * @param vlrDescontoComandaDetalhe the new vlr desconto comanda detalhe
	 */
	public void setVlrDescontoComandaDetalhe(double vlrDescontoComandaDetalhe) {
		this.vlrDescontoComandaDetalhe = vlrDescontoComandaDetalhe;
	}

	/**
	 * Gets the vlr acrescimo comanda detalhe.
	 *
	 * @return the vlr acrescimo comanda detalhe
	 */
	public double getVlrAcrescimoComandaDetalhe() {
		return vlrAcrescimoComandaDetalhe;
	}

	/**
	 * Sets the vlr acrescimo comanda detalhe.
	 *
	 * @param vlrAcrescimoComandaDetalhe the new vlr acrescimo comanda detalhe
	 */
	public void setVlrAcrescimoComandaDetalhe(double vlrAcrescimoComandaDetalhe) {
		this.vlrAcrescimoComandaDetalhe = vlrAcrescimoComandaDetalhe;
	}

	/**
	 * Gets the vlr tot final comanda detalhe.
	 *
	 * @return the vlr tot final comanda detalhe
	 */
	public double getVlrTotFinalComandaDetalhe() {
		return vlrTotFinalComandaDetalhe;
	}

	/**
	 * Sets the vlr tot final comanda detalhe.
	 *
	 * @param vlrTotFinalComandaDetalhe the new vlr tot final comanda detalhe
	 */
	public void setVlrTotFinalComandaDetalhe(double vlrTotFinalComandaDetalhe) {
		this.vlrTotFinalComandaDetalhe = vlrTotFinalComandaDetalhe;
	}

	/**
	 * Gets the data comanda.
	 *
	 * @return the data comanda
	 */
	public java.sql.Date getDataComanda() {
		return dataComanda;
	}

	/**
	 * Sets the data comanda.
	 *
	 * @param dataComanda the new data comanda
	 */
	public void setDataComanda(java.sql.Date dataComanda) {
		this.dataComanda = dataComanda;
	}

	/**
	 * Gets the hora comanda.
	 *
	 * @return the hora comanda
	 */
	public java.sql.Time getHoraComanda() {
		return horaComanda;
	}

	/**
	 * Sets the hora comanda.
	 *
	 * @param horaComanda the new hora comanda
	 */
	public void setHoraComanda(java.sql.Time horaComanda) {
		this.horaComanda = horaComanda;
	}

	/**
	 * Gets the id funcionario.
	 *
	 * @return the id funcionario
	 */
	public int getIdFuncionario() {
		return idFuncionario;
	}

	/**
	 * Sets the id funcionario.
	 *
	 * @param idFuncionario the new id funcionario
	 */
	public void setIdFuncionario(int idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	/**
	 * Gets the gravacao.
	 *
	 * @return the gravacao
	 */
	public java.sql.Timestamp getGravacao() {
		return gravacao;
	}

	/**
	 * Sets the gravacao.
	 *
	 * @param gravacao the new gravacao
	 */
	public void setGravacao(java.sql.Timestamp gravacao) {
		this.gravacao = gravacao;
	}

	/**
	 * Gets the comanda detalhe item.
	 *
	 * @return the comanda detalhe item
	 */
	public int getComandaDetalheItem() {
		return comandaDetalheItem;
	}

	/**
	 * Sets the comanda detalhe item.
	 *
	 * @param comandaDetalheItem the new comanda detalhe item
	 */
	public void setComandaDetalheItem(int comandaDetalheItem) {
		this.comandaDetalheItem = comandaDetalheItem;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(comandaDetalheItem, dataComanda, descricaoProduto, gravacao, horaComanda, idBlocoComanda,
				idComanda, idComandaDetalhe, idFuncionario, idProduto, qtdeComandaDetalhe, vlrAcrescimoComandaDetalhe,
				vlrDescontoComandaDetalhe, vlrTotFinalComandaDetalhe, vlrTotalComandaDetalhe,
				vlrUnitarioComandaDetalhe);
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
		ComandaDetBeans other = (ComandaDetBeans) obj;
		return comandaDetalheItem == other.comandaDetalheItem && Objects.equals(dataComanda, other.dataComanda)
				&& Objects.equals(descricaoProduto, other.descricaoProduto) && Objects.equals(gravacao, other.gravacao)
				&& Objects.equals(horaComanda, other.horaComanda) && idBlocoComanda == other.idBlocoComanda
				&& idComanda == other.idComanda && idComandaDetalhe == other.idComandaDetalhe
				&& idFuncionario == other.idFuncionario && Objects.equals(idProduto, other.idProduto)
				&& Double.doubleToLongBits(qtdeComandaDetalhe) == Double.doubleToLongBits(other.qtdeComandaDetalhe)
				&& Double.doubleToLongBits(vlrAcrescimoComandaDetalhe) == Double
						.doubleToLongBits(other.vlrAcrescimoComandaDetalhe)
				&& Double.doubleToLongBits(vlrDescontoComandaDetalhe) == Double
						.doubleToLongBits(other.vlrDescontoComandaDetalhe)
				&& Double.doubleToLongBits(vlrTotFinalComandaDetalhe) == Double
						.doubleToLongBits(other.vlrTotFinalComandaDetalhe)
				&& Double.doubleToLongBits(vlrTotalComandaDetalhe) == Double
						.doubleToLongBits(other.vlrTotalComandaDetalhe)
				&& Double.doubleToLongBits(vlrUnitarioComandaDetalhe) == Double
						.doubleToLongBits(other.vlrUnitarioComandaDetalhe);
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "ComandaDetBeans [idComandaDetalhe=" + idComandaDetalhe + ", idComanda=" + idComanda
				+ ", idBlocoComanda=" + idBlocoComanda + ", idProduto=" + idProduto + ", descricaoProduto="
				+ descricaoProduto + ", qtdeComandaDetalhe=" + qtdeComandaDetalhe + ", vlrUnitarioComandaDetalhe="
				+ vlrUnitarioComandaDetalhe + ", vlrTotalComandaDetalhe=" + vlrTotalComandaDetalhe
				+ ", vlrDescontoComandaDetalhe=" + vlrDescontoComandaDetalhe + ", vlrAcrescimoComandaDetalhe="
				+ vlrAcrescimoComandaDetalhe + ", vlrTotFinalComandaDetalhe=" + vlrTotFinalComandaDetalhe
				+ ", dataComanda=" + dataComanda + ", horaComanda=" + horaComanda + ", idFuncionario=" + idFuncionario
				+ ", gravacao=" + gravacao + ", comandaDetalheItem=" + comandaDetalheItem + "]";
	}
}