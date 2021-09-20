package nyx.example.pa1_example_native.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nyx.example.pa1_example_native.ui.theme.Yellow

@Composable
fun LoadingScreen() {

    Box(
        Modifier
            .fillMaxSize()
            .background(Yellow),
       Alignment.Center
    ) {

        CircularProgressIndicator(
            Modifier
                .size(90.dp), strokeWidth = 2.dp
        )
    }
}