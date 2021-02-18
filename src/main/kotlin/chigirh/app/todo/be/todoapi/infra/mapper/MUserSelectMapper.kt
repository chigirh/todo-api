package chigirh.app.todo.be.todoapi.infra.mapper

import chigirh.app.todo.be.todoapi.infra.dto.result.MUserRecordResult
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

/**
 * m_userのMapperインタフェース.
 */
@Mapper
interface MUserSelectMapper {

    fun selectByKey(@Param("userId")userId:String):MUserRecordResult?

    fun listBy(@Param("offset")offset:Int,@Param("limit")limit:Int):List<MUserRecordResult>

    fun selectCount():Int

    fun selectMaxId():Int
}