package com.example.aresgymjetpackcompose.Utils

import android.content.Context
import android.util.Log
import androidx.annotation.ColorRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.example.aresgymjetpackcompose.API.CRUDAPI
import com.example.aresgymjetpackcompose.R

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

@Composable
fun titleLarge(title : String){

    Text(text = title, style = TextStyle(
        color = colorResource(id = R.color.white),
        fontFamily = FontFamily(Font(R.font.merriweather_bold)),
        fontSize = TextUnit(30f, TextUnitType.Sp)),
        modifier = Modifier.padding(start = 16.dp))

}
/**
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

}*/

@Composable
fun StartBackground(){
    if(GlobalVariables.principalImage != null){
        Image(
            bitmap = GlobalVariables.principalImage!!,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
    }
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

/***
 * Method that creates a button.
 * @param title -> Title of the button
 * @param onClick -> Action if i click the button
 * @param color -> Container color of the button
 * @param textcolor -> Text color of the button
 */
@Composable
fun button(title : String, onClick : () -> Unit, modifier : Modifier,
        @ColorRes color : Int, @ColorRes textcolor : Int = R.color.white){

    Button(onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = color))
    ){
        textButton(text = title, textcolor)
    }

}

@Composable
fun textButton(text : String, @ColorRes color : Int = R.color.white){

    Text(text = text.uppercase(), fontFamily = FontFamily(Font(R.font.roboto_light)),
        //color = MaterialTheme.colorScheme.background
        color = colorResource(id = color),
        fontSize = TextUnit(14f, TextUnitType.Sp)
    )

}

/*@Composable
fun TextFieldPassword(value : MutableState<String>){

    OutlinedTextField(value = value.value, 
        onValueChange = {value.value = it },
        trailingIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable)
                )
            }
        }
    )

}*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun textFieldUpdated(value : MutableState<String>, labelText : String,
                     keyBoardType: KeyboardType){

    val unFocusedLabelColor = colorResource(id = if(value.value.isNotEmpty()){
            R.color.blue
            } else{
                R.color.white
            })

    OutlinedTextField(value = value.value, onValueChange = {
        Log.i("Texto", value.value)
        value.value = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 15.dp)
            .padding(start = 5.dp, end = 0.dp)
            .height(65.dp),
        textStyle = TextStyle(
            fontSize = TextUnit(18F, TextUnitType.Sp),
            color = Color.White
        ),
        label = {
            Text(text = labelText, fontSize = TextUnit(18F, TextUnitType.Sp))
        },

        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = colorResource(id = R.color.blue),
            focusedLabelColor = colorResource(id = R.color.blue),
            unfocusedLabelColor = unFocusedLabelColor,
            unfocusedBorderColor = colorResource(id = R.color.blue)
        ),
        keyboardOptions = KeyboardOptions(keyboardType = keyBoardType)
        )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun passwordTextField(value : MutableState<String>, labelText : String){

    val unFocusedLabelColor = colorResource(id = if(value.value.isNotEmpty()){
        R.color.blue
    } else{
        R.color.white
    })

    var passwordVisible by remember {mutableStateOf(false)}

    OutlinedTextField(value = value.value, onValueChange = { value.value = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 15.dp)
            .padding(start = 5.dp, end = 0.dp)
            .height(65.dp),
        textStyle = TextStyle(
            fontSize = TextUnit(18F, TextUnitType.Sp),
            color = Color.White
        ),
        label = {
            Text(text = labelText, fontSize = TextUnit(18F, TextUnitType.Sp))
        },

        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = colorResource(id = R.color.blue),
            focusedLabelColor = colorResource(id = R.color.blue),
            unfocusedLabelColor = unFocusedLabelColor,
            unfocusedBorderColor = colorResource(id = R.color.blue)
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation()  ,
        trailingIcon = {
            val image = if (passwordVisible){
                painterResource(id = R.drawable.icon_visibility_on)
            } else{
                painterResource(id = R.drawable.baseline_visibility_off_24)
            }

            IconButton(onClick = { passwordVisible = !passwordVisible}) {
                Icon(painter = image, null)
            }
        }
    )

}