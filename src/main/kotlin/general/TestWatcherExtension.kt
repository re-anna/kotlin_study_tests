package general

import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.TestWatcher

class TestWatcherExtension : TestWatcher {
    override fun testSuccessful(context: ExtensionContext) {
        println("|--- Test passed: ${context.displayName} ---|")
    }

    override fun testFailed(context: ExtensionContext, cause: Throwable?) {
        println("|--- Test failed: ${context.displayName} ---|")
        println("Cause: ${cause?.message}")
    }
}