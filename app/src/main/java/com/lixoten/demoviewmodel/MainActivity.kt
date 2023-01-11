package com.lixoten.demoviewmodel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lixoten.demoviewmodel.ui.theme.Purple700
import com.lixoten.demoviewmodel.ui.theme.DemoViewModelTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DemoViewModelTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    DemoApp()
                }
            }
        }
    }
}


@Composable
fun MyTopBar() {
    TopAppBar {
        Text(text = "uiState & ViewMode Demo")
    }
}

@Composable
fun DemoApp() {
    Scaffold(
        topBar = { MyTopBar() }
    ) {
        DemoScreen(
            modifier = Modifier
                .padding(it)
        )
    }
}

@Composable
fun DemoScreen(
    modifier: Modifier = Modifier,
    viewModel: DemoViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    //var foo by remember { mutableStateOf("") }
    //var counter by remember { mutableStateOf(0) }
    Column (
        modifier = modifier.padding(16.dp)
    ) {
        FooTextField(
           foo = uiState.foo,
           onFooChanged = { viewModel.updateFoo(it) }
        )

        Divider(thickness = 4.dp, color = Purple700, modifier = Modifier.padding(16.dp))

        ClickMe(
            onClickMe = { viewModel.incrementCounter() }
        )

        Divider(thickness = 4.dp, color = Purple700, modifier = Modifier.padding(16.dp))

        Text(
            "Entered value foo: ${uiState.foo}",
            style = MaterialTheme.typography.body1
        )
        Text(
            "Clicked Counter: ${uiState.counter}",
            style = MaterialTheme.typography.body1
        )
    }
}

@Composable
fun FooTextField(
    foo: String,
    onFooChanged: (String) -> Unit
) {
    Column {
        TextField(
            value = foo,
            onValueChange = onFooChanged,
            label = { Text(text = "Enter a Value)") }
        )
    }
}

@Composable
fun ClickMe(
    onClickMe: () -> Unit
) {
    // var counter by remember { mutableStateOf(0) }
    Button(
        onClick = onClickMe
    ) {
        Text(text = "Click me")
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DemoViewModelTheme {
        DemoApp()
    }
}