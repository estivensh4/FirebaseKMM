package com.estiven.app

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.estiven.app.ui.theme.FirebaseKMMTheme
import com.estiven.firebase_app.Firebase
import com.estiven.firebase_auth.PhoneAuthResult
import com.estiven.firebase_auth.auth
import com.estiven.firebase_config.config
import com.estiven.firebase_crashlytics.crashlytics
import com.estiven.firebase_firestore.firestore
import com.estiven.firebase_messaging.FirebaseService
import com.estiven.firebase_messaging.FirebaseServiceOptions
import com.estiven.firebase_messaging.messaging
import com.estiven.firebase_performance.performance
import com.estiven.firebase_storage.File
import com.estiven.firebase_storage.UploadResult
import com.estiven.firebase_storage.storage
import com.google.firebase.Timestamp
import com.google.firebase.remoteconfig.ktx.remoteConfig
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


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
                    val perf = Firebase.performance
                    val trace =perf.newTrace("test_trace")
                    trace?.start()

                    Column {
                        Greeting("Android ${viewM.result3}\n")
                        Button(onClick = {
                            viewM.generateLog()
                            //viewM.sendVerifyPhoneNumber(activity)
                        }) {
                            Text("Hola")
                        }
                    }
                    trace?.stop()

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
    var result3 by mutableStateOf("")
        private set


    init {
        //x()
        //createUser()
        //testConfig()
        testPerformance()
        testMessaging()
    }

    fun x() {
        viewModelScope.launch {
            //val y = Firebase.storage.reference.downloadUrl("images/22967b16-b32e-4572-a500-d6bacc755f99.jpg")
            val collection =
                Firebase.firestore.collection("users").document("vYN231bEPrTtaQSyj9ZhVJYUF8m2")
            collection.snapshotListener.onEach {
                val list = it.toObject<User>()
                //result1 = list

            }.launchIn(viewModelScope)

        }
    }

    fun createUser(){
        viewModelScope.launch {
            result3 = try {
                val x = Firebase.auth.createUserWithEmailAndPassword("prueba6@yopmail.com", "12345678")

                x.user

                x.toString()
            } catch (exception: Exception){
                exception.toString()
            }

        }
    }

    fun sendVerifyPhoneNumber(
        activity1: Activity
    ) {
       /* val auth = FirebaseAuth.getInstance()
        auth.currentUser?.metadata.creationTimestamp

        auth.createUserWithEmailAndPassword("", "").await()

        auth.signInAnonymously().await().additionalUserInfo.isNewUser

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
        }*/
    }

    fun testStorage(){
        Firebase.storage.getReferencePath("ejaej").putFile(File(Uri.parse(""))).onEach {
            when(it){
                is UploadResult.Progress -> {

                }

                is UploadResult.Success -> {
                    it.storage.downloadUrl()
                }

                UploadResult.Paused -> {

                }
            }
        }.launchIn(viewModelScope)
    }

    fun generateLog(){
        try {
            val crash = Firebase.crashlytics
            crash.log("errormessageprueab")
            crash.setUserId("123456")
            throw RuntimeException("prueba")
        } catch (e: Exception){
            Firebase.crashlytics.recordException(e)
        }
    }

    fun testConfig() {

       val config = Firebase.config

        viewModelScope.launch {
            config.settings(
                minimumFetchIntervalInSeconds = 1000,
                fetchTimeoutInSeconds = 60
            )
            config.fetchAndActivate()
        }

        val x = config.getValue("prueba2")
        val y = com.google.firebase.ktx.Firebase.remoteConfig
/*        y.addOnConfigUpdateListener(object : ConfigUpdateListener{
            override fun onUpdate(configUpdate: ConfigUpdate) {
                result3 = configUpdate.updatedKeys.toString()
            }

            override fun onError(error: FirebaseRemoteConfigException) {
                result3 = error.toString()
            }

        })*/
        val o = config.all
        config.configUpdateListener.onEach {
            result3 = it.toString()
        }.launchIn(viewModelScope)

    }

    fun testPerformance(){

        val x = Firebase.performance
        x.newTrace("prueba1")?.putMetric("nuevo", 4L)
    }

    fun testMessaging(){
        val y = Firebase.messaging
        viewModelScope.launch {
            val o = y.getToken()
        }
    }
}

data class Chats(
    val id: String = "",
    val members: List<String> = emptyList(),
    val whoIsWriting: String = "",
    val notificationId: String = ""
)

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