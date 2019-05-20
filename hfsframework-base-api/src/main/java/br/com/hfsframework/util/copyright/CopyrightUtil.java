package br.com.hfsframework.util.copyright;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public final class CopyrightUtil {

	//http://patorjk.com/software/taag/#p=display&f=Graffiti&t=Type%20Something%20
	
	public static String getAsString(CopyrightEnum copyrightEnum) {
		final Resource resource = new ClassPathResource(copyrightEnum.getFileName());
		String sText = "";
		try {
			sText = IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8);
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
		return sText;
	}

}
