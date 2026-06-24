package model.beans;

import java.util.Objects;

// TODO: Auto-generated Javadoc
/**
 * The Class ComandaDetBeans.
 */
public class ComandaDetBeans {
	
	/** The qtde comanda detalhe. */
	private double qtdeComandaDetalhe;
	
	/** The vlr unitario comanda detalhe. */
	private double vlrUnitarioComandaDetalhe;
	
	/** The vlr total comanda detalhe. */
	private double vlrTotalComandaDetalhe;
	
	/** The vlr tot final comanda detalhe. */
	private double vlrTotFinalComandaDetalhe;
	
	/**
	 * Instantiates a new comanda det beans.
	 *
	 * @param qtdeComandaDetalhe the qtde comanda detalhe
	 * @param vlrUnitarioComandaDetalhe the vlr unitario comanda detalhe
	 * @param vlrTotalComandaDetalhe the vlr total comanda detalhe
	 * @param vlrTotFinalComandaDetalhe the vlr tot final comanda detalhe
	 */
	public ComandaDetBeans(double qtdeComandaDetalhe, double vlrUnitarioComandaDetalhe, double vlrTotalComandaDetalhe,
			double vlrTotFinalComandaDetalhe) {
		super();
		this.qtdeComandaDetalhe = qtdeComandaDetalhe;
		this.vlrUnitarioComandaDetalhe = vlrUnitarioComandaDetalhe;
		this.vlrTotalComandaDetalhe = vlrTotalComandaDetalhe;
		this.vlrTotFinalComandaDetalhe = vlrTotFinalComandaDetalhe;
	}
	
	/**
	 * Instantiates a new comanda det beans.
	 */
	public ComandaDetBeans() {
		super();
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
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "ComandaDetBeans [qtdeComandaDetalhe=" + qtdeComandaDetalhe + ", vlrUnitarioComandaDetalhe="
				+ vlrUnitarioComandaDetalhe + ", vlrTotalComandaDetalhe=" + vlrTotalComandaDetalhe
				+ ", vlrTotFinalComandaDetalhe=" + vlrTotFinalComandaDetalhe + "]";
	}
	
	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(qtdeComandaDetalhe, vlrTotFinalComandaDetalhe, vlrTotalComandaDetalhe,
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
		return Double.doubleToLongBits(qtdeComandaDetalhe) == Double.doubleToLongBits(other.qtdeComandaDetalhe)
				&& Double.doubleToLongBits(vlrTotFinalComandaDetalhe) == Double
						.doubleToLongBits(other.vlrTotFinalComandaDetalhe)
				&& Double.doubleToLongBits(vlrTotalComandaDetalhe) == Double
						.doubleToLongBits(other.vlrTotalComandaDetalhe)
				&& Double.doubleToLongBits(vlrUnitarioComandaDetalhe) == Double
						.doubleToLongBits(other.vlrUnitarioComandaDetalhe);
	}
}