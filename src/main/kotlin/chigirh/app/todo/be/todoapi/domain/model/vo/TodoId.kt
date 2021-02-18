package chigirh.app.todo.be.todoapi.domain.model.vo

import java.util.*

/**
 * TodoID.
 * 初期値を設定しなかった場合は36桁の文字列を自動生成する.
 */
data class TodoId(
    val v:String = UUID.randomUUID().toString()
)
