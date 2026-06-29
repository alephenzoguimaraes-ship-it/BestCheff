package model.dao.Emitente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import JDBC.JdbcConnection;
import model.beans.Emitente.EmitenteBeans;

// TODO: Auto-generated Javadoc
/**
 * The Class EmitenteDao.
 */
public class EmitenteDao {
	
	/** The pstm. */
	private PreparedStatement pstm;
	
	/** The rs. */
	private ResultSet rs;
	
	/** The con. */
	private JdbcConnection con = new JdbcConnection();
	
	/**
	 * Gets the emitente.
	 *
	 * @param eb the eb
	 * @return the emitente
	 */
	public void getEmitente(EmitenteBeans eb) {
		String getEmitente = "SELECT r.CODEMITENTE, r.NOME, r.FANTASIA, r.ENDERECO, r.COMPLEMENTO, r.BAIRRO,\n"
				+ "    r.NUMERO, r.CEP, r.CIDADE, r.UF, r.TELEFONE, r.FAX, r.EMAIL, r.SITE, r.CNPJ,\n"
				+ "    r.IE, r.RESPONSAVEL, r.RAMO, r.LOGOTIPO, r.REGMUN, r.NFE_EMITE, r.CNAE,\n"
				+ "    r.PERFIL_SPEED, r.CRT\n"
				+ "FROM EMITENTE r";
		try {
			pstm = con.conectar().prepareStatement(getEmitente);
			rs = pstm.executeQuery();
			while (rs.next()) {
				eb.setCodEmitente(rs.getInt("CODEMITENTE"));
				eb.setNome(rs.getString("NOME"));
				eb.setFantasia(rs.getString("FANTASIA"));
				eb.setEndereco(rs.getString("ENDERECO"));
				eb.setComplemento(rs.getString("COMPLEMENTO"));
				eb.setBairro(rs.getString("BAIRRO"));
				eb.setNumero(rs.getString("NUMERO"));
				eb.setCep(rs.getString("CEP"));
				eb.setCidade(rs.getString("CIDADE"));
				eb.setUf(rs.getString("UF"));
				eb.setTelefone(rs.getString("TELEFONE"));
				eb.setFax(rs.getString("FAX"));
				eb.setEmail(rs.getString("EMAIL"));
				eb.setSite(rs.getString("SITE"));
				eb.setCnpj(rs.getString("CNPJ"));
				eb.setIe(rs.getString("IE"));
				eb.setResponsavel(rs.getString("RESPONSAVEL"));
				eb.setRamo(rs.getString("RAMO"));
				eb.setLogoTipo(rs.getString("LOGOTIPO"));
				eb.setRegmun(rs.getString("REGMUN"));
				eb.setNfeEmitente(rs.getString("NFE_EMITE"));
				eb.setCnae(rs.getString("CNAE"));
				eb.setCrt(rs.getShort("CRT"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.desconectar();
		}
	}
}