import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.sleep
import io.kotest.matchers.shouldBe
import frontend.helpers.BaseUiTest
import org.junit.jupiter.api.Test

class AdditionalTests : frontend.helpers.BaseUiTest() {

    @Test
    fun openGoldApple(){
        Selenide.open("/")
        val title = Selenide.title()
        sleep(10_000)
        title shouldBe "Gold Apple — checking device"
    }
}
