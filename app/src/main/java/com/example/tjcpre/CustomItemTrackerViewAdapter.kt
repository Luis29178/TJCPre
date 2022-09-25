package com.example.tjcpre

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.firebase.database.*
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.example.tjcpre.TJCPHome.Companion.UID
import com.google.firebase.firestore.DocumentReference


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
        val itemImg = Row.findViewById<ImageView>(R.id.ItemTracker_ListItemimg) // for use when database contains all images needed
        val CountUpButton = Row.findViewById<Button>(R.id.ItemTracker_ItemCountPlus)
        val CountDownButton =  Row.findViewById<Button>(R.id.ItemTracker_ItemCountMinus)
        val CountText = Row.findViewById<TextView>(R.id.ItemTracker_ListItemCount)
        val InfoButton =  Row.findViewById<ImageView>(R.id.ItemTracker_ItemInfo)


        CountUpButton.setOnClickListener{
            var temp = CountText.text.toString().toInt()
            temp++
            CountText.text = temp.toString()


            val dataBase = Firebase.firestore

            var UserItemDoc: DocumentReference
            if (UID == "") {
                UserItemDoc = dataBase.document("testUsers/Uid")

            }
            else{
                UserItemDoc = dataBase.document("testUsers/$UID")
            }

            //                Simulates Format In FireStore
            var UploadData : MutableMap<String,Any> = HashMap()

            val UploadValue : Int = temp
            UploadData[itemNameTxt.text.toString()] = UploadValue
            UserItemDoc.set(UploadData, SetOptions.merge())


        }
        CountDownButton.setOnClickListener{
            var temp = CountText.text.toString().toInt()
            temp--
            CountText.text = temp.toString()

            val dataBase = Firebase.firestore

            var UserItemDoc: DocumentReference
            if (UID == "") {
                UserItemDoc = dataBase.document("testUsers/Uid")
            }
            else{
                UserItemDoc = dataBase.document("testUsers/$UID")
            }
            //                Simulates Format In FireStore
            var UploadData : MutableMap<String,Any> = HashMap()

            val UploadValue : Int = temp
            UploadData[itemNameTxt.text.toString()] = UploadValue
            UserItemDoc.set(UploadData, SetOptions.merge())
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
                if(UID != "") {
                    Firebase.firestore.collection("testUsers").document("${UID}")
                        .get().addOnSuccessListener {
                            if (!it.contains(itemName.toString())) {
                                val dataBase = Firebase.firestore
                                val UserItemDoc = dataBase.document("testUsers/${UID}")

                                var UploadData: MutableMap<String, Any> = HashMap()
                                var tempname = itemName.toString()

                                UploadData[tempname] = 0
                                UserItemDoc.set(UploadData, SetOptions.merge())

                            } else {
                                val count = it.get(itemName.toString())
                                CountText.text = count.toString()


                            }
                        }

                }

            }

        return Row
    }



}