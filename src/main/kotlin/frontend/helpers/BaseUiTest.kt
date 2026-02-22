package org.example.frontend.helpers

import com.codeborne.selenide.Selenide
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

class BaseUiTest {

    @BeforeEach
    fun openBrowser() {
        Selenide.open("/")
    }


    @AfterEach
    fun cleanBrowser(){
        Selenide.clearBrowserCookies()
        Selenide.clearBrowserLocalStorage()
    }
}