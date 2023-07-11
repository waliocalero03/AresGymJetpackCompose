package com.example.aresgymjetpackcompose.Activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.aresgymjetpackcompose.API.CRUDAPI
import com.example.aresgymjetpackcompose.Classes.Message
import com.example.aresgymjetpackcompose.Classes.Person
import com.example.aresgymjetpackcompose.R
import com.example.aresgymjetpackcompose.Utils.Comprobations
import com.example.aresgymjetpackcompose.Utils.Encryption
import com.example.aresgymjetpackcompose.Utils.StartBackground
import com.example.aresgymjetpackcompose.Utils.button
import com.example.aresgymjetpackcompose.Utils.passwordTextField
import com.example.aresgymjetpackcompose.Utils.textButtonBack
import com.example.aresgymjetpackcompose.Utils.textFieldUpdated
import com.example.aresgymjetpackcompose.Utils.titleLarge

class LoginActivity : ComponentActivity() {

    private lateinit var llstTextField : SnapshotStateList<MutableState<String>>
    private var message = Message(0)
    private var positionUserName = 0
    private var positionPassword = 1
    private var id_person = 0
    private var typePerson = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            loginActivity()
        }
    }

    @Composable
    fun loginActivity(){

        val llstTxt = resources.getStringArray(R.array.loginArray)
        llstTextField = remember{ mutableStateListOf()}

        if(llstTextField.isEmpty()){
            llstTxt.forEach{ _ ->
                llstTextField.add(mutableStateOf(""))
            }
        }


        StartBackground()

        ConstraintLayout(modifier = Modifier.fillMaxSize()) {

            val (column, btn) = createRefs()

            Column(modifier = Modifier.constrainAs(column){
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }) {

                textButtonBack(this@LoginActivity)
                titleLarge(title = getString(R.string.login))

                Spacer(modifier = Modifier.size(15.dp))

                for (i in llstTxt.indices) {

                    val keyBoardType = when (llstTxt[i]) {
                        getString(R.string.password) -> KeyboardType.Password
                        else -> KeyboardType.Text
                    }

                    if (keyBoardType == KeyboardType.Password) {
                        passwordTextField(value = llstTextField[i], labelText = llstTxt[i])
                    } else {
                        textFieldUpdated(
                            value = llstTextField[i],
                            labelText = llstTxt[i],
                            keyBoardType = keyBoardType
                        )
                    }
                }
            }

            button(title = getString(R.string.login), { login() }, modifier = Modifier.constrainAs(btn){
                bottom.linkTo(parent.bottom, 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
                color = R.color.white, textcolor = R.color.blue)
        }
    }

    /***
     * Method that login the person.
     */
    fun login(){

        if(checkCorrectValues()){
            if(checkPerson()){
                Toast.makeText(this, "Login correct", Toast.LENGTH_LONG).show()
            } else
                Toast.makeText(this, R.string.personDontExist, Toast.LENGTH_LONG).show()
        } else
            Toast.makeText(this, R.string.emptyValues, Toast.LENGTH_LONG).show()

    }

    /**
     * Method that checks the values are correct. In this activity, only check if the textboxs has empty value.
     * @return a Boolean if the comprobations are true or false
     */
    fun checkCorrectValues() : Boolean = Comprobations(message)
        .checkNotEmptyValuesInList(llstTextField.map { it.value},
        false)

    /***
     * Method that checks the values has a person with this values.
     * @return a Boolean if exists a person with this username and this password
     */
    fun checkPerson() : Boolean{

        var person : Person? = null
        val password = Encryption.encrypt(llstTextField[positionPassword].value)

        person = CRUDAPI().login(llstTextField[positionUserName].value, password)

        return if(person != null){
            id_person = person.id_persona!!
            typePerson = person.tipusPersona
            true
        } else{
            false
        }
    }
}