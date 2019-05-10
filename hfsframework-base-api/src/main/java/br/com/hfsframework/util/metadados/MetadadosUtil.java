/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.util.metadados;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class MetadadosUtil.
 */
public class MetadadosUtil implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The log. */
	private Logger log;

	/** The config. */
	private MetadadosConfig config;

	/**
	 * Instantiates a new metadados util.
	 */
	public MetadadosUtil() {
		super();
		this.log = LogManager.getLogger(MetadadosUtil.class);
	}

	/**
	 * Configurar.
	 *
	 * @param config the config
	 */
	public void configurar(MetadadosConfig config) {
		this.config = config;
	}

	/**
	 * Gets the metadados.
	 *
	 * @param sEsquema the s esquema
	 * @return the metadados
	 */
	public Metadados getMetadados(String sEsquema) {
		Metadados m = new Metadados();
		MetadadosObjeto mo;
		List<MetadadosObjeto> lmo;
		MetadadosColuna mc;
		List<MetadadosColuna> lmc;		
		Connection conexao = null;
		ResultSet r1, r2, r3, r4;
		List<String> nomeColunasPK;
		List<String> nomeColunas;
		try {
			// DriverManager.registerDriver(new ...Driver());
			Class.forName(config.getDriverClasse());
			conexao = DriverManager.getConnection(config.getConexaoURL(), config.getUsuario(), config.getSenha());
			DatabaseMetaData dbmd = conexao.getMetaData();
			m.setVersaoDriverJDBC(dbmd.getDriverVersion());
			m.setVersaoBancoDeDados(dbmd.getDatabaseProductVersion());
			m.setSuportaSelectForUpdate(dbmd.supportsSelectForUpdate());
			m.setSuportaTransacoes(dbmd.supportsTransactions());
			r1 = dbmd.getSchemas(null, sEsquema);
			while (r1.next()) {
				sEsquema = r1.getString(1);
				lmo = new ArrayList<MetadadosObjeto>();
				r2 = dbmd.getTables(null, sEsquema, "%", new String[] { "TABLE" });
				while (r2.next()) {
					mo = new MetadadosObjeto();
					mo.setEsquema(sEsquema);
					mo.setObjeto(r2.getString("TABLE_NAME"));
					nomeColunas = new ArrayList<String>();

					lmc = new ArrayList<MetadadosColuna>();					
					r3 = dbmd.getColumns(null, mo.getEsquema(), mo.getObjeto(), "%");
					while (r3.next()) {
						mc = new MetadadosColuna();
						mc.setNome(r3.getString("COLUMN_NAME"));
						mc.setTipo(r3.getString("TYPE_NAME"));
						mc.setTipoSQL(r3.getInt("DATA_TYPE"));
						mc.setTamanho(r3.getInt("COLUMN_SIZE"));
						mc.setDigitos(r3.getInt("DECIMAL_DIGITS"));
						mc.setNulo(r3.getInt("NULLABLE") == DatabaseMetaData.columnNullable);
						mc.setPosicao(r3.getInt("ORDINAL_POSITION"));
						nomeColunas.add(mc.getNome());
						lmc.add(mc);
					}
															
					nomeColunasPK = new ArrayList<String>();
					r4 = dbmd.getPrimaryKeys(null, mo.getEsquema(), mo.getObjeto());
					while (r4.next()) {
						nomeColunasPK.add(r4.getString("COLUMN_NAME"));
					}					
					r4.close();					
					mo.setNomeColunasPK(nomeColunasPK);
					mo.setNomeColunas(nomeColunas);
					mo.setColunas(lmc);
					r3.close();
					
					lmo.add(mo);
				}
				m.setObjetos(lmo);
				r2.close();
			}
			r1.close();

		} catch (ClassNotFoundException e) {
			log.error(e);
		} catch (SQLException e) {
			log.error(e);
		} finally {
			try {
				if (conexao != null) {
					conexao.close();
				}
			} catch (SQLException e) {
				log.error(e);
			}
		}

		return m;
	}

}
