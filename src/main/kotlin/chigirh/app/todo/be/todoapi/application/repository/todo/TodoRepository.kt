package chigirh.app.todo.be.todoapi.application.repository.todo

import chigirh.app.todo.be.todoapi.domain.model.todo.ChildTodoEntity
import chigirh.app.todo.be.todoapi.domain.model.todo.ParentTodoEntity
import chigirh.app.todo.be.todoapi.domain.model.vo.TodoId
import chigirh.app.todo.be.todoapi.domain.model.vo.UserId

/**
 * Todoリポジトリのインタフェース.
 */
interface TodoRepository {
    /**
     * 親Todo取得.
     */
    fun selectByParentTodoId(todoId: TodoId): ParentTodoEntity?

    /**
     * 子Todo取得.
     */
    fun selectByChildTodoId(todoId: TodoId): ChildTodoEntity?

    /**
     * 親Todo登録.
     */
    fun insertBy(todo: ParentTodoEntity): Int

    /**
     * 子Todo登録.
     */
    fun insertBy(todo: ChildTodoEntity): Int

    /**
     * 親Todo更新.
     */
    fun updateBy(todo: ParentTodoEntity): Int

    /**
     * 子Todo更新.
     */
    fun updateBy(todo: ChildTodoEntity): Int

    /**
     * 親Todo削除.
     */
    fun deleteBy(todo: ParentTodoEntity): Int

    /**
     * 親Todo削除.
     */
    fun deleteBy(todo: ChildTodoEntity): Int

    /** Todo一覧の取得. */
    fun selectBy(userId: UserId, offset: Int, limit: Int): List<ParentTodoEntity>

    /** Todo一覧の総件数. */
    fun findCount(userId: UserId): Int

    /** 子Todo一覧の取得. */
    fun selectByForChildTodoList(parentTodo: ParentTodoEntity): List<ChildTodoEntity>
}