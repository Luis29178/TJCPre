package com.example.tjcpre

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

 class CustomMapViewAdapter(val context: Context) : BaseAdapter() {

    // region Functions

    // Responsible for grabbing a MapInfoString And retuning Grabber class containing
    // information needed for use in Row Assignment
    private fun StringGrabber(string: String, pos: Int): GrabberReturnClass {
        var tempTitle = ""
        var CharPos = pos
        val mapInfo = string
        var tempChar = ' '

        while(tempChar != '|')
        {
            //Adds Each char to tempTitle
            tempChar = mapInfo[CharPos]
            if (tempChar != '|'){
                tempTitle += mapInfo[CharPos]
            }

            CharPos++
        }

        return GrabberReturnClass(tempTitle, CharPos)
    }



    //endregion


    private data class GrabberReturnClass(val MapInfo: String, var Position: Int)

    private val mContext: Context = context

    private val mapNames = arrayListOf<String>(
        "Customs|30 MIN|9-12|Normal|!",
        "Interchange|40 MIN|10-14|Normal|!",
        "Reserve|40 MIN|9-12|Hard|!",
        "Labs|35 MIN|6-10|Hard|!",
        "Shoreline|45 MIN|10-13|Hard|!",
        "Factory|20-25 MIN|4-6|Easy|!",
        "Woods|35 MIN|9-14|Normal|!",
        "Lighthouse|35 MIN|9-12|Hard|!"
    )
    // responsible for num of rows
    override fun getCount(): Int {
        return mapNames.size
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

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val ListViewInflator = LayoutInflater.from(mContext)
        val Row : View = ListViewInflator.inflate(R.layout.mapviewlistrow, null)

        // Ini block
        val mapTitle = Row.findViewById<TextView>(R.id.mapListView_MapName)
        val mapTimeRange = Row.findViewById<TextView>(R.id.mapListView_TimeTxt)
        val mapDifficulty = Row.findViewById<TextView>(R.id.mapListView_DifficultyTxt)
        val mapPlayerRange = Row.findViewById<TextView>(R.id.mapListView_PlayerRangeTxt)


        val mapInfo = mapNames.get(position)

        var _mapTitle = ""
        var _mapTimeRange = ""
        var _mapPlayerRange = ""
        var _mapDificulty = ""

        var tempChar = 'x'
        var CharPos= 0
        var itemPos = 0
        var stringPos = 0

        // responsible for splitting the string up and assigning strings
        while (CharPos < (mapInfo.length-1)){
            //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            //              On Final Iteration
            //
            //      Customs|30 MIN|9-12|Normal|!
            //                                 ^
            //                              CharPos
            //
            //          Ending Loop
            //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!





            when(stringPos){

                // region MapName
                0 -> {

                    val (tempTitle,returnPos) = StringGrabber(mapInfo, CharPos)
                    CharPos = returnPos

                    stringPos++
                    // moves position of info Item
                    //Customs|30 MIN|9-12|Normal|!
                    //       [------]
                    //       stringPos

                    //sets the title for the row
                    _mapTitle = tempTitle

                }
                // endregion

                // region MapTime
                1 -> {
                    val (tempTime,returnPos) = StringGrabber(mapInfo, CharPos)
                    CharPos = returnPos

                    stringPos++
                    // moves position of info Item
                    //Customs|30 MIN|9-12|Normal|!
                    //              [----]
                    //              stringPos

                    //sets the title for the row
                    _mapTimeRange = tempTime

                }
                //endregion

                // region PlayerRange
                2 -> {
                    val (tempPRange,returnPos) = StringGrabber(mapInfo, CharPos)
                    CharPos = returnPos

                    stringPos++
                    // moves position of info Item
                    //Customs|30 MIN|9-12|Normal|!
                    //                   [------]
                    //                   stringPos

                    //sets the title for the row
                    _mapPlayerRange = tempPRange

                }
                //endregion

                // region Difficulty
                3 -> {
                    val (tempDifficulty,returnPos) = StringGrabber(mapInfo, CharPos)
                    CharPos = returnPos

                    stringPos++
                    // moves position of info Item
                    //Customs|30 MIN|9-12|Normal|!
                    //                   [------]
                    //                   stringPos

                    //sets the title for the row
                    _mapDificulty = tempDifficulty

                }
                //endregion

            }




        }

        //assigns Text based on row to include each map
        mapTitle.text = _mapTitle
        mapTimeRange.text = _mapTimeRange
        mapPlayerRange.text = _mapPlayerRange
        mapDifficulty.text = _mapDificulty



        // Returns the View for the row
        return Row






    }

}
