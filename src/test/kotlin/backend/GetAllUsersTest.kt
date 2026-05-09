package backend

import backend.helpers.AuthHelper
import backend.helpers.UserHelper
import backend.api.models.users.createUser.randomUser
import backend.controllers.Controllers
import backend.extension.ResponseExt.Companion.checkIsSuccessful
import backend.extension.ResponseExt.Companion.getAsObject
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.ints.shouldBeLessThanOrEqual
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.collections.emptyList

class GetAllUsersTest: Controllers() {

    private val userHelper = UserHelper()

    private val authHelper = AuthHelper()
    private val myLimit = 10
    private val limit = 50
    private val bigLimit = 400
    private val zeroLimit = 0

    private val bigOffset=400

    @Test
    @DisplayName("Get requested user from all users")
    fun getUserFromAllUsers(){
        val newUser = user.createUser(randomUser()).getAsObject()
        val allUsers = user.getAllUsers(authHelper.getAdminToken()).getAsObject()

        allUsers shouldContain newUser
    }

    @Test
    @DisplayName("Limit restricts page size and is capped 50")
    fun limitRestrictsSize(){
        userHelper.ensureAmountOfUsers(60)

        val page = user.getAllUsers(offset = 0, limit = myLimit)
            .getAsObject()

        page.size shouldBeLessThanOrEqual myLimit
        println(page.size)

        val pageOver = user.getAllUsers(offset = 0, limit = bigLimit)
            .getAsObject()
        pageOver.size shouldBeLessThanOrEqual limit
        println(pageOver.size)
    }

    @Test
    @DisplayName("Check that pages is not overlap")
    fun usersPagesIsNotOverlap(){
        userHelper.ensureAmountOfUsers(25)

        val page1 = user.getAllUsers(offset = 0, limit = myLimit).checkIsSuccessful().getAsObject()
        val page2 = user.getAllUsers(offset = 1, limit = myLimit).checkIsSuccessful().getAsObject()

        val ids1 = page1.map { it.id }.toSet()
        val ids2 = page2.map { it.id }.toSet()

        (ids1 intersect ids2).isEmpty() shouldBe true
    }

    //падает - 200
    @Test
    @DisplayName("Validation offset and limit parameters")
    fun negativeOffsetCheck(){

        val bigLimitPage = user.getAllUsers(offset = 0, limit = bigLimit)
        bigLimitPage.code() shouldBe 400

        val zeroLimitPage = user.getAllUsers(offset = 0, limit = zeroLimit)
        zeroLimitPage.code() shouldBe 400

        val bigOffset = user.getAllUsers(offset = bigOffset, limit = myLimit)
        bigOffset.code() shouldBe 400
    }

    @Test
    @DisplayName("Validation offset and limit size check")
    fun negativeOffsetCheckSize(){
        val bigLimitPage = user.getAllUsers(offset = 0, limit = bigLimit).getAsObject()
        bigLimitPage.size shouldBeLessThanOrEqual limit
        println(bigLimitPage.size)

        val zeroLimitPage = user.getAllUsers(offset = 0, limit = zeroLimit).getAsObject()
        zeroLimitPage.size shouldBeEqual 0
        println(zeroLimitPage.size)

        val bigOffset = user.getAllUsers(offset = bigOffset, limit = myLimit).getAsObject()
        bigOffset shouldBe emptyList()
        println(bigOffset.size)
    }
}