package chigirh.app.todo.be.todoapi.domain.model.user

import chigirh.app.todo.be.todoapi.domain.model.common.VersionableEntityBase
import chigirh.app.todo.be.todoapi.domain.model.vo.UserId
import chigirh.app.todo.be.todoapi.domain.model.vo.Version
import java.time.LocalDateTime

class UserEntity(
    /** ユーザーID */
    val userId: UserId,
    /** パスワード */
    val password: String,
    /** ユーザー名 */
    val userName: String? = null,
    /** 作成日 */
    override var createDate: LocalDateTime? = null,
    /** 作成者　*/
    override var createAt: UserId? = null,
    /** 更新日 */
    override var updatedDate: LocalDateTime? = null,
    /** 更新者 */
    override var updatedAt: UserId? = null,
    /** version */
    override var version: Version = Version()
) : VersionableEntityBase(
    createDate,
    createAt,
    updatedDate,
    updatedAt,
    version
)