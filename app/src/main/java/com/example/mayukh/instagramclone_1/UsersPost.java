package com.example.mayukh.instagramclone_1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class UsersPost extends AppCompatActivity {
    private LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_post);

        Intent receivedIntentObject = getIntent();
        final String receivedUserName = receivedIntentObject.getStringExtra("username");
        FancyToast.makeText(UsersPost.this,receivedUserName,FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,true).show();
        linearLayout = findViewById(R.id.linearLayout);
        setTitle(receivedUserName+"'s Posts");

        ParseQuery<ParseObject> parseQuery = new ParseQuery<ParseObject>("Photo");

        parseQuery.whereEqualTo("username",receivedUserName);
        parseQuery.orderByDescending("createdAt");

        final ProgressDialog progressDialog = new ProgressDialog(UsersPost.this);

        progressDialog.setMessage("Loading....");
        progressDialog.show();

        parseQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(objects.size()>0 && e == null)
                {
                  for(ParseObject post : objects){
                      final TextView imageDescription = new TextView(UsersPost.this);
                      imageDescription.setText(post.get("image_des")+"");

                      ParseFile postImage = (ParseFile)post.get("picture");
                      postImage.getDataInBackground(new GetDataCallback() {
                          @Override
                          public void done(byte[] data, ParseException e) {
                              if (data != null && e == null) {
                                  Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                                  ImageView postimageView = new ImageView(UsersPost.this);
                                  LinearLayout.LayoutParams imageView_params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                  imageView_params.setMargins(5, 5, 5, 5);
                                  postimageView.setLayoutParams(imageView_params);
                                  postimageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                                  postimageView.setImageBitmap(bitmap);

                                  if(imageDescription.getText().toString().equals("null")!=true) {
                                      LinearLayout.LayoutParams des_params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                      des_params.setMargins(5, 5, 5, 15);
                                      imageDescription.setLayoutParams(des_params);
                                      imageDescription.setGravity(Gravity.CENTER);
                                      imageDescription.setBackgroundColor(Color.CYAN);
                                      imageDescription.setTextColor(Color.WHITE);
                                      imageDescription.setTextSize(30f);
                                      linearLayout.addView(postimageView);
                                      linearLayout.addView(imageDescription);

                                  }
                                  else{
                                      linearLayout.addView(postimageView);
                                      linearLayout.removeView(imageDescription);
                                  }

                              }
                          }
                      });
                  }


                }
                else{
                    FancyToast.makeText(UsersPost.this,receivedUserName+" doesn't have any posts ",FancyToast.LENGTH_SHORT,FancyToast.INFO,true).show();
                    finish();
                }

                progressDialog.dismiss();
            }
        });

    }
}
