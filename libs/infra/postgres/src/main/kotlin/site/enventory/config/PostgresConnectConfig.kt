package site.enventory.config

import io.github.cdimascio.dotenv.Dotenv
import io.r2dbc.pool.ConnectionPool
import io.r2dbc.pool.ConnectionPoolConfiguration
import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import io.r2dbc.spi.ConnectionFactoryOptions
import io.r2dbc.spi.ConnectionFactoryOptions.*
import io.r2dbc.spi.Option
import io.r2dbc.proxy.ProxyConnectionFactory
import io.r2dbc.proxy.core.QueryExecutionInfo
import io.r2dbc.proxy.listener.ProxyExecutionListener
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.convert.CustomConversions
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions
import site.enventory.converter.ProviderReadingConverter
import site.enventory.converter.ProviderWritingConverter

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

        // 쿼리 로그 출력용 리스너 설정
        val listener = object : ProxyExecutionListener {
            override fun beforeQuery(executionInfo: QueryExecutionInfo) {
                println("➡️ Executing Query: ${executionInfo.queries}")
            }

            override fun afterQuery(executionInfo: QueryExecutionInfo) {
                val duration = executionInfo.executeDuration.toMillis()
                println("✅ Finished Query: ${executionInfo.queries} (Time: ${duration}ms)")
            }
        }


        val proxyConnectionFactory = ProxyConnectionFactory.builder(delegate)
            .listener(listener)
            .build()

        return ConnectionPool(
            ConnectionPoolConfiguration.builder(proxyConnectionFactory)
                .initialSize(5)
                .maxSize(20)
                .build()
        )
    }

    @Bean
    fun r2dbcCustomConversions(): R2dbcCustomConversions {
        val converters = listOf(
            ProviderWritingConverter(),
            ProviderReadingConverter()
        )
        return R2dbcCustomConversions(CustomConversions.StoreConversions.NONE, converters)
    }
}
