package backend.tests

import backend.api.helpers.AuthHelper
import backend.api.models.products.defaultProduct
import backend.controllers.Controllers
import backend.extension.ResponseExt.Companion.getAsObject
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ProductTests: Controllers() {

    private val authHelper = AuthHelper()

    @Test
    @DisplayName("Create valid product")
    fun createValidProduct(){
        val baseProduct = products.createProduct(
            token = authHelper.getAdminToken(),
            product = defaultProduct()
        ).getAsObject()

//TODO
      //  val expectedProduct = products.getProductById(18).getAsObject()

       // baseProduct shouldBe expectedProduct
    }

    /*@Test
    @DisplayName("Create product with null name")
    fun createProductWithoutName(){
        val actualProduct = products.createProduct( token = authHelper.getAdminToken(),
            product = "",)
    }*/
}