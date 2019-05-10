/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.util.metadados;

import java.io.Serializable;
import java.sql.Types;

import org.apache.commons.lang3.StringUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class Metadados.
 */
public class MetadadosColuna implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The campo. */
	private String nome;
	
	/** The tipo. */
	private String tipo;
	
	/** The tipo SQL. */
	private int tipoSQL;

	/** The tamanho. */
	private int tamanho;

	/** The digitos. */
	private int digitos;

	/** The nulo. */
	private boolean nulo;

	/** The posicao. */
	private int posicao;
	
	/**
	 * Instantiates a new metadados.
	 */
	public MetadadosColuna() {
		super();
	}

	/**
	 * Pega o the tipo.
	 *
	 * @return o the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Atribui o the tipo.
	 *
	 * @param tipo
	 *            o novo the tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Checa se e the nulo.
	 *
	 * @return o the nulo
	 */
	public boolean isNulo() {
		return nulo;
	}

	/**
	 * Atribui o the nulo.
	 *
	 * @param nulo
	 *            o novo the nulo
	 */
	public void setNulo(boolean nulo) {
		this.nulo = nulo;
	}

	/**
	 * Pega o the campo.
	 *
	 * @return o the campo
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Atribui o the campo.
	 *
	 * @param nome
	 *            o novo the campo
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Pega o the tamanho.
	 *
	 * @return o the tamanho
	 */
	public int getTamanho() {
		return tamanho;
	}

	/**
	 * Atribui o the tamanho.
	 *
	 * @param tamanho
	 *            o novo the tamanho
	 */
	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}

	/**
	 * Pega o the digitos.
	 *
	 * @return o the digitos
	 */
	public int getDigitos() {
		return digitos;
	}

	/**
	 * Atribui o the digitos.
	 *
	 * @param digitos
	 *            o novo the digitos
	 */
	public void setDigitos(int digitos) {
		this.digitos = digitos;
	}

	/**
	 * Pega o the posicao.
	 *
	 * @return o the posicao
	 */
	public int getPosicao() {
		return posicao;
	}

	/**
	 * Atribui o the posicao.
	 *
	 * @param posicao
	 *            o novo the posicao
	 */
	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}

	/**
	 * Pega o the tipo SQL.
	 *
	 * @return o the tipo SQL
	 */
	public int getTipoSQL() {
		return tipoSQL;
	}

	/**
	 * Atribui o the tipo SQL.
	 *
	 * @param tipoSQL
	 *            o novo the tipo SQL
	 */
	public void setTipoSQL(int tipoSQL) {
		this.tipoSQL = tipoSQL;
	}
	
	/**
	 * Gets the propriedade.
	 *
	 * @return the propriedade
	 */
	public String getPropriedade() {
		String prop = "";
		String[] nomes = nome.substring(4).split("_");
		for (String item : nomes) {
			prop += StringUtils.capitalize(item);
		}		
		return prop;
	}

	/**
	 * Gets the tipo propriedade.
	 *
	 * @return the tipo propriedade
	 */
	public String getTipoPropriedade(){
		if (tipoSQL == Types.DECIMAL || tipoSQL == Types.DOUBLE 
				|| tipoSQL == Types.FLOAT || tipoSQL == Types.NUMERIC 
				|| tipoSQL == Types.REAL || tipoSQL == Types.BIGINT){
			return "long";
		} else if (tipoSQL == Types.DATE || tipoSQL == Types.TIME || tipoSQL == Types.TIMESTAMP) {
			return "datetime";
		} else {
			return "string";
		}		
	}
	
	/**
	 * Gets the tipo formatado.
	 *
	 * @return the tipo formatado
	 */
	public String getTipoFormatado(){
		//java.sql.Types.BIGINT
		//java.sql.Types.DECIMAL
		//java.sql.Types.DOUBLE
		//java.sql.Types.FLOAT
		//java.sql.Types.INTEGER
		//java.sql.Types.NUMERIC
		//java.sql.Types.REAL
		//java.sql.Types.SMALLINT
		//java.sql.Types.TINYINT

		if (tipoSQL == Types.DECIMAL || tipoSQL == Types.DOUBLE 
				|| tipoSQL == Types.FLOAT || tipoSQL == Types.NUMERIC 
				|| tipoSQL == Types.REAL){
			return tipo + "(" + tamanho + "," + digitos + ")";
		} else
			return tipo + "(" + tamanho + ")";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		//return "\n\t" + nome + " " + getTipoFormatado() + (nulo ? "NULL" : "NOT NULL");
		return "\n\t" + nome + " " + getTipoFormatado();
	}

}
