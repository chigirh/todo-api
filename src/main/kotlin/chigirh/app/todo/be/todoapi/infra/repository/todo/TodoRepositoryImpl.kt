package chigirh.app.todo.be.todoapi.infra.repository.todo

import chigirh.app.todo.be.todoapi.application.repository.todo.TodoRepository
import chigirh.app.todo.be.todoapi.domain.model.todo.ChildTodoEntity
import chigirh.app.todo.be.todoapi.domain.model.todo.ParentTodoEntity
import chigirh.app.todo.be.todoapi.domain.model.vo.TodoId
import chigirh.app.todo.be.todoapi.domain.model.vo.UserId
import chigirh.app.todo.be.todoapi.domain.model.vo.Version
import chigirh.app.todo.be.todoapi.infra.dto.result.TodoRecordResult
import chigirh.app.todo.be.todoapi.infra.mapper.TodoSelectMapper
import org.springframework.stereotype.Repository
import org.springframework.util.CollectionUtils

@Repository
class TodoRepositoryImpl(
    val parentTodoRepository: ParentTodoRepository,
    val childTodoRepository: ChildTodoRepository,
    val todoSelectMapper: TodoSelectMapper
) : TodoRepository {
    override fun selectByParentTodoId(todoId: TodoId) = parentTodoRepository.findByKey(todoId)

    override fun selectByChildTodoId(todoId: TodoId) = childTodoRepository.findByKey(todoId)

    override fun insertBy(todo: ParentTodoEntity) = parentTodoRepository.insertBy(todo)

    override fun insertBy(todo: ChildTodoEntity) = childTodoRepository.insertBy(todo)

    override fun updateBy(todo: ParentTodoEntity) = parentTodoRepository.updateBy(todo)

    override fun updateBy(todo: ChildTodoEntity) = childTodoRepository.updateBy(todo)

    override fun deleteBy(todo: ParentTodoEntity) = parentTodoRepository.deleteBy(todo)

    override fun deleteBy(todo: ChildTodoEntity) = childTodoRepository.deleteBy(todo)

    override fun selectBy(userId: UserId, offset: Int, limit: Int): List<ParentTodoEntity> {
        val result = todoSelectMapper.selectByUserId(userId.v, offset, limit)
        if (CollectionUtils.isEmpty(result)) return listOf()
        val kv = HashMap<String, ParentTodoEntity>()

        for (record: TodoRecordResult in result) {
            if (!kv.containsKey(record.pTodoId)) kv[record.pTodoId] = ParentTodoEntity(
                todoId = TodoId(record.pTodoId),
                todoName = record.pTodoName,
                isFinished = record.pIsFinished,
                limitDate = record.pLimitDate,
                finishDate = record.pFinishDate,
                version = Version(record.pVarsion)
            )
            kv[record.pTodoId]?.addChild(
                ChildTodoEntity(
                    todoId = TodoId(record.cTodoId),
                    todoName = record.cTodoName,
                    isFinished = record.cIsFinished,
                    limitDate = record.cLimitDate,
                    finishDate = record.cFinishDate,
                    version = Version(record.cVersion),
                    parentTodoId = TodoId(record.pTodoId)
                )
            )
        }
        return kv.values.toList()
    }

    override fun findCount(userId: UserId) = todoSelectMapper.selectCount(userId.v)

    override fun selectByForChildTodoList(parentTodo: ParentTodoEntity) =
        childTodoRepository.selectBy(parentTodo.todoId)
}