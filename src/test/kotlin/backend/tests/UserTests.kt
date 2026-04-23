package backend.tests

import backend.api.models.ErrorResponse
import backend.api.models.users.randomUser
import backend.controllers.Controllers
import backend.extension.ResponseExt.Companion.getAsObject
import backend.extension.ResponseExt.Companion.getErrorAsObject
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class UserTests: Controllers() {

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

        error shouldBe ErrorResponse.UserAlreadyExists
    }
}