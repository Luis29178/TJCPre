package com.example.tjcpre

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.example.tjcpre.TJCPHome.Companion.UID
import com.example.tjcpre.MapSelectView.Companion.mapString
import com.example.tjcpre.MapSelectView.Companion.pathCount


class PathListAdapter(val context: Context) : BaseAdapter() {


    override fun getCount(): Int {
        return pathCount
    }

    override fun getItem(position: Int): Any {
        return "Implement If Nedded"
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val mContext: Context = context
        val ListViewInflator = LayoutInflater.from(mContext)
        val Row : View  = ListViewInflator.inflate(R.layout.path_list_object, null)

        val Pathname = Row.findViewById<TextView>(R.id.PathNameView)

        var dataBase =  Firebase.firestore
        var CustomsPathred = dataBase.document("testUsers/${UID}/Paths/${mapString}Names").get()
        CustomsPathred.addOnSuccessListener { it ->
            var pos = position.toString()
            var path = it.get(pos)
            Pathname.text = path.toString()
        }



        return Row
    }


}