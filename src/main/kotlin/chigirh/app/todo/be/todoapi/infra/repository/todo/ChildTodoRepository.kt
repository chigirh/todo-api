package chigirh.app.todo.be.todoapi.infra.repository.todo

import chigirh.app.todo.be.todoapi.domain.model.todo.ChildTodoEntity
import chigirh.app.todo.be.todoapi.domain.model.vo.TodoId
import chigirh.app.todo.be.todoapi.domain.model.vo.UserId
import chigirh.app.todo.be.todoapi.domain.model.vo.Version
import chigirh.app.todo.be.todoapi.infra.dto.TChildTodoRecord
import chigirh.app.todo.be.todoapi.infra.dto.result.TChildTodoRecordResult
import chigirh.app.todo.be.todoapi.infra.mapper.*
import chigirh.app.todo.be.todoapi.infra.repository.common.VersionableRepositoryBase
import org.springframework.stereotype.Repository

@Repository
class ChildTodoRepository(
    val tChildTodoMapper: TChildTodoMapper,
    val todoSelectMapper: TodoSelectMapper
) : VersionableRepositoryBase<TodoId>() {
    override fun findByKey(key: TodoId) = todoSelectMapper.selectByChildTodoId(key.v)?.let {
        ChildTodoEntity(
            todoId = TodoId(it.cTodoId),
            todoName = it.cTodoName,
            isFinished = it.isFinished,
            parentTodoId = TodoId(it.pTodoId),
            limitDate = it.limitDate,
            finishDate = it.finishDate,
            createDate = it.createDate,
            createAt = it.createAt?.let { UserId(it) },
            updatedDate = it.updatedDate,
            updatedAt = it.updatedAt?.let { UserId(it) },
            version = Version(it.version)
        )
    }

    /**
     * 子Todo登録.
     */
    fun insertBy(todo: ChildTodoEntity) = tChildTodoMapper.insertSelective(convertToRecord(todo))

    /**
     * 子Todo更新.
     */
    fun updateBy(todo: ChildTodoEntity, isSelective: Boolean): Int {
        versionCheck(todo.version, todo.todoId)
        todo.versionUp()
        return if (isSelective)
            tChildTodoMapper.updateByPrimaryKeySelective(convertToRecord(todo))
        else
            tChildTodoMapper.updateByPrimaryKey(convertToRecord(todo))
    }

    /**
     * 子Todo削除.
     */
    fun deleteBy(todo: ChildTodoEntity): Int {
        versionCheck(todo.version, todo.todoId)
        return tChildTodoMapper.deleteByPrimaryKey(todo.todoId.v)
    }

    /**
     * 子Todo一覧.
     */
    fun selectBy(pTodoId: TodoId) =
        todoSelectMapper.selectByParentTodoIdForChildList(pTodoId.v).map(this::convertToEntity).toList()

    private fun convertToRecord(entity: ChildTodoEntity) = entity.let {
        TChildTodoRecord(
            cTodoId = it.todoId.v,
            cTodoName = it.todoName,
            isFinished = it.isFinished,
            pTodoId = it.parentTodoId.v,
            limitDate = it.limitDate,
            finishDate = it.finishDate,
            createDate = it.createDate,
            createAt = it.createAt?.v,
            updatedDate = it.updatedDate,
            updatedAt = it.updatedAt?.v,
            version = it.version.v
        )
    }

    private fun convertToEntity(record: TChildTodoRecordResult) = record.let {
        ChildTodoEntity(
            todoId = TodoId(it.cTodoId),
            todoName = it.cTodoName,
            isFinished = it.isFinished,
            parentTodoId = TodoId(it.pTodoId),
            limitDate = it.limitDate,
            finishDate = it.finishDate,
            createDate = it.createDate,
            createAt = it.createAt?.let { UserId(it) },
            updatedDate = it.updatedDate,
            updatedAt = it.updatedAt?.let { UserId(it) },
            version = Version(it.version)
        )
    }
}
