package backend.api.helpers

import backend.api.models.users.createUser.randomUser
import backend.controllers.Controllers
import backend.extension.ResponseExt.Companion.getAsObject

class UserHelper: Controllers() {
     fun ensureAmountOfUsers(target: Int) {
        val existingUsers = user.getAllUsers()
            .getAsObject()
            .size

        val neededUsers = (target - existingUsers).coerceAtLeast(0)
        repeat(neededUsers){
            user.createUser(randomUser()).getAsObject()
        }
    }

}