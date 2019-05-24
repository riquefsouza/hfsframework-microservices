package br.com.hfsframework.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.ServletContextResource;

@RestController
@RequestMapping(value = "/api/public")
public class ImageRestController {

	private static final String LOGO_IMAGE = "/WEB-INF/static/img/logo.png";
	private static final String PDF_FILE = "/WEB-INF/static/img/role.pdf";

	private byte[] getFile(HttpServletRequest request, String arquivo) throws IOException {
		ServletContext sc = (ServletContext) request.getServletContext();
		String caminho = sc.getRealPath(File.separator);
		File flogo = new File(caminho += arquivo);
		
		byte[] bytes = FileUtils.readFileToByteArray(flogo);
		
		return bytes;
	}

	@GetMapping(value = "/logo1", produces = MediaType.IMAGE_PNG_VALUE)
	@ResponseBody
	public byte[] getLogo() throws IOException {
		//InputStream in = getClass().getResourceAsStream(LOGO_IMAGE);
		//byte[] bytes = IOUtils.toByteArray(in);
		
	    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	    return getFile(attr.getRequest(), LOGO_IMAGE);
	}

	@GetMapping(value = "/pdf1", produces = MediaType.APPLICATION_PDF_VALUE)
	@ResponseBody
	public byte[] getPdf() throws IOException {
		//InputStream in = getClass().getResourceAsStream(LOGO_IMAGE);
		//byte[] bytes = IOUtils.toByteArray(in);
		
	    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	    return getFile(attr.getRequest(), PDF_FILE);
	}

	@GetMapping(value = "/logo2", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public byte[] getOctetLogo(HttpServletRequest request) throws IOException {
		//InputStream in = getClass().getResourceAsStream(LOGO_IMAGE);
		//byte[] bytes = IOUtils.toByteArray(in);
		
	    return getFile(request, LOGO_IMAGE);
	}
	
	@GetMapping(value = "/pdf2", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public byte[] getOctetPdf(HttpServletRequest request) throws IOException {
		//InputStream in = getClass().getResourceAsStream(LOGO_IMAGE);
		//byte[] bytes = IOUtils.toByteArray(in);
		
	    return getFile(request, PDF_FILE);
	}
	
	
	@RequestMapping(value = "/logo3", method = RequestMethod.GET)
	public void getImageAsByteArray(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ServletContext sc = (ServletContext) request.getServletContext();
	    //InputStream in = sc.getResourceAsStream(LOGO_IMAGE);
				
		String caminho = sc.getRealPath(File.separator);
		File flogo = new File(caminho += LOGO_IMAGE);
		InputStream in = FileUtils.openInputStream(flogo);

	    response.setContentType(MediaType.IMAGE_PNG_VALUE);	    
	    IOUtils.copy(in, response.getOutputStream());
	}	

	@RequestMapping(value = "/pdf3", method = RequestMethod.GET)
	public void getPdfAsByteArray(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ServletContext sc = (ServletContext) request.getServletContext();
	    //InputStream in = sc.getResourceAsStream(LOGO_IMAGE);
				
		String caminho = sc.getRealPath(File.separator);
		File flogo = new File(caminho += PDF_FILE);
		InputStream in = FileUtils.openInputStream(flogo);

	    response.setContentType(MediaType.APPLICATION_PDF_VALUE);	    
	    IOUtils.copy(in, response.getOutputStream());
	}	

	@RequestMapping(value = "/logo4", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getImageAsResponseEntity() throws IOException {
	    HttpHeaders headers = new HttpHeaders();
	    //InputStream in = servletContext.getResourceAsStream(LOGO_IMAGE);
	    //byte[] media = IOUtils.toByteArray(in);
	    
	    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	    byte[] media = getFile(attr.getRequest(), LOGO_IMAGE);
	    
	    headers.setCacheControl(CacheControl.noCache().getHeaderValue());
	     
	    ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
	    return responseEntity;
	}	

	@RequestMapping(value = "/pdf4", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getPdfAsResponseEntity() throws IOException {
	    HttpHeaders headers = new HttpHeaders();
	    //InputStream in = servletContext.getResourceAsStream(LOGO_IMAGE);
	    //byte[] media = IOUtils.toByteArray(in);
	    
	    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	    byte[] media = getFile(attr.getRequest(), PDF_FILE);
	    
	    headers.setCacheControl(CacheControl.noCache().getHeaderValue());
	     
	    ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
	    return responseEntity;
	}	

	@RequestMapping(value = "/logo5", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Resource> getImageAsResource(HttpServletRequest request) {
		ServletContext sc = (ServletContext) request.getServletContext();
		
	    HttpHeaders headers = new HttpHeaders();
	    Resource resource = new ServletContextResource(sc, LOGO_IMAGE);
	    return new ResponseEntity<>(resource, headers, HttpStatus.OK);
	}	

	@RequestMapping(value = "/pdf5", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Resource> getPdfAsResource(HttpServletRequest request) {
		ServletContext sc = (ServletContext) request.getServletContext();
		
	    HttpHeaders headers = new HttpHeaders();
	    Resource resource = new ServletContextResource(sc, PDF_FILE);
	    return new ResponseEntity<>(resource, headers, HttpStatus.OK);
	}	
	
}
