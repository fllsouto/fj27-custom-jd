package br.com.caelum.casadocodigoapi.configuration;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfiguration {
	
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("br.com.caelum.casadocodigoapi"))
            .paths(PathSelectors.ant("/api/**"))
            .build()
            .apiInfo(apiInfo())
            .globalResponseMessage(RequestMethod.GET, getResponseMessages());
    }

    private List<ResponseMessage> getResponseMessages() {
        return Arrays.asList(
            createResponseMessage(500, "Xii! deu erro interno no servidor."),
            createResponseMessage(403, "Forbidden! Você não pode acessar esse recurso."),
            createResponseMessage(404, "O recurso que você buscou não foi encontrado.")
        );
    }

    private ResponseMessage createResponseMessage(int code, String message) {
        return new ResponseMessageBuilder()
                .code(code)
                .message(message)
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contato = new Contact("Caelum", "https://www.caelum.com.br/", "fellipe.souto@caelum.com.br");
        return new ApiInfoBuilder()
                .title("Casa do Codigo API Documentation")
                .description("Esta é a documentação interativa da Rest API da Casa do Código. Teste enviar algum request ;)")
                .version("1.0")
                .contact(contato)
                .build();
    }

}
