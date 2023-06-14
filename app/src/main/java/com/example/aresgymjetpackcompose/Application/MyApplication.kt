package com.example.aresgymjetpackcompose.Application

import android.app.Application
import android.graphics.Bitmap
import androidx.compose.ui.graphics.asImageBitmap
import com.example.aresgymjetpackcompose.API.CRUDAPI
import com.example.aresgymjetpackcompose.Utils.GlobalVariables
import com.example.aresgymjetpackcompose.Utils.exceptionLog

class MyApplication : Application() {

    private val crupAPI = CRUDAPI()

    override fun onCreate() {
        super.onCreate()
        uploadPrincipalImage()
    }

    private fun uploadPrincipalImage(){

        var filename = "imgprincipal3.png"
        var bitmap : Bitmap?

        try{

            bitmap = crupAPI.returnImage(filename)

            if(bitmap != null){
                GlobalVariables.principalImage = bitmap.asImageBitmap()
            } else{
                GlobalVariables.principalImage = null
            }

        } catch(e : Exception){
            exceptionLog(e, "UploadPrincipalImage")
        }

    }

}