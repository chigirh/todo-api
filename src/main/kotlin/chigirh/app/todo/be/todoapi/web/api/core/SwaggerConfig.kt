package chigirh.app.todo.be.todoapi.web.api.core

import com.google.common.collect.Sets.newHashSet
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

/**
 * SwaggerUI.
 */
@Configuration
@EnableSwagger2
class SwaggerConfig {
    @Bean //@Beanを使用することでDocketインスタンスを設定することができます。
    fun api(): Docket = Docket(DocumentationType.SWAGGER_2)
        .groupName("chigirh") //グループネームの設定。
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.regex("/(?!error).*"))
        .build()
        .apiInfo(apiinfo())
        .protocols(newHashSet("http", "https"))

    fun apiinfo(): ApiInfo = ApiInfoBuilder()
        .title("Todo API")
        .description("TodoアプリケーションAPI")
        .version("1.0")
        .contact(Contact("chigirh", "http://localhost:8080", "xxxxx@mail.com"))
        .build()
}
