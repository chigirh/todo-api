package chigirh.app.todo.be.todoapi.domain.model.common

open class PageableEntity<E : EntityBase>(
    /** 総件数 */
    open val total: Int,
    /** エンティティ一覧 */
    open val entitys: List<E>
) {
}