package frontend.components.list

import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide.elements
import com.codeborne.selenide.SelenideElement

data class ProductItem(
    val image: SelenideElement,
    val name: String,
    val description: String,
    val price: Double,
    val btnDecrement: SelenideElement,
    var quantity: Int,
    val btnIncrement: SelenideElement
)

class ProductItems( val items: ElementsCollection) {

    fun getProducts(): List<ProductItem>{
        return items
            .map { ProductItem(
                image = it.find(byTestGroup("product-card-image")),
                name = it.find(byTestGroup("product-card-name")).text,
                description = it.find(byTestGroup("product-card-description")).text,
                price = it.find(byTestGroup("product-card-price")).text.toPriceCents(),
                btnIncrement = it.find(byTestGroup("product-card-increment")),
                quantity = it.find(byTestGroup("product-card-qty")).text.toInt(),
                btnDecrement = it.find(byTestGroup("product-card-decrement")),
            )
            }
    }
}



