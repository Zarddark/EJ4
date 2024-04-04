package com.example.ej4

import android.os.Bundle
import android.util.Patterns
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ej4.ui.theme.EJ4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EJ4Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Ej4()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun Ej4(){


    var usuario by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {
        Column(modifier = Modifier
            .padding(25.dp)
            .weight(1f),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(id = R.drawable.insta), contentDescription = "InstagramImg", modifier = Modifier.padding(top = 150.dp, bottom = 50.dp))
            OutlinedTextField(
                value = usuario,
                onValueChange = { usuario = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Email address", color = Color.Gray) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.LightGray
                )
            )
            OutlinedTextField(
                value = contrasena,
                onValueChange = { contrasena = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp),
                placeholder = { Text("Password", color = Color.Gray) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.LightGray
                )
            )
            Row {
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "Forgotten Password?", color = colorResource(id = R.color.BlueInsta), fontWeight = FontWeight.Bold)
            }

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                colors = ButtonDefaults.buttonColors(containerColor =  if(enableLogin(
                        email = usuario,
                        password = contrasena
                    )) colorResource(id = R.color.BlueInsta) else Color.LightGray),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "Log In", color = Color.White)
            }
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically) {
                Divider(modifier = Modifier
                    .height(1.dp)
                    .weight(1f))
                Text(text = "OR", color = Color.LightGray, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 15.dp))
                Divider(modifier = Modifier
                    .height(1.dp)
                    .weight(1f))
            }
            Row(modifier = Modifier.padding(vertical = 35.dp)) {
                Image(painter = painterResource(id = R.drawable.fb), contentDescription = "FacebookIcon", modifier = Modifier
                    .size(25.dp)
                    .padding(end = 5.dp))
                Text(text = "Log in with Facebook", color = colorResource(id = R.color.BlueInsta), fontWeight = FontWeight.Bold)
            }
        }
        Divider(modifier = Modifier.height(1.dp))
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 25.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Dont't have account? ", color = Color.LightGray, textAlign = TextAlign.Center)
            Text(text = "Sign Up", color = colorResource(id = R.color.BlueInsta))
        }
    }
}

@Composable
fun enableLogin (email: String, password: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 6