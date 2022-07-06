package com.example.tjcpre

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue

class CustomItemTrackerViewAdapter(val context: Context) : BaseAdapter() {
    private lateinit var itemDatabase : DatabaseReference


    override fun getCount(): Int {
        //TODO: Implement
        return 1000
    }



    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    override fun getItem(position: Int): Any {
        return "Test String"
    }


    //Responsible for rendering each row

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val mContext: Context = context

        val ListViewInflator = LayoutInflater.from(mContext)
        val Row : View  = ListViewInflator.inflate(R.layout.itemtrakerlistobject, null)

        val itemNameTxt = Row.findViewById<TextView>(R.id.ItemTracker_ListItemName)
        val itemImg = Row.findViewById<ImageView>(R.id.ItemTracker_ListItemimg)
        val CountupButton = Row.findViewById<Button>(R.id.ItemTracker_ItemCountPlus)
        val CountDownButton = Row.findViewById<Button>(R.id.ItemTracker_ItemCountMinus)
        val itemCount = Row.findViewById<TextView>(R.id.ItemTracker_ListItemCount)

        itemDatabase = FirebaseDatabase.getInstance().getReference(position.toString())
            itemDatabase.child("Name").get().addOnSuccessListener {

                //TODO: Implement and Initialize other data with databse once the databse has been prepared

                    val itemName = it.value
                // add once images are uploaded to the database
                    //val itemImg = it.child("").value
                    itemNameTxt.text = itemName.toString()



            }







        return Row






    }

}