package com.estiven.app

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.estiven.app.ui.theme.FirebaseKMMTheme
import com.estiven.firebase_app.Firebase
import com.estiven.firebase_auth.PhoneAuthProvider
import com.estiven.firebase_auth.PhoneAuthResult
import com.estiven.firebase_auth.PhoneAuthVerifyNumber
import com.estiven.firebase_firestore.firestore
import com.google.firebase.Timestamp
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    private val viewM: ViewM = ViewM()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Firebase
            FirebaseKMMTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val context = LocalContext.current
                    val activity = context as Activity
                    Column {
                        Greeting("Android ${viewM.result2}\n")
                        Button(onClick = {
                            viewM.sendVerifyPhoneNumber(activity)
                        }) {
                            Text("Hola")
                        }
                    }
                    /* LazyColumn{
                         items(viewM.result){ item ->
                             Greeting("Android ${item.fullName}\n")

                         }
                     }*/
                }

            }
        }
    }
}

class ViewM() : ViewModel() {

    var result = mutableStateListOf<User>()
        private set
    var result1 by mutableStateOf(User())
        private set
    var result2 by mutableStateOf<PhoneAuthResult?>(null)
        private set


    init {
        x()
    }

    fun x() {
        viewModelScope.launch {
            //val y = Firebase.storage.reference.downloadUrl("images/22967b16-b32e-4572-a500-d6bacc755f99.jpg")
            val collection =
                Firebase.firestore.collection("users").document("vYN231bEPrTtaQSyj9ZhVJYUF8m2")
            collection.snapshotListener.onEach {
                val list = it.toObject<User>()
                result1 = list

            }.launchIn(viewModelScope)

        }
    }

    fun sendVerifyPhoneNumber(
        activity1: Activity
    ) {
        viewModelScope.launch {
            val phoneAuthVerifyNumber = object : PhoneAuthVerifyNumber {
                override val activity: Activity
                    get() = activity1
                override val timeout: Long
                    get() = 60L
                override val timeunit: TimeUnit
                    get() = TimeUnit.SECONDS

            }

            val x = PhoneAuthProvider().verifyPhoneNumber(
                phoneNumber = "+5735047764756",
                phoneAuthVerifyNumber = phoneAuthVerifyNumber
            )
            result2 = x
        }
    }
}

data class Chats(
    val id: String = "",
    val members: List<String> = emptyList(),
    val whoIsWriting: String = "",
    val notificationId: String = ""
)

@Serializable
object StatusMessage {
    const val SENT = 1
    const val DELIVERED = 2
    const val RECEIVED = 3
}


@Serializable
object TypeMessage {
    const val TEXT = 1
    const val GIF = 2
    const val PHOTO = 3
    const val VIDEO = 4
    const val AUDIO = 5
    const val PDF = 6
}

data class User(
    val id: String = "",
    val fullName: String = "",
    val image: String? = null,
    val number: String = "",
    val biography: String? = null,
    val online: Boolean = false,
    val token: String = "",
    val username: String = "",
    val lastConnection: Timestamp = Timestamp.now(),
)


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