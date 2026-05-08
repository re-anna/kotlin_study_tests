package backend.controllers

open class Controllers {
    protected val auth: AuthController by lazy { AuthController() }
    protected val user: UserController by lazy { UserController() }
    val products: ProductController by lazy { ProductController() }
}