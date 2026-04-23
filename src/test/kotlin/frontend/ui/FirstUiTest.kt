package frontend.ui

import frontend.constants.AVORONTSOVA
import frontend.helpers.BaseUiTest
import frontend.pages.MainPage
import io.kotest.matchers.shouldBe
import io.qameta.allure.Owner
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
        MainPage().getTitle()
    }
}