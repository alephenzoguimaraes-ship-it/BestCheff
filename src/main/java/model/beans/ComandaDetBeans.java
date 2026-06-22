package model.beans;

import java.util.Objects;

public class ComandaDetBeans {
	private double qtdeComandaDetalhe;
	private double vlrUnitarioComandaDetalhe;
	private double vlrTotalComandaDetalhe;
	private double vlrTotFinalComandaDetalhe;
	public ComandaDetBeans(double qtdeComandaDetalhe, double vlrUnitarioComandaDetalhe, double vlrTotalComandaDetalhe,
			double vlrTotFinalComandaDetalhe) {
		super();
		this.qtdeComandaDetalhe = qtdeComandaDetalhe;
		this.vlrUnitarioComandaDetalhe = vlrUnitarioComandaDetalhe;
		this.vlrTotalComandaDetalhe = vlrTotalComandaDetalhe;
		this.vlrTotFinalComandaDetalhe = vlrTotFinalComandaDetalhe;
	}
	public ComandaDetBeans() {
		super();
	}
	public double getQtdeComandaDetalhe() {
		return qtdeComandaDetalhe;
	}
	public void setQtdeComandaDetalhe(double qtdeComandaDetalhe) {
		this.qtdeComandaDetalhe = qtdeComandaDetalhe;
	}
	public double getVlrUnitarioComandaDetalhe() {
		return vlrUnitarioComandaDetalhe;
	}
	public void setVlrUnitarioComandaDetalhe(double vlrUnitarioComandaDetalhe) {
		this.vlrUnitarioComandaDetalhe = vlrUnitarioComandaDetalhe;
	}
	public double getVlrTotalComandaDetalhe() {
		return vlrTotalComandaDetalhe;
	}
	public void setVlrTotalComandaDetalhe(double vlrTotalComandaDetalhe) {
		this.vlrTotalComandaDetalhe = vlrTotalComandaDetalhe;
	}
	public double getVlrTotFinalComandaDetalhe() {
		return vlrTotFinalComandaDetalhe;
	}
	public void setVlrTotFinalComandaDetalhe(double vlrTotFinalComandaDetalhe) {
		this.vlrTotFinalComandaDetalhe = vlrTotFinalComandaDetalhe;
	}
	@Override
	public String toString() {
		return "ComandaDetBeans [qtdeComandaDetalhe=" + qtdeComandaDetalhe + ", vlrUnitarioComandaDetalhe="
				+ vlrUnitarioComandaDetalhe + ", vlrTotalComandaDetalhe=" + vlrTotalComandaDetalhe
				+ ", vlrTotFinalComandaDetalhe=" + vlrTotFinalComandaDetalhe + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(qtdeComandaDetalhe, vlrTotFinalComandaDetalhe, vlrTotalComandaDetalhe,
				vlrUnitarioComandaDetalhe);
	}
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