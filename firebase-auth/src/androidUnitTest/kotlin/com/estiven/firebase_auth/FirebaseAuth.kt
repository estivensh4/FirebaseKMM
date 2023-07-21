package com.estiven.firebase_auth

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.estiven.firebase_app.Firebase
import com.estiven.firebase_app.FirebaseOptions
import com.estiven.firebase_app.apps
import com.estiven.firebase_app.initialize
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import kotlin.random.Random
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

//@RunWith(AndroidJUnit4::class)
@RunWith(RobolectricTestRunner::class)
class FirebaseAuth
//: TestsWithMocks()
{

    val context = InstrumentationRegistry.getInstrumentation().targetContext
    val emulatorHost = "10.0.2.2"
    @BeforeTest
    fun initializeFirebase() {
        /*Firebase
            .takeIf { Firebase.apps(context).isEmpty() }
            ?.apply {
                initialize(
                    context,
                    FirebaseOptions(
                        applicationId = "1:828388273940:android:2ced50e8fc76115e959c01",
                        apiKey = "AIzaSyBZ0GNxhzDr8vzMjnA0CGk-CX9TOz27nng",
                        databaseUrl = "https://telegramclonetest-default-rtdb.firebaseio.com",
                        storageBucket = "telegramclonetest.appspot.com",
                        projectId = "telegramclonetest",
                        gcmSenderId = "828388273940"
                    )
                )
                Firebase.auth.useEmulator(emulatorHost, 9099)
            }*/
        /*if (Firebase.apps(context).isEmpty()){
            Firebase.initialize(
                context,
                FirebaseOptions(
                    applicationId = "1:262199812612:android:c951ea2618e1382c960b51",
                    apiKey = "AIzaSyCch8ysaZVf4yO_3IbczDDl5AeZp_sORWk",
                    databaseUrl = "https://whatsappclone-62617-default-rtdb.firebaseio.com",
                    storageBucket = "whatsappclone-62617.appspot.com",
                    projectId = "whatsappclone-62617",
                    gcmSenderId = "262199812612"
                ),
                "AppTest"
            )
        }*/
    }

    @Test
    fun testCreateUserWithEmailAndPassword() = runBlocking {

        val email = "test+${Random.nextInt(100000)}@test.com"

        Firebase
            .takeIf { Firebase.apps(context).isEmpty() }
            ?.apply {
                initialize(
                    context,
                    FirebaseOptions(
                        applicationId = "1:828388273940:android:2ced50e8fc76115e959c011",
                        apiKey = "AIzaSyBZ0GNxhzDr8vzMjnA0CGk-CX9TOz27nng1",
                        databaseUrl = "https://telegramclonetest-default-rtdb.firebaseio.com1",
                        storageBucket = "telegramclonetest.appspot.com1",
                        projectId = "telegramclonetest1",
                        gcmSenderId = "8283882739401"
                    )
                )
                Firebase.auth.useEmulator(emulatorHost, 9099)
            }

        coEvery {
            Firebase.auth.createUserWithEmailAndPassword(email, "12345678")
        } returns mockk()

        val createResult = Firebase.auth.currentUser


        assertNotEquals(true, createResult == null)

        //val signInResult = Firebase.auth.signInWithEmailAndPassword(email, "test123")
        //assertEquals(createResult.user?.uid, signInResult.user?.uid)

        //signInResult.user!!.delete()
    }


    @Test
    fun x() {
        assertEquals("1", "1")
    }
}