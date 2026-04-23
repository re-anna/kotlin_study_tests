package backend.api.helpers

import backend.controllers.Controllers
import backend.extension.ResponseExt.Companion.getAsObject
import backend.extension.ResponseExt.Companion.toBearer
import io.qameta.allure.Step
import org.junit.jupiter.api.DisplayName

class AuthHelper: Controllers() {

    @Step
    @DisplayName("Get authorization token")
    fun getAuthorizationToken(email: String, password: String): String {
        return auth.login(email,password).getAsObject().accessToken.toBearer()
    }

}