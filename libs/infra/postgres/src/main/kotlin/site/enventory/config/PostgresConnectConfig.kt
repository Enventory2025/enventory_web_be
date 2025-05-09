package site.enventory.config

import io.github.cdimascio.dotenv.Dotenv
import io.r2dbc.pool.ConnectionPool
import io.r2dbc.pool.ConnectionPoolConfiguration
import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import io.r2dbc.spi.ConnectionFactoryOptions
import io.r2dbc.spi.ConnectionFactoryOptions.DATABASE
import io.r2dbc.spi.ConnectionFactoryOptions.DRIVER
import io.r2dbc.spi.ConnectionFactoryOptions.HOST
import io.r2dbc.spi.ConnectionFactoryOptions.PASSWORD
import io.r2dbc.spi.ConnectionFactoryOptions.PORT
import io.r2dbc.spi.ConnectionFactoryOptions.USER
import io.r2dbc.spi.Option
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PostgresConnectConfig(
    private val dotenv: Dotenv
) {

    private val driver = "postgresql"
    private val host = dotenv["DB_HOST"]
    private val port = dotenv["DB_PORT"]
    private val user = dotenv["DB_USER"]
    private val password = dotenv["DB_PASSWORD"]
    private val database = dotenv["DB_NAME"]

    @Bean
    fun connectionFactory(): ConnectionFactory {
        val options: ConnectionFactoryOptions = ConnectionFactoryOptions.builder()
            .option(DRIVER, driver)
            .option(HOST, host)
            .option(PORT, port.toInt())
            .option(USER, user)
            .option(PASSWORD, password)
            .option(DATABASE, database)
            .option(Option.valueOf("schema"), "public")
            .build()

        val delegate: ConnectionFactory = ConnectionFactories.get(options)

        return ConnectionPool(
            ConnectionPoolConfiguration.builder(delegate)
                .initialSize(5)
                .maxSize(20)
                .build()
        );
    }
}