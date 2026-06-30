package accessLevels;

import java.util.Objects;

// TODO: Auto-generated Javadoc
/**
 * The Class accessLevels.
 */
public class accessLevels {
	
	/** The cod func. */
	private int codFunc;
	
	/** The nome modulo. */
	private String nomeModulo;
	
	/** The codigo. */
	private int codigo;
	
	/** The c. */
	private String C;
	
	/** The r. */
	private String R;
	
	/** The u. */
	private String U;
	
	/** The d. */
	private String D;
	
	/**
	 * Instantiates a new access levels.
	 *
	 * @param codFunc the cod func
	 * @param nomeModulo the nome modulo
	 * @param codigo the codigo
	 * @param C the c
	 * @param R the r
	 * @param U the u
	 * @param D the d
	 */
	public accessLevels(int codFunc, String nomeModulo, int codigo, String C, String R, String U, String D) {
		this.codFunc = codFunc;
		this.nomeModulo = nomeModulo;
		this.codigo = codigo;
		this.C = C;
		this.R = R;
		this.U = U;
		this.D = D;
	}
	
	/**
	 * Instantiates a new access levels.
	 */
	public accessLevels() {
		super();
	}

	/**
	 * Gets the cod func.
	 *
	 * @return the cod func
	 */
	public int getCodFunc() {
		return codFunc;
	}

	/**
	 * Sets the cod func.
	 *
	 * @param codFunc the new cod func
	 */
	public void setCodFunc(int codFunc) {
		this.codFunc = codFunc;
	}

	/**
	 * Gets the nome modulo.
	 *
	 * @return the nome modulo
	 */
	public String getNomeModulo() {
		return nomeModulo;
	}

	/**
	 * Sets the nome modulo.
	 *
	 * @param nomeModulo the new nome modulo
	 */
	public void setNomeModulo(String nomeModulo) {
		this.nomeModulo = nomeModulo;
	}

	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * Sets the codigo.
	 *
	 * @param codigo the new codigo
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * Gets the c.
	 *
	 * @return the c
	 */
	public String getC() {
		return C;
	}

	/**
	 * Sets the c.
	 *
	 * @param C the new c
	 */
	public void setC(String C) {
		this.C = C;
	}

	/**
	 * Gets the r.
	 *
	 * @return the r
	 */
	public String getR() {
		return R;
	}

	/**
	 * Sets the r.
	 *
	 * @param R the new r
	 */
	public void setR(String R) {
		this.R = R;
	}

	/**
	 * Gets the u.
	 *
	 * @return the u
	 */
	public String getU() {
		return U;
	}

	/**
	 * Sets the u.
	 *
	 * @param U the new u
	 */
	public void setU(String U) {
		this.U = U;
	}

	/**
	 * Gets the d.
	 *
	 * @return the d
	 */
	public String getD() {
		return D;
	}

	/**
	 * Sets the d.
	 *
	 * @param D the new d
	 */
	public void setD(String D) {
		this.D = D;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(C, D, R, U, codFunc, codigo, nomeModulo);
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
		accessLevels other = (accessLevels) obj;
		return Objects.equals(C, other.C) && Objects.equals(D, other.D) && Objects.equals(R, other.R)
				&& Objects.equals(U, other.U) && codFunc == other.codFunc && codigo == other.codigo
				&& Objects.equals(nomeModulo, other.nomeModulo);
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "accessLevels [codFunc=" + codFunc + ", nomeModulo=" + nomeModulo + ", codigo=" + codigo + ", C=" + C
				+ ", R=" + R + ", U=" + U + ", D=" + D + "]";
	}
}