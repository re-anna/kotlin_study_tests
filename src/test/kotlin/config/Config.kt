package config

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.util.Properties

object Config {

    private const val DEFAULT_PROP_FILE = "/example.properties"
   private const val DEFAULT_JSON_FILE = "/example.json"

    data class Props(
        val browserName: String,
        val browserVersion: String,
        val frontendUrl: String,
        val backendUrl: String,
        val backendApiVersion: String,
        val moonHost: String,
    )

    //Загрузка из пропертис
    //by lazy - инициализируется только при первом обращении
    val get: Props by lazy {
        //динамическая конфигурация, если передали -Denv_config=/prod.properties, то возьмет его.
        // если нет - то файл DEFAULT_PROP_FILE
        val fileName = System.getProperty("env_config") ?: DEFAULT_PROP_FILE
        val properties = Properties().apply {
            val stream = Config::class.java.getResourceAsStream(fileName)
                ?: throw IllegalStateException("Properties file '$fileName' not found")
            stream.use {load(it)}
        }
        //маппинг
        Props(
            browserName = properties.getProperty("browser.name"),
            browserVersion = properties.getProperty("browser.version"),
            frontendUrl = properties.getProperty("frontend.url"),
            backendUrl = properties.getProperty("backend.url"),
            backendApiVersion = properties.getProperty("backend.api.version"),
            moonHost = properties.getProperty("moon.host")
        )
    }

    //загрузка из JSON
    val fromJson: Props by lazy {
        val fileName = System.getProperty("env_config_json") ?: DEFAULT_JSON_FILE
        val stream = Config::class.java.getResourceAsStream(fileName)
        ?: throw IllegalStateException("JSON file '$fileName' not found")

        jacksonObjectMapper().readValue<Props>(stream)
    }
}