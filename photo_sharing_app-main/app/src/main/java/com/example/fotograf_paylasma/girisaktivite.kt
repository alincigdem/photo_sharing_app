package com.example.fotograf_paylasma

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.fotograf_paylasma.R
import com.example.fotograf_paylasma.anaMenuAktivite
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.girisaktivite.*
class girisaktivite : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.girisaktivite)


        auth = FirebaseAuth.getInstance()


        val guncelKullanici = auth.currentUser

        if (guncelKullanici != null)
        {

            val intent = Intent(this, anaMenuAktivite::class.java)
            startActivity(intent)
            finish()
        }


    }

    fun girisyap(view: View) {


        auth.signInWithEmailAndPassword(emailText.text.toString(), passwordText.text.toString())
            .addOnCompleteListener { task ->

                if (task.isSuccessful) {
                    val guncelKullanici = auth.currentUser?.email.toString()
                    Toast.makeText(this, "Hoşgeldin:${guncelKullanici}", Toast.LENGTH_LONG)
                        .show()

                    val intent = Intent(this, anaMenuAktivite::class.java)
                    startActivity(intent)
                    finish()
                }
            }


    }

    fun kayitol(view: View) {
        val email = emailText.text.toString()
        val sifre = passwordText.text.toString()
        auth.createUserWithEmailAndPassword(email, sifre).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val intent = Intent(this, anaMenuAktivite::class.java)
                startActivity(intent)
                finish()
            }
        }
            .addOnFailureListener { exception ->
                Toast.makeText(
                    applicationContext,
                    exception.localizedMessage,
                    Toast.LENGTH_LONG
                ).show()
            }
    }

}

