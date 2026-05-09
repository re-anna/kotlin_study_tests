package frontend

import backend.helpers.ProductsHelper
import backend.api.models.products.CreateProductsRequest
import backend.controllers.Controllers
import backend.extension.ResponseExt.Companion.getAsObject
import frontend.helpers.BaseUiTest
import frontend.pages.MainPage
import frontend.pages.ProductsPage
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.shouldBe
import io.qameta.allure.Feature
import io.qameta.allure.Story
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@Feature("")
@Story("products")
class ProductsTest: BaseUiTest() {

    private val controllers = Controllers()
    val productsHelper = ProductsHelper()

    @Test
    @DisplayName("Check popular products exist")
    fun testPopularProductsExist() {
        // Precondition
        val body = CreateProductsRequest(name = "Coffee Black", description = "Coffee without milk", price = 2.5)
        val product = controllers.products.createProduct(product = body).getAsObject()

        // Steps
        val popularList = MainPage()
            .open()
            .getPopularProducts()

        // Assertions
        popularList.size shouldBe 1
        popularList.first().name shouldBe product.name
    }

    @Test
    @DisplayName("Check that 5 products exist")
    fun testFiveProductsExist() {
        val listOfProducts = productsHelper.createProducts(5).sortedByDescending { it.name } // НЕ ЗАБЫВАТЬ

        val products = ProductsPage()
            .open()
            .getProductsAsObjects()
            .sortedByDescending { it.name }

        products.size shouldBe 5
        products.forEachIndexed { index, product ->
            product.name.uppercase() shouldBe listOfProducts[index].name.uppercase()
        }
    }

    @Test
    @DisplayName("Check first popular product is first on products page")
    fun firstPopularProductIsSame(){
        val firstPopularProduct = MainPage()
            .open()
            .getPopularProducts()[1]
            .btnIncrement
            .click()

        val popularProductOnProductPage = ProductsPage()
            .open()
            .getProductsAsObjects()[1]
        println(popularProductOnProductPage)

        firstPopularProduct shouldBeEqual popularProductOnProductPage
    }

    @Test
    @DisplayName("Popular products equal products on product page")
    fun popularProductIsSame(){
        val popularProductMain = MainPage()
            .getPopularProducts()
            .map { Triple(it.name,it.priceCents,it.description) }

        val popularProductsPage = ProductsPage()
            .open()
            .getProductsAsObjects()
            .map  { Triple(it.name,it.priceCents,it.description) }

        popularProductMain shouldContainAll popularProductsPage
    }

}
