package backend.controllers

open class Controllers {
    val auth get() = AuthController()
    val user get() = UserController()
    val products get() = ProductController()
}