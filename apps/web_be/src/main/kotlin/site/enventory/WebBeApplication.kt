package site.enventory

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy

@EnableAspectJAutoProxy
@SpringBootApplication
class WebBeApplication

fun main(args: Array<String>) {
    org.springframework.boot.runApplication<WebBeApplication>(*args)
}