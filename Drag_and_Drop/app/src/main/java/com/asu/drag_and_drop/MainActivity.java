package com.asu.drag_and_drop;

import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener, View.OnDragListener{


    // Cat keeps the Layout params. To move the cat anywhere add a new Layout params
    // The cat has marginBottom =10dp and alignParentBottom. Thats why it keeps the same
    // layout params in the new draggable area.

    private ImageView draggableImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        draggableImage = findViewById(R.id.draggableImage);
        addTags();
        addListeners();
    }

    // Imp thing Add drag listener to both Draggable content and Draggable Area
    private void addListeners(){
        draggableImage.setOnLongClickListener(this);
        draggableImage.setOnLongClickListener(this);
        findViewById(R.id.draggableArea).setOnDragListener(this);
    }

    private void addTags(){

        draggableImage.setTag("plus");

    }


    @Override
    public boolean onDrag(View v, DragEvent event) {

        int action = event.getAction();

        switch (action) {

            case DragEvent.ACTION_DRAG_STARTED:

                if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {

                    return true;
                }

                return false;

            case DragEvent.ACTION_DRAG_ENTERED:

                v.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
                v.invalidate();
                return true;

            case DragEvent.ACTION_DRAG_LOCATION:
                return true;

            case DragEvent.ACTION_DRAG_EXITED:

                v.getBackground().clearColorFilter();
                v.invalidate();
                return true;

            case DragEvent.ACTION_DROP:

                dropKitty(v, event);

                return true;

            case DragEvent.ACTION_DRAG_ENDED:

                v.getBackground().clearColorFilter();

                v.invalidate();

                if (event.getResult())
                    Toast.makeText(this, "The drop was handled.", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, "The drop didn't work.", Toast.LENGTH_SHORT).show();

                return true;

            default:
                Log.e("DragDrop Example", "Unknown action type received by OnDragListener.");
                break;
        }
        return false;


    }

    @Override
    public boolean onLongClick(View v) {

        ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());

        String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
        ClipData data = new ClipData(v.getTag().toString(), mimeTypes, item);
        View.DragShadowBuilder dragshadow = new View.DragShadowBuilder(v);

        v.startDrag(data
                , dragshadow
                , v
                , 0
        );

        return true;
    }

    // drop happens with the Layout params
    public void dropKitty(View v, DragEvent event){

        ClipData.Item item = event.getClipData().getItemAt(0);
        String dragData = item.getText().toString();
        Toast.makeText(this, "Dragged data is " + dragData, Toast.LENGTH_SHORT).show();
        v.getBackground().clearColorFilter();
        v.invalidate();

        View vw = (View) event.getLocalState();
        ViewGroup owner = (ViewGroup) vw.getParent();
        owner.removeView(draggableImage);
        RelativeLayout container = (RelativeLayout) v;
        container.addView(draggableImage);
        vw.setVisibility(View.VISIBLE);

    }
}
