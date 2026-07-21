package com.example.finflow.feature.transaction.domain.model

data class Account(
    val id:Long,
    val title: String,
    val bank:Bank?,    //set nullable because wallet or csh han no bank
    val type: AccountType,
    val color: String
)
