package model.dao.Funcionario;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import JDBC.JdbcConnection;
import model.beans.Funcionario.FuncionarioBeans;

// TODO: Auto-generated Javadoc
/**
 * The Class FuncionarioDao.
 */
public class FuncionarioDao {
	
	/** The pstm. */
	PreparedStatement pstm;
	
	/** The rs. */
	ResultSet rs;
	
	/** The con. */
	JdbcConnection con = new JdbcConnection();
	
	/**
	 * Logar funcionario.
	 *
	 * @param funcionario the funcionario
	 */
	public void logarFuncionario(FuncionarioBeans funcionario) {
		String logar = "SELECT * FROM SP_LOGAR (?, ?)";
		try {
			pstm = con.conectar().prepareStatement(logar);
			pstm.setString(1, funcionario.getLoginFuncionario().toUpperCase());
			pstm.setString(2, funcionario.getSenhaFuncionario().toUpperCase());
			rs = pstm.executeQuery();
			while(rs.next()) {
				funcionario.setCodFuncionario(rs.getInt("COD_FUNCIONARIO"));
				funcionario.setNomeFuncionario(rs.getString("NOME_FUNCIONARIO"));
				funcionario.setFuncaoFuncionario(rs.getString("FUNCAO_FUNCIONARIO"));
				funcionario.setAdmissaoFuncionario(rs.getDate("ADMISSAO_FUNCIONARIO"));
				funcionario.setAcessoTotal(rs.getInt("ACESSO_TOTAL"));
				funcionario.setTelefone(rs.getString("TELEFONE"));
				funcionario.setCelular(rs.getString("CELULAR"));
				funcionario.setEmail(rs.getString("EMAIL"));
				funcionario.setCpf(rs.getString("CPF"));
				funcionario.setComissao(BigDecimal.valueOf(rs.getDouble("COMISSAO")));
				funcionario.setRg(rs.getString("RG"));
			}
		} catch(Exception e) {
			e.printStackTrace();
			Logger.getLogger("SqlBancoLog").log(Level.SEVERE, "Erro ao executar sql no banco", e);
		} finally {
			con.desconectar();
		}
	}
}
