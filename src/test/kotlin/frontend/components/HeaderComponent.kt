package frontend.components

import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide.elements
import frontend.helpers.Wrappers.byDataTestGroup
import frontend.pages.ProductsPage
import io.qameta.allure.Step

class HeaderComponent {
    private val links: ElementsCollection = elements(byDataTestGroup("nav-link"))

    fun getLinksText(): List<String> = links.map {it.text()}

    @Step("Click on {name} link")
    fun clickLink(name: String):HeaderComponent{
        links.first{ it.text() == name }.click()
        return this
    }

    @Step("Go to Products page")
    fun goToProducts(): ProductsPage {
        clickLink("Products")
        return ProductsPage()
    }
}