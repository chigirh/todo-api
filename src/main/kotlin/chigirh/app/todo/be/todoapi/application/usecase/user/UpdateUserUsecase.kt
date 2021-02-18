package chigirh.app.todo.be.todoapi.application.usecase.user

import chigirh.app.todo.be.todoapi.application.repository.user.UserRepository
import chigirh.app.todo.be.todoapi.application.usecase.Usecase
import chigirh.app.todo.be.todoapi.domain.model.user.UserEntity
import chigirh.app.todo.be.todoapi.domain.model.vo.UserId
import org.springframework.stereotype.Component

@Component
class UpdateUserUsecase(
    val userRepository: UserRepository
) : Usecase() {
    operator fun invoke(userId: UserId, user: UserEntity) {
        user.update(userId)
        userRepository.updateBy(user)
    }
}