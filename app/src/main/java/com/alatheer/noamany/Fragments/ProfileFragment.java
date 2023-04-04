package com.alatheer.noamany.Fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alatheer.noamany.Data.Local.Govern;
import com.alatheer.noamany.Data.Local.SharedPreference2;
import com.alatheer.noamany.Data.Local.UserSharedPreference;
import com.alatheer.noamany.Data.Remote.AuthenticationModels.UserModel;
import com.alatheer.noamany.Data.Remote.GetDataService;
import com.alatheer.noamany.Data.Remote.RetrofitClientInstance;
import com.alatheer.noamany.Data.Remote.wallet.Wallet;
import com.alatheer.noamany.Helper.LocaleHelper;
import com.alatheer.noamany.R;
import com.alatheer.noamany.Utilities.Utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ProfileFragment extends Fragment {

    @BindView(R.id.et_client_name)
    EditText user_name_data;
    @BindView(R.id.et_client_email)
    EditText user_email_data;
    @BindView(R.id.et_client_phone)
    EditText user_phone_data;
    @BindView(R.id.et_client_birthdate)
    EditText user_birthdate_data;
    @BindView(R.id.et_client_password)
    EditText user_password_data;
    @BindView(R.id.et_client_national_num)
    EditText user_national_num_data;
    @BindView(R.id.img_back)
    ImageView img_back;
    @BindView(R.id.btn_edit)
    TextView btn_edit;
    @BindView(R.id.txt_points)
    TextView txt_points;
    @BindView(R.id.txt_value)
    TextView txt_value;
    @BindView(R.id.points)
    TextView points;
    @BindView(R.id.values)
    TextView value;
    UserSharedPreference userSharedPreference;
    SharedPreference2 sharedPreference2;
    UserModel userModel;
    Govern govern;
    String name,email,phone,birthdate,language,password,national_num,id,govern_id,m_code;
    Context context;
    Resources resources;
    DatePickerDialog.OnDateSetListener date_picker_dialog;
    Calendar myCalendar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this,view);
        Paper.init(getActivity());
        sharedPreference2 = SharedPreference2.getInstance();
        govern = sharedPreference2.Get_UserData(getActivity());
        govern_id = govern.getId();
        myCalendar = Calendar.getInstance();
        language = Paper.book().read("language");
        if(language == null){
            Paper.book().write("language","ar");
        }
        updateview(language);
        date_picker_dialog = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelStart();

            }

        };

        setData();
        /*img_edit_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_name_data.setFocusable(true);
                user_name_data.setEnabled(true);
                user_name_data.setClickable(true);
                user_name_data.setFocusableInTouchMode(true);
                user_name_data.requestFocus();
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
            }
        });
        img_edit_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_email_data.setFocusable(true);
                user_email_data.setEnabled(true);
                user_email_data.setClickable(true);
                user_email_data.setFocusableInTouchMode(true);
                user_email_data.requestFocus();
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
            }
        });
        img_edit_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_phone_data.setFocusable(true);
                user_phone_data.setEnabled(true);
                user_phone_data.setClickable(true);
                user_phone_data.setFocusableInTouchMode(true);
                user_phone_data.requestFocus();
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
            }
        });
        img_edit_birthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_birthdate_data.setFocusable(true);
                user_birthdate_data.setEnabled(true);
                user_birthdate_data.setClickable(true);
                // user_birthdate_data.setFocusableInTouchMode(true);
                user_birthdate_data.setFocusableInTouchMode(true);
                user_birthdate_data.setError("اضغط هنا تعديل تاريخ ميلادك");
            }
        });
        img_edit_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_password_data.setFocusable(true);
                user_password_data.setEnabled(true);
                user_password_data.setClickable(true);
                user_password_data.setFocusableInTouchMode(true);
                user_password_data.requestFocus();
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
            }
        });
        img_edit_national_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_national_num_data.setFocusable(true);
                user_national_num_data.setEnabled(true);
                user_national_num_data.setClickable(true);
                user_national_num_data.setFocusableInTouchMode(true);
                user_national_num_data.requestFocus();
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
            }
        });*/
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new SettingsFragment();

                FragmentManager fragmentManager = getFragmentManager();

                fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
            }
        });
        user_birthdate_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCalender();
            }
        });
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Edit();
            }
        });
        return view;
    }

    private void Edit() {
        name = user_name_data.getText().toString();
        email = user_email_data.getText().toString();
        phone = user_phone_data.getText().toString();
        birthdate = user_birthdate_data.getText().toString();
        password = user_password_data.getText().toString();
        national_num = user_national_num_data.getText().toString();
        id = userModel.getMember().getId();
        //govern_id = SharedPreference2.loadData(context);
        if (Utilities.isNetworkAvailable(context)){
            if (govern_id.equals("1")){
                GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                Call<UserModel> call = getDataService.edit_user(name,email,phone,birthdate,password,national_num,id);
                call.enqueue(new Callback<UserModel>() {
                    @Override
                    public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                        if(response.isSuccessful()){
                            if(response.body().getSuccess() ==1){
                                Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                userSharedPreference.Create_Update_UserData(getActivity(),response.body());
                                UserModel userModel = response.body();
                                setDatafromApi(userModel);
                            }else {
                                Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<UserModel> call, Throwable t) {

                    }
                });
            }else {
                GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance2().create(GetDataService.class);
                Call<UserModel> call = getDataService.edit_user(name,email,phone,birthdate,password,national_num,id);
                call.enqueue(new Callback<UserModel>() {
                    @Override
                    public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                        if(response.isSuccessful()){
                            if(response.body().getSuccess() ==1){
                                Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                userSharedPreference.Create_Update_UserData(getActivity(),response.body());
                                UserModel userModel = response.body();
                                setDatafromApi(userModel);
                            }else {
                                Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<UserModel> call, Throwable t) {

                    }
                });
            }
        }
    }

    private void updateLabelStart() {
        String myFormat = "dd-MM-yyyy";//In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());
        birthdate = sdf.format(myCalendar.getTime());
        user_birthdate_data.setText(birthdate);
    }

    private void openCalender() {
        new DatePickerDialog(getActivity(), date_picker_dialog, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();

    }

    private void updateview(String language) {
        context = LocaleHelper.setLocale(getActivity(),language);
        resources = context.getResources();
        btn_edit.setText(resources.getString(R.string.edit_profile));
        points.setText(resources.getString(R.string.points));
        value.setText(resources.getString(R.string.value));

    }

    private void setData() {
        userSharedPreference = UserSharedPreference.getInstance();
        userModel = userSharedPreference.Get_UserData(getActivity());
        m_code = userModel.getMember().getMCode();
        name = userModel.getMember().getMName();
        email = userModel.getMember().getMEmail();
        phone = userModel.getMember().getMTel();
        birthdate = userModel.getMember().getMBirthday();
        password = userModel.getMember().getMPassword();
        national_num = userModel.getMember().getMCard();
        user_email_data.setText(email);
        long dt = Long.parseLong(birthdate);
        final Date d = new Date((long) (dt * 1000.05));
        final DateFormat f = new SimpleDateFormat("dd-MM-yyyy",Locale.getDefault());
        user_birthdate_data.setText(f.format(d));
        user_phone_data.setText(phone);
        user_name_data.setText(name);
        user_national_num_data.setText(national_num);
        user_password_data.setText(password);
        get_points_in_wallet();
    }

    private void get_points_in_wallet() {
        if (Utilities.isNetworkAvailable(context)){
            if (govern_id.equals("1")){
                GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                Call<Wallet> call = getDataService.get_points_in_wallet(m_code);
                call.enqueue(new Callback<Wallet>() {
                    @Override
                    public void onResponse(Call<Wallet> call, Response<Wallet> response) {
                        if (response.isSuccessful()){
                            txt_points.setText(response.body().getPoints());
                            txt_value.setText(response.body().getValues()+" "+"جنيه");
                        }
                    }

                    @Override
                    public void onFailure(Call<Wallet> call, Throwable t) {

                    }
                });
            }else {
                GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance2().create(GetDataService.class);
                Call<Wallet> call = getDataService.get_points_in_wallet(m_code);
                call.enqueue(new Callback<Wallet>() {
                    @Override
                    public void onResponse(Call<Wallet> call, Response<Wallet> response) {
                        if (response.isSuccessful()){
                            points.setText(response.body().getPoints());
                            value.setText(response.body().getValues());
                        }
                    }

                    @Override
                    public void onFailure(Call<Wallet> call, Throwable t) {

                    }
                });
            }
        }
    }

    private void setDatafromApi(UserModel userModel) {
        user_name_data.setText(userModel.getMember().getMName());
        user_email_data.setText(userModel.getMember().getMEmail());
        user_phone_data.setText(userModel.getMember().getMTel());
        long dt = Long.parseLong(userModel.getMember().getMBirthday());
        final Date d = new Date((long) (dt * 1000.05));
        final DateFormat f = new SimpleDateFormat("dd-MM-yyyy",Locale.getDefault());
        user_birthdate_data.setText(f.format(d));
        //user_birthdate_data.setText(userModel.getMember().getMBirthday());
        user_password_data.setText(userModel.getMember().getMPassword());
        user_national_num_data.setText(userModel.getMember().getMCard());
    }
}
