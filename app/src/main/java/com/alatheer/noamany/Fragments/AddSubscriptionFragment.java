package com.alatheer.noamany.Fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import io.paperdb.Paper;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.alatheer.noamany.AddSubscriptionViewModel;
import com.alatheer.noamany.Data.Local.Govern;
import com.alatheer.noamany.Data.Local.SharedPreference2;
import com.alatheer.noamany.Data.Local.UserSharedPreference;
import com.alatheer.noamany.Data.Remote.AllSubscription.AllSubscription;
import com.alatheer.noamany.Data.Remote.AuthenticationModels.UserModel;
import com.alatheer.noamany.Data.Remote.branches.Record;
import com.alatheer.noamany.Helper.LocaleHelper;
import com.alatheer.noamany.R;
import com.alatheer.noamany.databinding.FragmentAddSubscriptionBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddSubscriptionFragment extends Fragment {
    FragmentAddSubscriptionBinding fragmentAddSubscriptionBinding;
    AddSubscriptionViewModel addSubscriptionViewModel;
    String num_of_days="",start_date ="",end_date="",price="",branch_id,subscription_id,branch_title,subscription_title,gender_id,gender_title;
    Record branch;
    AllSubscription allSubscription;
    ArrayAdapter<String> allcitiesadapter,subscriptionadapter,genderadapter;
    List<Record> branchList;
    List<AllSubscription> allSubscriptionList;
    Calendar myCalendar,c;
    DatePickerDialog date_picker_dialog;
    Date d1,date;
    List<String> gendertitlelist;
    UserSharedPreference userSharedPreference;
    UserModel userModel;
    String user_id,language;
    Context context;
    Resources resources;
    DatePickerDialog datePickerDialog;
    Integer expired_days;
    SharedPreference2 sharedPreference2;
    String govern_id;
    Govern govern;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentAddSubscriptionBinding =  DataBindingUtil.inflate(inflater,R.layout.fragment_add_subscription, container, false);
        View view = fragmentAddSubscriptionBinding.getRoot();
        addSubscriptionViewModel = new AddSubscriptionViewModel(getActivity(),this);
        fragmentAddSubscriptionBinding.setAddsubscriptionviewmodel(addSubscriptionViewModel);
        gendertitlelist = new ArrayList<>();
        userSharedPreference = UserSharedPreference.getInstance();
        userModel = userSharedPreference.Get_UserData(getActivity());
        user_id = userModel.getMember().getId();
        sharedPreference2 = SharedPreference2.getInstance();
        govern = sharedPreference2.Get_UserData(getActivity());
        govern_id = govern.getId();
        branch = new Record();
        allSubscription = new AllSubscription();
        addSubscriptionViewModel.get_branches(govern_id);
        addSubscriptionViewModel.get_subscriptions(govern_id);
        addSubscriptionViewModel.get_gender();
        myCalendar = Calendar.getInstance();
        c = Calendar.getInstance();
        date_picker_dialog = new DatePickerDialog(getActivity());
        datePickerDialog = new DatePickerDialog(getActivity());
        final int day =  c.get(Calendar.DAY_OF_MONTH);
        final int year =  c.get(Calendar.YEAR);
        final int month = c.get(Calendar.MONTH);
        //Toast.makeText(context, day+"", Toast.LENGTH_SHORT).show();
        language = Paper.book().read("language");
        if(language == null){
           Paper.book().write("language","ar");
        }
        updateview(language);
        fragmentAddSubscriptionBinding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getActivity().onBackPressed();
                Fragment fragment = new SettingsFragment();

                FragmentManager fragmentManager = getFragmentManager();

                fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
            }
        });
        fragmentAddSubscriptionBinding.etStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                date_picker_dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        updateLabelStart(year,month,dayOfMonth);
                    }
                },year,month,day);
                date_picker_dialog.getDatePicker().setMinDate(myCalendar.getTimeInMillis());
                if(expired_days != 0){
                    myCalendar.add(Calendar.DATE, expired_days-1);
                    date_picker_dialog.getDatePicker().setMaxDate(myCalendar.getTimeInMillis());
                    date_picker_dialog.show();
                    myCalendar.add(Calendar.DATE, -(expired_days-1));
                }else {
                    myCalendar.add(Calendar.DATE,0);
                    date_picker_dialog.getDatePicker().setMaxDate(myCalendar.getTimeInMillis());
                    date_picker_dialog.show();
                    myCalendar.add(Calendar.DATE,0);
                }
                /*datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener(), myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();*/
            }
        });
        fragmentAddSubscriptionBinding.branchSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                branch_id = branchList.get(position).getId();
                branch_title = branchList.get(position).getTitle();
                branch.setId(branch_id);
                branch.setTitle(branch_title);
                try {
                    TextView textView = (TextView) view;
                    textView.setTextColor(getResources().getColor(R.color.colorPrimary));

                } catch (Exception e) {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        fragmentAddSubscriptionBinding.subscriptionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                subscription_id = allSubscriptionList.get(position).getId();
                subscription_title = allSubscriptionList.get(position).getTitle();
                num_of_days = allSubscriptionList.get(position).getNumDays();
                price = allSubscriptionList.get(position).getValueGal2();
                expired_days = allSubscriptionList.get(position).getExpireNumDays();
                //Toast.makeText(getActivity(), num_of_days, Toast.LENGTH_SHORT).show();
                if (allSubscriptionList.get(position).getId().equals("0")){
                    fragmentAddSubscriptionBinding.etDays.setText("");
                }else {
                    /*String myFormat = "dd-MM-yyyy";//In which you need put here
                    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);
                    start_date = sdf.format(new Date());
                    fragmentAddSubscriptionBinding.etStartDate.setText(start_date);*/
                    fragmentAddSubscriptionBinding.etDays.setText(num_of_days);
                    updateLabelStart(year,month,day);
                }
                fragmentAddSubscriptionBinding.etSubscriptionPrice.setText(price);
                try {
                    TextView textView = (TextView) view;
                    textView.setTextColor(getResources().getColor(R.color.colorPrimary));

                }catch (Exception e) {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        fragmentAddSubscriptionBinding.genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (gendertitlelist.get(position).equals("رجالي")){
                    gender_id = "0";
                }else if (gendertitlelist.get(position).equals("حريمي")){
                    gender_id = "1";
                }else {
                    gender_id = "3";
                }
                try {
                    TextView textView = (TextView) view;
                    textView.setTextColor(getResources().getColor(R.color.colorPrimary));

                } catch (Exception e) {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        fragmentAddSubscriptionBinding.btnSubscripe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validation();
            }
        });
        /*date_picker_dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                updateLabelStart();
            }
        },myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),Calendar.DAY_OF_MONTH);*/

        return view;
    }

    private void updateview(String language) {
        context = LocaleHelper.setLocale(getActivity(),language);
        resources = context.getResources();
        fragmentAddSubscriptionBinding.etEndDate.setHint(resources.getString(R.string.end_date));
        fragmentAddSubscriptionBinding.etDays.setHint(resources.getString(R.string.num_of_days));
        fragmentAddSubscriptionBinding.etSubscriptionPrice.setHint(resources.getString(R.string.subscription_price));
        fragmentAddSubscriptionBinding.etStartDate.setHint(resources.getString(R.string.start_date));
        fragmentAddSubscriptionBinding.txtBarcode.setText(resources.getString(R.string.add_subscription));
        fragmentAddSubscriptionBinding.btnSubscripe.setText(resources.getString(R.string.subscripe2));
    }

    private void validation() {
        if (!TextUtils.isEmpty(price)&&!subscription_id.equals("0")&&!branch_id.equals("0")&&!gender_id.equals("3")){
            addSubscriptionViewModel.add_subscription(user_id,branch_id,subscription_id,price,start_date,end_date,gender_id,govern_id);
        }else{
            if (branch_id.equals("0")){
                Toast.makeText(getActivity(), "اختر الفرع من فضلك", Toast.LENGTH_SHORT).show();
            }
            if (subscription_id.equals("0")){
                Toast.makeText(getActivity(), "اختر الاشتراك من فضلك", Toast.LENGTH_SHORT).show();
            }
            if (gender_id.equals("3")){
                Toast.makeText(getActivity(), "اختر الجنس من فضلك", Toast.LENGTH_SHORT).show();
            }

        }
    }

    private void updateLabelStart(int year,int month,int day) {
        start_date = day+""+"-"+(month+1)+""+"-"+year+"";
        String myFormat = "dd-MM-yyyy";//In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);
        try {
            c.setTime(sdf.parse(start_date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, Integer.parseInt(num_of_days)-1);
        Date resultdate = new Date(c.getTimeInMillis());
        end_date = sdf.format(resultdate);

        /*Toast.makeText(context, "hello", Toast.LENGTH_SHORT).show();
        String myFormat = "dd-MM-yyyy";//In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);
        Date Today = new Date();
        date_picker_dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        Date formattedExpired = myCalendar.getTime();
        int diffInDays = (int)( (formattedExpired.getTime() -  Today.getTime())
                / (1000 * 60 * 60 * 24) );
        if (diffInDays > 6){
            fragmentAddSubscriptionBinding.etStartDate.setText("");
            fragmentAddSubscriptionBinding.etEndDate.setText("");
            myCalendar.getTime();
            Toast.makeText(context, "عفوا تاريخ بداية الإشتراك لا يزيد عن أسبوع من تاريخ اليوم", Toast.LENGTH_LONG).show();
        }else {
            //Toast.makeText(context, diffInDays+"", Toast.LENGTH_SHORT).show();
            start_date = sdf.format(myCalendar.getTime());
            myCalendar.add(Calendar.DATE, Integer.parseInt(num_of_days)-1);
            Date resultdate = new Date(myCalendar.getTimeInMillis());
            end_date = sdf.format(resultdate);
            try {
                d1 = sdf.parse(start_date);
            } catch (ParseException e) {
                e.printStackTrace();
            }*/
            fragmentAddSubscriptionBinding.etStartDate.setText(start_date);
            fragmentAddSubscriptionBinding.etEndDate.setText(end_date);

    }

    public void setSpinner(List<String> branchListtitle, List<Record> branchList) {
        branchListtitle.add(0, "اختر الفرع");
        Record branch = new Record();
        branch.setTitle("اختر الفرع");
        branch.setId("0");
        branchList.add(0,branch);
        allcitiesadapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, branchListtitle);
        fragmentAddSubscriptionBinding.branchSpinner.setAdapter(allcitiesadapter);
        this.branchList = branchList;
    }

    public void setSubscriptionSpinner(List<String> subscriptionlisttitle, List<AllSubscription> allSubscriptionList) {
        subscriptionlisttitle.add(0, "اختر الإشتراك");
        allSubscription = new AllSubscription();
        allSubscription.setTitle("اختر الإشتراك");
        allSubscription.setId("0");
        allSubscription.setNumDays("0");
        allSubscriptionList.add(0,allSubscription);
        subscriptionadapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, subscriptionlisttitle);
        fragmentAddSubscriptionBinding.subscriptionSpinner.setAdapter(subscriptionadapter);
        this.allSubscriptionList = allSubscriptionList;
    }

    public void setgenderSpinner(List<String> genderlisttitle) {
        genderadapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, genderlisttitle);
        fragmentAddSubscriptionBinding.genderSpinner.setAdapter(genderadapter);
        gendertitlelist = genderlisttitle;
    }

    public void finish() {
        getActivity().onBackPressed();
    }
}