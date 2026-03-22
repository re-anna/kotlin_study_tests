package org.example.general

import org.example.general.Config.DEFAULT_JSON_FILE
import org.example.general.Config.Props
import tools.jackson.module.kotlin.jacksonObjectMapper
import tools.jackson.module.kotlin.readValue

fun readJsonConfig(path: String): Config.Props{}
private const val DEFAULT_JSON_FILE = "/example.json"

val fromJson: Props by lazy {
    val fileName = System.getProperty("env_config_json") ?: DEFAULT_JSON_FILE
    val mapper = jacksonObjectMapper()
    val stream = Config::class.java.getResourceAsStream(fileName)
        ?: throw IllegalStateException("Properties file '$fileName' not found")
    stream.use {
        mapper.readValue<Props>(it)
    }
}