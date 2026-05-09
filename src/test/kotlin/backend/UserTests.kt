package backend

import backend.helpers.AuthHelper
import backend.api.models.ErrorResponse
import backend.api.models.userAlreadyExists
import backend.api.models.users.createUser.defaultUser
import backend.api.models.users.createUser.randomUser
import backend.api.models.users.updateUser.UpdateRequest
import backend.controllers.Controllers
import backend.extension.ResponseExt.Companion.checkIsSuccessful
import backend.extension.ResponseExt.Companion.getAsObject
import backend.extension.ResponseExt.Companion.getErrorAsObject
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class UserTests: Controllers() {

    private val authHelper = AuthHelper()

    @Test
    @DisplayName("Create user with valid data")
    fun createUser(){
        val actualUser = user.createUser(defaultUser()).getAsObject()
        val expectedUser = user.getUserById(token = authHelper.getAdminToken(), id = actualUser.id)

        expectedUser shouldBeEqual actualUser
    }

    @Test
    @DisplayName("Delete valid user should return 200")
    fun deleteDefaultUser(){
        val actualUser = user.createUser(defaultUser()).getAsObject()
        val delete = user.deleteUserById(token = authHelper.getAdminToken(), id = actualUser.id)

        delete.code() shouldBe 200
    }

    @Test
    @DisplayName("Update phone number")
    fun updatePhoneNumber(){
        val createdUser = user.createUser(randomUser()).getAsObject()
        val newPhone = "89998988998"

        user.updateUserById(authHelper.getAdminToken(),createdUser.id,UpdateRequest(phoneNumber = newPhone)).getAsObject()

        val updatedUser = user.getUserById(authHelper.getAdminToken(),createdUser.id).getAsObject()

        updatedUser.phoneNumber shouldBeEqual newPhone
    }

    @Test
    @DisplayName("Update full user model with valid data")
    fun updateFullUserData(){
        val newUser = user.createUser(randomUser()).getAsObject()
        val updateRequest = UpdateRequest(
            "updated-${newUser.username}",
            "updated-password",
            "updated-${newUser.email}",
            "899888888}"
        )

        val updatedUser = user.updateUserById(authHelper.getAdminToken(),id = newUser.id, body = updateRequest).getAsObject()
        val login = auth.login(updateRequest.email!!, updateRequest.password!!).getAsObject()

        login.accessToken.length shouldBeGreaterThan 10
        updatedUser.phoneNumber shouldBe updateRequest.phoneNumber
        updatedUser.username shouldBe updateRequest.username
        updatedUser.email shouldBe updateRequest.email
    }

    @Test
    @DisplayName("Update partial user model with valid data")
    fun updatePartialUserData(){
        val newUser = user.createUser(randomUser()).getAsObject()
        val updateRequest = UpdateRequest( password ="UpdatePassword")
        user.updateUserById(authHelper.getAdminToken(),newUser.id,updateRequest).checkIsSuccessful()

        val login = auth.login(newUser.email, updateRequest.password!!).getAsObject()

        login.accessToken.length shouldBeGreaterThan 10
    }

    @Test
    @DisplayName("Check that random user is created")
    fun checkCreationOfNewUser(){
        val newUser = randomUser()
        val request = user.createUser(newUser).getAsObject()

        request.id shouldBeGreaterThan 0
        request.username shouldBe newUser.username
        request.email shouldBe newUser.email
    }

    @Test
    @DisplayName("Error: Create same user 2 times")
    fun checkDuplicationUserCreationError(){
        val newUser = randomUser()
        user.createUser(newUser).getAsObject()

        val error = user.createUser(newUser).getErrorAsObject<ErrorResponse>()

        error shouldBe userAlreadyExists
    }
}