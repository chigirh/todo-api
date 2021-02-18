package chigirh.app.todo.be.todoapi.domain.model.vo

/**
 * バージョン.
 * 初期値を設定しなかった場合は1を設定する.
 */
data class Version(
    val v: Int = 1
) {
    fun check(version: Version) = v == version.v
}
