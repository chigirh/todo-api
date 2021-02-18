package chigirh.app.todo.be.todoapi.web.api

import chigirh.app.todo.be.todoapi.application.usecase.user.*
import chigirh.app.todo.be.todoapi.domain.constant.UserConstant
import chigirh.app.todo.be.todoapi.domain.exception.NotFoundException
import chigirh.app.todo.be.todoapi.domain.model.vo.UserId
import chigirh.app.todo.be.todoapi.oas3.controller.UserApiDelegate
import chigirh.app.todo.be.todoapi.oas3.model.InlineObject3
import chigirh.app.todo.be.todoapi.oas3.model.InlineResponse2001
import chigirh.app.todo.be.todoapi.oas3.model.User
import chigirh.app.todo.be.todoapi.oas3.model.UserDetail
import chigirh.app.todo.be.todoapi.web.common.PageableHelper
import chigirh.app.todo.be.todoapi.web.converter.UserConverter
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

/**
 * User系APIのControllerの処理を委譲した実装クラス.
 */
@Component
class UserApiDelegateImpl(
    val converter: UserConverter,
    val createUserUsecase: CreateUserUsecase,
    val updateUserUsecase: UpdateUserUsecase,
    val deleteUserUsecase: DeleteUserUsecase,
    val listUserUsecase: ListUserUsecase,
    val getUserUsecase: GetUserUsecase,
    val pageableHelper: PageableHelper
) : UserApiDelegate {
    /**
     * ユーザー登録.
     */
    override fun postUserCreate(userDetail: UserDetail?): ResponseEntity<Unit> {
        userDetail?.run {
            createUserUsecase(userDetail.userName, UserConstant.DEFAULT_PASSWORD)
        }
        return ResponseEntity(HttpStatus.OK)

    }

    /**
     * ユーザー削除.
     */
    override fun postUserDelete(xUserId: kotlin.String, user: User?): ResponseEntity<Unit> {
        user?.run { deleteUserUsecase(UserId(xUserId), converter.toEntity(user)) }
        return ResponseEntity(HttpStatus.OK)
    }

    /**
     * ユーザー取得.
     */
    override fun postUserGet(xUserId: kotlin.String, user: User?): ResponseEntity<User> {
        val res = getUserUsecase(
            UserId(xUserId),
            UserId(user?.userId ?: throw NotFoundException("request"))
        )?.let {
            converter.toResponse(it)
        }
        return ResponseEntity(res, HttpStatus.OK)
    }

    /**
     * 全ユーザー取得.
     */
    override fun postUserList(
        xUserId: kotlin.String,
        inlineObject3: InlineObject3?
    ): ResponseEntity<InlineResponse2001> {
        val res =
            listUserUsecase(
                UserId(xUserId),
                inlineObject3?.pageable?.offset ?: 0,
                inlineObject3?.pageable?.limit ?: 10
            ).let {
                InlineResponse2001(
                    nextPage = pageableHelper.getNextPage(inlineObject3?.pageable, it.total),
                    users = it.entitys.map(converter::toResponse).toList()
                )
            }
        return ResponseEntity(res, HttpStatus.OK)

    }

    /**
     * ユーザー更新.
     */
    override fun postUserUpdated(xUserId: kotlin.String, user: User?): ResponseEntity<Unit> {
        user?.run { updateUserUsecase(UserId(xUserId), converter.toEntity(user)) }
        return ResponseEntity(HttpStatus.OK)

    }
}