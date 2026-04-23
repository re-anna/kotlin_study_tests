package backend.api.models

data class ErrorResponse(
    var code: Int,
    var reason: String
) {
    companion object{
        val InvalidCredentials = ErrorResponse(
            code = 400,
            reason = "Invalid email or password"
        )

        val EmptyCredentials = ErrorResponse(
            code = 400,
            reason = "Invalid email or password"
        )

        val UserAlreadyExists = ErrorResponse(
            code = 400,
            reason = "Something went wrong. Please verify request."
        )
    }
}
