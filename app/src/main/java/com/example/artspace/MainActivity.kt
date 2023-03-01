package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ArtSpaceScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpaceScreen() {
    var art by remember { mutableStateOf(1) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp, start = 24.dp, end = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Art(art = art, modifier = Modifier.weight(0.7F))
        Spacer(modifier = Modifier.height(24.dp))
        ArtTitle(art = art, modifier = Modifier.weight(0.2F))
        Spacer(modifier = Modifier.height(24.dp))
        Buttons(modifier = Modifier.weight(0.1F), onPrevious = {
            art = if(art - 1 == 0) {
                2
            } else {
                1
            }
        },
        onNext = {
            art = if(art + 1 == 3) {
                1
            } else {
                2
            }
        })
    }
}

@Composable
fun Art(art: Number, modifier: Modifier = Modifier) {
    val image = when(art) {
        1 -> R.drawable.img1
        else -> R.drawable.img2
    }

 Surface(modifier = modifier
     .border(width = 2.dp, color = Color.LightGray)
     .padding(24.dp), elevation = 24.dp) {
     Image(
         painter = painterResource(id = image),
         contentDescription = null,
         contentScale = ContentScale.Crop,
     )
 }
}

@Composable
fun ArtTitle(art: Number, modifier: Modifier = Modifier) {
    val author = when(art) {
        1 -> "Frans Aldio"
        else -> "Roberto Shumski"
    }

    Surface(modifier = modifier, elevation = 24.dp) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = "Waterfall in the Middle of the Forest", fontSize = 24.sp, fontWeight = FontWeight.Light)
            Text(text = author, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun Buttons(onPrevious: () -> Unit, onNext: () -> Unit ,modifier: Modifier = Modifier) {
    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
       Button(onClick = onPrevious, modifier = Modifier.weight(1F)) {
           Text(text = "Previous")
       }
        Spacer(modifier = Modifier.weight(1F))
        Button(onClick = onNext, modifier = Modifier.weight(1F)) {
            Text(text = "Next")
        }
    }
}