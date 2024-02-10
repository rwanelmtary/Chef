package com.example.mealplanner.planner.view;

import static com.example.mealplanner.network.MealsRemoteDataSourceImp.TAG;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.mealplanner.R;
import com.example.mealplanner.database.MealsLocalDataSourceImp;
import com.example.mealplanner.model.DateFormatter;
import com.example.mealplanner.model.MealsRepo;
import com.example.mealplanner.model.MealsRepoImp;
import com.example.mealplanner.model.Planner;
import com.example.mealplanner.network.MealsRemoteDataSourceImp;
import com.example.mealplanner.planner.presenter.PlannerPresenter;
import com.example.mealplanner.planner.presenter.PlannerPresenterImp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class PlannerFragment extends Fragment implements PlannerView {

    CalendarView calendarView;

    PlannerAdapter plannerAdapter;
    PlannerPresenter plannerPresenter;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    MealsRepo mealsRepo;
    TextView dateTextView;
    Calendar now;
    Planner planner;
    int year, month, datOfMonth;
    DatePickerDialog.OnDateSetListener onDateSetListener;

    public PlannerFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_planner, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        calendarView = view.findViewById(R.id.calendarView);
        String date = DateFormatter.getString(new Date());
        mealsRepo = MealsRepoImp.getInstance(getContext());
        plannerPresenter = new PlannerPresenterImp(this, mealsRepo);
        recyclerView = view.findViewById(R.id.plannerrecycler);
        plannerAdapter = new PlannerAdapter(getContext(), new ArrayList<>());
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(plannerAdapter);

        plannerAdapter.setOnItemClickListener(item -> plannerPresenter.deleta(planner));
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        calendar.add(Calendar.DAY_OF_MONTH,6);
        int maxDay = calendar.get(Calendar.DAY_OF_MONTH);
        calendarView.setMinDate(System.currentTimeMillis());
        calendarView.setMaxDate(calendar.getTimeInMillis());
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                plannerPresenter.getTheMeal(date);
            }
        });
        plannerAdapter.setOnItemClickListener(item->plannerPresenter.deleta(planner));

    }

    private void showDatePickerDialog() {
        // Ensure plannerPresenter is initialized before calling getTheMeal()
        if (plannerPresenter == null) {
            Log.e("TAG", "PlannerPresenter is null");
            return;
        }

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String d = DateFormatter.getString(year, month, dayOfMonth);
                dateTextView.setText(d);

                // Call plannerPresenter.getTheMeal(d) directly to get a Flowable
//                Flowable<List<Planner>> plannerFlowable = plannerPresenter.getTheMeal(d);
//                Log.i(TAG, "onDateSet: " +plannerFlowable.toString());
//                showPlanner(plannerFlowable);

            }
        };

        now = Calendar.getInstance();
        year = now.get(Calendar.YEAR);
        month = now.get(Calendar.MONTH);
        datOfMonth = now.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                onDateSetListener, year, month, datOfMonth);
        datePickerDialog.setTitle("Please select a date.");
        datePickerDialog.show();
    }



    // tripDate = getView().findViewById(R.id.calenderTv);


        @Override
    public void showPlanner(Flowable<List<Planner>> planner) {
            Log.i(TAG, "showPlanner: we re in ");

        planner.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(plan -> {
                            Log.i(TAG, "showPlanner: plan is "+plan.size());
                            plannerAdapter.setList(plan);
                            plannerAdapter.notifyDataSetChanged();
                        },
                        throwable -> {
                            Log.i("TAG", "showMeals: unable to show meals because: "+throwable.getMessage());
                        });


    }

    @Override
    public void delete(Planner planner) {

    }
}