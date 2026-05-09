package frontend.products

import backend.helpers.ProductsHelper
import backend.controllers.Controllers
import backend.extension.ResponseExt.Companion.getAsObject
import frontend.helpers.BaseUiTest
import frontend.pages.ProductsPage
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ProductsTest: BaseUiTest() {

    val controllers = Controllers()
    val productsHelper = ProductsHelper()

    @Test
    @DisplayName("Check that created 5 products via backend is displayed on frontend")
    fun checkFiveProducts(){
        val listOfProducts = productsHelper.createProducts(5).sortedByDescending { it.name }

        val products = ProductsPage()
            .open()
            .getProductsAsObjects()


        products.forEachIndexed { index, item ->
            item.name shouldBe listOfProducts[index].name
        }
    }

    @Test
    @DisplayName("Create 'COFFEE' products via back and then check them on fronend and backend")
    fun checkCreatedItems(){
        val createdItems = productsHelper.createCoffeeProducts(5)

        val frontendCoffeeCount = ProductsPage()
            .open()
            .getProductsAsObjects()
            .filter { it.name.contains("COFFEE",ignoreCase = true) }.size

        val backendProductCount = productsHelper.products.getAllProducts().getAsObject()
            .filter {it.name.contains("COFFEE",ignoreCase = true)}.size

        frontendCoffeeCount shouldBeEqual backendProductCount
    }
}
