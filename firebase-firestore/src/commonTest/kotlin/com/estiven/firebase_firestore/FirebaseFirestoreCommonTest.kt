package com.estiven.firebase_firestore

import com.estiven.firebase_app.Firebase
import com.estiven.firebase_app.FirebaseOptions
import com.estiven.firebase_app.apps
import com.estiven.firebase_app.initialize
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertTrue

expect val context: Any
expect val emulatorHost: String

class FirebaseFirestoreCommonTest {

    fun initializeFirebase() {
        Firebase.takeIf { Firebase.apps(context).isEmpty() }
            ?.apply {
                initialize(
                    context,
                    FirebaseOptions(
                        applicationId = "1:262199812612:android:1c4a54f79658b9eb960b51",
                        apiKey = "AIzaSyCch8ysaZVf4yO_3IbczDDl5AeZp_sORWk",
                        databaseUrl = "https://whatsappclone-62617-default-rtdb.firebaseio.com",
                        storageBucket = "whatsappclone-62617.appspot.com",
                        projectId = "whatsappclone-62617",
                        gcmSenderId = "262199812612"
                    )
                )
            }
    }


    @Test
    fun `esrsr rs`() = runBlocking {


        assertTrue(1 > 0)
    }
}