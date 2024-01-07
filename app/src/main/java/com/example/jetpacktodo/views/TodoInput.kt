package com.example.jetpacktodo.views

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    fun TodoInput(onAddItem: (String) -> Unit, taskText: String) {
        var isExpanded by remember { mutableStateOf(false) }
        val keyboardController = LocalSoftwareKeyboardController.current

        var textState by remember { mutableStateOf(taskText) }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 56.dp)
        ) {
            TextField(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                value = textState,
                onValueChange = { textState = it },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        onAddItem(textState)
                        isExpanded = false
                        keyboardController?.hide()
                    }
                ),
                label = { Text("Enter your text", style = MaterialTheme.typography.bodyMedium) }
            )
            IconButton(
                onClick = {
                    onAddItem(textState)
                    isExpanded = false
                    keyboardController?.hide()
                }
            ) {
                Icon(imageVector = Icons.Default.AddCircle, contentDescription = "Add")
            }
        }
    }