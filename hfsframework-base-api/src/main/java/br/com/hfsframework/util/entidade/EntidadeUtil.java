/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.util.entidade;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class EntidadeUtil.
 */
public class EntidadeUtil {

	/** The log. */
	private static Logger log = LogManager.getLogger(EntidadeUtil.class);

	/**
	 * Ler campos.
	 *
	 * @param pacote
	 *            the pacote
	 * @param nomeClasse
	 *            the nome classe
	 * @return the list
	 */
	public static List<EntidadeCampo> lerCampos(String pacote, String nomeClasse){
		List<String> listaClasse = new ArrayList<String>();
		listaClasse.add(nomeClasse);
		List<EntidadeClasse> listaEntidade = lerClasse(pacote, listaClasse);	
		return listaEntidade.get(0).getCampos();
	}
	
	/**
	 * Ler classe.
	 *
	 * @param pacote
	 *            the pacote
	 * @param nomeClasse
	 *            the nome classe
	 * @return the list
	 */
	public static List<EntidadeClasse> lerClasse(String pacote, List<String> nomeClasse) {
		List<EntidadeClasse> classes = new ArrayList<EntidadeClasse>();
		List<EntidadeCampo> campos, camposPK;
		EntidadeClasse classe;
		String nomePK = "", itemPK = "";

		for (String item : nomeClasse) {
			itemPK = item;
			campos = lerCampos(pacote, item, "", false);
			for (EntidadeCampo campo : campos) {				 
				if (campo.isPk()) {
					nomePK = campo.getNome();
					itemPK = item + "PK";
					break;
				}
			}	
			if (!nomePK.isEmpty()) {
				camposPK = lerCampos(pacote, itemPK, nomePK, true);
			} else {
				camposPK = new ArrayList<EntidadeCampo>();
			}
			camposPK.addAll(campos);
			classe = new EntidadeClasse(pacote, item, camposPK);
			classes.add(classe);
		}
		return classes;
	}

	/**
	 * Ler campos.
	 *
	 * @param pacote
	 *            the pacote
	 * @param classe
	 *            the classe
	 * @param nomePK
	 *            the nome PK
	 * @param pk
	 *            the pk
	 * @return the list
	 */
	private static List<EntidadeCampo> lerCampos(String pacote, String classe, String nomePK, boolean pk) {
		List<EntidadeCampo> campos = new ArrayList<EntidadeCampo>();
		EntidadeCampo campo;
		Class<?> clazz;
		int xPos = 0;
		
		try {
			clazz = Class.forName(pacote + classe);

			Field[] fld = clazz.getDeclaredFields();
			for (Field item : fld) {
				if (!item.getName().equals("serialVersionUID")) {
					if (item.getType().getSimpleName().endsWith("PK"))
						campo = new EntidadeCampo(true, item.getType().getName(), item.getName(), xPos);
					else if (pk)
						campo = new EntidadeCampo(pk, item.getType().getName(), nomePK + "." + item.getName(), xPos);
					else
						campo = new EntidadeCampo(pk, item.getType().getName(), item.getName(), xPos);

					campos.add(campo);
					xPos += campo.getTamanho();
				}
			}
		} catch (ClassNotFoundException e) {
			log.error(e);
		}
		return campos;
	}

}
