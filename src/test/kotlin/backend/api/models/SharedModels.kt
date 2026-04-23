package backend.api.models

import org.openqa.selenium.devtools.v142.network.model.ErrorReason

data class ErrorResponse(
    var code: Int,
    var reason: String
)
