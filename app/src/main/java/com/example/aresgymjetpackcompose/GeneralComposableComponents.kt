package com.example.aresgymjetpackcompose

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
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
import androidx.compose.ui.unit.sp
import com.example.aresgymjetpackcompose.Activities.returnSplashScreen

@OptIn(ExperimentalUnitApi::class)
@Composable
fun textButtonBack(context : Context){

    TextButton(onClick = { returnSplashScreen(context) }, modifier = Modifier.padding(start = 10.dp)){
        Image(painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24), contentDescription = "")
        Text(text = "Enrere", style = TextStyle(
            fontFamily = FontFamily(Font(R.font.oxygen_light)),
            color = colorResource(id = R.color.blue),
            fontSize = TextUnit(16f, TextUnitType.Sp)
            ))
    }

}

@OptIn(ExperimentalUnitApi::class)
@Composable
fun titleLarge(title : String){

    Text(text = title, style = TextStyle(
        color = colorResource(id = R.color.white),
        fontFamily = FontFamily(Font(R.font.merriweather_bold)),
        fontSize = TextUnit(30f, TextUnitType.Sp)
    ),
        modifier = Modifier.padding(start = 20.dp, top = 16.dp)
    )

}


@OptIn(ExperimentalUnitApi::class, ExperimentalMaterial3Api::class)
@Composable
fun registerPart(title : String){

    var name by remember { mutableStateOf("") }

    Text(text = title, modifier = Modifier.padding(start = 20.dp, top = 16.dp),
        style = TextStyle(
            color = colorResource(id = R.color.white),
            fontSize = TextUnit(18f, TextUnitType.Sp),
            fontFamily = FontFamily(Font(R.font.oxygen_bold))
        ))
    
    TextField(value = name, onValueChange = { name = it
    }, modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp)
        .height(50.dp), textStyle = TextStyle(
        fontSize = 15.sp
        ))

}

@Composable
fun StartBackground(){
    Image(painter = painterResource(id = R.drawable.imgprincipal3), contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds)
}