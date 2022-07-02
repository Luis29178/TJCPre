package com.example.tjcpre

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MapSelectView : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mapselectview)
        val listView = findViewById<ListView>(R.id.MapList)

        listView.adapter = CustumAdapter(this)



    }

    private class CustumAdapter(context: Context) : BaseAdapter() {


        private val mContext: Context = context

        // responsible for num of rows
        override fun getCount(): Int {
            return 8
        }


        //gets Item ID  TODO: add way of getting ItemID if needed
        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        //gets Item  TODO: add way of getting Item if needed
        override fun getItem(position: Int): Any {
            return "Test String"
        }


        //Responsible for rendering each row
        @SuppressLint("SetTextI18n")
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val textView = TextView(mContext)
            textView.text = "Customs"
            return  textView
        }

    }


}