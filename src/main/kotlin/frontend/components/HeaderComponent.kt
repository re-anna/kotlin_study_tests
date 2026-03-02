package org.example.frontend.components

import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide.elements
import io.qameta.allure.Step
import org.example.frontend.helpers.Wrappers.Companion.byDataTestGroup

class HeaderComponent {
    private val linksHeader: ElementsCollection = elements(byDataTestGroup("nav-link"))

    @Step("Click on {name} link")
    fun clickLink(name: String): HeaderComponent {
        linksHeader.first{ it.text == name}.click()
        return this
    }
}