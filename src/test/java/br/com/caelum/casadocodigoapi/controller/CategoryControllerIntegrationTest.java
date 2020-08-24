package br.com.caelum.casadocodigoapi.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.caelum.casadocodigoapi.controller.dto.input.CategoryInputDto;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CategoryControllerIntegrationTest {
	
	@Autowired
	private MockMvc mvc;
	

    private ObjectMapper objectMapper = new ObjectMapper();

	@Test
	void aoSubmeterOsDadosCorretosDeveraCriarUmaNovaCategoria() throws Exception {
		CategoryInputDto dto = createDto("Backend", "Backend development is the activity of developing an application that runs on a server");
		String content = this.objectMapper.writeValueAsString(dto);
		URI uri = new UriTemplate("/api/admin/categories").expand();
		
		MockHttpServletRequestBuilder post = post(uri)
			.contentType(MediaType.APPLICATION_JSON)
			.content(content);
		
		mvc.perform(post)
			.andExpect(status().isCreated())
			.andExpect(content().string(containsString(dto.getTitle())));
	}
	

	@Test
	void aoSubmeterDadosIncorretosDeveraOcorrerUmErro500() throws Exception {
		CategoryInputDto dto = createDto("", "Backend development is the activity of developing an application that runs on a server");
		String content = this.objectMapper.writeValueAsString(dto);
		URI uri = new UriTemplate("/api/admin/categories").expand();
		
		MockHttpServletRequestBuilder post = post(uri)
			.contentType(MediaType.APPLICATION_JSON)
			.content(content);
		
		mvc.perform(post)
			.andExpect(status().isBadRequest());

	}
	
	@Test
	void aoSubmeterOsDadosCorretosParaEdicaoDeveraEditarUmaCategoriaExistente() throws Exception {
		CategoryInputDto dto = createDto("Backend", "Backend development is the activity of developing an application that runs on a server");
		String content = this.objectMapper.writeValueAsString(dto);
		URI postUri = new UriTemplate("/api/admin/categories").expand();
		
		MockHttpServletRequestBuilder post = post(postUri)
			.contentType(MediaType.APPLICATION_JSON)
			.content(content);
		
		mvc.perform(post);
		
		String newCategoryTitle = "Front-End";

		dto.setId(1l);
		dto.setTitle(newCategoryTitle);
		
		URI updateUri = new UriTemplate("/api/admin/categories").expand();
		content = this.objectMapper.writeValueAsString(dto);
		
		MockHttpServletRequestBuilder put = put(updateUri)
			.contentType(MediaType.APPLICATION_JSON)
			.content(content);
		
		mvc.perform(put)
			.andExpect(status().isOk())
			.andExpect(content().string(containsString(newCategoryTitle)));
		
	}
	
	
	private CategoryInputDto createDto(String title, String description) {
		CategoryInputDto dto = new CategoryInputDto();
		dto.setTitle(title);
		dto.setDescription(description);
		
		return dto;
	}


}
