package com.example.aresgymjetpackcompose.Utils

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aresgymjetpackcompose.R

@OptIn(ExperimentalUnitApi::class)
@Composable
fun textButtonBack(context : Context){

    TextButton(onClick = { returnSplashScreen(context) }, ){
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
        fontSize = TextUnit(30f, TextUnitType.Sp)),
        modifier = Modifier.padding(start = 16.dp))

}

@Composable
fun TextField(value : MutableState<String>, keyBoardType : GlobalVariables.Companion.KeyBoardType){

    val keyboardType = when(keyBoardType){
        GlobalVariables.Companion.KeyBoardType.Decimal -> {
            KeyboardType.Decimal
        }
        GlobalVariables.Companion.KeyBoardType.Integer ->{
         KeyboardType.Number
        }
        else -> {
            KeyboardType.Text
        }
    }

    Log.i("KeyBoardType", when(keyboardType){
        KeyboardType.Decimal -> "Decimal"
        KeyboardType.Number -> "Number"
        else -> "String"
    })

    val keyboardOptions = KeyboardOptions(
        imeAction = ImeAction.Done,
        keyboardType = keyboardType
    )

    BasicTextField(value = value.value, onValueChange = {

        value.value = if(it.contains(",") && keyboardType == KeyboardType.Decimal){
            it.replace(",", ".")
        } else{
            it
        }

        Log.i("TextChange", value.value)},
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .background(
                color = colorResource(id = R.color.black)
            )
            .border(
                2.dp, color = colorResource(id = R.color.white),
                shape = RoundedCornerShape(3.dp)
            )
            .height(30.dp)
            .fillMaxWidth()
            .padding(top = 6.dp, start = 10.dp),
        textStyle = TextStyle(color = colorResource(id = R.color.white)),
        keyboardOptions = keyboardOptions
    )

}

@Composable
fun StartBackground(){
    Image(painter = painterResource(id = R.drawable.imgprincipal3), contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds)
}

@Composable
fun text(title : String){

    Text(text = title, modifier = Modifier.padding(start = 20.dp, top = 16.dp),
        style = TextStyle(
            color = colorResource(id = R.color.white),
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.oxygen_bold))
        ))

}

@Composable
fun btnBlue(title : String, onClick : () -> Unit, modifier : Modifier){

    Button(onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.blue)
        )
    ){
        textButton(text = title)
    }

}

@OptIn(ExperimentalUnitApi::class)
@Composable
fun textButton(text : String){

    Text(text = text.uppercase(), fontFamily = FontFamily(Font(R.font.roboto_light)),
        //color = MaterialTheme.colorScheme.background
        color = colorResource(id = R.color.white),
        fontSize = TextUnit(14f, TextUnitType.Sp)
    )

}