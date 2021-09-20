package nyx.example.pa1_example_native

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import com.example.pa1_example_native.R
import kotlinx.coroutines.DelicateCoroutinesApi
import nyx.example.pa1_example_native.model.ArticlePreview
import nyx.example.pa1_example_native.screen.ErrorScreen
import nyx.example.pa1_example_native.ui.theme.*
import nyx.example.pa1_example_native.util.isLandscape
import nyx.example.pa1_example_native.util.isPortrait
import nyx.example.pa1_example_native.util.openLink
import nyx.sdui.LoadableView

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Pa1_example_nativeTheme {

                LoadableView {
                    val result by loadAsync {
                        viewModel.getPreviews()
                    }

                    whenReady {
                        when (result) {
                            is Exception -> ErrorScreen(
                                errorTitle = "Loading Feed failed",
                                errorMessage = (result as Exception).message!!
                            )

                            is Array<*> -> Content(result as Array<ArticlePreview>)
                        }
                    }
                }
            }
        }
    }


    @OptIn(ExperimentalCoilApi::class)
    @Composable
    fun Content(articlePreviews: Array<ArticlePreview>) {

        val orientation = LocalConfiguration.current.orientation

        Scaffold(
            topBar = {
                TopAppBar(backgroundColor = blue) {
                    Box(Modifier.fillMaxWidth().padding(horizontal = 10.dp)) {

                        Image(
                            painter = rememberImagePainter(
                                data = R.drawable.logo_192,
                                imageLoader = LocalImageLoader.current
                            ),
                            contentDescription = null,
                            Modifier
                                .size(45.dp)
                                .align(Alignment.CenterStart)
                        )

                        if (orientation.isLandscape()) {

                            Row(Modifier.align(Alignment.CenterEnd)) {

                                IconButton(
                                    onClick = {
                                        this@MainActivity.openLink("https://t.me/militaernews")
                                    }
                                ) {
                                    Icon(
                                        painterResource(R.drawable.ic_telegram),
                                        "Telegram",
                                        Modifier.size(35.dp),
                                        tint = White
                                    )
                                }


                                IconButton(
                                    onClick = {
                                        this@MainActivity.openLink("https://twitter.com/MilitaerNews?s=09")
                                    }
                                ) {
                                    Icon(
                                        painterResource(R.drawable.ic_twitter),
                                        "Twitter",
                                        Modifier.size(35.dp),
                                        tint = White
                                    )
                                }
                            }

                        }
                    }
                }
            },
            bottomBar = {

                if (orientation.isPortrait()) {
                    BottomAppBar(backgroundColor = blue) {
                        IconButton(
                            onClick = {
                                this@MainActivity.openLink("https://t.me/militaernews")
                            },
                            Modifier
                                .fillMaxWidth()
                                .weight(1f)
                        ) {
                            Icon(
                                painterResource(R.drawable.ic_telegram),
                                "Telegram",
                                Modifier.size(35.dp),
                                tint = White
                            )
                        }


                        IconButton(
                            onClick = {
                                this@MainActivity.openLink("https://twitter.com/MilitaerNews?s=09")
                            },
                            Modifier
                                .fillMaxWidth()
                                .weight(1f)
                        ) {
                            Icon(
                                painterResource(R.drawable.ic_twitter),
                                "Twitter",
                                Modifier.size(35.dp),
                                tint = White
                            )
                        }
                    }
                }

            }
        ) {

            LazyColumn(
                Modifier
                    .background(lightBlue)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Spacer(modifier = Modifier.height(if (orientation.isPortrait()) 80.dp else 35.dp))
                }

                itemsIndexed(articlePreviews) { index, item ->

                    if (index % 12 == 0) {
                        Card(
                            Modifier
                                .fillMaxWidth(if (orientation.isLandscape()) 0.6f else 1f)
                                .padding(top = 20.dp,start=10.dp,end=10.dp),
                            shape = RoundedCornerShape(16.dp),
                            backgroundColor = Cyan
                        ) {

                            Column(
                                Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    "Auf dem Laufenden bleiben",
                                    Modifier.padding(top = 20.dp),
                                    color = White,
                                    fontWeight = FontWeight.Bold
                                )

                                Text(
                                    "Nachrichten rund um Militär- und Protest-Aktionen weltweit und brandaktuell \uD83D\uDD30",
                                    Modifier.padding(10.dp),
                                    color = White
                                )

                                OutlinedButton(onClick = {

                                }, Modifier.fillMaxWidth()) {
                                    Text("Benachrichtungen aktivieren")
                                }
                            }
                        }


                    }

                    ArticlePreviewCard(item, orientation.isLandscape())

                }

                item {
                    Spacer(modifier = Modifier.height(100.dp))
                }


            }
        }
    }


    @OptIn(ExperimentalCoilApi::class)
    @Composable
    fun ArticlePreviewCard(articlePreview: ArticlePreview, isLandscape: Boolean) = Card(
        Modifier
            .fillMaxWidth(if (isLandscape) 0.6f else 1f)
            .padding(top = 20.dp,start=10.dp,end=10.dp),
        shape = RoundedCornerShape(16.dp),
        backgroundColor = darkBlue
    ) {

        Column(Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = rememberImagePainter(
                    data = articlePreview.imageUrl,
                    imageLoader = LocalImageLoader.current,
                    builder = {
                        crossfade(true)
                    }
                ), contentDescription = null,

                Modifier
                .fillMaxWidth()
                   .height(150.dp),
                        contentScale = ContentScale.Crop
            )

            Text(
                articlePreview.title,
                Modifier.padding(16.dp),
                fontWeight = FontWeight.Bold,
                color = White
            )
        }

    }

}