package com.example.fotograf_paylasma

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.activity_ana_menu_aktivite.*


class anaMenuAktivite : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database : FirebaseFirestore
    private lateinit var recyclerAdapter: AnaMenuRecyclerAdapter
    var postListesi = ArrayList<Post>()
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ana_menu_aktivite)

        auth = FirebaseAuth.getInstance()
        database = FirebaseFirestore.getInstance()
        verileriAl()
        var layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerAdapter = AnaMenuRecyclerAdapter(postListesi)
        recyclerView.adapter = recyclerAdapter
    }

    fun verileriAl() {

        database.collection("post").orderBy("tarih",
            Query.Direction.DESCENDING).addSnapshotListener { snapshot, exception ->

            if (exception != null) {
                Toast.makeText(this, exception.localizedMessage, Toast.LENGTH_LONG).show()

            }
            else {
                if (snapshot != null) {
                    if (!snapshot.isEmpty) {
                        val documents = snapshot.documents
                        postListesi.clear()
                        for (document in documents) {
                            val kullaniciEmail = document.get("kullaniciemaili") as String
                            val kullaniciYorumu = document.get("kullaniciyorum") as String
                            val gorselUrl = document.get("gorselurl") as String

                            val indirilenPost = Post(kullaniciEmail,kullaniciYorumu,gorselUrl)
                            postListesi.add(indirilenPost)



                        }
                        recyclerAdapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }




    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.secenekler_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.fotograf_paylas)
        {
            val intent = Intent(this,FotografPaylasmaAktivite::class.java)
            startActivity(intent)


        }
        else if(item.itemId == R.id.cikis_yap)
        {
            auth.signOut()
            val intent = Intent(this,girisaktivite::class.java)
            startActivity(intent)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}