package chigirh.app.todo.be.todoapi.domain.constant

interface UserConstant {

    companion object {
        /** 認証を行わないためパスワードには定義した定数を利用する. */
        const val DEFAULT_PASSWORD = "Xxxxxx9999"
    }
}