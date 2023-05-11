package com.example.aresgym.APIService.Classes

data class Persona(
    val contrasenya: String,
    val correu_electronic: String,
    val id_persona: Int,
    val nom_complet: String,
    val nom_usuari: String,
    var tipusPersona: Int
)