package frontend.components.list

import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.SelenideElement

data class CartItem(
    val image: SelenideElement,
    val name: String,
    val price: Float,
    val btnDecrement: SelenideElement,
    val quantity: Int,
    val btnIncrement: SelenideElement,
    val btnDeleteAll: SelenideElement

)
class CartItems (val listCartProducts: ElementsCollection) {

    fun getItems(): List<CartItem>{
        return listCartProducts
            .map { CartItem(
                    image = it.find(byTestGroup("cart-item-image")),
                    name = it.find(byTestGroup("cart-item-name")).text,
                    price = it.find(byTestGroup("cart-item-price")).text.toPriceCents(),
                    btnIncrement = it.find(byTestGroup("cart-item-increment")),
                    btnDecrement = it.find(byTestGroup("cart-item-decrement")),
                    quantity = it.find(byTestGroup("cart-item-qty")).text.toInt(),
                    btnDeleteAll = it.find(byTestGroup("cart-item-remove"))
                )
            }
    }
}