package site.enventory

import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class WebBeApplication

fun main(args: Array<String>) {
    org.springframework.boot.runApplication<WebBeApplication>(*args)
}