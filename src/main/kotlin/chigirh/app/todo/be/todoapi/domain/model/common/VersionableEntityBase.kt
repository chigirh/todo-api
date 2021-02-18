package chigirh.app.todo.be.todoapi.domain.model.common

import chigirh.app.todo.be.todoapi.domain.model.vo.UserId
import chigirh.app.todo.be.todoapi.domain.model.vo.Version
import java.time.LocalDateTime

/**
 * バージョン管理可能エンティティBaseクラス
 */
abstract class VersionableEntityBase(
    /** 作成日 */
    override var createDate: LocalDateTime? = null,
    /** 作成者　*/
    override var createAt: UserId? = null,
    /** 更新日 */
    override var updatedDate: LocalDateTime? = null,
    /** 更新者 */
    override var updatedAt: UserId? = null,
    /** version */
    open var version: Version
) : EntityBase(
    createDate,
    createAt,
    updatedDate,
    updatedAt
) {
    fun versionCheck(version: Version) = this.version.v == version.v;
    fun versionUp() {
        version = Version(version.v + 1)
    }
}