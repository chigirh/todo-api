package chigirh.app.todo.be.todoapi.application.repository.user

import chigirh.app.todo.be.todoapi.domain.model.user.UserEntity
import chigirh.app.todo.be.todoapi.domain.model.user.UsersEntity
import chigirh.app.todo.be.todoapi.domain.model.vo.UserId

/**
 * ユーザーリポジトリのインタフェース.
 */
interface UserRepository {

    /**
     * ユーザー情報取得.
     */
    fun findBy(userId:UserId): UserEntity?

    /**
     * ユーザー情報取得.
     */
    fun listBy(offset:Int,limit:Int): List<UserEntity>

    /**
     * ユーザー情報登録.
     */
    fun insertBy(user: UserEntity): Int

    /**
     * ユーザー情報更新.
     */
    fun updateBy(user: UserEntity): Int

    /**
     * ユーザー情報削除.
     */
    fun deleteBy(user: UserEntity):Int

    /**
     * ユーザーの総件数取得.
     */
    fun findTotal(): Int

    /**
     * ユーザーIDの最大値取得.
     */
    fun findMaxId(): Int
}