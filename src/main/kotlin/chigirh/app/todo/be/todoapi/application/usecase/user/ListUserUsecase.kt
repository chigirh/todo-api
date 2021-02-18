package chigirh.app.todo.be.todoapi.application.usecase.user

import chigirh.app.todo.be.todoapi.application.repository.user.UserRepository
import chigirh.app.todo.be.todoapi.application.usecase.Usecase
import chigirh.app.todo.be.todoapi.domain.model.user.UsersEntity
import chigirh.app.todo.be.todoapi.domain.model.vo.UserId
import org.springframework.stereotype.Component

@Component
class ListUserUsecase(
    val userRepository: UserRepository
) : Usecase() {
    operator fun invoke(
        userId: UserId,
        offset: Int,
        limit: Int
    ) =
        UsersEntity(
            total = userRepository.findTotal(),
            entitys = userRepository.listBy(offset, limit)
        )
}