package com.example.appdescuentos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appdescuentos.ui.theme.AppDescuentosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppDescuentosTheme {
               GreetingPreview(


                    )
                }
            }
        }
    }

@Composable
fun SimpleImage() {
    Image(
        painter = painterResource(id = R.drawable.descuentos),
        contentDescription = "",
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun descuento() {
    var desc by remember { mutableStateOf("") }

    Column {
        TextField(
            value = desc,
            onValueChange = { desc = it },
            label = { Text(text = "Ingrese el descuento: ") },
            placeholder = { Text(text = "") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .padding(20.dp)

        )
        var numero1 by remember { mutableStateOf("") }


            TextField(
                value = numero1,
                onValueChange = { numero1 = it },
                label = { Text(text = "Valor 1") },
                placeholder = { Text(text = "Ingrese el valor 1") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(1f)
            )
        val precio = numero1.toDoubleOrNull() ?:0.0
        val porcentaje = desc.toDoubleOrNull() ?:0.0


        val descuento = calcularDescuento(precio,porcentaje);
        Text(text = "Tu discount es: $descuento")
    }
}
@Composable
fun calcularDescuento(precioOriginal: Double, porcentajeDescuento: Double): Double {
        if (porcentajeDescuento < 0 || porcentajeDescuento > 100) {
            throw IllegalArgumentException("Ingrese un descuento válido")
        }

        val descuento = precioOriginal * (porcentajeDescuento / 100)

        return precioOriginal - descuento
    }


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppDescuentosTheme {
        Column {
            SimpleImage()
            descuento()

        }
    }
}