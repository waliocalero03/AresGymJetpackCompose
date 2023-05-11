package com.example.aresgymjetpackcompose.Activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.aresgymjetpackcompose.R
import com.example.aresgymjetpackcompose.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class LoggingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            loggingActivity(context = this)
        }
    }
}


@Preview
@Composable
fun previewLogging(){
    loggingActivity(context = null)
}


@OptIn(ExperimentalUnitApi::class)
@Composable
fun loggingActivity(context : Context? = null){

    var name by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 10.dp)){

        textButtonBack(context!!)
        titleLarge(title = "Registrar-se")


    }
}




fun returnSplashScreen(context : Context){
    val intent = Intent(context, MainActivity::class.java)
    context.startActivity(intent)
}

