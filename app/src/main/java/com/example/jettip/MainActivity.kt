package com.example.jettip

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jettip.components.CardButton
import com.example.jettip.components.InputField
import com.example.jettip.ui.theme.JetTipTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetTipTheme {
                val tipViewModel: TipViewModel by viewModels()
                MyApp(
                    tipViewModel = tipViewModel
                )
            }
        }
    }
}

@Composable
fun MyApp(tipViewModel: TipViewModel = viewModel()) {
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp),
            verticalArrangement = Arrangement.Center
        ) {
            TotalAmountCard(tipViewModel)
            Spacer(
                modifier = Modifier.height(5.dp)
            )
            MainContent()
        }
    }
}

@Preview
@Composable
fun TotalAmountCard(tipViewModel: TipViewModel = viewModel()) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(
                shape = RoundedCornerShape(corner = CornerSize(12.dp))
            ),
        backgroundColor = Color(0xFFa02be3),
        elevation = 5.dp
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Total Per Person",
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "$${"%.2f".format(tipViewModel.getTotalPerPerson())}",
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Preview
@Composable
fun MainContent(tipViewModel: TipViewModel = viewModel()) {

    val keyboardController = LocalSoftwareKeyboardController.current

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(
            corner = CornerSize(8.dp)
        ),
        border = BorderStroke(
            width = 1.dp,
            color = Color.LightGray
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 10.dp
                )
        ) {
            InputField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = tipViewModel.getBillInput(),
                label = "Enter Bill",
                enabled = true,
                isSingleLine = true,
                onAction = KeyboardActions {
                    if (!tipViewModel.isBillValid()) return@KeyboardActions
                    keyboardController?.hide()
                }
            ) { input ->
                tipViewModel.setBillInput(input)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 10.dp,
                        end = 10.dp,
                        top = 10.dp,
                        bottom = 10.dp
                    ),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .padding(
                            end = 10.dp
                        ),
                    text = "Split:",
                    fontSize = 16.sp
                )
                CardButton(
                    modifier = Modifier
                        .size(40.dp),
                    elevation = 5.dp,
                    imageVector = Icons.Default.Remove,
                    onClick = { tipViewModel.decrementSplit() }
                )
                Text(
                    modifier = Modifier
                        .padding(
                            start = 10.dp,
                            end = 10.dp
                        ),
                    text = tipViewModel.getSplit().toString(),
                    fontSize = 16.sp
                )
                CardButton(
                    modifier = Modifier
                        .size(40.dp),
                    elevation = 5.dp,
                    imageVector = Icons.Default.Add,
                    onClick = { tipViewModel.incrementSplit() }
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 10.dp,
                        end = 10.dp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Tip percentage:",
                    fontSize = 16.sp
                )
                Slider(
                    value = tipViewModel.getTipPercent(),
                    onValueChange = { tipViewModel.setTipPercent(it) }
                )
                Text(
                    modifier = Modifier
                        .padding(
                            bottom = 10.dp
                        ),
                    text = "${(tipViewModel.getTipPercent() * 100).roundToInt()}%",
                    fontSize = 16.sp
                )
            }
        }
    }
}
