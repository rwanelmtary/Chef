package com.example.mealplanner.details;

import static com.example.mealplanner.model.PlanDetailConverter.getMealPlannerFromMealAndDate;
import static com.example.mealplanner.network.MealsRemoteDataSourceImp.TAG;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.helper.widget.MotionEffect;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mealplanner.R;
import com.example.mealplanner.database.MealsLocalDataSourceImp;
import com.example.mealplanner.filter.view.FilterAdapter;
import com.example.mealplanner.home.presenter.HomePresenter;
import com.example.mealplanner.home.presenter.HomePresenterImp;
import com.example.mealplanner.home.view.HomeView;
import com.example.mealplanner.model.DateFormatter;
import com.example.mealplanner.model.MealsItem;
import com.example.mealplanner.model.MealsRepo;
import com.example.mealplanner.model.MealsRepoImp;
import com.example.mealplanner.model.PlanDetailConverter;
import com.example.mealplanner.model.Planner;
import com.example.mealplanner.planner.view.OnSavedClickListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.Calendar;
import java.util.Collections;


public class DetailsFragment extends Fragment implements OnSavedClickListener{

    private ImageView imageView;
    private TextView mealNameTextView;
    private TextView areaTextView;
    private TextView instructionsTextView;
    OnDetailClickListener onDetailClickListener;
    YouTubePlayerView youtubePlayerView;
    Button datePickerButton;
    Calendar calendar;
    Planner planner;
    OnSavedClickListener onSavedClickListener;
    String date;
    HomePresenter homePresenter;
     MealsRepo mealsRepo;

    MealsItem mealsItem;

    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        // Initialize views
        MealsLocalDataSourceImp mealsLocalDataSource= MealsLocalDataSourceImp.getInstance(requireContext());
        MealsRepoImp imp = MealsRepoImp.getInstance(requireContext());
        imageView = view.findViewById(R.id.imageView5);
        mealNameTextView = view.findViewById(R.id.mealnamedatail);
        areaTextView = view.findViewById(R.id.areatv);
        instructionsTextView = view.findViewById(R.id.textView6);
        youtubePlayerView = view.findViewById(R.id.ytPlayer);
        homePresenter = new HomePresenterImp(imp);

        onSavedClickListener =  this;


        getLifecycle().addObserver(youtubePlayerView);
        youtubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = getId(mealsItem.getStrYoutube());
                youTubePlayer.cueVideo(videoId, 0);
            }

            private String getId(String strYoutube) {
                return strYoutube;
            }
        });

        // Get mealId from arguments
        DetailsFragmentArgs args = DetailsFragmentArgs.fromBundle(getArguments());
        mealsItem = args.getMealsitems();

        // Check if mealsItem is not null before accessing its properties
        if (mealsItem != null) {
            Glide.with(requireContext())
                    .load(mealsItem.getStrMealThumb())
                    .into(imageView);
            mealNameTextView.setText(mealsItem.getStrMeal());
            areaTextView.setText(mealsItem.getStrArea());
            instructionsTextView.setText(mealsItem.getStrInstructions());
        } else {
            Log.e(TAG, "onCreateView: mealsItem is null");
        }
        datePickerButton = view.findViewById(R.id.savebtn);
        datePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDatePicker();
            }
        });


        return view;

    }

    private void showDatePicker(){
        calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Handle selected date
                        String selectedDate = year + "-" + (month + 1) + "-" + dayOfMonth;
                        Log.d("Selected Date", selectedDate);

                        // Assign selected date to the 'date' variable
                        date = selectedDate;
                         planner = PlanDetailConverter
                                .getMealPlannerFromMealAndDate(mealsItem, DateFormatter
                                        .getString(year, month, dayOfMonth),0);
                        Log.i(TAG, "onDateSet: "+planner.strMeal+" on "+date);
//                       homePresenter.addToPlan(planner,date);
//                        addToSaved(planner,date);
                        homePresenter.addToPlan(planner,date);
                        // Check if planner is not null before calling onMealClick
                        if (planner != null) {
//                            onSavedClickListener.onMealClick(planner,date);
                            Log.i(MotionEffect.TAG, "onClick: added to saved");
                        } else {
                            Log.e(MotionEffect.TAG, "onClick: MealsItem is null");
                        }

                    }
                    },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );

        // Show the date picker dialog
        datePickerDialog.show();
    }



    private String getId(String url) {
        String result = "";
        if (url != null && url.split("\\?v=").length > 1)
            result = url.split("\\?v=")[1];
        return result;
    }
    public void addToSaved(Planner planner,String date){
        homePresenter.addToPlan(planner,date);
    }

    @Override
    public void onMealClick(Planner planner, String date) {
        addToSaved(planner,date);
    }
}