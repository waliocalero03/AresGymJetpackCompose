package com.example.aresgymjetpackcompose.Activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.aresgymjetpackcompose.StartBackground
import com.example.aresgymjetpackcompose.textButtonBack
import com.example.aresgymjetpackcompose.titleLarge
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.aresgymjetpackcompose.API.CRUDAPI
import com.example.aresgymjetpackcompose.Classes.*
import com.example.aresgymjetpackcompose.R
import com.example.aresgymjetpackcompose.TextField
import com.example.aresgymjetpackcompose.Utils.Comprobations
import com.example.aresgymjetpackcompose.btnBlue
import com.example.aresgymjetpackcompose.text

class RegisterActivity : ComponentActivity() {

    private lateinit var llstTextField :  SnapshotStateList<MutableState<String>>
    private lateinit var checked :  MutableState<Boolean>
    private var message = Message(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            registerActivity()
        }
    }

    @Composable
    @Preview
    fun registerActivity() {

        val numTextFields = 8
        llstTextField = remember{ mutableStateListOf<MutableState<String>>()}
        checked = remember { mutableStateOf(false) }
        val llstTxt = resources.getStringArray(R.array.registerArray)

        repeat(numTextFields){
            llstTextField.add(mutableStateOf(""))
        }

        StartBackground()

        Column(modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())){

            textButtonBack(this@RegisterActivity)
            titleLarge(title = getString(R.string.register))

            for(i in 0 until numTextFields - 1){
                text(title = llstTxt[i])
                TextField(llstTextField[i])
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 16.dp)){

                Checkbox(checked = checked.value, onCheckedChange = {
                    checked.value = !checked.value
                }, modifier = Modifier
                    .padding(top = 3.dp, start = 2.dp)
                    .width(15.dp),
                    colors = CheckboxDefaults.colors(
                        checkedColor = colorResource(id = R.color.blue),
                        uncheckedColor = colorResource(id = R.color.white)
                    ))
                text(getString(R.string.coach))
            }

            if(checked.value){
                text(title = llstTxt[llstTxt.size - 1])
                TextField(llstTextField.last())
            }

            btnBlue(title = "Registrar-se", onClick = { register() }, modifier = Modifier.padding(top = 16.dp, bottom = 16.dp))

        }

    }

    fun register(){

        if(checkCorrectValues()){

        } else
            Toast.makeText(this@RegisterActivity, message.text, Toast.LENGTH_LONG).show()
    }

    fun checkCorrectValues() : Boolean{

        val comprobations = Comprobations(message)
        val numEmptyValue = 1
        val positionFullName = 0
        val positionUserName = 1
        val positionEmail = 2
        val positionWeight = 3
        val positionAge = 4
        val positionPassword = 5

        if(!comprobations.checkNotEmptyValuesInList(
                llstTextField.map { it.value},
                checked.value,
                numEmptyValue
        ))
            return false

        if(!comprobations.checkFullName(llstTextField[positionFullName].value))
            return false

        if(!comprobations.checkUserName(llstTextField[positionUserName].value))
            return false

        if(!comprobations.checkEmail(llstTextField[positionEmail].value))
            return false

        if(!comprobations.checkDoubleValue(llstTextField[positionWeight].value, R.string.weightIncorrectValue))
            return false

        if(!comprobations.checkIntValue(llstTextField[positionAge].value, R.string.ageIncorrectValue))
            return false

        if(!comprobations.checkPassword(llstTextField[positionPassword].value))
            return false

        if(!comprobations.checkPasswordsEqual(llstTextField[positionPassword].value, llstTextField[positionPassword+1].value))
            return false

        if(checked.value){
            if(!checkCoachCode()){
            }
        }

        return true
    }

    fun checkCoachCode() : Boolean{
        var positionCoachCode = llstTextField.size - 1
        var crupAPI = CRUDAPI()
        var coachCode = crupAPI.getCoachCodeByCode(llstTextField[positionCoachCode].value)

        return if(coachCode != null){
            coachCode.id_persona == null
        } else{
            false
        }
    }

}

fun returnSplashScreen(context : Context){
    val intent = Intent(context, MainActivity::class.java)
    context.startActivity(intent)
}

