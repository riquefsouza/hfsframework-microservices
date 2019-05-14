package br.com.hfsframework.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static io.restassured.path.json.JsonPath.from;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpStatus;
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
	
	@BeforeEach
    public void setup() throws Exception {
        objList.add(new ParametroCategoria(1L, "ALFA Parâmetros", 1L));
        objList.add(new ParametroCategoria(2L, "BETA Parâmetros", 2L));
        objList.add(new ParametroCategoria(3L, "GAMA Parâmetros", 3L));
        objList.add(new ParametroCategoria(4L, "TETA Parâmetros", 4L));
        
        accessToken = obtainAccessToken("admin", "admin");
    }
	
    @Test
    @Order(1)
    public void addAll() throws Exception {
    	this.objList.stream().forEach(bean -> 
	        {
				try {
					String sjson = this.json(bean);
					
					final Response response = RestAssured
							.given().header("Authorization", "Bearer " + accessToken)
							//.contentType(ContentType.JSON)
							.contentType(CONTENT_TYPE)
							.when().body(sjson)
							.post(HFSFRAMEWORK_ADMIN_SERVER + "/parametroCategoria");
					
					assertEquals(HttpStatus.SC_CREATED, response.getStatusCode());			
					assertEquals(CONTENT_TYPE, response.getContentType());

					/*
					mockMvc.perform(post("/parametroCategoria")
							.header("Authorization", "Bearer " + accessToken).accept(contentType)
					        .content(this.json(bean))
					        .contentType(contentType))
					        .andExpect(status().isOk());
					*/
					response.prettyPrint();
					
				} catch (Exception e) {
					log.error(e.getMessage());
				}
			}
    	);
    }

    @Test
    @Order(2)
    public void getAll() throws Exception {
		final Response response = RestAssured
				.given().header("Authorization", "Bearer " + accessToken)
				.contentType(CONTENT_TYPE)
				.get(HFSFRAMEWORK_ADMIN_SERVER + "/parametroCategoria");
						
		assertEquals(HttpStatus.SC_OK, response.getStatusCode());			
		assertEquals(CONTENT_TYPE, response.getContentType());
		
		String content = response.asString();
		List<String> lst = from(content).getList("");
		assertThat(lst).hasSize(4);
		assertEquals(lst.size(), 4);
		assertNotNull(lst.get(0));
		assertEquals(this.objList.get(0).getId(), from(content).getLong("[0].id"));
		assertEquals(this.objList.get(0).getDescricao(), from(content).getString("[0].descricao"));
		assertEquals(this.objList.get(0).getOrdem(), from(content).getLong("[0].ordem"));
		
    	/*
        mockMvc.perform(get("/parametroCategoria")
        		.header("Authorization", "Bearer " + accessToken).accept(contentType))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(this.objList.size())))
                .andExpect(jsonPath("$[0].id", is(this.objList.get(0).getId().intValue())))
                .andExpect(jsonPath("$[0].descricao", is(this.objList.get(0).getDescricao())))
                .andExpect(jsonPath("$[1].id", is(this.objList.get(1).getId().intValue())))
                .andExpect(jsonPath("$[1].descricao", is(this.objList.get(1).getDescricao())));
        */
		
		response.prettyPrint();
    }

    @Test
    @Order(3)
    public void getById() throws Exception {
    	this.objList.stream().forEach(bean -> 
        {
			try {
				final Response response = RestAssured.given()
						.header("Authorization", "Bearer " + accessToken)
						.contentType(ContentType.JSON)
						.get(HFSFRAMEWORK_ADMIN_SERVER + "/parametroCategoria/" + bean.getId());
				
				assertEquals(HttpStatus.SC_OK, response.getStatusCode());
				assertEquals(CONTENT_TYPE, response.getContentType());
				assertNotNull(response.jsonPath().get("id"));
				assertEquals(bean.getId(), response.jsonPath().getLong("id"));
				assertNotNull(response.jsonPath().get("descricao"));
				assertEquals(bean.getDescricao(), response.jsonPath().get("descricao"));
				
				/*
		        mockMvc.perform(get("/parametroCategoria/" + bean.getId())
		        		.header("Authorization", "Bearer " + accessToken).accept(contentType))
		                .andExpect(status().isOk())
		                .andExpect(content().contentType(contentType))
		                .andExpect(jsonPath("$.id", is(bean.getId().intValue())))
		                .andExpect(jsonPath("$.descricao", is(bean.getDescricao())));
		        */        
				response.prettyPrint();
				
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
	);
    }
    
    @Test
    @Order(4)
    public void updateById() throws Exception {
    	objList.clear();
        objList.add(new ParametroCategoria(1L, "Parâmetros do Tribunal", 1L));
        objList.add(new ParametroCategoria(2L, "Parâmetros de Login", 2L));
        objList.add(new ParametroCategoria(3L, "Parâmetros de E-mail", 3L));
        objList.add(new ParametroCategoria(4L, "Parâmetros de Conexão de Rede", 4L));
    	
    	this.objList.stream().forEach(bean -> 
	        {
				try {
					String sjson = this.json(bean);
					
					final Response response = RestAssured
							.given().header("Authorization", "Bearer " + accessToken)
							.contentType(CONTENT_TYPE)
							.when().body(sjson)
							.put(HFSFRAMEWORK_ADMIN_SERVER + "/parametroCategoria/" + bean.getId());
					
					assertEquals(HttpStatus.SC_OK, response.getStatusCode());			
					assertEquals(CONTENT_TYPE, response.getContentType());
										
					/*
					mockMvc.perform(put("/parametroCategoria/"+bean.getId())
							.header("Authorization", "Bearer " + accessToken).accept(contentType)
					        .content(this.json(bean))
					        .contentType(contentType))
					        .andExpect(status().isOk());
					 */
					
					response.prettyPrint();
					
				} catch (Exception e) {
					log.error(e.getMessage());
				}
			}
    	);
    }

    @Test
    @Order(5)
    public void deleteById() throws Exception {
    	this.objList.stream().forEach(bean -> 
    		{
				try {
					
					final Response response = RestAssured
							.given().header("Authorization", "Bearer " + accessToken)
							.contentType(CONTENT_TYPE)
							.delete(HFSFRAMEWORK_ADMIN_SERVER + "/parametroCategoria/" + bean.getId());
					
					assertEquals(HttpStatus.SC_OK, response.getStatusCode());			
					assertEquals(CONTENT_TYPE, response.getContentType());
					
					/*
			        mockMvc.perform(delete("/parametroCategoria/"+bean.getId())
			        		.header("Authorization", "Bearer " + accessToken).accept(contentType))
			                .andExpect(status().isNoContent());
			        */
					
					response.prettyPrint();
					
				} catch (Exception e) {
					log.error(e.getMessage());
				}
			}
		);
    }
	
}
