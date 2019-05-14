package br.com.hfsframework.test;

import static io.restassured.path.json.JsonPath.from;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
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

import br.com.hfsframework.admin.domain.Parametro;
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
public class ParametroRestTest extends BaseOAuth2RestAssuredTest {

	private static final Logger log = LogManager.getLogger(ParametroRestTest.class);
	
	private static final String HFSFRAMEWORK_ADMIN_SERVER = "http://localhost:8080/hfsframework-admin-server/api/v1";
	
	private List<Parametro> objList = new ArrayList<Parametro>();
	
	@BeforeAll
    public void setup() throws Exception {
    	objList.add(new Parametro(1L, "ALFA valor", "ALFA descricao", "ALFA codigo", 1L, new ParametroCategoria(69L)));
    	objList.add(new Parametro(2L, "BETA valor", "BETA descricao", "BETA codigo", 1L, new ParametroCategoria(69L)));
    	objList.add(new Parametro(3L, "GAMA valor", "GAMA descricao", "GAMA codigo", 1L, new ParametroCategoria(69L)));
    	objList.add(new Parametro(4L, "TETA valor", "TETA descricao", "TETA codigo", 1L, new ParametroCategoria(69L)));
        
        accessToken = obtainAccessToken("admin", "admin");
    }
	
    @Test
    @Order(1)
    public void addAll() throws Exception {
    	log.info(TEST_ACTION.ADD_ALL);
    	
    	this.objList.stream().forEach(bean -> 
	        {
				try {
					String sjson = this.json(bean);
					
					final Response response = RestAssured
							.given().header("Authorization", "Bearer " + accessToken)
							.contentType(CONTENT_TYPE)
							.when().body(sjson)
							.post(HFSFRAMEWORK_ADMIN_SERVER + "/parametro");
					
					assertEquals(HttpStatus.SC_CREATED, response.getStatusCode());			
					assertEquals(CONTENT_TYPE, response.getContentType());
					assertNotNull(response.jsonPath().getLong("id"));
					bean.setId(response.jsonPath().getLong("id"));
					
					log.info(bean);
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
    	log.info(TEST_ACTION.GET_ALL);
    	
		final Response response = RestAssured
				.given().header("Authorization", "Bearer " + accessToken)
				.contentType(CONTENT_TYPE)
				.get(HFSFRAMEWORK_ADMIN_SERVER + "/parametro");
						
		assertEquals(HttpStatus.SC_OK, response.getStatusCode());			
		assertEquals(CONTENT_TYPE, response.getContentType());
		
		String content = response.asString();
		List<String> lst = from(content).getList("");
		assertThat(lst).hasSize(4);
		assertEquals(lst.size(), 4);
		assertNotNull(lst.get(0));
		assertNotNull(from(content).getLong("[0].id"));
		assertEquals(this.objList.get(0).getValor(), from(content).getString("[0].valor"));
		assertEquals(this.objList.get(0).getDescricao(), from(content).getString("[0].descricao"));
		assertEquals(this.objList.get(0).getCodigo(), from(content).getLong("[0].codigo"));
		
		response.prettyPrint();
    }

    @Test
    @Order(3)
    public void getById() throws Exception {
    	log.info(TEST_ACTION.GET_BY_ID);
    	
    	this.objList.stream().forEach(bean -> 
        {
			try {
				final Response response = RestAssured.given()
						.header("Authorization", "Bearer " + accessToken)
						.contentType(ContentType.JSON)
						.get(HFSFRAMEWORK_ADMIN_SERVER + "/parametro/" + bean.getId());
				
				assertEquals(HttpStatus.SC_OK, response.getStatusCode());
				assertEquals(CONTENT_TYPE, response.getContentType());
				assertNotNull(response.jsonPath().get("id"));
				assertEquals(bean.getId(), response.jsonPath().getLong("id"));
				assertNotNull(response.jsonPath().get("descricao"));
				assertEquals(bean.getDescricao(), response.jsonPath().get("descricao"));
				
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
    	log.info(TEST_ACTION.UPDATE_BY_ID);
    	
    	this.objList.stream().forEach(bean -> bean.setDescricao("DESC "+bean.getId()));
    	
    	this.objList.stream().forEach(bean -> 
	        {
				try {
					String sjson = this.json(bean);
					
					final Response response = RestAssured
							.given().header("Authorization", "Bearer " + accessToken)
							.contentType(CONTENT_TYPE)
							.when().body(sjson)
							.put(HFSFRAMEWORK_ADMIN_SERVER + "/parametro/" + bean.getId());
					
					assertEquals(HttpStatus.SC_OK, response.getStatusCode());			
					assertEquals(CONTENT_TYPE, response.getContentType());
										
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
    	log.info(TEST_ACTION.DELETE_BY_ID);
    	
    	this.objList.stream().forEach(bean -> 
    		{
				try {
					
					final Response response = RestAssured
							.given().header("Authorization", "Bearer " + accessToken)
							.contentType(CONTENT_TYPE)
							.delete(HFSFRAMEWORK_ADMIN_SERVER + "/parametro/" + bean.getId());
					
					assertEquals(HttpStatus.SC_OK, response.getStatusCode());			

					response.prettyPrint();
					
				} catch (Exception e) {
					log.error(e.getMessage());
				}
			}
		);
    }
	
}
