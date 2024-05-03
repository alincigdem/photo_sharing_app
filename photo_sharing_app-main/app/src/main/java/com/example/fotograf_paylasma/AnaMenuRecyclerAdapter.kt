package com.example.fotograf_paylasma

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recycler_row.view.*

class AnaMenuRecyclerAdapter(val postlist : ArrayList<Post>): RecyclerView.Adapter<AnaMenuRecyclerAdapter.PostHolder>() {
    class PostHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycler_row,parent,false)
        return PostHolder(view)
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        holder.itemView.recycler_row_kullanici_email.text = postlist[position].kullaniciEmail
        holder.itemView.recycler_row_kullanici_yorum.text = postlist[position].kullaniciYorumu
        Picasso.get().load(postlist[position].gorselUrl).into(holder.itemView.recycler_row_gorsel)
    }

    override fun getItemCount(): Int {
        return postlist.size
    }
}