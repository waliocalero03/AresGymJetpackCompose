package com.example.aresgymjetpackcompose.Utils

import android.util.Base64
import android.util.Log
import java.security.NoSuchAlgorithmException
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class Encryption {

    companion object{

        private val algoritm = "Blowfish"
        private val mode = "Blowfish/CBC/PKCS5Padding"
        private val IV = "abcdefgh"
        private val key = "AresGymKey"
        private val secretKey = SecretKeySpec(key.toByteArray(), algoritm)

        fun encrypt(value : String) : String{
            val values = cipher(value)
            return Base64.encodeToString(values, Base64.DEFAULT)
        }

        fun desencrypt(value: String) : String?{
            val values = Base64.decode(value, Base64.DEFAULT)
            return String(cipher(value)!!)
        }

        private fun cipher(value : String) : ByteArray? {
            return try{
                val cipher = Cipher.getInstance(mode)
                val ivparameter = IvParameterSpec(IV.toByteArray())
                cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivparameter)
                cipher.doFinal(value.toByteArray())
            } catch(e : Exception){
                Log.e("EncryptionError", e.message.toString())
                null
            }

        }

    }

}