package com.example.aresgymjetpackcompose.Utils

import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.aresgymjetpackcompose.Activities.MainActivity


/**
 * Method theat changes the activity to mainActivity (Inicial Activity).
 * @param context -> Context in this moment
 */
fun returnSplashScreen(context : Context){
    val intent = Intent(context, MainActivity::class.java)
    context.startActivity(intent)
}

fun exceptionLog(e : Exception, title : String){
    Log.e(title, e.message.toString())
}