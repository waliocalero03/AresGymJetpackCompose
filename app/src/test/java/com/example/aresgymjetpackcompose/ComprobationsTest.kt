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

    /**
     * Method that check the email value.
     */
    @Test
    fun checkEmail(){
        val emailCorrect = "admin_2@aresgym14.com"
        val emailFormatIncorrect = "admin2.aresgym.20"
        val emailSpaceIncorrect = "admin @aresgym.es"
        val emailIncorrectCharacter = "admin*@aresgym.es"
        val emailIncorrectCharacterInDomine = "admin@are*gym.es"
        val emailDomineIncorrect = "admin@aresgym.i"
        val emailDomineCharacterIncorrect = "admin@aresgym.32s"

        assertEquals(true, comprobations.checkEmail(emailCorrect))
        assertEquals(false, comprobations.checkEmail(emailFormatIncorrect))
        assertEquals(false, comprobations.checkEmail(emailSpaceIncorrect))
        assertEquals(false, comprobations.checkEmail(emailIncorrectCharacter))
        assertEquals(false, comprobations.checkEmail(emailIncorrectCharacterInDomine))
        assertEquals(false, comprobations.checkEmail(emailDomineIncorrect))
        assertEquals(false, comprobations.checkEmail(emailDomineCharacterIncorrect))
    }

    /**
     * Method that check the weight value
     */
    @Test
    fun checkDouble(){

        val doubleCorrect = "14.5"
        val double = "14"
        val doubleIncorrect = "e14.5"

        assertEquals(true, comprobations.checkDoubleValue(doubleCorrect, R.string.weightIncorrectValue, true))
        assertEquals(true, comprobations.checkDoubleValue(double, R.string.weightIncorrectValue, true))
        assertEquals(false, comprobations.checkDoubleValue(doubleIncorrect, R.string.weightIncorrectValue, true))
    }

    /**
     * Method that check if the string is valid for int
     */
    @Test
    fun checkInt(){

        val intCorrect = "14"
        val intLetter = "e14"
        val intDouble = "14.5"

        assertEquals(true, comprobations.checkIntValue(intCorrect, R.string.ageIncorrectValue, true))
        assertEquals(false, comprobations.checkIntValue(intLetter, R.string.ageIncorrectValue, true))
        assertEquals(false, comprobations.checkIntValue(intDouble, R.string.ageIncorrectValue, true))
    }

    /**
     * Method that check if the string is valid for password
     */
    @Test
    fun checkPassword(){

        val passwordCorrect = "Aa@12345"
        val passwordWithNotCapitalLetter = "AA@12345"
        val passwordWithNotLowerLetter = "aa@12345"
        val passwordWithNoNumbers = "aa@aaaaa"
        val passwordWithNoSpecialCharacter = "Aa123456"
        val passwordWithNotMinimum = "Aa@1234"
        val passwordWithMoreThanMaximium = "Aa@1234567890iikkik123"

        assertEquals(true, comprobations.checkPassword(passwordCorrect))
        assertEquals(false, comprobations.checkPassword(passwordWithNotCapitalLetter))
        assertEquals(false, comprobations.checkPassword(passwordWithNotLowerLetter))
        assertEquals(false, comprobations.checkPassword(passwordWithNoNumbers))
        assertEquals(false, comprobations.checkPassword(passwordWithNoSpecialCharacter))
        assertEquals(false, comprobations.checkPassword(passwordWithNotMinimum))
        assertEquals(false, comprobations.checkPassword(passwordWithMoreThanMaximium))
    }

    /**
     * Method that check if the string are equals
     */
    @Test
    fun checkSameString(){

        val positionFirstElement = 0
        val positionSecondElement = 1
        val arCorrect = arrayListOf("Password", "Password")
        val arIncorrect = arrayListOf("Password", "Passw0rd")

        assertEquals(true, comprobations.checkPasswordsEqual(arCorrect[positionFirstElement], arCorrect[positionSecondElement]))
        assertEquals(false, comprobations.checkPasswordsEqual(arIncorrect[positionFirstElement], arIncorrect[positionSecondElement]))
    }

    /**
     * Method that check if the list of Strings are empty
     */
    @Test
    fun checkEmptyValues(){

        val llstStringNotEmpty = listOf("P", "A", "S", "S", "G")
        val llstStringEmpty = listOf("P", "A", "", "ER", "ER")

        assertEquals(true, comprobations.checkNotEmptyValuesInList(llstStringNotEmpty))
        assertEquals(true, comprobations.checkNotEmptyValuesInList(llstStringEmpty, true, 1))
        assertEquals(false, comprobations.checkNotEmptyValuesInList(llstStringEmpty, false, 1))
        assertEquals(false, comprobations.checkNotEmptyValuesInList(llstStringEmpty))

    }

}