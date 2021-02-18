package chigirh.app.todo.be.todoapi.domain.model.todo

import chigirh.app.todo.be.todoapi.domain.model.common.PageableEntity

class TodosEntity(
    override val total: Int,
    override val entitys: List<ParentTodoEntity>
) : PageableEntity<ParentTodoEntity>(total, entitys)