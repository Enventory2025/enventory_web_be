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
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing
import site.enventory.constant.ColorCode.*

@Configuration
@EnableR2dbcAuditing
class R2dbcConfig(
    private val dotenv: Dotenv
) {

    private val serverEnv = dotenv["SERVER_ENV"]

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
            override fun afterQuery(executionInfo: QueryExecutionInfo) {
                if (serverEnv == "production") return

                val duration = executionInfo.executeDuration.toMillis()
                executionInfo.queries.forEach { queryInfo ->
                    val query = queryInfo.query
                    val params = queryInfo.bindingsList
                    println("✅ Query executed in ${AQUA.code}${duration}ms${RESET.code}. Query: ${YELLOW.code}$query${RESET.code}")
                    params.forEach { param ->
                        param.namedBindings.forEach { namedBinding ->
                            println("   🪢 Named Binding: ${namedBinding.key} = ${namedBinding.boundValue.value}")
                        }
                        param.indexBindings.forEach { indexBindings ->
                            println("   🪢 Index Binding: ${indexBindings.key} = ${indexBindings.boundValue.value}")
                        }
                    }
                }
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
}
