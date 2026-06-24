package model.beans;

import java.util.Objects;

// TODO: Auto-generated Javadoc
/**
 * The Class ComandaBeans.
 */
public class ComandaBeans {
	
	/** The id comanda. */
	private long idComanda;
	
	/** The id bloco comanda. */
	private int idBlocoComanda;
	
	/** The cod barra bloco comanda. */
	private String codBarraBlocoComanda;
	
	/** The status comanda. */
	private String statusComanda;
	
	/**
	 * Instantiates a new comanda beans.
	 *
	 * @param idComanda the id comanda
	 * @param idBlocoComanda the id bloco comanda
	 * @param codBarraBlocoComanda the cod barra bloco comanda
	 * @param statusComanda the status comanda
	 */
	public ComandaBeans(long idComanda, int idBlocoComanda, String codBarraBlocoComanda, String statusComanda) {
		super();
		this.idComanda = idComanda;
		this.idBlocoComanda = idBlocoComanda;
		this.codBarraBlocoComanda = codBarraBlocoComanda;
		this.statusComanda = statusComanda;
	}

	/**
	 * Instantiates a new comanda beans.
	 */
	public ComandaBeans() {
		super();
		// TODO Auto-generated constructor stub
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
	 * Gets the cod barra bloco comanda.
	 *
	 * @return the cod barra bloco comanda
	 */
	public String getCodBarraBlocoComanda() {
		return codBarraBlocoComanda;
	}
	
	/**
	 * Sets the cod barra bloco comanda.
	 *
	 * @param codBarraBlocoComanda the new cod barra bloco comanda
	 */
	public void setCodBarraBlocoComanda(String codBarraBlocoComanda) {
		this.codBarraBlocoComanda = codBarraBlocoComanda;
	}
	
	/**
	 * Gets the status comanda.
	 *
	 * @return the status comanda
	 */
	public String getStatusComanda() {
		return statusComanda;
	}
	
	/**
	 * Sets the status comanda.
	 *
	 * @param statusComanda the new status comanda
	 */
	public void setStatusComanda(String statusComanda) {
		this.statusComanda = statusComanda;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(codBarraBlocoComanda, idBlocoComanda, idComanda, statusComanda);
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
		ComandaBeans other = (ComandaBeans) obj;
		return Objects.equals(codBarraBlocoComanda, other.codBarraBlocoComanda)
				&& idBlocoComanda == other.idBlocoComanda && idComanda == other.idComanda
				&& Objects.equals(statusComanda, other.statusComanda);
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "ComandaBeans [idComanda=" + idComanda + ", idBlocoComanda=" + idBlocoComanda + ", codBarraBlocoComanda="
				+ codBarraBlocoComanda + ", statusComanda=" + statusComanda + "]";
	}
}