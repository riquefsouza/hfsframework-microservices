/**
 * <p><b>HFS Framework</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2017
 */
package br.com.hfsframework.util.entity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class EntidadeUtil.
 */
public class EntityUtil {

	/** The log. */
	private static Logger log = LogManager.getLogger(EntityUtil.class);

	/**
	 * Ler campos.
	 *
	 * @param pacote
	 *            the pacote
	 * @param nomeClasse
	 *            the nome classe
	 * @return the list
	 */
	public static List<EntityField> lerCampos(String pacote, String nomeClasse){
		List<String> listaClasse = new ArrayList<String>();
		listaClasse.add(nomeClasse);
		List<EntityClass> listaEntidade = lerClasse(pacote, listaClasse);	
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
	public static List<EntityClass> lerClasse(String pacote, List<String> nomeClasse) {
		List<EntityClass> classes = new ArrayList<EntityClass>();
		List<EntityField> campos, camposPK;
		EntityClass classe;
		String nomePK = "", itemPK = "";

		for (String item : nomeClasse) {
			itemPK = item;
			campos = lerCampos(pacote, item, "", false);
			for (EntityField campo : campos) {				 
				if (campo.isPk()) {
					nomePK = campo.getNome();
					itemPK = item + "PK";
					break;
				}
			}	
			if (!nomePK.isEmpty()) {
				camposPK = lerCampos(pacote, itemPK, nomePK, true);
			} else {
				camposPK = new ArrayList<EntityField>();
			}
			camposPK.addAll(campos);
			classe = new EntityClass(pacote, item, camposPK);
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
	private static List<EntityField> lerCampos(String pacote, String classe, String nomePK, boolean pk) {
		List<EntityField> campos = new ArrayList<EntityField>();
		EntityField campo;
		Class<?> clazz;
		int xPos = 0;
		
		try {
			clazz = Class.forName(pacote + classe);

			Field[] fld = clazz.getDeclaredFields();
			for (Field item : fld) {
				if (!item.getName().equals("serialVersionUID")) {
					if (item.getType().getSimpleName().endsWith("PK"))
						campo = new EntityField(true, item.getType().getName(), item.getName(), xPos);
					else if (pk)
						campo = new EntityField(pk, item.getType().getName(), nomePK + "." + item.getName(), xPos);
					else
						campo = new EntityField(pk, item.getType().getName(), item.getName(), xPos);

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
