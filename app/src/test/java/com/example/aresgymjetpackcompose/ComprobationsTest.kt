package com.example.aresgymjetpackcompose

import com.example.aresgymjetpackcompose.Classes.Message
import com.example.aresgymjetpackcompose.Utils.Comprobations
import org.junit.Test
import org.junit.Assert.*

class ComprobationsTest {

    private val message = Message(0)
    private val comprobations = Comprobations(message)

    /**
     * Method that check full name value.
     */
    @Test
    fun testCheckFullName(){

        val fullNameCorrect = "Full Name Correct"
        val nameIncorrectNumber = "Full N4me Correct"
        val nameIncorrectSpecialCharacter = "Full N*me Correct"
        //val incorrectEmptyOnlySpaces = " "
        val nameMoreThanFourSpaces = "Full Name Correct Four Five "

        assertEquals(true, comprobations.checkFullName(fullNameCorrect))
        assertEquals(false, comprobations.checkFullName(nameIncorrectNumber))
        assertEquals(false, comprobations.checkFullName(nameIncorrectSpecialCharacter))

        /**
         * I don't use this test because in this method don't check empty value.
         * This is in another method.
         */
        //assertEquals(false, comprobations.checkFullName(incorrectEmptyOnlySpaces))
        assertEquals(false, comprobations.checkFullName(nameMoreThanFourSpaces))
    }

    /**
     * Method that check the username value.
     */
    @Test
    fun checkUserName(){
        val userNameCorrect = "walioCalero_14"
        val userNameWithSpaces = "walio calero14"
        val userNameWithSpecialCharacter = "wal*oCalero"
        val userNameWithNoMinimCharacter = "wal"

        assertEquals(true, comprobations.checkUserName(userNameCorrect))
        assertEquals(false, comprobations.checkUserName(userNameWithSpaces))
        assertEquals(false, comprobations.checkUserName(userNameWithSpecialCharacter))
        assertEquals(false, comprobations.checkUserName(userNameWithNoMinimCharacter))
    }


}