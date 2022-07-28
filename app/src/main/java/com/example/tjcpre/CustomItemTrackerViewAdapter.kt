package com.example.tjcpre

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.firebase.database.*


class CustomItemTrackerViewAdapter(val context: Context) : BaseAdapter() {
    private lateinit var itemDatabase : DatabaseReference





    // Will return Count of Items in Database Based on Item "0"'s Info
    override fun getCount(): Int {

        return 2158
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
        val CountUpButton = Row.findViewById<Button>(R.id.ItemTracker_ItemCountPlus)
        val CountDownButton =  Row.findViewById<Button>(R.id.ItemTracker_ItemCountMinus)
        val CountText = Row.findViewById<TextView>(R.id.ItemTracker_ListItemCount)
        val InfoButton =  Row.findViewById<ImageView>(R.id.ItemTracker_ItemInfo)


        CountUpButton.setOnClickListener{
            var temp = CountText.text.toString().toInt()
            temp++
            CountText.text = temp.toString()
        }
        CountDownButton.setOnClickListener{
            var temp = CountText.text.toString().toInt()
            temp--
            CountText.text = temp.toString()
        }
       InfoButton.setOnClickListener{
            var intent = Intent(mContext,ItemInfoView::class.java)
            intent.putExtra("itemName", itemNameTxt.text.toString())
            mContext.startActivity(intent)
        }


        itemDatabase = FirebaseDatabase.getInstance().getReference(((position +1).toString()))
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