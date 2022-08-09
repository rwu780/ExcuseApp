package com.rwu780.excuseapp.presentation.excuse_screen


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rwu780.excuseapp.data.remote.ExcuseDto
import com.rwu780.excuseapp.ui.theme.background1
import com.rwu780.excuseapp.ui.theme.background2
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ExcuseScreen(
    modifier: Modifier = Modifier,
    viewModel: ExcuseViewModel = hiltViewModel(),
    onNavigate: () -> Unit
) {

    val scaffoldState = rememberScaffoldState()
    var showErrorDialog by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                is ExcuseUiEvent.ShowErrorDialog -> {
                    Log.d("TAG", "ExcuseScreen: ")
                    showErrorDialog = true
                }
                is ExcuseUiEvent.ShowLoadingSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = "Loading",
                        duration = SnackbarDuration.Short
                    )
                }
            }

        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Your Excuse") },
                navigationIcon = {
                    IconButton(onClick = onNavigate) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
                    }
                },
                backgroundColor = background1,
                contentColor = Color.White
            )
        },
    ) {
        Box(
            modifier = modifier.background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        background1,
                        background2
                    )
                )
            )
        ) {
            if (showErrorDialog) {
                AlertDialog(
                    modifier = Modifier.fillMaxWidth(),
                    onDismissRequest = { },
                    title = {
                        Text(text = "Error...")
                    },
                    text = {
                        Text(text = "Unable to fetch excuses, please try again")
                    },
                    confirmButton = {
                        TextButton(onClick = onNavigate) {
                            Text(text = "Back to Main Screen")
                        }
                    }
                )
                return@Scaffold
            }
            val excuses = viewModel.excuse.collectAsState()
            LazyColumn(
                modifier = modifier
            ) {
                items(excuses.value) { excuse ->
                    ExcuseItem(excuse = excuse)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun ExcuseItem(
    excuse: ExcuseDto,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.padding(8.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = 15.dp
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = excuse.excuse, style = MaterialTheme.typography.h6)

            Spacer(modifier = Modifier.height(4.dp))
            Column(
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Category: ${excuse.category}",
                    style = MaterialTheme.typography.subtitle2
                )

            }

        }
    }
}
