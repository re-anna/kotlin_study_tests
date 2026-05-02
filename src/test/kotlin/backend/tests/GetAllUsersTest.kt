package backend.tests

import backend.api.helpers.AuthHelper
import backend.api.helpers.UserHelper
import backend.api.models.users.createUser.randomUser
import backend.controllers.Controllers
import backend.extension.ResponseExt.Companion.getAsObject
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.ints.shouldBeLessThanOrEqual
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.collections.emptyList

class GetAllUsersTest: Controllers() {

    private val userHelper = UserHelper()

    private val authHelper = AuthHelper()
    private val limit = 10
    val bigLimit = 400
    val zeroLimit = 0

    val bigOffset=400

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

        val page = user.getAllUsers(offset = 0, limit = limit)
            .getAsObject()

        page.size shouldBeLessThanOrEqual limit
        println(page.size)

        val pageOver = user.getAllUsers(offset = 0, limit = bigLimit)
            .getAsObject()
        pageOver.size shouldBeLessThanOrEqual limit
        println(pageOver.size)
    }

    @Test
    @DisplayName("Check that pages is not overlaping")
    fun usersPagesIsNotOverlap(){
        userHelper.ensureAmountOfUsers(25)

        val page1 = user.getAllUsers(offset = 0, limit = limit).getAsObject()
        val page2 = user.getAllUsers(offset = 1, limit = limit).getAsObject()

        val ids1 = page1.map { it.id }.toSet()
        val ids2 = page2.map { it.id }.toSet()

        (ids1 intersect ids2).isEmpty() shouldBe true
    }

    @Test
    @DisplayName("Validation offset and limit parameters")
    fun negativeOffsetCheck(){

        val bigLimitPage = user.getAllUsers(offset = 0, limit = bigLimit)
        bigLimitPage.code() shouldBe 400

        val zeroLimitPage = user.getAllUsers(offset = 0, limit = zeroLimit)
        zeroLimitPage.code() shouldBe 400

        val bigOffset = user.getAllUsers(offset = bigOffset, limit = limit)
        bigOffset.code() shouldBe 400
    }

    @Test
    @DisplayName("Validation offset and limit size check")
    fun negativeOffsetCheckSize(){
        val bigLimitPage = user.getAllUsers(offset = 0, limit = bigLimit).getAsObject()
        bigLimitPage.size shouldBeLessThanOrEqual 50

        val zeroLimitPage = user.getAllUsers(offset = 0, limit = zeroLimit).getAsObject()
        zeroLimitPage.size shouldBeLessThanOrEqual 50

        val bigOffset = user.getAllUsers(offset = bigOffset, limit = limit).getAsObject()
        bigOffset shouldBe emptyList()
    }
}