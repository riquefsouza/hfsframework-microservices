package br.com.hfsframework.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/public")
public class FileDownloadRestController {

	private File getFile(HttpServletRequest request) throws IOException {
		ServletContext sc = (ServletContext) request.getServletContext();
		String caminho = sc.getRealPath(File.separator);
		File flogo = new File(caminho += "/WEB-INF/static/img/role.pdf");
		
		//byte[] bytes = FileUtils.readFileToByteArray(flogo);
		
		return flogo;
	}
	
	@GetMapping(value = "/download1", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> download1(HttpServletRequest request) throws IOException {

		File file = getFile(request);
		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
				.contentType(MediaType.APPLICATION_PDF)
				.contentLength(file.length())
				.body(resource);
	}

	@GetMapping(value = "/download2", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<ByteArrayResource> download2(HttpServletRequest request) throws IOException {

		File file = getFile(request);
		Path path = Paths.get(file.getAbsolutePath());
		byte[] data = Files.readAllBytes(path);
		ByteArrayResource resource = new ByteArrayResource(data);

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + path.getFileName().toString())
				.contentType(MediaType.APPLICATION_PDF)
				.contentLength(data.length)
				.body(resource);
	}

	@GetMapping(value = "/download3")
	public void download3(HttpServletRequest request, HttpServletResponse response) throws IOException {
		File file = getFile(request);

		response.setContentType("application/pdf");
		//response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
		response.setHeader("Content-Disposition", "inline;filename=" + file.getName());
		BufferedInputStream inStrem = new BufferedInputStream(new FileInputStream(file));
		BufferedOutputStream outStream = new BufferedOutputStream(response.getOutputStream());

		byte[] buffer = new byte[1024];
		int bytesRead = 0;
		while ((bytesRead = inStrem.read(buffer)) != -1) {
			outStream.write(buffer, 0, bytesRead);
		}
		outStream.flush();
		inStrem.close();
	}
}
