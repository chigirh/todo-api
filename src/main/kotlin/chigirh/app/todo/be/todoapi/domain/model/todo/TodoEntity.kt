package chigirh.app.todo.be.todoapi.domain.model.todo

import chigirh.app.todo.be.todoapi.domain.model.common.VersionableEntityBase
import chigirh.app.todo.be.todoapi.domain.model.vo.TodoId
import chigirh.app.todo.be.todoapi.domain.model.vo.UserId
import chigirh.app.todo.be.todoapi.domain.model.vo.Version
import java.time.LocalDateTime

open class TodoEntity(
    /** TodoID */
    open val todoId: TodoId = TodoId(),
    /** Todo名 */
    open val todoName: String,
    /** 完了フラグ */
    open var isFinished: Boolean,
    /** 期限 */
    open var limitDate: LocalDateTime?,
    /** 完了日 */
    open var finishDate: LocalDateTime?,
    /** 作成日 */
    override var createDate: LocalDateTime? = null,
    /** 作成者　*/
    override var createAt: UserId? = null,
    /** 更新日 */
    override var updatedDate: LocalDateTime? = null,
    /** 更新者 */
    override var updatedAt: UserId? = null,
    /** version */
    override var version: Version
) : VersionableEntityBase(
    createDate,
    createAt,
    updatedDate,
    updatedAt,
    version
) {
    /**
     * 完了済みに更新.
     * 更新した場合はtrueを返却
     */
    fun finish(userId: UserId): Boolean {
        if (isFinished) return false
        isFinished = true
        this.finishDate = LocalDateTime.now()
        update(userId)
        return true
    }

    /**
     * 未完了に更新.
     * 更新した場合はtrueを返却
     */
    fun release(userId: UserId): Boolean {
        if (!isFinished) return false
        isFinished = false
        this.finishDate = null
        update(userId)
        return true
    }
}