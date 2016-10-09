package com.example.sse.interfragmentcommratingbar;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DrawableFragment extends Fragment {

    ArrayList<Drawable> drawables;  //keeping track of our images
    private int currDrawableIndex;
    private ImageView imgRateMe;
    private RatingBar rating;
    private int[][] imageRatingCombine;
    private float ratingValue;


    public DrawableFragment() {
        // Required empty public constructor
    }

    public interface DrawableFragmentListener {
        public void sendDrawablesSize();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_drawable, container, false);

        View v = inflater.inflate(R.layout.fragment_drawable, container, false);   //MUST HAPPEN FIRST, otherwise components don't exist.

//getting all drawable resources, handy third party see method below.
        //currDrawableIndex = 0;
        getDrawables();
        imgRateMe = (ImageView) v.findViewById(R.id.imgRateMe);
        rating = (RatingBar) v.findViewById(R.id.ratingBar);
        imageRatingCombine = new int[100][20];
        imgRateMe.setImageDrawable(drawables.get(0));
//        btnRight = (Button) v.findViewById(R.id.btnRight);
//        btnLeft = (Button) v.findViewById(R.id.btnLeft);
//
//
//        btnLeft.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (currDrawableIndex == 0)
//                    currDrawableIndex = drawables.size() - 1;
//                else
//                    currDrawableIndex--;
//                changePicture();
//            }
//        });

//        btnRight.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (currDrawableIndex == drawables.size() - 1)
//                    currDrawableIndex = 0;
//                else
//                    currDrawableIndex++;
//
//                changePicture();
//            }
//        });
        rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingValue = rating;
                imageRatingCombine[currDrawableIndex][0] = (int)ratingValue;
            }
        });


        return v;   //must happen last, it is a return statement after all, it can't happen sooner!
    }


    public void changePicture(int d) {
        currDrawableIndex = d;
        imgRateMe.setImageDrawable(drawables.get(d));

        // Retrieve the ratting value based on the current index
        rating.setRating(imageRatingCombine[d][0]);
        //imageRatingCombine[d][0] = (int)ratingValue;;
    }




    //REF: http://stackoverflow.com/questions/31921927/how-to-get-all-drawable-resources

    public void getDrawables() {
        Field[] drawablesFields = com.example.sse.interfragmentcommratingbar.R.drawable.class.getFields();
        drawables = new ArrayList<>();

        String fieldName;
        for (Field field : drawablesFields) {
            try {
                fieldName = field.getName();
                Log.i("LOG_TAG", "com.your.project.R.drawable." + fieldName);
                if (fieldName.startsWith("animals_"))  //only add drawable resources that have our prefix.
                    drawables.add(getResources().getDrawable(field.getInt(null)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}