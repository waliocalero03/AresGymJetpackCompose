package com.example.aresgymjetpackcompose.Activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.aresgymjetpackcompose.R
import com.example.aresgymjetpackcompose.StartBackground
import com.example.aresgymjetpackcompose.registerPart
import com.example.aresgymjetpackcompose.textButtonBack
import com.example.aresgymjetpackcompose.titleLarge

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            registerActivity(context = this)
        }
    }
}

@Composable
fun registerActivity(context : Context? = null) {

    StartBackground()

    Column(modifier = Modifier.fillMaxSize()){
        textButtonBack(context!!)
        titleLarge(title = "Registrar-se")
        registerPart(title = "Nom i cognoms *")
    }

}

@Preview(showBackground = true)
@Composable
fun RegisterPreview() {
    registerActivity()
}

fun returnSplashScreen(context : Context){
    val intent = Intent(context, MainActivity::class.java)
    context.startActivity(intent)
}