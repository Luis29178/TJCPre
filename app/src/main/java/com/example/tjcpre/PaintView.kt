package com.example.tjcpre

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.example.tjcpre.CustomsRaidMode.Companion.brush
import com.example.tjcpre.CustomsRaidMode.Companion.path
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread


class PaintView : View {

        private var params: ViewGroup.LayoutParams? = null
        private var timer = 0






        companion object {
            // TODO: check If yu can use these companion Objects to reset or save paths !!!!!!!!????????
            var PathL = arrayListOf<Path>()
            var ColorL = ArrayList<Int>()
            var CurrBrush = Color.BLACK
            var resettingPath : Boolean ?= null
            var lineDesTime : Long = 1500
            var clear = false
            var isClick = false


        }

        constructor(context: Context) : this(context, null) {
            init()


        }

        constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {
            init()

        }

        constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
        super(context, attrs, defStyleAttr) {
            init()


        }

        private fun init() {
            brush.isAntiAlias = true
            brush.color = CurrBrush
            brush.style = Paint.Style.STROKE
            brush.strokeJoin = Paint.Join.ROUND
            brush.strokeWidth = 4f

            params = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

        }



        @SuppressLint("ClickableViewAccessibility")
        //TODO: Add Clickable method for Desktop Version

        override fun onTouchEvent(event: MotionEvent): Boolean {




            val x = event.x
            val y = event.y





                //TODO: disable paintview actions if in move mode
                if(isClick) {


                    when (event.action) {
                        MotionEvent.ACTION_DOWN -> {
                            if (clear) {
                                resetPath()
                                clear = false
                            }
                            path.moveTo(x, y)
                            return true

                        }
                        MotionEvent.ACTION_MOVE -> {
                            path.lineTo(x, y)
                            PathL.add(path)
                            ColorL.add(CurrBrush)
                        }
                        MotionEvent.ACTION_UP -> {
                            if (clear) {
                                resetPath()
                                clear = false
                            }

                             // replace with sequential delete with bitmap array to save states of the path being drawn to then reset it bit by bit will give smooth transition

                        }
                        else -> return false

                    }
                }



            postInvalidate()        //informs non-ui threads of changes on the UI
            return false

        }

        fun resetPath() {
            /// use bitmaps to save previous states before drawn then use them to switch back and forth to slowly delete the drawn line


            /// temp






            for (i in PathL.indices) {

                // this handler deals with a delay of 1.5 sec per path point
                Handler(Looper.getMainLooper()).postDelayed(
                    {

                        resettingPath = true
                        PathL[i].rewind()
                        invalidate() //informs non-ui threads of changes on the UI
                    },
                    lineDesTime // delay of line Deception
                )


            }
            resettingPath = false




        }

        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)
            if (clear) {
                resetPath()
                clear = false
            }
 

            for (i in PathL.indices) {

                brush.color = ColorL[i]
                canvas.drawPath(PathL[i], brush)
                invalidate() //informs non-ui threads of changes on the UI

            }





        }



}