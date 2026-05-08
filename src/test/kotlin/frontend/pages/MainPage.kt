package frontend.pages

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.SelenideElement
import frontend.components.HeaderComponent
import frontend.components.list.ProductItem
import frontend.components.list.ProductItems
import frontend.helpers.Wrappers.byDataTestId
import io.qameta.allure.Step

class MainPage {
    private val title: SelenideElement get() = element(byDataTestId("main-image-text"))
    private val listPopularProducts get() = ProductItems().getProducts()
    private val header = HeaderComponent()

    fun open(): MainPage = apply { Selenide.open("/") }
    fun getTitle(): String = title.text()
    fun header(): HeaderComponent = header

    @Step("Get popular products list")
    fun getPopularProducts(): List<ProductItem> = listPopularProducts


}