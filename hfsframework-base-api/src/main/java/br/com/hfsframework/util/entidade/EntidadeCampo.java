/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.util.entidade;

import java.security.SecureRandom;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.RandomBasedGenerator;

// TODO: Auto-generated Javadoc
/**
 * The Class EntidadeCampo.
 */
public class EntidadeCampo {

	/** The Constant secureRandom. */
	private static final SecureRandom secureRandom = new SecureRandom();

	/**
	 * Generate unique id.
	 *
	 * @return the string
	 */
	public synchronized static String generateUniqueId() {
		RandomBasedGenerator gen = Generators.randomBasedGenerator(secureRandom);
		UUID uuid = gen.generate();
		return uuid.toString();
	}

	/** The pk. */
	private boolean pk;
	
	/** The tipo. */
	private String tipo;
	
	/** The nome. */
	private String nome;
	
	/** The titulo. */
	private String titulo;
	
	/** The x pos. */
	private int xPos;
	
	/** The tamanho. */
	private int tamanho;
	
	/** The uuid. */
	private String uuid;

	/**
	 * Instantiates a new entidade campo.
	 *
	 * @param pk
	 *            the pk
	 * @param tipo
	 *            the tipo
	 * @param nome
	 *            the nome
	 * @param xPos
	 *            the x pos
	 */
	public EntidadeCampo(boolean pk, String tipo, String nome, int xPos) {
		super();
		this.pk = pk;
		this.tipo = tipo;
		this.nome = nome;
		this.titulo = StringUtils.capitalize(this.nome);
		this.xPos = xPos;
		this.tamanho = 50;
		this.uuid = generateUniqueId();
	}

	/**
	 * Checks if is pk.
	 *
	 * @return true, if is pk
	 */
	public boolean isPk() {
		return pk;
	}

	/**
	 * Sets the pk.
	 *
	 * @param pk
	 *            the new pk
	 */
	public void setPk(boolean pk) {
		this.pk = pk;
	}

	/**
	 * Gets the tipo.
	 *
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Sets the tipo.
	 *
	 * @param tipo
	 *            the new tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Gets the nome.
	 *
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Sets the nome.
	 *
	 * @param nome
	 *            the new nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Gets the titulo.
	 *
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Sets the titulo.
	 *
	 * @param titulo
	 *            the new titulo
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Gets the uuid.
	 *
	 * @return the uuid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * Sets the uuid.
	 *
	 * @param uuid
	 *            the new uuid
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * Gets the x pos.
	 *
	 * @return the x pos
	 */
	public int getxPos() {
		return xPos;
	}

	/**
	 * Sets the x pos.
	 *
	 * @param xPos
	 *            the new x pos
	 */
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	/**
	 * Gets the tamanho.
	 *
	 * @return the tamanho
	 */
	public int getTamanho() {
		return tamanho;
	}

	/**
	 * Sets the tamanho.
	 *
	 * @param tamanho
	 *            the new tamanho
	 */
	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EntidadeCampo [pk=" + pk + ", tipo=" + tipo + ", nome=" + nome + ", titulo=" + titulo + ", xPos=" + xPos
				+ ", tamanho=" + tamanho + ", uuid=" + uuid + "]";
	}

}
