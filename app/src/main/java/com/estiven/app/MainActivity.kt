package com.estiven.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.estiven.app.ui.theme.FirebaseKMMTheme
import com.estiven.firebase_app.Firebase
import com.estiven.firebase_app.app
import com.estiven.firebase_firestore.FieldPath
import com.estiven.firebase_firestore.firestore
import com.estiven.firebase_storage.storage
import com.google.firebase.firestore.MetadataChanges
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val viewM: ViewM = ViewM()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirebaseKMMTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android ${viewM.result}")
                }
            }
        }
    }
}

class ViewM() : ViewModel() {

    var result by mutableStateOf(false)
        private set


    init {
        x()
    }

    fun x() {
        viewModelScope.launch {
            Firebase.firestore.collection("").snapshotListener.onEach {
                it.documents.forEach { x ->
                    x
                }
            }.launchIn(viewModelScope)
            //val y = Firebase.storage.reference.downloadUrl("images/22967b16-b32e-4572-a500-d6bacc755f99.jpg")
           val x = Firebase.storage.reference.downloadUrl("1.png")
            result = x != null

        }
    }
}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FirebaseKMMTheme {
        Greeting("Android")
    }
}