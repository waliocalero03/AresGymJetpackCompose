package com.example.aresgymjetpackcompose.API

import android.util.Log
import com.example.aresgymjetpackcompose.API.Interfaces.*
import com.example.aresgymjetpackcompose.Classes.*
import kotlinx.coroutines.runBlocking
import retrofit2.create

class CRUDAPI() {

    private val coachCodeClass = "Coach Code"
    private val modifyAction = "Modify"
    private val addAction = "Add"
    private val correctResult = 1

    fun getCoachCodeByCode(code : String) : CoachCode?{

        var result : CoachCode?

        runBlocking {
            result = APIConnection.getRetrofit().create(CoachCodeService::class.java)
                .getCoachCodeByCode(code)
                .body()
        }

        return result
    }

    fun registerPerson(person : Person) : Person?{

        var result : Person?

        runBlocking {
            result = APIConnection.getRetrofit().create(IPersonRepository::class.java)
                .addPerson(person)
                .body()
        }

        return result

    }

    fun modifyCoachCode(coachCode: CoachCode){

        var result : Int?

        runBlocking {
            result = APIConnection.getRetrofit().create(CoachCodeService::class.java)
                .updateIdPersona(coachCode)
                .body()
        }

        logs(result, this.coachCodeClass, this.modifyAction)
    }

    private fun logs(result : Int?, classType : String, action : String){
        val message = "Result (${classType}, ${action}):"

        if(result != null){

            if(result >= this.correctResult){
                Log.i("CRUP API", "$message $result" )
            } else{
                Log.e("CRUP API", "$message $result")
            }

        } else{
            Log.e("CRUD API", "$message Null")
        }
    }

}