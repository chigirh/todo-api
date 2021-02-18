package chigirh.app.todo.be.todoapi.infra.mapper

import chigirh.app.todo.be.todoapi.infra.dto.result.TChildTodoRecordResult
import chigirh.app.todo.be.todoapi.infra.dto.result.TParentTodoRecordResult
import chigirh.app.todo.be.todoapi.infra.dto.result.TodoRecordResult
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

/**
 * t_parent_todoのMapperインタフェース.
 */
@Mapper
interface TodoSelectMapper {
    fun selectByParentTodoId(@Param("todoId") todoId: String): TParentTodoRecordResult?

    fun selectByChildTodoId(@Param("todoId") todoId: String): TChildTodoRecordResult?

    fun selectByUserId(
        @Param("userId") userId: String,
        @Param("offset") offset: Int,
        @Param("limit") limit: Int
    ): List<TodoRecordResult>

    fun selectCount(@Param("userId") userId: String): Int

    fun selectByParentTodoIdForChildList(@Param("pTodoId") pTodoId: String): List<TChildTodoRecordResult>

}