package nyx.example.pa1_example_native.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nyx.example.pa1_example_native.ui.theme.Yellow

@Composable
fun ErrorScreen(
    errorTitle: String,
    errorMessage: String
) {
    Box(
        Modifier
            .fillMaxSize()
            .background(Black)
    ) {

        Column(
            Modifier
                .padding(10.dp)
                .align(Alignment.Center)
        ) {

            Text(
                errorTitle,
                color = Yellow,
                fontSize = 16.sp
            )

            Text(
                errorMessage,
                Modifier.padding(top = 8.dp),
                White,
                fontSize = 12.sp
            )
        }
    }
}