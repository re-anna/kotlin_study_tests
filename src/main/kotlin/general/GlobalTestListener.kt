package org.example.general

import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Screenshots
import com.codeborne.selenide.Selenide
import io.qameta.allure.Attachment
import org.example.frontend.helpers.DriverProvider
import org.junit.platform.engine.TestExecutionResult
import org.junit.platform.launcher.TestExecutionListener
import org.junit.platform.launcher.TestIdentifier
import org.junit.platform.launcher.TestPlan
import kotlin.jvm.java
import kotlin.text.get

class GlobalTestListener : TestExecutionListener {

    override fun testPlanExecutionStarted(testPlan: TestPlan) {
        println("|------ Test Plan Started -----|")
        println("Initializing Configurations...").also { Config.get }
        println("Initializing Selenide WebDriver...").also { Configuration.browser = DriverProvider::class.java.name }
    }

    override fun executionStarted(testIdentifier: TestIdentifier) {
        if (!testIdentifier.isTest) return
        println("|--- Test started: ${testIdentifier.displayName}")
    }

    override fun executionFinished(
        testIdentifier: TestIdentifier,
        testExecutionResult: TestExecutionResult
    ) {
        if (testIdentifier.isTest)println("Finished test: ${testIdentifier.displayName} Result: ${testExecutionResult.status}")
        if(testExecutionResult.status == TestExecutionResult.Status.FAILED && testIdentifier.displayName != "JUnit Jupiter"){
            attachScreenshot()
        }
    }

    override fun executionSkipped(testIdentifier: TestIdentifier, reason: String) {
        if (!testIdentifier.isTest) return
        println("|--- Test Ignored: ${testIdentifier.displayName} - Reason: $reason")
    }

    override fun testPlanExecutionFinished(testPlan: TestPlan) {
        println("|------ Test Plan Finished -----|")
        Selenide.closeWebDriver()
        /*println("|------ GarbageCollector -----|")
        GarbageCollector.user.forEach { id ->
            users.deleteUserById(token = authHelper.getAdminToken(), id = id).also { println("Deleted User: $id") }
        }

        users.getAllUsers(token = authHelper.getAdminToken(), offset = 1, limit = 50).getAsObject().forEach { user ->
            if (user.email.contains("@autotest.com")) {
                users.deleteUserById(token = authHelper.getAdminToken(), id = user.id).also { println("Deleted User: ${user.email}") }
            }
        }*/
    }

    @Attachment(value = "{name}", type = "image/png")
    fun attachScreenshot(name: String = "SCREENSHOT"): ByteArray? {
        return Screenshots.takeScreenShotAsFile()?.readBytes()
    }
}
