package org.example.frontend.pages

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.SelenideElement
import io.qameta.allure.Step
import org.example.frontend.components.HeaderComponent
import org.example.frontend.helpers.Wrappers.Companion.byDataTestId

class MainPage{
    private val txtTitle: SelenideElement get() = element(byDataTestId("main-image-text"))

    @Step("Open Main Page")
    fun open() {
        Selenide.open("/")
    }

    @Step("Get name of CoffeePlace")
    fun getTitle(): String {
        return txtTitle.text
    }

    @Step("Go to Header Component")
    fun header(): HeaderComponent {
        return HeaderComponent()
    }
}