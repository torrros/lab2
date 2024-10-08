package com.lab2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lab2.ui.theme.Lab2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Lab2Theme the container with app theme (shares the attributes like colors, typography, etc. for all nested composable views)
            Lab2Theme {
                // ... starting of custom composable view com.lab2.MainActivityScreen()
                MainActivityScreen()
            }
        }
    }
}

/** com.lab2.MainActivityScreen()
 * - composable function for custom view
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainActivityScreen() {
    // itemList - the state with list for storing and updating data on screen dynamically
    // TODO: ! Learn more about State in Compose and remember{} !
    val itemList = remember { mutableStateListOf<Item>() }

    //  Column() [vertical] - Composable function for displaying any type items (other Composable views) in column
    // TODO: ! Practice with Column() and Row() !
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White)
            .clip(RoundedCornerShape(20.dp))
        ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .weight(7.0f)
                .background(Color.White)
                .padding(10.dp)
        ) {
            if (itemList.isEmpty()) {
                item {
                    Text(
                        text = "Додайте вашу нотатку... ",
                        modifier = Modifier.padding(end = 130.dp),
                        color = Color.DarkGray,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            } else {

                items(itemList) { item ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(15.dp))
                            .background(Color.DarkGray)
                            .padding(10.dp)
                            .pointerInput(Unit) {
                                detectTapGestures(
                                    onLongPress = {
                                        itemList.remove(item)
                                    }
                                )
                            }
                    ) {
                        Text(text = "Назва нотатки: ${item.name}", color = Color.White)
                        Text(text = "Текст нотатки: ${item.task}", color = Color.White)
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }

            Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxSize()
                .weight(3.0f)
                .background(Color.White)
                .clip(RoundedCornerShape(0.dp))
        ) {
            //  states for storing strings values of textFields
            // TODO: Learn Mode about State, remember {}, mutableStateOf()

            val textFieldName = remember { mutableStateOf("") }
            val textFieldTask = remember { mutableStateOf("") }

            //  TextField() -  view for displaying and inputting text


            TextField(
                value = textFieldName.value,
                onValueChange = { newName -> textFieldName.value = newName },
                modifier = Modifier
                    .size(385.dp, 60.dp)
                    .padding(1.dp),
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(10.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.DarkGray,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                   ),
                placeholder = {
                    Text(
                        text = "Назва нотатки",
                        color = Color.White ,
                        fontSize = 14.sp

                    )
                }
            )

            TextField(
                value = textFieldTask.value, //displays value from state
                onValueChange = { newTask -> textFieldTask.value = newTask }, // change value in state
                modifier = Modifier
                    .size(385.dp, 60.dp)
                    .padding(1.dp),
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.DarkGray,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                placeholder = {
                    Text(
                        text = "Текст нотатки",
                        color = Color.White ,
                        fontSize = 14.sp

                    )
                }
            )
            //  Button() -  default button container
            Button(
                onClick = {  //  button click action
                    itemList.add(
                        Item(

                            name = textFieldName.value,
                            task = textFieldTask.value,
                        )
                    )
                },
                modifier = Modifier
                    .size(300.dp, 80.dp)
                    .padding(10.dp),
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(25.dp),

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.DarkGray
                )

            ) {
                //  button content -  here just includes one Text()
                Text(text = "Додати нотатку",
                    fontSize = 17.sp,
                    color = Color.White)
            }
        }
    }
}

/**
 * com.lab2.Item
 * - data class to store info (more than one)
 * - can be extended by other info
 */
data class Item( val name: String, val task: String)


/**
 * com.lab2.MainActivityPreview
 * - just preview for developing
 */
@Preview
@Composable
fun MainActivityPreview() {
    Lab2Theme {
        MainActivityScreen()
    }
}