package com.example.tjcpre;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import java.util.HashMap;
import java.util.Map;


public class ZoomableImage extends androidx.appcompat.widget.AppCompatImageView
{
    Matrix matrix = new Matrix();

    static final int IMAGE_VIEW_MODE_DONE = 0;
    static final int IMAGE_VIEW_MODE_DRAG = 1;
    static final int IMAGE_VIEW_MODE_ZOOM = 2;
    static final int IMAGE_VIEW_MODE_CLICK = 3; // this value determines the distance between clicks for Zoom mode
    int mode = IMAGE_VIEW_MODE_DONE;

    PointF last = new PointF();
    PointF start = new PointF();
    float minScale = 1f;
    float maxScale = 4f;
    float[] m;

    float redundantXSpace;
    float redundantYSpace;
    float width;
    float height;

    float saveScale = 1f;


    float right;
    float bottom;
    float origWidth;
    float origHeight;
    float bmWidth;
    float bmHeight;


    ScaleGestureDetector mScaleDetector;
    Context context;



    public ZoomableImage(Context context, AttributeSet attr)
    {
        super(context, attr);
        //Sets Clickable to true Witch allows Interactions with the object
        super.setClickable(true);
        this.context = context;
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
        //sets the size of each translation tic
        matrix.setTranslate(1f, 1f);
        m = new float[9];
        setImageMatrix(matrix);
        setScaleType(ScaleType.MATRIX);


            setOnTouchListener((v, event) -> {
                mScaleDetector.onTouchEvent(event);

                matrix.getValues(m);
                float x = m[Matrix.MTRANS_X];
                float y = m[Matrix.MTRANS_Y];
                PointF curr = new PointF(event.getX(), event.getY());



                switch (event.getAction()) {
                    //when one finger is touching
                    //set the mode to IMAGE_VIEW_MODE_DRAG
                    //TODO: Add Method of including paintView or share a mode That would prevent actoins from overlaping

                    case MotionEvent.ACTION_DOWN:
                        last.set(event.getX(), event.getY());
                        start.set(last);
                        mode = IMAGE_VIEW_MODE_DRAG;
                        break;
                    //when two fingers are touching
                    //set the mode to IMAGE_VIEW_MODE_ZOOM and sets pointers to get scale
                    case MotionEvent.ACTION_POINTER_DOWN:
                        last.set(event.getX(), event.getY());
                        start.set(last);
                        mode = IMAGE_VIEW_MODE_ZOOM;
                        break;
                    //when a finger moves
                    //If mode is applicable move image
                    case MotionEvent.ACTION_MOVE:
                        //if the mode is IMAGE_VIEW_MODE_ZOOM or
                        //if the mode is IMAGE_VIEW_MODE_DRAG and already zoomed
                        if (mode == IMAGE_VIEW_MODE_ZOOM || (mode == IMAGE_VIEW_MODE_DRAG && saveScale > minScale)) {
                            float deltaX = curr.x - last.x;// x difference
                            float deltaY = curr.y - last.y;// y difference
                            float scaleWidth = Math.round(origWidth * saveScale);// width after applying current scale
                            float scaleHeight = Math.round(origHeight * saveScale);// height after applying current scale
                            //if scaleWidth is smaller than the views width in
                            //other words if the image width fits in the view
                            //limit left and right movement
                            if (scaleWidth < width) {
                                deltaX = 0;
                                if (y + deltaY > 0)
                                    deltaY = -y;
                                else if (y + deltaY < -bottom)
                                    deltaY = -(y + bottom);
                            }
                            //if scaleHeight is smaller than the views height
                            //in other words if the image height fits in the view
                            //limit up and down movement
                            else if (scaleHeight < height) {
                                deltaY = 0;
                                if (x + deltaX > 0)
                                    deltaX = -x;
                                else if (x + deltaX < -right)
                                    deltaX = -(x + right);
                            }
                            //if the image doesnt fit in the width or height
                            //limit both up and down and left and right
                            else {
                                if (x + deltaX > 0)
                                    deltaX = -x;
                                else if (x + deltaX < -right)
                                    deltaX = -(x + right);

                                if (y + deltaY > 0)
                                    deltaY = -y;
                                else if (y + deltaY < -bottom)
                                    deltaY = -(y + bottom);
                            }
                            //move the image with the matrix
                            matrix.postTranslate(deltaX, deltaY);
                            //set the last touch location to the current
                            last.set(curr.x, curr.y);
                        }
                        break;
                    //first finger is lifted
                    case MotionEvent.ACTION_UP:
                        mode = IMAGE_VIEW_MODE_DONE;
                        int xDiff = (int) Math.abs(curr.x - start.x);
                        int yDiff = (int) Math.abs(curr.y - start.y);
                        //Helps user perform ACTION_POINTER_DOWN
                        if (xDiff < IMAGE_VIEW_MODE_CLICK && yDiff < IMAGE_VIEW_MODE_CLICK)
                            performClick();
                        break;
                    // second finger is lifted
                    case MotionEvent.ACTION_POINTER_UP:
                        mode = IMAGE_VIEW_MODE_DONE;
                        break;
                }
                setImageMatrix(matrix);
                invalidate();
                return true;
            });





    }


    public ZoomableImage(Context context) {
        super(context);
    }

    @Override
    public void setImageBitmap(Bitmap bm)
    {
        super.setImageBitmap(bm);
        bmWidth = bm.getWidth();
        bmHeight = bm.getHeight();
    }
    //TODO: (Special TODO Notice) Use setMaxZoom From ZoomableImageClass
    public void setMaxZoom(float x)
    {
        maxScale = x;
    }

    //Internal class that serves the purpose of Listening and obtaining the scale of the Pinch
    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener
    {
        //Will Set Mode for Move Action To scale Image
        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector)
        {
            mode = IMAGE_VIEW_MODE_ZOOM;
            return true;
        }

        @Override
        public boolean onScale(ScaleGestureDetector detector)
        {
            float mScaleFactor = detector.getScaleFactor();
            float origScale = saveScale;
            saveScale *= mScaleFactor;
            if (saveScale > maxScale)
            {
                saveScale = maxScale;
                mScaleFactor = maxScale / origScale;
            }
            else if (saveScale < minScale)
            {
                saveScale = minScale;
                mScaleFactor = minScale / origScale;
            }
            right = width * saveScale - width - (2 * redundantXSpace * saveScale);
            bottom = height * saveScale - height - (2 * redundantYSpace * saveScale);
            if (origWidth * saveScale <= width || origHeight * saveScale <= height)
            {
                matrix.postScale(mScaleFactor, mScaleFactor, width / 2, height / 2);
                if (mScaleFactor < 1)
                {
                    matrix.getValues(m);
                    float x = m[Matrix.MTRANS_X];
                    float y = m[Matrix.MTRANS_Y];
                    if (mScaleFactor < 1)
                    {
                        if (Math.round(origWidth * saveScale) < width)
                        {
                            if (y < -bottom)
                                matrix.postTranslate(0, -(y + bottom));
                            else if (y > 0)
                                matrix.postTranslate(0, -y);
                        }
                        else
                        {
                            if (x < -right)
                                matrix.postTranslate(-(x + right), 0);
                            else if (x > 0)
                                matrix.postTranslate(-x, 0);
                        }
                    }
                }
            }
            else
            {
                matrix.postScale(mScaleFactor, mScaleFactor, detector.getFocusX(), detector.getFocusY());
                matrix.getValues(m);
                float x = m[Matrix.MTRANS_X];
                float y = m[Matrix.MTRANS_Y];
                if (mScaleFactor < 1) {
                    if (x < -right)
                        matrix.postTranslate(-(x + right), 0);
                    else if (x > 0)
                        matrix.postTranslate(-x, 0);
                    if (y < -bottom)
                        matrix.postTranslate(0, -(y + bottom));
                    else if (y > 0)
                        matrix.postTranslate(0, -y);
                }
            }

            FirebaseFirestore dataBase = FirebaseFirestore.getInstance();
            DocumentReference RaidDoc = dataBase.document("RaidData/InstanceID");


            Map<String,Float> UploadFocusX = new HashMap<>();
            UploadFocusX.put("FocusX",detector.getFocusX());
            RaidDoc.set(UploadFocusX, SetOptions.merge());

            Map<String,Float> UploadFocusY = new HashMap<>();
            UploadFocusY.put("FocusY",detector.getFocusY());
            RaidDoc.set(UploadFocusY, SetOptions.merge());

            Map<String,Float> Uploadright = new HashMap<>();
            Uploadright.put("right",right);
            RaidDoc.set(Uploadright, SetOptions.merge());

            Map<String,Float> Uploadbottom = new HashMap<>();
            Uploadbottom.put("bottom",bottom);
            RaidDoc.set(Uploadbottom, SetOptions.merge());

            Map<String,Float> UploadmScaleFactor = new HashMap<>();
            UploadmScaleFactor.put("mScaleFactor",mScaleFactor);
            RaidDoc.set(UploadmScaleFactor, SetOptions.merge());




            return true;
        }
    }


    // this function is responsible for fitting the image to the screen

    // use this function to share the paramters for changes in other users devices
    // must send scaleX, scaleY, redundantYSpace, redundantXSpace, width, height
    @Override
    protected void onMeasure (int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        //Fit to screen.
        float scale;
        float scaleX =  width / bmWidth;
        float scaleY = height / bmHeight;
        scale = Math.min(scaleX, scaleY);
        matrix.setScale(scale, scale);
        setImageMatrix(matrix);
        saveScale = 1f;


        // Center the image
        redundantYSpace = height - (scale * bmHeight) ;
        redundantXSpace = width - (scale * bmWidth);
        redundantYSpace /= 2;
        redundantXSpace /= 2;

        matrix.postTranslate(redundantXSpace, redundantYSpace);

        origWidth = width - 2 * redundantXSpace;
        origHeight = height - 2 * redundantYSpace;
        right = width * saveScale - width - (2 * redundantXSpace * saveScale);
        bottom = height * saveScale - height - (2 * redundantYSpace * saveScale);

        //FirebaseFirestore dataBase = FirebaseFirestore.getInstance();
        //DocumentReference UserItemDoc = dataBase.document("RaidData/InstanceID");
        // Uploads Data of image On the initial call
       /*

       Map<String,Float> UploadWidth = new HashMap<>();
        UploadWidth.put("width",width);
        UserItemDoc.set(UploadWidth, SetOptions.merge());

        Map<String,Float> UploadHeight = new HashMap<>();
        UploadHeight.put("height",height);
        UserItemDoc.set(UploadHeight, SetOptions.merge());

        Map<String,Float> UploadscaleX = new HashMap<>();
        UploadscaleX.put("scaleX",scaleX);
        UserItemDoc.set(UploadscaleX, SetOptions.merge());

        Map<String,Float> UploadscaleY = new HashMap<>();
        UploadscaleY.put("scaleY",scaleY);
        UserItemDoc.set(UploadscaleY, SetOptions.merge());

        Map<String,Float> UploadredundantYSpace = new HashMap<>();
        UploadredundantYSpace.put("redundantYSpace",redundantYSpace);
        UserItemDoc.set(UploadredundantYSpace, SetOptions.merge());

        Map<String,Float> UploadredundantXSpace = new HashMap<>();
        UploadredundantXSpace.put("redundantXSpace",redundantXSpace);
        UserItemDoc.set(UploadredundantXSpace, SetOptions.merge());
        */


        setImageMatrix(matrix);
    }
}
