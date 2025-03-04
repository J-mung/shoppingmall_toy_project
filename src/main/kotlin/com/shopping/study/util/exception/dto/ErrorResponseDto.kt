package com.shopping.study.util.exception.dto

import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable
import java.time.LocalDateTime

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ErrorResponseDto(
    val status: Int,
    val message: String,
    val time: LocalDateTime,
    var validErrors: MutableList<ValidationError>? = null
): Serializable {
    data class ValidationError(
        val field: String,
        val message: String
    )

    fun addValidationError(field: String, message: String) {
        if (validErrors == null) {
            validErrors = mutableListOf()
        }
        validErrors!!.add(ValidationError(field, message))
    }
}