package com.rwu780.excuseapp.presentation.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.rwu780.excuseapp.data.remote.ExcuseDto
import com.rwu780.excuseapp.ui.theme.background1
import com.rwu780.excuseapp.ui.theme.background2


@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    onNavigate: (String, String) -> Unit
) {

    Column(
        modifier = modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        background1,
                        background2
                    )
                )
            )
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val openDialog = remember { mutableStateOf(false) }

        Text(
            text = "Excuse App",
            style = MaterialTheme.typography.h3,
            modifier = Modifier.padding(top = 50.dp),
            color = Color.White
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "A true life saver",
            style = MaterialTheme.typography.subtitle1,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(20.dp))
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    openDialog.value = true
                },

            ) {
                Text(
                    text = "Find Excuse",
                    style = MaterialTheme.typography.h5,
                )
            }
        }

        if (openDialog.value) {

            var categoryText by remember {
                mutableStateOf(ExcuseDto.categories.random())
            }

            var numberOfExcuse by remember {
                mutableStateOf("1")
            }

            AlertDialog(
                modifier = Modifier.fillMaxWidth(),
                onDismissRequest = { openDialog.value = false },
                title = {
                    Text(text = "Select", style = MaterialTheme.typography.h6)
                },
                text = {
                    Column() {

                        var numberExpand by remember {
                            mutableStateOf(false)
                        }

                        DropDownMenu(
                            list = (1..10).map { it.toString() },
                            value = numberOfExcuse,
                            label = "Number of Excuses",
                            expand = numberExpand,
                            onSelectedTextChange = {
                                numberOfExcuse = it
                            },
                            onIconClicked = { numberExpand = !numberExpand },
                            onDismissed = {
                                numberExpand = false
                            }
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        var expand by remember {
                            mutableStateOf(false)
                        }

                        DropDownMenu(
                            list = ExcuseDto.categories,
                            value = categoryText,
                            label = "Categories",
                            expand = expand,
                            onSelectedTextChange = {
                                categoryText = it

                            },
                            onIconClicked = {
                                expand = !expand
                            },
                            onDismissed = {
                                expand = false
                            })
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                },
                dismissButton = {
                    TextButton(onClick = { openDialog.value = false }) {
                        Text(text = "Cancel")

                    }
                },
                confirmButton = {
                    TextButton(onClick = {
                        onNavigate(categoryText, numberOfExcuse)

                    }) {
                        Text(text = "Continue")

                    }
                },
            )
        }
    }
}

@Composable
fun DropDownMenu(
    list: List<String>,
    value: String,
    label: String,
    expand: Boolean,
    onSelectedTextChange: (String) -> Unit,
    onIconClicked: () -> Unit,
    onDismissed: () -> Unit

) {

    var mTextFieldSize by remember {
        mutableStateOf(Size.Zero)
    }

    val icon =
        if (expand) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown


    OutlinedTextField(
        value = value,
        readOnly = true,
        onValueChange = {
            onSelectedTextChange(it)
        },
        modifier = Modifier
            .fillMaxWidth()
            .onGloballyPositioned { coordinates ->
                mTextFieldSize = coordinates.size.toSize()
            },
        label = { Text(text = label) },
        trailingIcon = {
            Icon(
                icon,
                contentDescription = "",
                modifier = Modifier.clickable {
                    onIconClicked()
                })
        }
    )
    DropdownMenu(
        expanded = expand,
        onDismissRequest = onDismissed,
        modifier = Modifier.width(
            with(LocalDensity.current) {
                mTextFieldSize.width.toDp()
            }
        )
    ) {
        list.forEach { label ->
            DropdownMenuItem(onClick = {
                onSelectedTextChange(label)
                onDismissed()
            }) {
                Text(text = label)

            }
        }
    }
}