package org.example.frontend.pages

import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.Selenide.elements
import com.codeborne.selenide.SelenideElement
import io.qameta.allure.Step
import org.example.frontend.helpers.Wrappers.Companion.byDataTestGroup
import org.example.frontend.helpers.Wrappers.Companion.byDataTestId

class ProductsPage {
    private val txtTitle: SelenideElement get() = element(byDataTestId("products-title"))
    private val listItems get() = elements(byDataTestGroup("product-card"))

    @Step("Get products page title")
    fun getTitle(): String {
        return txtTitle.text
    }

    @Step("Get products list from page")
    fun getProducts(): ElementsCollection {
        return listItems
    }
}