package chigirh.app.todo.be.todoapi.infra.repository.todo

import chigirh.app.todo.be.todoapi.domain.model.todo.ParentTodoEntity
import chigirh.app.todo.be.todoapi.domain.model.vo.TodoId
import chigirh.app.todo.be.todoapi.domain.model.vo.UserId
import chigirh.app.todo.be.todoapi.domain.model.vo.Version
import chigirh.app.todo.be.todoapi.infra.dto.TParentTodoRecord
import chigirh.app.todo.be.todoapi.infra.mapper.*
import chigirh.app.todo.be.todoapi.infra.repository.common.VersionableRepositoryBase
import org.springframework.stereotype.Repository

@Repository
class ParentTodoRepository(
    val tParentTodoMapper: TParentTodoMapper,
    val todoSelectMapper: TodoSelectMapper
) : VersionableRepositoryBase<TodoId>() {
    override fun findByKey(key: TodoId) = todoSelectMapper.selectByParentTodoId(key.v)?.let {
        ParentTodoEntity(
            todoId = TodoId(it.pTodoId),
            todoName = it.pTodoName,
            isFinished = it.isFinished,
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
     * 親Todo登録.
     */
    fun insertBy(todo: ParentTodoEntity) = tParentTodoMapper.insertSelective(convertToRecord(todo))

    /**
     * 親Todo更新.
     */
    fun updateBy(todo: ParentTodoEntity): Int {
        versionCheck(todo.version, todo.todoId);
        todo.versionUp();
        return tParentTodoMapper.updateByPrimaryKeySelective(convertToRecord(todo));
    }

    /**
     * 親Todo削除.
     */
    fun deleteBy(todo: ParentTodoEntity): Int {
        versionCheck(todo.version, todo.todoId);
        return tParentTodoMapper.deleteByPrimaryKey(todo.todoId.v)
    }

    private fun convertToRecord(entity: ParentTodoEntity) = entity.let {
        TParentTodoRecord(
            pTodoId = it.todoId.v,
            pTodoName = it.todoName,
            isFinished = it.isFinished,
            limitDate = it.limitDate,
            finishDate = it.finishDate,
            createDate = it.createDate,
            createAt = it.createAt?.v,
            updatedDate = it.updatedDate,
            updatedAt = it.updatedAt?.v,
            version = it.version.v

        )
    }
}