package org.example.frontend.components

import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide.elements
import io.kotest.matchers.collections.shouldHaveSize
import io.qameta.allure.Step
import org.example.frontend.helpers.Wrappers.Companion.byDataTestGroup

class HeaderComponent {
    private val linksHeader: ElementsCollection = elements(byDataTestGroup("nav-link"))
    private val expectedButtons = listOf("Brew & Bean", "Products", "Orders", "Contact","Cart", "Join")

    @Step("Click on {name} link")
    fun clickLink(name: String): HeaderComponent {
        linksHeader.first{ it.text == name}.click()
        return this
    }

    @Step("Check header UI")
    fun checkHeaderUI(){
        linksHeader.shouldHaveSize(6)
        linksHeader.forEach { it.shouldBe(visible) }

        val actualButtons = linksHeader.map { it.text }
        assert(actualButtons == expectedButtons
        ){"Header buttons не совпадают, ожидались: $expectedButtons, получили: $actualButtons"}
    }
}