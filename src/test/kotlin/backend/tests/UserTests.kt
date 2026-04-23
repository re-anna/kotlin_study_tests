package backend.tests

import backend.api.models.users.defaultUser
import backend.api.models.users.randomUser
import backend.controllers.Controllers
import backend.extension.ResponseExt.Companion.getAsObject
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class UserTests: Controllers() {

    @Test
    @DisplayName("Create default user")
    fun createUser() {
        val user =  user.createUser(defaultUser()).isSuccessful
    }

//  TODO
//    @Test
//    @DisplayName("Check that random user is created")
//    fun checkCreationOfNewUser(){
//        val user =  user.createUser(randomUser()).getAsObject()
//
//    }
}