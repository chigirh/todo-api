package chigirh.app.todo.be.todoapi.domain.model.user

import chigirh.app.todo.be.todoapi.domain.model.common.PageableEntity

class UsersEntity(
    override val total: Int,
    override val entitys: List<UserEntity>
) : PageableEntity<UserEntity>(total, entitys)