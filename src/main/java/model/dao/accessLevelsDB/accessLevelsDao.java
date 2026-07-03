package model.dao.accessLevelsDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import JDBC.JdbcConnection;
import accessLevels.accessLevels;

// TODO: Auto-generated Javadoc
/**
 * The Class accessLevelsDao.
 */
public class accessLevelsDao {
	
	/** The pstm. */
	private PreparedStatement pstm;
	
	/** The rs. */
	private ResultSet rs;
	
	/** The con. */
	private JdbcConnection con = new JdbcConnection();

	/**
	 * Gets the the access levels func.
	 *
	 * @param codFunc the cod func
	 * @return the the access levels func
	 */
	public ArrayList<accessLevels> getTheAccessLevelsFunc(int codFunc) {
		ArrayList<accessLevels> accessLevels = new ArrayList<accessLevels>();
		String getInformation = "SELECT r.COD_FUNC, r.NOME_MODULO, r.CODIGO, r.C, r.R, r.U, r.D\n"
				+ "FROM NIVEL_ACESSO r\n"
				+ "where r.COD_FUNC = ? and r.NOME_MODULO like 'Comandas'";
		try {
			pstm = con.conectar().prepareStatement(getInformation);
			pstm.setInt(1, codFunc);
			rs = pstm.executeQuery();
			while (rs.next()) {
				accessLevels.add(new accessLevels(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7)));
			}
			return accessLevels;
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger("SqlBancoLog").log(Level.SEVERE, "Erro ao executar sql no banco", e);
			return null;
		} finally {
			con.desconectar();
		}
	}
}
