package chigirh.app.todo.be.todoapi.infra.repository.user

import chigirh.app.todo.be.todoapi.application.repository.user.UserRepository
import chigirh.app.todo.be.todoapi.domain.model.common.VersionableEntityBase
import chigirh.app.todo.be.todoapi.domain.model.user.UserEntity
import chigirh.app.todo.be.todoapi.domain.model.user.UsersEntity
import chigirh.app.todo.be.todoapi.domain.model.vo.UserId
import chigirh.app.todo.be.todoapi.domain.model.vo.Version
import chigirh.app.todo.be.todoapi.infra.dto.MUserRecord
import chigirh.app.todo.be.todoapi.infra.dto.result.MUserRecordResult
import chigirh.app.todo.be.todoapi.infra.mapper.*
import chigirh.app.todo.be.todoapi.infra.repository.common.VersionableRepositoryBase
import org.springframework.stereotype.Repository

/**
 * ユーザーリポシトリの実装クラス.
 */
@Repository
class UserRepositoryImpl(
    val mUserMapper: MUserMapper,
    val mUserSelectMapper: MUserSelectMapper
) : UserRepository, VersionableRepositoryBase<UserId>() {

    override fun findByKey(key: UserId) = findBy(key)

    override fun findBy(userId: UserId) =
        mUserSelectMapper.selectByKey(userId = userId.v)?.let { convertToEntity(record = it) }

    override fun listBy(offset: Int, limit: Int): List<UserEntity> = mUserSelectMapper.listBy(offset, limit).map(::convertToEntity).toList()

    override fun insertBy(user: UserEntity) = mUserMapper.insert(
        record = convertToRecord(user)
    )


    override fun updateBy(user: UserEntity): Int {
        versionCheck(user.version, user.userId);
        user.versionUp();
        return mUserMapper.updateByPrimaryKeySelective(
            record = convertToRecord(user)
        )
    }

    override fun deleteBy(user: UserEntity): Int {
        versionCheck(user.version, user.userId);
        return mUserMapper.deleteByPrimaryKey(userId_ = user.userId.v)
    }

    override fun findTotal() = mUserSelectMapper.selectCount()

    override fun findMaxId() = mUserSelectMapper.selectMaxId()

    private fun convertToRecord(entity: UserEntity) = entity.let {
        MUserRecord(
            userId = it.userId.v,
            password = it.password,
            userName = it.userName,
            createDate = it.createDate,
            createAt = it.createAt?.v,
            updatedDate = it.updatedDate,
            updatedAt = it.updatedAt?.v,
            version = it.version.v
        )
    }

    private fun convertToEntity(record: MUserRecordResult) = record.let {
        UserEntity(
            userId = UserId(it.userId),
            password = it.password,
            userName = it.userName,
            createDate = it.createDate,
            createAt = it.createAt?.let { UserId(it) },
            updatedDate = it.updatedDate,
            updatedAt = it.updatedAt?.let { UserId(it) },
            version = Version(it.version)
        )
    }
}