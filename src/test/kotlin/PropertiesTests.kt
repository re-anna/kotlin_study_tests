import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.kotest.matchers.string.shouldNotBeEmpty
import io.kotest.matchers.string.shouldStartWith
import config.Config
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class PropertiesTests {

    @Test
    @DisplayName("Positive test: read and check example.properties")
    fun testPropertiesLoading() {
        System.setProperty("env_config", "/example.properties")
        val props = Config.get
        println("Properties file: $props")
        props.browserName shouldBe "chrome"
        props.browserVersion.shouldNotBeEmpty()
        props.frontendUrl shouldStartWith "http"
        props.backendUrl shouldStartWith "http"
        props.backendApiVersion.matches(Regex("v\\d+")) shouldBe true
        props.moonHost shouldContain "http://localhost:30765/wd/hub"
    }
}