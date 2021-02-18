/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2021-02-15T21:37:11.6171408+09:00
 */
package chigirh.app.todo.be.todoapi.infra.mapper

import chigirh.app.todo.be.todoapi.infra.dto.TChildTodoRecord
import chigirh.app.todo.be.todoapi.infra.mapper.TChildTodoDynamicSqlSupport.TChildTodo
import chigirh.app.todo.be.todoapi.infra.mapper.TChildTodoDynamicSqlSupport.TChildTodo.cTodoId
import chigirh.app.todo.be.todoapi.infra.mapper.TChildTodoDynamicSqlSupport.TChildTodo.cTodoName
import chigirh.app.todo.be.todoapi.infra.mapper.TChildTodoDynamicSqlSupport.TChildTodo.createAt
import chigirh.app.todo.be.todoapi.infra.mapper.TChildTodoDynamicSqlSupport.TChildTodo.createDate
import chigirh.app.todo.be.todoapi.infra.mapper.TChildTodoDynamicSqlSupport.TChildTodo.finishDate
import chigirh.app.todo.be.todoapi.infra.mapper.TChildTodoDynamicSqlSupport.TChildTodo.isFinished
import chigirh.app.todo.be.todoapi.infra.mapper.TChildTodoDynamicSqlSupport.TChildTodo.limitDate
import chigirh.app.todo.be.todoapi.infra.mapper.TChildTodoDynamicSqlSupport.TChildTodo.pTodoId
import chigirh.app.todo.be.todoapi.infra.mapper.TChildTodoDynamicSqlSupport.TChildTodo.updatedAt
import chigirh.app.todo.be.todoapi.infra.mapper.TChildTodoDynamicSqlSupport.TChildTodo.updatedDate
import chigirh.app.todo.be.todoapi.infra.mapper.TChildTodoDynamicSqlSupport.TChildTodo.version
import org.mybatis.dynamic.sql.SqlBuilder.isEqualTo
import org.mybatis.dynamic.sql.util.kotlin.*
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.*

fun TChildTodoMapper.count(completer: CountCompleter) =
    countFrom(this::count, TChildTodo, completer)

fun TChildTodoMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, TChildTodo, completer)

fun TChildTodoMapper.deleteByPrimaryKey(cTodoId_: String) =
    delete {
        where(cTodoId, isEqualTo(cTodoId_))
    }

fun TChildTodoMapper.insert(record: TChildTodoRecord) =
    insert(this::insert, record, TChildTodo) {
        map(cTodoId).toProperty("cTodoId")
        map(cTodoName).toProperty("cTodoName")
        map(isFinished).toProperty("isFinished")
        map(pTodoId).toProperty("pTodoId")
        map(limitDate).toProperty("limitDate")
        map(finishDate).toProperty("finishDate")
        map(createDate).toProperty("createDate")
        map(createAt).toProperty("createAt")
        map(updatedDate).toProperty("updatedDate")
        map(updatedAt).toProperty("updatedAt")
        map(version).toProperty("version")
    }

fun TChildTodoMapper.insertMultiple(records: Collection<TChildTodoRecord>) =
    insertMultiple(this::insertMultiple, records, TChildTodo) {
        map(cTodoId).toProperty("cTodoId")
        map(cTodoName).toProperty("cTodoName")
        map(isFinished).toProperty("isFinished")
        map(pTodoId).toProperty("pTodoId")
        map(limitDate).toProperty("limitDate")
        map(finishDate).toProperty("finishDate")
        map(createDate).toProperty("createDate")
        map(createAt).toProperty("createAt")
        map(updatedDate).toProperty("updatedDate")
        map(updatedAt).toProperty("updatedAt")
        map(version).toProperty("version")
    }

fun TChildTodoMapper.insertMultiple(vararg records: TChildTodoRecord) =
    insertMultiple(records.toList())

fun TChildTodoMapper.insertSelective(record: TChildTodoRecord) =
    insert(this::insert, record, TChildTodo) {
        map(cTodoId).toPropertyWhenPresent("cTodoId", record::cTodoId)
        map(cTodoName).toPropertyWhenPresent("cTodoName", record::cTodoName)
        map(isFinished).toPropertyWhenPresent("isFinished", record::isFinished)
        map(pTodoId).toPropertyWhenPresent("pTodoId", record::pTodoId)
        map(limitDate).toPropertyWhenPresent("limitDate", record::limitDate)
        map(finishDate).toPropertyWhenPresent("finishDate", record::finishDate)
        map(createDate).toPropertyWhenPresent("createDate", record::createDate)
        map(createAt).toPropertyWhenPresent("createAt", record::createAt)
        map(updatedDate).toPropertyWhenPresent("updatedDate", record::updatedDate)
        map(updatedAt).toPropertyWhenPresent("updatedAt", record::updatedAt)
        map(version).toPropertyWhenPresent("version", record::version)
    }

private val columnList = listOf(cTodoId, cTodoName, isFinished, pTodoId, limitDate, finishDate, createDate, createAt, updatedDate, updatedAt, version)

fun TChildTodoMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, TChildTodo, completer)

fun TChildTodoMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, TChildTodo, completer)

fun TChildTodoMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, TChildTodo, completer)

fun TChildTodoMapper.selectByPrimaryKey(cTodoId_: String) =
    selectOne {
        where(cTodoId, isEqualTo(cTodoId_))
    }

fun TChildTodoMapper.update(completer: UpdateCompleter) =
    update(this::update, TChildTodo, completer)

fun KotlinUpdateBuilder.updateAllColumns(record: TChildTodoRecord) =
    apply {
        set(cTodoId).equalTo(record::cTodoId)
        set(cTodoName).equalTo(record::cTodoName)
        set(isFinished).equalTo(record::isFinished)
        set(pTodoId).equalTo(record::pTodoId)
        set(limitDate).equalTo(record::limitDate)
        set(finishDate).equalTo(record::finishDate)
        set(createDate).equalTo(record::createDate)
        set(createAt).equalTo(record::createAt)
        set(updatedDate).equalTo(record::updatedDate)
        set(updatedAt).equalTo(record::updatedAt)
        set(version).equalTo(record::version)
    }

fun KotlinUpdateBuilder.updateSelectiveColumns(record: TChildTodoRecord) =
    apply {
        set(cTodoId).equalToWhenPresent(record::cTodoId)
        set(cTodoName).equalToWhenPresent(record::cTodoName)
        set(isFinished).equalToWhenPresent(record::isFinished)
        set(pTodoId).equalToWhenPresent(record::pTodoId)
        set(limitDate).equalToWhenPresent(record::limitDate)
        set(finishDate).equalToWhenPresent(record::finishDate)
        set(createDate).equalToWhenPresent(record::createDate)
        set(createAt).equalToWhenPresent(record::createAt)
        set(updatedDate).equalToWhenPresent(record::updatedDate)
        set(updatedAt).equalToWhenPresent(record::updatedAt)
        set(version).equalToWhenPresent(record::version)
    }

fun TChildTodoMapper.updateByPrimaryKey(record: TChildTodoRecord) =
    update {
        set(cTodoName).equalTo(record::cTodoName)
        set(isFinished).equalTo(record::isFinished)
        set(pTodoId).equalTo(record::pTodoId)
        set(limitDate).equalTo(record::limitDate)
        set(finishDate).equalTo(record::finishDate)
        set(createDate).equalTo(record::createDate)
        set(createAt).equalTo(record::createAt)
        set(updatedDate).equalTo(record::updatedDate)
        set(updatedAt).equalTo(record::updatedAt)
        set(version).equalTo(record::version)
        where(cTodoId, isEqualTo(record::cTodoId))
    }

fun TChildTodoMapper.updateByPrimaryKeySelective(record: TChildTodoRecord) =
    update {
        set(cTodoName).equalToWhenPresent(record::cTodoName)
        set(isFinished).equalToWhenPresent(record::isFinished)
        set(pTodoId).equalToWhenPresent(record::pTodoId)
        set(limitDate).equalToWhenPresent(record::limitDate)
        set(finishDate).equalToWhenPresent(record::finishDate)
        set(createDate).equalToWhenPresent(record::createDate)
        set(createAt).equalToWhenPresent(record::createAt)
        set(updatedDate).equalToWhenPresent(record::updatedDate)
        set(updatedAt).equalToWhenPresent(record::updatedAt)
        set(version).equalToWhenPresent(record::version)
        where(cTodoId, isEqualTo(record::cTodoId))
    }