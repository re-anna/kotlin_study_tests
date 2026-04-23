package frontend.pages

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.SelenideElement
import frontend.components.HeaderComponent
import frontend.helpers.Wrappers.Companion.byDataTestId
import io.kotest.matchers.shouldBe
import io.qameta.allure.Step

class MainPage{
    private val txtTitle: SelenideElement get() = element(byDataTestId("main-image-text"))
    val header = HeaderComponent()

    @Step("Open Main Page")
    fun open() {
        Selenide.open("/")
    }

    @Step("Get name of CoffeePlace")
    fun getTitle(): String {
        return txtTitle.text
    }

    @Step("Check Title")
    fun checkTitle(): String{
        val title = return txtTitle.text
        title shouldBe "Welcome to Brew & Bean"
    }

    @Step("Go to Header Component")
    fun header(): HeaderComponent {
        return HeaderComponent()
    }
}