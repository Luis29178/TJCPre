package com.example.tjcpre

import android.app.AlertDialog
import android.content.pm.ActivityInfo
import android.graphics.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tjcpre.MapSelectView.Companion.SelectPath
import com.example.tjcpre.MapSelectView.Companion.pathUri
import com.example.tjcpre.PaintView.Companion.clear
import com.example.tjcpre.PaintView.Companion.isClick
import com.example.tjcpre.TJCPHome.Companion.LoggedIn
import com.example.tjcpre.TJCPHome.Companion.UID
import com.example.tjcpre.TJCPHome.Companion.inPaths
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream



class CustomsRaidMode : AppCompatActivity(){

    companion object {
        var path = Path()
        var brush = Paint()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.customs_raid_mode)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)

        var EditmodeButton : Button = findViewById(R.id.EditMode)
        var clearButton : Button = findViewById(R.id.Customs_Clear)
        var UploadButtton : Button = findViewById(R.id.UploadButton)


        if(!inPaths || !LoggedIn) {
            UploadButtton.visibility = View.GONE

        }
        //Path Mode Logic
        else
        {
            UploadButtton.visibility = View.VISIBLE
            UploadButtton.setOnClickListener {

                UploadButtton.visibility = View.INVISIBLE
                EditmodeButton.visibility = View.INVISIBLE
                clearButton.visibility = View.INVISIBLE


                //clips screen
                val ScreenView : View = window.decorView.rootView
                var ScreenBitmap : Bitmap = createBitmapFromView(ScreenView)


                //converts screen grab to PNG
                val bos = ByteArrayOutputStream()
                ScreenBitmap.compress(Bitmap.CompressFormat.PNG, 0, bos)
                var ScreenData = bos.toByteArray()


                //Builds AlertDialog Toast
                val builder = AlertDialog.Builder(this)
                val inflater : LayoutInflater = layoutInflater
                val dialogLayout = inflater.inflate(R.layout.upload_toast, null)
                val editText = dialogLayout.findViewById<EditText  >(R.id.UPathName)
                var uploaded : Boolean = false

                //Dialog for Upload name and Uploads img
                with(builder) {
                    setTitle("Enter the Paths Name")
                    setPositiveButton("OK"){dialog, which ->
                        var name = editText.text.toString()


                        val storageRef = FirebaseStorage.getInstance().reference
                        val pathsRef = storageRef.child("Paths/${UID}/Customs/${name}.PNG")



                        var uploadTask = pathsRef.putBytes(ScreenData)
                        uploadTask.addOnFailureListener{
                            uploaded = false
                        }.addOnSuccessListener {
                            uploaded = true
                            var count = 0
                            Firebase.firestore.collection("testUsers").document("${UID}")
                                .collection("Paths").document("Customs")
                                .get().addOnSuccessListener{
                                    if (!it.contains("count")){
                                        val dataBase = Firebase.firestore
                                        val UserItemDoc = dataBase.document("testUsers/${UID}/Paths/Customs")
                                        var UploadData : MutableMap<String,Any> = HashMap()
                                        UploadData["count"] = 1
                                        count = 1
                                        UserItemDoc.set(UploadData, SetOptions.merge())
                                    }
                                    else  {
                                        var temp = it.get("count").toString().toInt()
                                        temp++
                                        count = temp
                                        val dataBase = Firebase.firestore
                                        val UserItemDoc = dataBase.document("testUsers/${UID}/Paths/Customs")
                                        var UploadData : MutableMap<String,Any> = HashMap()
                                        UploadData["count"] = temp
                                        UserItemDoc.set(UploadData, SetOptions.merge())
                                    }


                                }

                            val dataBase = Firebase.firestore
                            val UserItemDoc = dataBase.document("testUsers/${UID}/Paths/Customs")
                            var UploadData : MutableMap<String,Any> = HashMap()
                            UploadData[name] = "Paths/${UID}/Customs/${name}.PNG"
                            UserItemDoc.set(UploadData, SetOptions.merge())

                            val pathnamedoc = dataBase.document("testUsers/${UID}/Paths/CustomsNames")
                            var pathData : MutableMap<String,Any> = HashMap()
                            pathData[count.toString()] = name
                            pathnamedoc.set(pathData, SetOptions.merge())


                        }
                        uploadTask.addOnCompleteListener{
                            if (!uploaded){
                                Toast.makeText(this.context, "Upload Failed", Toast.LENGTH_SHORT).show()
                            }
                        }


                        UploadButtton.visibility = View.VISIBLE
                        EditmodeButton.visibility = View.VISIBLE
                        clearButton.visibility = View.VISIBLE

                    }
                    setNegativeButton("Cancel"){dialog, which->

                    }
                    setView(dialogLayout)
                    show()
                }


            }
        }



        var CustomsMap : ZoomableImage = findViewById(R.id.CustomsMap)
        if (SelectPath){
            // download image from storage and set to the bacground
            val storageRef = FirebaseStorage.getInstance().reference
            val pathsRef = storageRef.child(pathUri)

            pathsRef.getBytes(5242880*2) // 10mb max size download
                .addOnSuccessListener { bytes ->
                    var bitmap :Bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.size)
                    CustomsMap.setImageBitmap(bitmap)


                }

        }
        else {
            CustomsMap.setImageBitmap(BitmapFactory.decodeResource(resources,R.drawable.custumsmapog))
        }
        CustomsMap.isClickable = false

        var paintView: View = findViewById(R.id.Internal_Paintviewer)
        isClick = true

        EditmodeButton.setOnClickListener(){

            isClick = !isClick
            clear = true

            CustomsMap.isClickable = !(paintView.isClickable)
            if (isClick) {
                EditmodeButton.text = "Draw Mode"
            }
            else {
                EditmodeButton.text = "Zoom Mode"
            }

        }

        clearButton.setOnClickListener(){
            clear = true
        }





    }


    fun createBitmapFromView(view: View): Bitmap {
        if (view.width > 0 && view.height > 0) {
            view.measure(
                View.MeasureSpec.makeMeasureSpec(
                    view.width, View.MeasureSpec.EXACTLY
                ),
                View.MeasureSpec.makeMeasureSpec(
                    view.height, View.MeasureSpec.EXACTLY
                )
            )
        }
        view.layout(0, 0, view.measuredWidth, view.measuredHeight)
        val bitmap = Bitmap.createBitmap(
            view.measuredWidth,
            view.measuredHeight, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        val background = view.background
        background?.draw(canvas)
        view.draw(canvas)
        return bitmap
    }




}

