package chigirh.app.todo.be.todoapi.web.api.controller

import chigirh.app.todo.be.todoapi.application.usecase.todo.*
import chigirh.app.todo.be.todoapi.domain.model.todo.TodoType
import chigirh.app.todo.be.todoapi.domain.model.vo.TodoId
import chigirh.app.todo.be.todoapi.domain.model.vo.UserId
import chigirh.app.todo.be.todoapi.oas3.controller.TodoApiDelegate
import chigirh.app.todo.be.todoapi.oas3.model.*
import chigirh.app.todo.be.todoapi.web.api.converter.ChildTodoConverter
import chigirh.app.todo.be.todoapi.web.api.converter.ParentTodoConverter
import java.time.LocalDateTime
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
class TodoApiDelegateImpl(
    val createParentTodoUsecase: CreateParentTodoUsecase,
    val createChildTodoUsecase: CreateChildTodoUsecase,
    val updateParentTodoUsecase: UpdateParentTodoUsecase,
    val updateChildTodoUsecase: UpdateChildTodoUsecase,
    val deleteParentTodoUsecase: DeleteParentTodoUsecase,
    val deleteChildTodoUsecase: DeleteChildTodoUsecase,
    val listTodoUsecase: ListTodoUsecase,
    val finishTodoUsecase: FinishTodoUsecase,
    val parentTodoConverter: ParentTodoConverter,
    val childTodoConverter: ChildTodoConverter
) : TodoApiDelegate {
    /**
     * 子Todo登録.
     */
    override fun postTodoCCreate(
        xUserId: kotlin.String,
        inlineObject2: InlineObject2?
    ): ResponseEntity<Unit> {
        createChildTodoUsecase(
            userId = UserId(xUserId),
            todoName = inlineObject2!!.detail.todoName,
            limitDate = inlineObject2!!.detail.limitDate?.let { LocalDateTime.from(it) },
            parentTodoId = TodoId(inlineObject2!!.parentTodoId)
        )

        return ResponseEntity(HttpStatus.OK)
    }

    /**
     * 子Todo削除.
     */
    override fun postTodoCDelete(
        xUserId: kotlin.String,
        childTodo: ChildTodo?
    ): ResponseEntity<Unit> {
        deleteChildTodoUsecase(UserId(xUserId), childTodoConverter.toEntity(childTodo!!))

        return ResponseEntity(HttpStatus.OK)

    }

    /**
     * 子Todo更新.
     */
    override fun postTodoCUpdate(
        xUserId: kotlin.String,
        childTodo: ChildTodo?
    ): ResponseEntity<Unit> {
        updateChildTodoUsecase(UserId(xUserId), childTodoConverter.toEntity(childTodo!!))

        return ResponseEntity(HttpStatus.OK)

    }

    /**
     * Todo完了:完了解除.
     */
    override fun postTodoFinish(
        xUserId: kotlin.String,
        inlineObject3: InlineObject3?
    ): ResponseEntity<Unit> {
        finishTodoUsecase(
            userId = UserId(xUserId),
            todoId = TodoId(inlineObject3!!.todoId),
            todoType = TodoType.of(inlineObject3!!.todoType.type),
            isFinished = inlineObject3!!.isFinished
        )

        return ResponseEntity(HttpStatus.OK)

    }

    /**
     * Todo一覧.
     */
    override fun postTodoList(
        xUserId: kotlin.String,
        inlineObject1: InlineObject1?
    ): ResponseEntity<InlineResponse200> {
        val offset = inlineObject1!!.pageable.offset ?: 0
        val limit = inlineObject1!!.pageable.limit ?: 10
        val res = listTodoUsecase(
            UserId(
                inlineObject1!!.user.userId
            ),
            offset,
            limit
        ).let {
            InlineResponse200(
                pageable = PageableResponse(
                    offset = offset,
                    limit = limit,
                    total = it.total
                ),
                todos = it.entitys.map(parentTodoConverter::toResponse).toList()
            )
        }
        return ResponseEntity(res, HttpStatus.OK)

    }

    /**
     * 親Todo登録.
     */
    override fun postTodoPCreate(
        xUserId: kotlin.String,
        todoDetail: TodoDetail?
    ): ResponseEntity<Unit> {
        createParentTodoUsecase(
            userId = UserId(xUserId),
            todoName = todoDetail!!.todoName,
            limitDate = todoDetail!!.limitDate?.let { LocalDateTime.from(it) }
        )

        return ResponseEntity(HttpStatus.OK)

    }

    /**
     * 親Todo削除.
     */
    override fun postTodoPDelete(
        xUserId: kotlin.String,
        parentTodo: ParentTodo?
    ): ResponseEntity<Unit> {
        deleteParentTodoUsecase(UserId(xUserId), parentTodoConverter.toEntity(parentTodo!!))

        return ResponseEntity(HttpStatus.OK)

    }

    /**
     * 親Todo更新.
     */
    override fun postTodoPUpdate(
        xUserId: kotlin.String,
        parentTodo: ParentTodo?
    ): ResponseEntity<Unit> {
        updateParentTodoUsecase(UserId(xUserId), parentTodoConverter.toEntity(parentTodo!!))
        return ResponseEntity(HttpStatus.OK)

    }
}