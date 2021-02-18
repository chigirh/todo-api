package chigirh.app.todo.be.todoapi.domain.model.common

import chigirh.app.todo.be.todoapi.domain.model.vo.UserId
import java.time.LocalDateTime

abstract class EntityBase(
    /** 作成日 */
    open var createDate: LocalDateTime? = null,
    /** 作成者　*/
    open var createAt: UserId? = null,
    /** 更新日 */
    open var updatedDate: LocalDateTime? = null,
    /** 更新者 */
    open var updatedAt: UserId? = null
) : Entity {
    open fun create(userId: UserId) {
        LocalDateTime.now().let {
            createDate = it
            updatedDate = it
        }
        userId.let {
            createAt = it
            updatedAt = it
        }
    }

    open fun update(userId: UserId) {
        LocalDateTime.now().let {
            updatedDate = it
        }
        userId.let {
            updatedAt = it
        }
    }
}
