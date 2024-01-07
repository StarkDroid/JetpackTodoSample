package com.example.jetpacktodo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpacktodo.data.TodoItem
import com.example.jetpacktodo.ui.theme.JetpackTodoTheme
import com.example.jetpacktodo.views.TodoInput
import com.example.jetpacktodo.views.TodoList

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackTodoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TodoApp()
                }
            }
        }
    }
}

@Composable
fun TodoApp() {
    var taskText by remember { mutableStateOf("") }
    var todoItems by remember { mutableStateOf(listOf<TodoItem>()) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        TodoList(
            todoItems = todoItems,
            onDeleteItem = { deletedItem ->
                todoItems = todoItems.filterNot { it == deletedItem }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        TodoInput(onAddItem = {
            val trimmedText = it.trim()
            todoItems = todoItems + TodoItem(todoItems.size, trimmedText)
            taskText = ""
        }, taskText = taskText)
    }
}

@Composable
@Preview(showBackground = true)
fun TodoAppPreview() {
    TodoApp()
}
