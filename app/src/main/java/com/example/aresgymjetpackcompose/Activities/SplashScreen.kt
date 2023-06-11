package com.example.aresgymjetpackcompose.Activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.aresgymjetpackcompose.R
import com.example.aresgymjetpackcompose.Utils.StartBackground
import com.example.aresgymjetpackcompose.Utils.btnBlue
import com.example.aresgymjetpackcompose.Utils.textButton
import com.example.aresgymjetpackcompose.theme.Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            pantallaInicial()
        }
    }

    /**
     * Composable method where contains the layout.
     */
    @OptIn(ExperimentalUnitApi::class)
    @Composable
    @Preview
    fun pantallaInicial(){

        Theme{

            ConstraintLayout(modifier = Modifier.fillMaxSize()) {

                val (btnIniciarSession, btnRegistrarse, column) = createRefs()

                StartBackground()

                Column(modifier = Modifier.constrainAs(column){
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                ){
                    Text(text = "BENVINGUT A ARES GYM", style = TextStyle(
                        textAlign = TextAlign.Center,
                        fontSize = TextUnit(40f, TextUnitType.Sp),
                    ),
                        color = colorResource(id = R.color.white),
                        fontFamily = FontFamily(Font(R.font.bebasneue_regular)),
                        modifier = Modifier.fillMaxWidth())

                    Text(text = "Aquí comença el teu progrés d'entrenament.",
                        style = TextStyle(
                            textAlign = TextAlign.Center,
                            fontSize = TextUnit(20f, TextUnitType.Sp),
                            fontFamily = FontFamily(Font(R.font.sourcesanspro_lightitalic)),
                        ), color = colorResource(id = R.color.gray_light),
                        modifier = Modifier.fillMaxWidth())

                }

                btnBlue(title = "Registrar-se", onClick = { openRegisterActivity() },
                    modifier = Modifier.constrainAs(btnRegistrarse){
                        bottom.linkTo(parent.bottom, 16.dp)
                    })

                Button(onClick = { openSignUpActivity() },
                    modifier = Modifier
                        .constrainAs(btnIniciarSession) {
                            bottom.linkTo(btnRegistrarse.top, margin = 16.dp)
                        }
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp)
                    , shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(
                        //containerColor = MaterialTheme.colorScheme.primary
                        containerColor = colorResource(id = R.color.black_light)
                    )
                ) {
                    textButton(text = "Iniciar sessión")
                }
            }
        }
    }

    /**
     * Method where changes the activity. Activity to RegisterActivity
     */
    fun openRegisterActivity(){
        val intent = Intent(this@MainActivity, RegisterActivity::class.java)
        startActivity(intent)
    }

    /**
     * Method where changes the activity. Activity to .(Not implemented)
     */
    fun openSignUpActivity(){
        /*val intent = Intent(context, LoggingActivity::class.java)
        context.startActivity(intent)*/
    }


}



