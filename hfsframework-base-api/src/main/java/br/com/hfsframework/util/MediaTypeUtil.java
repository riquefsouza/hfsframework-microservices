package br.com.hfsframework.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.http.MediaType;

public final class MediaTypeUtil {

	public static List<MediaType> getByteArrayMediaTypes() {
		List<MediaType> list = new ArrayList<MediaType>();
		list.add(MediaType.IMAGE_GIF);
		list.add(MediaType.IMAGE_JPEG);
		list.add(MediaType.IMAGE_PNG);
		list.add(MediaType.APPLICATION_OCTET_STREAM);
		list.add(MediaType.APPLICATION_PDF);
		list.add(MediaType.APPLICATION_XML);
				
		return list;
	}

	public static MediaType getMediaTypeForFileName(ServletContext servletContext, String fileName) {
		String mineType = servletContext.getMimeType(fileName);

		try {
			MediaType mediaType = MediaType.parseMediaType(mineType);
			return mediaType;
		} catch (Exception e) {
			return MediaType.APPLICATION_OCTET_STREAM;
		}
	}
}
