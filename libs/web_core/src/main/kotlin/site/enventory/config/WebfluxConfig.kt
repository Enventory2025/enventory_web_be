package site.enventory.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.config.WebFluxConfigurer
import org.springframework.web.reactive.result.method.annotation.ArgumentResolverConfigurer
import site.enventory.annotation.AccessTokenArgumentResolver
import site.enventory.annotation.LoginUserArgumentResolver

@Configuration
class WebFluxConfig(
    private val loginUserArgumentResolver: LoginUserArgumentResolver,
    private val accessTokenArgumentResolver: AccessTokenArgumentResolver
): WebFluxConfigurer {

    override fun configureArgumentResolvers(configurer: ArgumentResolverConfigurer) {
        configurer.addCustomResolver(loginUserArgumentResolver)
        configurer.addCustomResolver(accessTokenArgumentResolver)
    }
}