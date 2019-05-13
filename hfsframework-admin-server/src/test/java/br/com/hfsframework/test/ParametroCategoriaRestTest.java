package br.com.hfsframework.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import br.com.hfsframework.admin.domain.ParametroCategoria;
import br.com.hfsframework.base.BaseOAuth2RestAssuredTest;
import br.com.hfsframework.config.TestWebConfig;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration()
@ContextConfiguration(classes = { TestWebConfig.class })
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class ParametroCategoriaRestTest extends BaseOAuth2RestAssuredTest {

	private static final Logger log = LogManager.getLogger(ParametroCategoriaRestTest.class);
	
	private static final String HFSFRAMEWORK_ADMIN_SERVER = "http://localhost:8080/hfsframework-admin-server/api/v1";
	
	private List<ParametroCategoria> objList = new ArrayList<ParametroCategoria>();
	
	private String accessToken;
	
	private HttpMessageConverter<Object> mappingJackson2HttpMessageConverter;
    
    @Autowired
    protected void setConverters(HttpMessageConverter<Object>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
            .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
            .findAny()
            .orElse(null);

        assertNotNull(this.mappingJackson2HttpMessageConverter, 
        		"the JSON message converter must not be null");
    }
	
	protected String json(Object o) throws IOException {
	    MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
	    this.mappingJackson2HttpMessageConverter.write(
	            o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
	    return mockHttpOutputMessage.getBodyAsString();
	}

	
	@BeforeEach
    public void setup() throws Exception {
        objList.add(new ParametroCategoria(1L, "Parâmetros do Tribunal", 1L));
        objList.add(new ParametroCategoria(2L, "Parâmetros de Login", 2L));
        objList.add(new ParametroCategoria(3L, "Parâmetros de E-mail", 3L));
        objList.add(new ParametroCategoria(4L, "Parâmetros de Conexão de Rede", 4L));
        
        accessToken = obtainAccessToken("admin", "admin");
    }
	
    @Test
    @Order(1)
    public void addAll() throws Exception {
    	ParametroCategoria bean = new ParametroCategoria(1L, "Parâmetros do Tribunal", 1L);
    	//this.objList.stream().forEach(bean -> 
	      //  {
				try {
					String sjson = this.json(bean);
					
					final Response response = RestAssured
							.given().header("Authorization", "Bearer " + accessToken)
							//.contentType(ContentType.JSON)
							.contentType(CONTENT_TYPE)
							.when().body(sjson)
							.post(HFSFRAMEWORK_ADMIN_SERVER + "/parametroCategoria");
					
					log.info("STATUS CODE: " + response.getStatusCode());
					log.info("STATUS STR: " + status().isCreated().toString());
					assertEquals(201, response.getStatusCode());			
					assertEquals(CONTENT_TYPE, response.getContentType());

					/*
					mockMvc.perform(post("/parametroCategoria")
							.header("Authorization", "Bearer " + accessToken).accept(contentType)
					        .content(this.json(bean))
					        .contentType(contentType))
					        .andExpect(status().isOk());
					*/
					log.info(bean.getId() + " - " + bean.getDescricao());
					
				} catch (Exception e) {
					log.error(e.getMessage());
				}
			//}
    	//);
    }

	
}
