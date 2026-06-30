package model.dao.accessLevelsDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import JDBC.JdbcConnection;
import accessLevels.accessLevels;

public class accessLevelsDao {
	private PreparedStatement pstm;
	private ResultSet rs;
	private JdbcConnection con = new JdbcConnection();

	public ArrayList<accessLevels> getTheAccessLevelsFunc(int codFunc) {
		ArrayList<accessLevels> accessLevels = new ArrayList<accessLevels>();
		String getInformation = "";
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
			return null;
		} finally {
			con.desconectar();
		}
	}
}
