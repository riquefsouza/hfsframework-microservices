package br.com.hfsframework.test;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import br.com.hfsframework.admin.domain.Parametro;
import br.com.hfsframework.base.BaseOAuth2RestTest;
import br.com.hfsframework.config.TestWebConfig;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration()
@ContextConfiguration(classes = {TestWebConfig.class})
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class ParametroRestTest extends BaseOAuth2RestTest {

	private static final Logger log = LogManager.getLogger(ParametroRestTest.class);
	
	private List<Parametro> objList = new ArrayList<Parametro>();

    @BeforeEach
    public void setup() {
    	objList.add(new Parametro(1L, "Tribunal Regional do Trabalho da 1a. Região", "Nome do tribunal onde o sistema está instalado.", "NOME_TRIBUNAL", 1L));
    	objList.add(new Parametro(2L, "TRT1", "Sigla do tribunal onde o sistema está instalado.", "SIGLA_TRIBUNAL", 1L));
    	objList.add(new Parametro(3L, "080009", "Código númérico de 6 dígitos que identifica o órgão no SIAFI.", "CODIGO_UNIDADE_GESTORA", 1L));
    	objList.add(new Parametro(4L, "102", "Código númérico de 3 dígitos da UG no código de barras da GRU.", "APELIDO_UNIDADE_GESTORA", 1L));
    	objList.add(new Parametro(5L, "false", "Bloquear o sistema para que os usuários, exceto do administador, não façam login", "BLOQUEAR_LOGIN", 2L));
    	objList.add(new Parametro(6L, "Produção", "Define o ambiente onde o sistema está instalado. Este parâmetro pode ser preenchido com: desenvolvimento, homologação ou produção", "AMBIENTE_SISTEMA", 2L));
    	objList.add(new Parametro(7L, "NOME_USUARIO", "Define o atributo usado para efetuar login no sistema. Este parâmetro pode ser preenchido com: NOME_USUARIO ou CPF", "ATRIBUTO_LOGIN", 2L));
    	objList.add(new Parametro(8L, "smtp.com.br", "Servidor SMTP para que o sistema envie e-mail.", "SMTP_SERVIDOR", 3L));
    	objList.add(new Parametro(9L, "25", "Porta do servidor SMTP para que o sistema envie e-mail.", "SMTP_PORTA", 3L));
    	objList.add(new Parametro(10L, null, "Usuário para login no servidor SMTP.", "SMTP_USERNAME", 3L));
    	objList.add(new Parametro(11L, null, "Senha para login no servidor SMTP.", "SMTP_PASSWORD", 3L));
    	objList.add(new Parametro(12L, "sistema@teste.com.br", "E-mail do sistema.", "SMTP_EMAIL_FROM", 3L));
    	objList.add(new Parametro(13L, "teste.com.br", "Servidor do Proxy.", "PROXY_SERVIDOR", 4L));
    	objList.add(new Parametro(14L, "8080", "Porta do Proxy.", "PROXY_PORTA", 4L));
    }
       
    /*
	@Test
	public void getAll() {
		final String accessToken = obtainAccessToken("admin", "admin");

		final Response response = RestAssured.given().header("Authorization", "Bearer " + accessToken)
				.get(HFSFRAMEWORK_ADMIN_SERVER + "/api/v1/parametro");
		assertEquals(200, response.getStatusCode());
		// assertNotNull();
		// log.info("PARAMETRO: " + );
		response.jsonPath().prettyPrint();
	}
     */
    
    @Test
    @Order(1)
    public void addAll() throws Exception {
    	this.objList.stream().forEach(bean -> 
	        {
				try {
					mockMvc.perform(post("/parametro")
							.header("Authorization", "Bearer " + accessToken).accept(contentType)
					        .content(this.json(bean))
					        .contentType(contentType))
					        .andExpect(status().isOk());
					
					log.info(bean.getId() + " - " + bean.getDescricao());
					
				} catch (Exception e) {
					log.error(e.getMessage());
				}
			}
    	);
    }

    /**
     * B listar.
     *
     * @throws Exception the exception
     */
    @Test
    @Order(2)
    public void getAll() throws Exception {
        mockMvc.perform(get("/parametro")
        		.header("Authorization", "Bearer " + accessToken).accept(contentType))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(this.objList.size())))
                .andExpect(jsonPath("$[0].id", is(this.objList.get(0).getId().intValue())))
                .andExpect(jsonPath("$[0].descricao", is(this.objList.get(0).getDescricao())))
                .andExpect(jsonPath("$[1].id", is(this.objList.get(1).getId().intValue())))
                .andExpect(jsonPath("$[1].descricao", is(this.objList.get(1).getDescricao())));
    }

    @Test
    @Order(3)
    public void getById() throws Exception {
    	this.objList.stream().forEach(bean -> 
        {
			try {
		        mockMvc.perform(get("/parametro/" + bean.getId())
		        		.header("Authorization", "Bearer " + accessToken).accept(contentType))
		                .andExpect(status().isOk())
		                .andExpect(content().contentType(contentType))
		                .andExpect(jsonPath("$.id", is(bean.getId().intValue())))
		                .andExpect(jsonPath("$.descricao", is(bean.getDescricao())));
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
	);
    }
    
    @Test
    @Order(4)
    public void updateById() throws Exception {
    	this.objList.stream().forEach(bean -> 
	        {
				try {
					mockMvc.perform(put("/parametro/"+bean.getId())
							.header("Authorization", "Bearer " + accessToken).accept(contentType)
					        .content(this.json(bean))
					        .contentType(contentType))
					        .andExpect(status().isOk());
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
			        mockMvc.perform(delete("/parametro/"+bean.getId())
			        		.header("Authorization", "Bearer " + accessToken).accept(contentType))
			                .andExpect(status().isNoContent());
				} catch (Exception e) {
					log.error(e.getMessage());
				}
			}
		);
    }
    
}
