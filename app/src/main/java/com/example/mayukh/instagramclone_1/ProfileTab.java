package com.example.mayukh.instagramclone_1;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileTab extends Fragment {

    EditText edtProfileName,edtProfileBio,edtProfileHobbies,edtProfileProfession,edtProfileFavSport;
    Button btnUpdateInfo;
    public ProfileTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_tab, container, false);
        edtProfileName = view.findViewById(R.id.edtProfileName);
        edtProfileBio = view.findViewById(R.id.edtProfileBio);
        edtProfileHobbies = view.findViewById(R.id.edtProfileHobbies);
        edtProfileProfession = view.findViewById(R.id.edtProfileProfession);
        edtProfileFavSport = view.findViewById(R.id.edtProfileFavSport);

        btnUpdateInfo = view.findViewById(R.id.btnUpdateInfo);
        final ParseUser appUser = ParseUser.getCurrentUser();
        retainInfo(appUser);
        btnUpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                appUser.put("profileName",edtProfileName.getText().toString());
                appUser.put("profileBio",edtProfileBio.getText().toString());
                appUser.put("profileHobbies",edtProfileHobbies.getText().toString());
                appUser.put("profileProfession",edtProfileProfession.getText().toString());
                appUser.put("profileFavouriteSport",edtProfileFavSport.getText().toString());

                final ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setMessage("Updating Info.....");
                progressDialog.show();
                appUser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e == null){
                            FancyToast.makeText(getContext(),"Info Updated",
                                    FancyToast.LENGTH_SHORT,FancyToast.INFO,true).show();
                        }
                        else{
                            FancyToast.makeText(getContext(),e.getMessage(),
                                    FancyToast.LENGTH_SHORT,FancyToast.ERROR,true).show();
                        }
                        progressDialog.dismiss();

                    }
                });
            }
        });



        return view;
    }

    private void retainInfo(ParseUser user)
    {

        //if any one of field is null we set the field to empty if we don't do this then it will show null
        if(user.get("profileName") == null){
            edtProfileName.setText("");
        }
        //retaining the info even after logging out
        else
        {
            edtProfileName.setText(user.get("profileName").toString());
        }
        if(user.get("profileBio") == null){
            edtProfileBio.setText("");
        }
        else
        {
            edtProfileBio.setText(user.get("profileBio").toString());
        }
        if(user.get("profileHobbies") == null){
            edtProfileHobbies.setText("");
        }
        else
        {
            edtProfileHobbies.setText(user.get("profileHobbies").toString());
        }
        if(user.get("profileProfession") == null){
            edtProfileProfession.setText("");
        }
        else
        {
            edtProfileProfession.setText(user.get("profileProfession").toString());
        }
        if(user.get("profileFavouriteSport") == null){
            edtProfileFavSport.setText("");
        }
        else
        {
            edtProfileFavSport.setText(user.get("profileFavouriteSport").toString());
        }


    }

}
