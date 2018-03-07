package com.example.mario.GeoLoco;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class DialogAddStory extends AppCompatDialogFragment {
    ImageButton imageButton;
    private static final int PICK_IMAGE = 1;
    Uri imageUri;
    ImageView imageView;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_dialog_add_story, null);

        builder.setView(view)
                .setTitle("Tell us your story")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                })
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //TODO:open new activity here
                        System.out.println("You clicked me");
                    }
                });
        imageView = view.findViewById(R.id.dialog_story_image);
        if (imageView.getDrawable() == null) {
            imageView.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
        }
        imageButton = view.findViewById(R.id.dialog_button_image);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGalery();
            }
        });

        return builder.create();
    }

    private void openGalery(){
        Intent galery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(galery, PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE){
            imageView.setLayoutParams(new LinearLayout.LayoutParams(600,400));
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
    }
}
