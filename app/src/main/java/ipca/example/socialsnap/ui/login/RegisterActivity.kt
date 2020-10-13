package ipca.example.socialsnap.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import ipca.example.socialsnap.MainActivity
import ipca.example.socialsnap.R
import kotlinx.android.synthetic.main.activity_register_activity.*

class RegisterActivity : AppCompatActivity() {


    companion object {
        val TAG = "LoginActivity"
    }

    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_activity)

        auth = Firebase.auth

        buttonRegister.setOnClickListener {
            auth.createUserWithEmailAndPassword(
                editTextName.text.toString(),
                editTextPassword.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        val user = auth.currentUser
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()

                    }
                }
        }

    }
}