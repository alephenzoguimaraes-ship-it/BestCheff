package model.dao.Comanda;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import JDBC.JdbcConnection;
import model.beans.Comandas.ComandaBeans;
import model.beans.Comandas.ComandaDetBeans;
import model.beans.Funcionario.FuncionarioBeans;

// TODO: Auto-generated Javadoc
/**
 * The Class ComandaDao.
 */
public class ComandaDao {
	
	/** The pstm. */
	PreparedStatement pstm;
	
	/** The rs. */
	ResultSet rs;
	
	/** The conexao. */
	JdbcConnection conexao = new JdbcConnection();
	
	/**
	 * Buscar comanda status.
	 *
	 * @param cb the cb
	 * @param codComanda the cod comanda
	 * @param barraComanda the barra comanda
	 * @param codFuncionario the cod funcionario
	 */
	public void buscarComandaStatus(ComandaBeans cb, String codComanda, String barraComanda, int codFuncionario) {
	    String busca = "SELECT p.R_ID_COMANDA, p.R_ID_BLOCO_COMANDA, p.R_COD_BARRAS, p.R_STATUS_COMANDA FROM SP_PESQUI_COD_BARRAS_COM (?, ?, ?) p;";
	    try {
	        pstm = conexao.conectar().prepareStatement(busca);
	        if (codComanda != null && !codComanda.isEmpty()) {
	            pstm.setInt(1, Integer.valueOf(codComanda));
	            pstm.setString(2, null);
	            pstm.setInt(3, codFuncionario);
	        } else {
	            pstm.setString(1, null);
	            pstm.setString(2, barraComanda);
	            pstm.setInt(3, codFuncionario);
	        }
	        rs = pstm.executeQuery();
	        while (rs.next()) {
	        	cb.setIdComanda(rs.getLong("R_ID_COMANDA"));
	        	cb.setIdBlocoComanda(rs.getInt("R_ID_BLOCO_COMANDA"));
	        	cb.setCodBarraBlocoComanda(rs.getString("R_COD_BARRAS"));
	            cb.setStatusComanda(rs.getString("R_STATUS_COMANDA"));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        conexao.desconectar();
	    }
	}
	
	/**
	 * Abrir comanda.
	 *
	 * @param cb the cb
	 */
	public void abrirComanda(ComandaBeans cb) {
		String abrirComanda = "UPDATE COMANDA\n"
				+ "SET    STATUS_COMANDA    = 'Aberto',\n"
				+ "       GRAVACAO_COMANDA  = CURRENT_TIMESTAMP\n"
				+ "WHERE  ID_COMANDA = ?;";
		try {
			pstm = conexao.conectar().prepareStatement(abrirComanda);
			pstm.setLong(1, cb.getIdComanda());
			cb.setStatusComanda("Aberto");
			pstm.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			conexao.desconectar();
		}
	}
	
	/**
	 * Inserir comanda.
	 *
	 * @param cb the cb
	 * @param cdb the cdb
	 * @param fb the fb
	 * @param codProd the cod prod
	 */
	public void inserirComanda(ComandaBeans cb, ComandaDetBeans cdb, FuncionarioBeans fb, String codProd) {
		String insert = "EXECUTE PROCEDURE SP_SALVAR_COMANDA_DETALHE (?, ?, ?, ?, ?, ?, ?, ?);";
		try {
			pstm = conexao.conectar().prepareStatement(insert);
			pstm.setLong(1, cb.getIdComanda());
			pstm.setLong(2, cb.getIdComanda());
			pstm.setString(3, codProd);
			pstm.setDouble(4, cdb.getQtdeComandaDetalhe());
			pstm.setDouble(5, cdb.getVlrUnitarioComandaDetalhe());
			pstm.setDouble(6, cdb.getVlrTotalComandaDetalhe());
			pstm.setDouble(7, cdb.getVlrTotFinalComandaDetalhe());
			pstm.setInt(8, fb.getCodFuncionario());
			pstm.execute();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			conexao.desconectar();
		}
	}
	
	/**
	 * Buscar todas comandas det.
	 *
	 * @param comandaBeans the comanda beans
	 * @return the array list
	 */
	public ArrayList<ComandaDetBeans> buscarTodasComandasDet(ComandaBeans comandaBeans) {
		String buscar = "SELECT r.ID_COMANDA_DETALHE, r.RF_ID_COMANDA, c.RF_ID_BLOCO_COMANDA, r.RF_ID_PRODUTO, upper(p.DESCRICAO_PRODUTO),\n"
				+ "r.QTDE_COMANDA_DETALHE, r.VLR_UNITARIO_COMANDA_DETALHE,\n"
				+ "r.VLR_TOTAL_COMANDA_DETALHE, r.VLR_DESCONTO_COMANDA_DETALHE,\n"
				+ "r.VLR_ACRESCIMO_COMANDA_DETALHE, r.VLR_TOT_FINAL_COMANDA_DETALHE,\n"
				+ "r.DATA_COMANDA, r.HORA_COMANDA, r.RF_ID_FUNCIONARIO, f.NOME_FUNCIONARIO, r.GRAVACAO,\n"
				+ "r.COMANDA_DETALHE_ITEM\n"
				+ "FROM COMANDA_DETALHE r\n"
				+ "inner join PRODUTO p ON r.RF_ID_PRODUTO = p.COD_PRODUTO\n"
				+ "inner join COMANDA c ON r.ID_COMANDA_DETALHE = c.ID_COMANDA\n"
				+ "inner join FUNCIONARIO f on r.RF_ID_FUNCIONARIO = f.COD_FUNCIONARIO \n"
				+ "WHERE r.ID_COMANDA_DETALHE = ? and r.RF_ID_COMANDA = ?\n"
				+ "order by r.ID_COMANDA_DETALHE desc";
		ArrayList<ComandaDetBeans> comandasDetalhe = new ArrayList<>();
		try {
			pstm = conexao.conectar().prepareStatement(buscar);
			pstm.setLong(1, comandaBeans.getIdComanda());
			pstm.setLong(2, comandaBeans.getIdComanda());
			rs = pstm.executeQuery();
			while(rs.next()) {
				comandasDetalhe.add(new ComandaDetBeans(rs.getLong("ID_COMANDA_DETALHE"), rs.getLong("RF_ID_COMANDA"), rs.getInt("RF_ID_BLOCO_COMANDA"), rs.getString("RF_ID_PRODUTO"), rs.getString("UPPER"), rs.getDouble("QTDE_COMANDA_DETALHE"),
						rs.getDouble("VLR_UNITARIO_COMANDA_DETALHE"), rs.getDouble("VLR_TOTAL_COMANDA_DETALHE"), rs.getDouble("VLR_DESCONTO_COMANDA_DETALHE"),
						rs.getDouble("VLR_ACRESCIMO_COMANDA_DETALHE"), rs.getDouble("VLR_TOT_FINAL_COMANDA_DETALHE"), rs.getDate("DATA_COMANDA"), rs.getTime("HORA_COMANDA"),
						rs.getInt("RF_ID_FUNCIONARIO"), rs.getString("NOME_FUNCIONARIO"), rs.getTimestamp("GRAVACAO"), rs.getInt("COMANDA_DETALHE_ITEM")));
			}
			return comandasDetalhe;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			conexao.desconectar();
		}
	}
}