package com.example.sse.interfragmentcommratingbar;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.lang.reflect.Field;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShiftFragment extends Fragment {

    private Button btnLeft;
    private Button btnRight;
    private  int currDrawableIndex;
    ArrayList<Drawable> drawables;


    public ShiftFragment() {
        // Required empty public constructor
    }


    /* Passing message */

    public interface ShiftFragmentListener {
        public void shiftImage(int d);
    }

    ShiftFragmentListener CFL;

    public void onAttach(Context context) {
        super.onAttach(context);
        CFL = (ShiftFragmentListener) context;  //context is a handle to the main activity, let's bind it to our interface.
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_shift, container, false);

        btnRight = (Button) v.findViewById(R.id.btnRight);
        btnLeft = (Button) v.findViewById(R.id.btnLeft);
        currDrawableIndex = 0;
        getDrawables();


        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currDrawableIndex == 0)
                    currDrawableIndex = drawables.size() - 1;
                else
                    currDrawableIndex--;
                CFL.shiftImage(currDrawableIndex);
            }
        });

        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currDrawableIndex == drawables.size() - 1)
                    currDrawableIndex = 0;
                else
                    currDrawableIndex++;

                CFL.shiftImage(currDrawableIndex);
            }
        });

        return v;
    }

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
