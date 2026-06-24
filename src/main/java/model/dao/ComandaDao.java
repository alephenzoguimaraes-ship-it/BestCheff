package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import JDBC.JdbcConnection;
import model.beans.ComandaBeans;
import model.beans.ComandaDetBeans;
import model.beans.FuncionarioBeans;
import model.beans.ProdutoBeans;

public class ComandaDao {
	PreparedStatement pstm;
	ResultSet rs;
	JdbcConnection conexao = new JdbcConnection();
	
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
	        	cb.setIdComanda(rs.getLong(1));
	        	cb.setIdBlocoComanda(rs.getInt(2));
	        	cb.setCodBarraBlocoComanda(rs.getString(3));
	            cb.setStatusComanda(rs.getString(4));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        conexao.desconectar();
	    }
	}
	
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
	
	public void inserirComanda(ComandaBeans cb, ComandaDetBeans cdb, FuncionarioBeans fb, String codProd) {
		String insert = "INSERT INTO COMANDA_DETALHE (ID_COMANDA_DETALHE, RF_ID_COMANDA, RF_ID_PRODUTO,\n"
				+ "    QTDE_COMANDA_DETALHE, VLR_UNITARIO_COMANDA_DETALHE,\n"
				+ "    VLR_TOTAL_COMANDA_DETALHE, VLR_DESCONTO_COMANDA_DETALHE,\n"
				+ "    VLR_ACRESCIMO_COMANDA_DETALHE, VLR_TOT_FINAL_COMANDA_DETALHE, DATA_COMANDA,\n"
				+ "    HORA_COMANDA, RF_ID_FUNCIONARIO, GRAVACAO, COMANDA_DETALHE_ITEM)\n"
				+ "VALUES (\n"
				+ "    ?, \n"
				+ "    ?, \n"
				+ "    ?, \n"
				+ "    ?, \n"
				+ "    ?, \n"
				+ "    ?, \n"
				+ "    0.00, \n"
				+ "    0.00, \n"
				+ "    ?, \n"
				+ "    current_date, \n"
				+ "    current_time, \n"
				+ "    ?, \n"
				+ "    current_timestamp, \n"
				+ "    1\n"
				+ ");\n"
				+ "";
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
}