package com.example.aresgym.APIService.Classes

data class Exercici(
    val foto: String,
    val id_exercici: Int,
    val id_persona: Int,
    val instruccions: String,
    val nom: String,
    val video: String
)