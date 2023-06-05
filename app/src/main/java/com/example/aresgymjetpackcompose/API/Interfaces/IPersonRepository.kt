package com.example.aresgymjetpackcompose.API.Interfaces

import com.example.aresgymjetpackcompose.Classes.Person
import retrofit2.Response
import retrofit2.http.*


private const val globalURL = "person"

interface IPersonRepository {

    @GET("${globalURL}/{id}")
    suspend fun getPersonById(@Path("id") id : Int) : Response<Person>

    @GET(globalURL)
    suspend fun getPersonByFullName(@Query("full_name") fullName : String) : Response<Person>

    @POST(globalURL)
    suspend fun addPerson(@Body person: Person) : Response<Person>

    @POST("checkEmail")
    suspend fun checkRepeatedEmail(@Query("mail") mail : String) : Response<Boolean>

    @POST("checkUserName")
    suspend fun checkRepeatedUserName(@Query("user_name") username : String) : Response<Boolean>

    @POST("login")
    suspend fun login(@Query("mailOrFullName") mailOrFullName : String, @Query("password") password : String) : Response<Person>

    @PUT(globalURL)
    suspend fun changePerson(@Body person : Person) : Response<Int>

}