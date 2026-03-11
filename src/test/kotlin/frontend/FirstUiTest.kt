package frontend

import io.kotest.matchers.shouldBe
import io.qameta.allure.Owner
import org.example.frontend.constants.AVORONTSOVA
import org.example.frontend.helpers.BaseUiTest
import org.example.frontend.pages.MainPage
import org.example.frontend.pages.ProductsPage
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class FirstUiTest : BaseUiTest() {

    @Owner(AVORONTSOVA)
    @Test
    @DisplayName("Check name of CoffeePlace on main page")
    fun testFirstUI() {
        val title = MainPage()
            .getTitle()

        title shouldBe "Welcome to Brew & Bean"
    }

    @Owner(AVORONTSOVA)
    @Test
    @DisplayName("Check MainPage Header UI")
    fun checkHeaderTitles() {
        MainPage().header.checkHeaderUI()
    }

    @Owner(AVORONTSOVA)
    @Test
    @DisplayName("Check Products link")
    fun checkHeaderLinks() {
        MainPage().header.clickLink("Products")
        ProductsPage().getTitle()
    }
}