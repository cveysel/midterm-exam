package com.example.vize

import android.content.Context
import android.widget.ArrayAdapter
//listviewde gözükmesi için yemeğin adını alıyoruz
class YemekAdapter(context: Context, private val yemekListesi: MutableList<Yemek>) :
    ArrayAdapter<Yemek>(context, android.R.layout.simple_list_item_1, yemekListesi) {

    override fun getView(position: Int, convertView: android.view.View?, parent: android.view.ViewGroup): android.view.View {
        val inflater = android.view.LayoutInflater.from(context)
        val view = convertView ?: inflater.inflate(android.R.layout.simple_list_item_1, parent, false)

        // TextView de sadece yemek adının görüneceği şekilde ayarlıyoruz
        val yemekAdi = view.findViewById<android.widget.TextView>(android.R.id.text1)
        yemekAdi.text = yemekListesi[position].ad

        return view
    }
}

