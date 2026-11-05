package com.example.pmdm_p07_artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pmdm_p07_artspace.ui.theme.PMDMP07ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PMDMP07ArtSpaceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtSpace(
                       modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ArtSpace( modifier: Modifier = Modifier) {
    var imagenActual by remember { mutableIntStateOf(1) }
    // Según el estado, elegimos los recursos de la obra actual
    val imagenesRes = when (imagenActual) {
        1 -> R.drawable.ic_android_black_24dp
        2 -> R.drawable.outline_2d_2_24
        3 -> R.drawable.outline_3d_24
        else -> R.drawable.outline_adb_24
    }
    val tituloRes = when (imagenActual) {
        1 -> R.string.titulo1
        2 -> R.string.titulo2
        3 -> R.string.titulo3
        else -> R.string.titulo4
    }
    val textosImagenRes = when (imagenActual) {
        1 -> R.string.texto1
        2 -> R.string.texto2
        3 -> R.string.texto3
        else -> R.string.texto4
    }
    Column(
        modifier = modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState()) // en horizontal/tablet no se corta
        .safeDrawingPadding()                  // respeta barras del sistema
        .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center)
    {
        Row(){
            Surface(

                color = Color.White,
                shadowElevation = 8.dp
            ) {
                Image(
                    painter = painterResource(imagenesRes),
                    contentDescription = stringResource(tituloRes),
                    modifier = Modifier
                        .padding(32.dp)
                        .heightIn(max = 400.dp)
                )
            }
        }
        Row(){
            Box(){
                Text (text =stringResource(textosImagenRes ))
            }
        }
        Botones(
            onAnteriorClick = {
            imagenActual=when (imagenActual) {
                1 -> 4
                else -> imagenActual - 1}},
            onSiguienteClick = {
                imagenActual=when (imagenActual) {
                    4 -> 1
                    else -> imagenActual + 1}}
        )
    }
}
@Composable
fun Botones (onAnteriorClick:()->Unit,
             onSiguienteClick:()->Unit,
             modifier:Modifier = Modifier
){
    Row(modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ){
        Button(onClick = onAnteriorClick) {
            Text(stringResource(R.string.anterior))
        }
        Button(onClick = onSiguienteClick) {
            Text(stringResource(R.string.siguiente))
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PMDMP07ArtSpaceTheme {
        ArtSpace()
    }
}