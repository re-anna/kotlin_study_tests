import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.element
import org.junit.jupiter.api.Test

class FirstUiTest {

    @Test
    fun testFirstUI() {
        Selenide.open("https://google.com")
        val searchInput = element("[name = 'q']")
        searchInput.value = "Selenide"
        searchInput.pressEnter()
        Selenide.sleep(5000)
    }
}