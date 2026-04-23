package frontend.pages

import com.codeborne.selenide.CollectionCondition
import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.Selenide.elements
import com.codeborne.selenide.SelenideElement
import frontend.helpers.Wrappers.Companion.byDataTestGroup
import frontend.helpers.Wrappers.Companion.byDataTestId
import io.qameta.allure.Step

data class ProductData(
    val name: String,
    val description: String,
    val price: String
)

class ProductsPage {
    val txtTitle: SelenideElement get() = element(byDataTestId("products-title"))
    val listCards: ElementsCollection get() = elements(byDataTestGroup("product-card"))
    val listItems get() = elements(byDataTestGroup("product-card-name"))

    fun productName(card: SelenideElement) = card.find(byDataTestGroup("product-card-name"))
    fun itemsDescription(card: SelenideElement) = card.find(byDataTestGroup("product-card-description"))
    fun itemsPrice(card: SelenideElement) = card.find(byDataTestGroup("product-card-price"))

    @Step("Get products page title")
    fun getTitle(): String {
        return txtTitle.text
    }

    @Step("Get products list from page")
    fun getProductsItems(): ElementsCollection {
        return listItems
    }

    @Step("Получить список продуктов[2]")
    fun getProducts(): List<String> {
        listItems.shouldHave(CollectionCondition.sizeGreaterThan(0))
        return listItems.map { it.text }
    }

    @Step ("Получить информацию из карточек продуктов")
    fun getProductsInfo(): List<ProductData> {
        MainPage().header.clickLink("Products")
        listCards.shouldHave(CollectionCondition.sizeGreaterThan(0))
        return listCards.map { card ->
            ProductData(
                name = productName(card).text,
                description = itemsDescription(card).text,
                price = itemsPrice(card).text
            )
        }
    }
}