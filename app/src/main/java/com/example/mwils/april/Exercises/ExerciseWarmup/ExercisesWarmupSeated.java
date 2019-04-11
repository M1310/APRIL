package com.example.mwils.april.Exercises.ExerciseWarmup;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mwils.april.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class ExercisesWarmupSeated extends Fragment {

    //Creating variables to hold the entire String that comes back from the DB
    String titles, descriptions;
    //Creating String variables to hold the data from the DB after split
    TextView tvTitle1, tvTitle2, tvTitle3, tvTitle4, tvDesc1, tvDesc2, tvDesc3, tvDesc4;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_exercises_seated, container, false);

        return view;
    }//end onCreate

    /**
     * onStart runs after onCreateView
     * allowing me to assign elements to variables without
     * getting a Null Pointer exception
     */
    @Override
    public void onStart() {
        super.onStart();
        setUpFields();
        setData();
    }//end onStart

    /**
     * This method assigns the variables to the various elements
     * in the layout file.
     */
    public void setUpFields() {

        //Assigning each of the exercises titles to relveant TextViews
        tvTitle1 = getView().findViewById(R.id.tvSeatedTitle1);
        tvTitle2 = getView().findViewById(R.id.tvSeatedTitle2);
        tvTitle3 = getView().findViewById(R.id.tvSeatedTitle3);
        tvTitle4 = getView().findViewById(R.id.tvSeatedTitle4);

        //Assigning each of the exercises descriptions to the relevant TextViews
        tvDesc1 = getActivity().findViewById(R.id.tvSeatedDesc1);
        tvDesc2 = getActivity().findViewById(R.id.tvSeatedDesc2);
        tvDesc3 = getActivity().findViewById(R.id.tvSeatedDesc3);
        tvDesc4 = getActivity().findViewById(R.id.tvSeatedDesc4);



    }//end setUpFields

    /**
     * This method gathers the data from the Firestore and
     * enters it into the various elements on the tab.
     */
    public void setData() {

        //First getting an instance of the Firestore Database tied to this project
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        //Looking for the Collection within that Database that contains all the Exercises
        CollectionReference colref = database.collection("Exercises");
        //Checking for a specific Exercise in that Collection
        DocumentReference docRef = colref.document("Warmup Seated");

        //Retrieves the document from the Firestore
        docRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        //Assigning the information from the database
                        //to the initialised Strings
                        titles = documentSnapshot.get("Titles").toString();
                        descriptions = documentSnapshot.get("Description").toString();
                        //call the methods that format and place the Strings into the relevant textViews
                        formatTitles(titles);
                        formatDescription(descriptions);


                    }//end on Success
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "Failed to retrieve data " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }//end onFailure
        });//end onFailureListener


    }//end getData

    /**
     * This method takes the Sting retrieved from the DB
     * splits it into a String Array, and then places
     * those values in the relevant textViews for titles
     * @param unformat - contains the String from the DB to be formatted
     */
    private void formatTitles(String unformat){
        //Removes the square bracket from the end of the text
        unformat = unformat.replaceAll("]","");
        //Splits the String into cells in a String Array
        String [] formatTitles = unformat.split(",");

        //Assigns in the individual Strings into the
        //relevant TextViews
        tvTitle1.setText(formatTitles[0]);
        tvTitle2.setText(formatTitles[1]);
        tvTitle3.setText(formatTitles[2]);
        tvTitle4.setText(formatTitles[3]);

    }//end formatTitles

    /**
     * This method takes the Sting retrieved from the DB
     * splits it into a String Array, and then places
     * those values in the relevant textViews for descriptions
     * @param unformat - contains the String from the DB to be formatted
     */
    private void formatDescription(String unformat){
        //Removes the square bracket from the end of the text
        unformat = unformat.replaceAll("]","");
        //Splits the String into cells in a String Array
        String [] formatDesc = unformat.split(",");

        //Assigns in the individual Strings into the
        //relevant TextViews
        tvDesc1.setText(formatDesc[0]);
        tvDesc2.setText(formatDesc[1]);
        tvDesc3.setText(formatDesc[2]);
        tvDesc4.setText(formatDesc[3]);
    }//end formatDescription

}//end Class



