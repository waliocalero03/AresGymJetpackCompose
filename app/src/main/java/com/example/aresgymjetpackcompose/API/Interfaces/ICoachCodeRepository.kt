package com.example.aresgymjetpackcompose.API.Interfaces

import com.example.aresgymjetpackcompose.Classes.CoachCode
import retrofit2.Response
import retrofit2.http.*


private const val globalURL = "aresgym/coachcode"

interface CoachCodeService {

    @GET(globalURL)
    suspend fun getCoachCodeByCode(@Query("code") code : String) : Response<CoachCode>

    @PUT(globalURL)
    suspend fun updateIdPersona(@Body coachCode: CoachCode) : Response<Int>

}