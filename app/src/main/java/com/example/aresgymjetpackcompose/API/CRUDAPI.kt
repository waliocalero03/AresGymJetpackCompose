package com.example.aresgymjetpackcompose.API

import com.example.aresgymjetpackcompose.API.Interfaces.*
import com.example.aresgymjetpackcompose.Classes.*
import kotlinx.coroutines.runBlocking

class CRUDAPI() {

    private val ModifiedResult = 1

    fun getCoachCodeByCode(code : String) : CoachCode?{

        var result : CoachCode?

        runBlocking {
            result = APIConnection.getRetrofit().create(CoachCodeService::class.java)
                .getCoachCodeByCode(code)
                .body()
        }

        return result
    }
}