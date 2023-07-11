package com.example.aresgymjetpackcompose.API

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import coil.request.ImageRequest
import coil.request.ImageResult
import com.example.aresgymjetpackcompose.API.Interfaces.*
import com.example.aresgymjetpackcompose.Classes.*
import kotlinx.coroutines.runBlocking
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.create
import java.net.URL

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

    fun returnImage(filename : String) : Bitmap?{

        var result : ResponseBody?

        runBlocking {
            result = APIConnection.getRetrofit().create(IFileService::class.java)
                .returnImage(filename)
                .body()
        }

        return if(result != null){
            BitmapFactory.decodeStream(result!!.byteStream())
        } else{
            null
        }

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

    fun checkedRepeatedEmail(email : String) : Boolean{
        return try{

            var result : Boolean? = null

            runBlocking{
                result = APIConnection.getRetrofit().create(IPersonRepository::class.java)
                    .checkRepeatedEmail(email)
                    .body()
            }

            if(result != null){
                Log.i("CRUD API. checkedRepeatedEmail", result!!.toString())
                !result!!
            } else{
                false
            }

        } catch (e : Exception){
            Log.e("CRUD API. CheckedRepeatedEmail", e.message.toString())
            false
        }

    }

    fun checkedUserName(userName : String) : Boolean{

        return try{

            var result : Boolean? = null

            runBlocking {
                result = APIConnection.getRetrofit().create(IPersonRepository::class.java)
                    .checkRepeatedUserName(userName)
                    .body()
            }
            
            if(result != null){
                !result!!
            } else{
                false
            }


        } catch(e : Exception){
            Log.e("CRUD API. CheckedUserName", e.message.toString())
            false
        }

    }

    fun login(username : String, password : String) : Person?{

        var result : Person? = null

        try{

            runBlocking {
                result = APIConnection.getRetrofit().create(IPersonRepository::class.java)
                    .login(username, password)
                    .body()
            }

        } catch(e : Exception){
            Log.e("CRUD API. Login", e.message.toString())
        }

        return result

    }

}