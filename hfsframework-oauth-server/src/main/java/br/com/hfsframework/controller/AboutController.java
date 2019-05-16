package br.com.hfsframework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
public class AboutController {

	@RequestMapping(value = "/api/public/about")
	public String getAbout() {
		return "/about-server.html";
	}
	
	/*
	
	@RequestMapping(consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_HTML_VALUE, method = RequestMethod.GET)
	@ResponseBody
	public String handleRequest(@RequestBody byte[] bytes, @RequestBody String str) {

		System.out.println("body in bytes: " + bytes);
		System.out.println("body in string: " + str);

		return "<html><body><h1>Hi there</h1></body></html>";
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void handleFormRequest(@RequestBody MultiValueMap<String, String> formParams) {
		System.out.println("form params received " + formParams);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public void handleFormPutRequest(@RequestBody MultiValueMap<String, String> formParams) {
		System.out.println("form params received " + formParams);
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<User> handleAllUserRequest() {
		List<User> list = new ArrayList<User>();
		list.setUsers(userService.getAllUsers());
		return list;
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<User> handleAllUserRequest() {
		return userService.getAllUsers();
	}	
	*/
}
