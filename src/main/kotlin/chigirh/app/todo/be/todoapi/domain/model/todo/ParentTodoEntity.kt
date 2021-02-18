package chigirh.app.todo.be.todoapi.domain.model.todo

import chigirh.app.todo.be.todoapi.domain.model.vo.TodoId
import chigirh.app.todo.be.todoapi.domain.model.vo.UserId
import chigirh.app.todo.be.todoapi.domain.model.vo.Version
import java.time.LocalDateTime

class ParentTodoEntity(
    /** TodoID */
    override val todoId: TodoId = TodoId(),
    /** Todo名 */
    override val todoName: String,
    /** 完了フラグ */
    override var isFinished: Boolean,
    /** 期限 */
    override var limitDate: LocalDateTime? = LocalDateTime.MAX,
    /** 完了日 */
    override var finishDate: LocalDateTime? = null,
    /** 作成日 */
    override var createDate: LocalDateTime? = null,
    /** 作成者　*/
    override var createAt: UserId? = null,
    /** 更新日 */
    override var updatedDate: LocalDateTime? = null,
    /** 更新者 */
    override var updatedAt: UserId? = null,
    /** version */
    override var version: Version = Version(),
    /** 子Todo */
    val children: MutableList<ChildTodoEntity> = mutableListOf()
) : TodoEntity(
    todoId,
    todoName,
    isFinished,
    limitDate,
    finishDate,
    createDate,
    createAt,
    updatedDate,
    updatedAt,
    version
) {
    fun addChild(child: ChildTodoEntity) {
        children.add(child)
    }
}