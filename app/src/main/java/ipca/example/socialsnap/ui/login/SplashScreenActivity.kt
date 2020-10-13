package ipca.example.socialsnap.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import ipca.example.socialsnap.MainActivity
import ipca.example.socialsnap.R

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        auth = Firebase.auth
        val currentUser = auth.currentUser

        currentUser?.let {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }?:run{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
/*
        Handler(Looper.getMainLooper()).postDelayed({

        }, 1000)
*/
    }
}