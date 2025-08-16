package br.com.fiap.praticando

import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.widget.Space
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.praticando.ui.theme.PraticandoTheme
import kotlin.math.log

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PraticandoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ComponenteIdade(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}

@Composable
fun ComponenteIdade( modifier: Modifier = Modifier) {

    var idade = remember {
        mutableIntStateOf(0)
    }
    var mensagemExibida by remember {
        mutableStateOf("")
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Center
    ) {
        Text(
            text = "Me informe a sua idade.",
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            color = Color(0xFFAD1F4E),
            fontWeight = FontWeight.Bold

        )
        Text(
            text = "Usando os Botões abaixo",
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            color = Color(color = 0xFF000000),
            fontWeight = FontWeight.Bold

        )
        Text(
            text = "${idade.value}",
            fontSize = 48.sp,
            color = Color.Red,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(32.dp))

        Row() {
            Button(
                onClick = {
                    idade.value--
                },
                modifier = Modifier.size(84.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFFAD1F4E))
            ) {
                Text("-", fontSize = 40.sp)
            }

            Spacer(modifier = Modifier.width(32.dp))

            Button(
                onClick = {
                    idade.value++
                    Log.i("FIAP", "Componente: $idade")
                },
                modifier = Modifier.size(84.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFFAD1F4E))
            ) {
                Text("+", fontSize = 40.sp)
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        Row() {
            Button(
                onClick = {
                    mensagemExibida = calcularIdade(idade.value)
                },
                modifier = Modifier.padding(10.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFFAD1F4E))
            ) {
                Text("Enviar")
            }
        }
        Text(
            text = mensagemExibida,
            fontSize = 48.sp,
            color = Color.Red,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}


@Preview
@Composable
private fun PreviewComponente() {
    PraticandoTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ComponenteIdade(modifier = Modifier.fillMaxSize())
        }
    }
}

private fun calcularIdade(idade: Int): String {
    var menssagem = ""
    if(idade < 18) menssagem = "É Menor de idade" else menssagem = "Não é menor de idade"
    return menssagem
}


