package com.example.aresgymjetpackcompose.Activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.example.aresgymjetpackcompose.Utils.Encryption
import com.example.aresgymjetpackcompose.Utils.GlobalVariables
import com.example.aresgymjetpackcompose.btnBlue
import com.example.aresgymjetpackcompose.text

class RegisterActivity : ComponentActivity() {

    private lateinit var llstTextField :  SnapshotStateList<MutableState<String>>
    private lateinit var checked :  MutableState<Boolean>
    private var message = Message(0)
    private var crupAPI = CRUDAPI()
    private var id_person : Int = 0
    private val positionFullName = 0
    private val positionUserName = 1
    private val positionEmail = 2
    private val positionWeight = 3
    private val positionAge = 4
    private val positionPassword = 5
    private lateinit var coachCode : CoachCode
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            registerActivity()
        }
    }

    /**
     * Function where are all the view of the Register Activity
     */
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

    /**
     * Function where register person
     */
    fun register(){

        if(checkCorrectValues()){
            if(registerPerson()){

            }
        } else
            Toast.makeText(this@RegisterActivity, message.text, Toast.LENGTH_LONG).show()
    }

    /**
     * Function where check all the values are correct.
     * Return a true or false depends if all the values are correct or not.
     */
    fun checkCorrectValues() : Boolean{

        val comprobations = Comprobations(message)
        val numEmptyValue = 1

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
            if(!checkCoachCode())
                return false
        }

        return true
    }

    /**
     * Function where checks that the coach code exists and has no one related to it
     * Return a true or false depends if the coach code has no one realated and exists
     */
    fun checkCoachCode() : Boolean{
        var positionCoachCode = llstTextField.size - 1

        var coachCode = crupAPI.getCoachCodeByCode(llstTextField[positionCoachCode].value)

        return if(coachCode != null){
           if( coachCode.id_persona == null){
               this.coachCode = coachCode!!
               true
           } else{
               false
           }
        } else{
            false
        }
    }

    /**
     * Function where register a person in DB
     * Return a true or false depends if all the action are correct.
     */
    fun registerPerson() : Boolean{

        val encryptPassword = Encryption.encrypt(llstTextField[positionPassword].value)
        val typePerson = if(checked.value) GlobalVariables.coachTypePerson
                            else GlobalVariables.normalUserTypePerson

        val age = llstTextField[positionAge].value.toInt()
        val weight = llstTextField[positionWeight].value.toDouble()
        val person = Person(encryptPassword, llstTextField[positionEmail].value, age, null,
                    llstTextField[positionFullName].value, llstTextField[positionUserName].value, weight,
                    typePerson)

        var personAdd = crupAPI.registerPerson(person)

        if(personAdd == null){
            Log.e("RegisterActivity", "Person in DB was null")
            return false
        }

        if(personAdd.id_persona != null)
            id_person = personAdd.id_persona!!
        else{
            Log.e("RegisterActivity", "Id_Person was null")
            return false
        }

        if(checked.value){
            coachCode.id_persona = id_person
            crupAPI.modifyCoachCode(coachCode)
        }

        return true
    }

}

fun returnSplashScreen(context : Context){
    val intent = Intent(context, MainActivity::class.java)
    context.startActivity(intent)
}

