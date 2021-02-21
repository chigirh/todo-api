package chigirh.app.todo.be.todoapi.web.api.controller

import chigirh.app.todo.be.todoapi.application.usecase.user.*
import chigirh.app.todo.be.todoapi.domain.constant.UserConstant
import chigirh.app.todo.be.todoapi.domain.exception.NotFoundException
import chigirh.app.todo.be.todoapi.domain.model.vo.UserId
import chigirh.app.todo.be.todoapi.oas3.controller.UserApiDelegate
import chigirh.app.todo.be.todoapi.oas3.model.*
import chigirh.app.todo.be.todoapi.web.api.converter.UserConverter
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
    val getUserUsecase: GetUserUsecase
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
        deleteUserUsecase(UserId(xUserId), converter.toEntity(user!!))
        return ResponseEntity(HttpStatus.OK)
    }

    /**
     * ユーザー取得.
     */
    override fun postUserGet(
        xUserId: kotlin.String,
        inlineObject: InlineObject?
    ): ResponseEntity<User> {
        val res =
            getUserUsecase(UserId(xUserId), UserId(inlineObject!!.userId))
                ?.let {
                    converter.toResponse(it)
                }
                ?: throw NotFoundException("userId", inlineObject!!.userId)


        return ResponseEntity(res, HttpStatus.OK)
    }

    /**
     * 全ユーザー取得.
     */
    override fun postUserList(
        xUserId: kotlin.String,
        inlineObject4: InlineObject4?
    ): ResponseEntity<InlineResponse2001> {
        val offset = inlineObject4!!.pageable.offset
        val limit = inlineObject4!!.pageable.limit
        val res =
            listUserUsecase(
                UserId(xUserId),
                offset,
                limit
            ).let {
                InlineResponse2001(
                    pageable = PageableResponse(
                        offset = offset,
                        limit = limit,
                        total = it.total
                    ),
                    users = it.entitys.map(converter::toResponse).toList()
                )
            }
        return ResponseEntity(res, HttpStatus.OK)

    }

    /**
     * ユーザー更新.
     */
    override fun postUserUpdated(xUserId: kotlin.String, user: User?): ResponseEntity<Unit> {
        updateUserUsecase(UserId(xUserId), converter.toEntity(user!!))
        return ResponseEntity(HttpStatus.OK)

    }
}