package chigirh.app.todo.be.todoapi.web.api.controller

import chigirh.app.todo.be.todoapi.application.usecase.todo.*
import chigirh.app.todo.be.todoapi.domain.model.todo.TodoType
import chigirh.app.todo.be.todoapi.domain.model.vo.TodoId
import chigirh.app.todo.be.todoapi.domain.model.vo.UserId
import chigirh.app.todo.be.todoapi.oas3.controller.TodoApiDelegate
import chigirh.app.todo.be.todoapi.oas3.model.*
import chigirh.app.todo.be.todoapi.web.api.converter.ChildTodoConverter
import chigirh.app.todo.be.todoapi.web.api.converter.ParentTodoConverter
import chigirh.app.todo.be.todoapi.web.api.helper.PageableHelper
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
    val childTodoConverter: ChildTodoConverter,
    val pageableHelper: PageableHelper
) : TodoApiDelegate {
    /**
     * 子Todo登録.
     */
    override fun postTodoCCreate(
        xUserId: kotlin.String,
        inlineObject1: InlineObject1?
    ): ResponseEntity<Unit> {
        inlineObject1?.run {
            createChildTodoUsecase(
                userId = UserId(xUserId),
                todoName = detail.todoName,
                limitDate = detail.limitDate?.let { LocalDateTime.from(it) },
                parentTodoId = TodoId(parentTodoId)
            )
        }
        return ResponseEntity(HttpStatus.OK)
    }

    /**
     * 子Todo削除.
     */
    override fun postTodoCDelete(
        xUserId: kotlin.String,
        childTodo: ChildTodo?
    ): ResponseEntity<Unit> {
        childTodo?.run {
            deleteChildTodoUsecase(UserId(xUserId), childTodoConverter.toEntity(this))
        }
        return ResponseEntity(HttpStatus.OK)

    }

    /**
     * 子Todo更新.
     */
    override fun postTodoCUpdate(
        xUserId: kotlin.String,
        childTodo: ChildTodo?
    ): ResponseEntity<Unit> {
        childTodo?.run {
            updateChildTodoUsecase(UserId(xUserId), childTodoConverter.toEntity(this))
        }
        return ResponseEntity(HttpStatus.OK)

    }

    /**
     * Todo完了:完了解除.
     */
    override fun postTodoFinish(
        xUserId: kotlin.String,
        inlineObject2: InlineObject2?
    ): ResponseEntity<Unit> {
        inlineObject2?.run {
            finishTodoUsecase(
                userId = UserId(xUserId),
                todoId = TodoId(todoId),
                todoType = TodoType.of(todoType.type),
                isFinished = isFinished
            )
        }
        return ResponseEntity(HttpStatus.OK)

    }

    /**
     * Todo一覧.
     */
    override fun postTodoList(
        xUserId: kotlin.String,
        inlineObject: InlineObject?
    ): ResponseEntity<InlineResponse200> {
        val res = listTodoUsecase(
            UserId(xUserId),
            inlineObject?.pageable?.offset ?: 0,
            inlineObject?.pageable?.limit ?: 10
        ).let {
            InlineResponse200(
                nextPage = pageableHelper.getNextPage(inlineObject?.pageable, it.total),
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
        todoDetail?.run {
            createParentTodoUsecase(
                userId = UserId(xUserId),
                todoName = todoName,
                limitDate = limitDate?.let { LocalDateTime.from(it) }
            )
        }
        return ResponseEntity(HttpStatus.OK)

    }

    /**
     * 親Todo削除.
     */
    override fun postTodoPDelete(
        xUserId: kotlin.String,
        parentTodo: ParentTodo?
    ): ResponseEntity<Unit> {
        parentTodo?.run {
            deleteParentTodoUsecase(UserId(xUserId), parentTodoConverter.toEntity(this))
        }
        return ResponseEntity(HttpStatus.OK)

    }

    /**
     * 親Todo更新.
     */
    override fun postTodoPUpdate(
        xUserId: kotlin.String,
        parentTodo: ParentTodo?
    ): ResponseEntity<Unit> {
        parentTodo?.run {
            updateParentTodoUsecase(UserId(xUserId), parentTodoConverter.toEntity(this))
        }
        return ResponseEntity(HttpStatus.OK)

    }
}