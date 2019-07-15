package com.example.apinstagramclone;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

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

    private EditText profilenameET,profilebioET,profileprofessionET,profilehobbiET,profilefavouritesportsET;
    private Button updatenifobtn;

    public ProfileTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile_tab, container, false);

        profilenameET = view.findViewById(R.id.profilenameET);
        profilebioET = view.findViewById(R.id.profilebioET);
        profileprofessionET = view.findViewById(R.id.profileprofessionET);
        profilehobbiET = view.findViewById(R.id.profilehobbiET);
        profilefavouritesportsET = view.findViewById(R.id.profilefavouritesportsET);

        updatenifobtn = view.findViewById(R.id.updateinfobtn);

        final ParseUser user = ParseUser.getCurrentUser();

        if ((user.get("profilename")== null) ||(user.get("profilebio")==null) || (user.get("profileprofession")==null) ||
                (user.get("profilehobbie")==null) || (user.get("profilefavouritesport")==null)){

            profilenameET.setText("");
            profilebioET.setText("");
            profileprofessionET.setText("");
            profilehobbiET.setText("");
            profilefavouritesportsET.setText("");

        }else {
            profilenameET.setText(user.get("profilename")+"");
            profilebioET.setText(user.get("profilebio")+"");
            profileprofessionET.setText(user.get("profileprofession")+"");
            profilehobbiET.setText(user.get("profilehobbie")+"");
            profilefavouritesportsET.setText(user.get("profilefavouritesport")+"");
        }


        updatenifobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user.put("profilename",profilenameET.getText().toString());
                user.put("profilebio",profilebioET.getText().toString());
                user.put("profileprofession",profileprofessionET.getText().toString());
                user.put("profilehobbie",profilehobbiET.getText().toString());
                user.put("profilefavouritesport",profilefavouritesportsET.getText().toString());

                user.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null){
                            FancyToast.makeText(getContext(),"Info Updated Successfully!",
                                    FancyToast.LENGTH_LONG,FancyToast.INFO,true).show();
                        }else {
                            FancyToast.makeText(getContext(),e.getMessage(),
                                    FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                        }
                    }
                });

            }
        });

        return view;

    }

}
