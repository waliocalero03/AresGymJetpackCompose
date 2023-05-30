package com.example.aresgymjetpackcompose.Utils

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.aresgymjetpackcompose.Classes.Message
import com.example.aresgymjetpackcompose.R
import org.jetbrains.annotations.NotNull

class Comprobations(private val message: Message) {

    fun checkNotEmptyValuesInList(llstValues : List<String>, visible : Boolean = false, numEmptyValue : Int = 0) : Boolean
    {

        val llstEmptyValues = llstValues.filter{
            it.isEmpty()
        }

        if(!visible)
            if(llstEmptyValues.isNotEmpty()) {
                returnEmptyValue(R.string.emptyValues)
            }
        else
            if(llstEmptyValues.size > numEmptyValue) {
                returnEmptyValue(R.string.emptyValues)
            }

        return true
    }

    private fun returnEmptyValue(@StringRes stringRes : Int) : Boolean{
        message.text = stringRes
        return false
    }

    fun checkFullName(fullName : String) : Boolean{

        val pattern = Regex("^[A-Za-z ]{1,4}$")
        return check(pattern, fullName, R.string.fullNameIncorrectValue)
    }

    fun checkUserName(userName : String) : Boolean{

        val pattern = Regex("^[A-Za-z0-9._]$")
        return check(pattern, userName, R.string.userNameIncorrectValue)
    }

    fun checkEmail(email : String) : Boolean{
        val pattern = Regex("^([\\w.-]+)@([\\w]+)\\.([a-z]{2,})$")
        return check(pattern, email, R.string.emailIncorrectValue)
    }

    fun checkIntValue(value : String, @StringRes messageText : Int) : Boolean{
        return try{
            value.toInt()
            true
        } catch(e : Exception){
            Log.e("Error", "Error al parsar de string a int")
            returnEmptyValue(messageText)
        }
    }

    fun checkDoubleValue(value : String, @StringRes messageText: Int) : Boolean{
        return try{
            value.toDouble()
            true
        } catch(e : Exception){
            Log.e("Error", "Error al parsar de string a double")
            returnEmptyValue(messageText)
        }
    }

    fun checkPassword(password : String) : Boolean{
        val pattern = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#\$%^&*(),.?\":{}|<>])[A-Za-z\\d!@#\$%^&*(),.?\":{}|<>]{8,16}$")
        return check(pattern, password, R.string.passwordIncorrectValue)
    }

    fun checkPasswordsEqual(password : String, confirmPassword : String) : Boolean{
        return if(password.equals(confirmPassword)){
            true
        } else{
            returnEmptyValue(R.string.passwordsDifferents)
        }
    }

    private fun check(pattern : Regex, value : String, @StringRes messageValue : Int) : Boolean{
        return if(pattern.matches(value)){
            true
        } else{
            message.text = messageValue
            false
        }
    }

}