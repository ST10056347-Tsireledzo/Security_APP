package com.example.muramba_2

data class Query(
    var id: String = "",
    var userId: String = "",
    var email: String = "",
    var subject: String = "",
    var message: String = "",
    var response: String = "",
    var timestamp: Long = 0L
)

