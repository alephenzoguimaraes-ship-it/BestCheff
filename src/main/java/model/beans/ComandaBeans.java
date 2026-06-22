package model.beans;

import java.util.Objects;

public class ComandaBeans {
	private long idComanda;
	private int idBlocoComanda;
	private String codBarraBlocoComanda;
	private String statusComanda;
	
	public ComandaBeans(long idComanda, int idBlocoComanda, String codBarraBlocoComanda, String statusComanda) {
		super();
		this.idComanda = idComanda;
		this.idBlocoComanda = idBlocoComanda;
		this.codBarraBlocoComanda = codBarraBlocoComanda;
		this.statusComanda = statusComanda;
	}

	public ComandaBeans() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getIdComanda() {
		return idComanda;
	}
	
	public void setIdComanda(long idComanda) {
		this.idComanda = idComanda;
	}
	
	public int getIdBlocoComanda() {
		return idBlocoComanda;
	}
	
	public void setIdBlocoComanda(int idBlocoComanda) {
		this.idBlocoComanda = idBlocoComanda;
	}
	
	public String getCodBarraBlocoComanda() {
		return codBarraBlocoComanda;
	}
	
	public void setCodBarraBlocoComanda(String codBarraBlocoComanda) {
		this.codBarraBlocoComanda = codBarraBlocoComanda;
	}
	
	public String getStatusComanda() {
		return statusComanda;
	}
	
	public void setStatusComanda(String statusComanda) {
		this.statusComanda = statusComanda;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codBarraBlocoComanda, idBlocoComanda, idComanda, statusComanda);
	}

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

	@Override
	public String toString() {
		return "ComandaBeans [idComanda=" + idComanda + ", idBlocoComanda=" + idBlocoComanda + ", codBarraBlocoComanda="
				+ codBarraBlocoComanda + ", statusComanda=" + statusComanda + "]";
	}
}