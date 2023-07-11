package com.example.aresgymjetpackcompose.API.Interfaces

import coil.request.ImageRequest
import coil.request.ImageResult
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IFileService {

    @GET("images")
    suspend fun returnImage(@Query("filename") filename : String) : Response<ResponseBody>

}