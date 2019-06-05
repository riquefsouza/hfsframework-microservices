/**
 * <p><b>HFS Framework Spring</b></p>
 * @author Henrique Figueiredo de Souza
 * @version 1.0
 * @since 2018
 */
package br.com.hfsframework.admin.serializer;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import br.com.hfsframework.admin.domain.AdmUserIp;
import br.com.hfsframework.admin.domain.AdmUserIpPK;

// TODO: Auto-generated Javadoc
/**
 * The Class AdmUserIpListSerializer.
 */
public class AdmUserIpListSerializer extends StdSerializer<List<AdmUserIp>> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant log. */
	private static final Logger log = LoggerFactory.getLogger(AdmUserIpListSerializer.class);

	/**
	 * Instantiates a new adm usuario ip list serializer.
	 */
	public AdmUserIpListSerializer() {
		this(null);
	}

	/**
	 * Instantiates a new adm usuario ip list serializer.
	 *
	 * @param t the t
	 */
	public AdmUserIpListSerializer(Class<List<AdmUserIp>> t) {
		super(t);
	}

	/* (non-Javadoc)
	 * @see com.fasterxml.jackson.databind.ser.std.StdSerializer#serialize(java.lang.Object, com.fasterxml.jackson.core.JsonGenerator, com.fasterxml.jackson.databind.SerializerProvider)
	 */
	@Override
	public void serialize(List<AdmUserIp> items, JsonGenerator generator, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		Method metodo;
		AdmUserIpPK valor;
		List<AdmUserIpPK> ids = new ArrayList<AdmUserIpPK>();

		for (AdmUserIp item : items) {
			metodo = ReflectionUtils.findMethod(item.getClass(), "getId");
			valor = (AdmUserIpPK) ReflectionUtils.invokeMethod(metodo, item);

			try {
				ids.add(valor);
			} catch (IllegalArgumentException e) {
				log.error(e.getMessage());
			}
		}
		generator.writeObject(ids);
	}
}
