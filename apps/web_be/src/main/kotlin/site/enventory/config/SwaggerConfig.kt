package site.enventory.config

import org.springdoc.core.customizers.OpenApiCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun openApiCustomiser(): OpenApiCustomizer {
        return OpenApiCustomizer { openApi ->
            openApi.info.title = "Enventory Web BE API"
            openApi.info.version = "1.0.0"
            openApi.info.description = """
                API documentation for Enventory Web BE
            """.trimIndent()
        }
    }
}