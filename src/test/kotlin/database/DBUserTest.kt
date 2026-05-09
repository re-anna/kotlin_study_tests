package database

import frontend.components.popup.CreateUserPopup
import frontend.pages.MainPage
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.random.Random

class DBUserTest {

    val jdbcClient = JDBCHelper()

    @Test
    @DisplayName("Check user is created via frontend and then exists in database")
    fun createUserKotlin(){
        val createUser = MainPage()
            .open()
            .header()
            .clickLink("Join")

        CreateUserPopup()
            .fillCreateAccount(
                username = "default",
                email = "random-${Random.nextInt(10000)}@autotest.com",
                pass = "password")

        val userDB = jdbcClient.getUsers()

    }


}