package com.alatheer.noamany.Fragments;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import io.paperdb.Paper;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alatheer.noamany.AddAdvertismentViewModel;
import com.alatheer.noamany.Helper.LocaleHelper;
import com.alatheer.noamany.R;
import com.alatheer.noamany.databinding.AddAdvertismentFragmentBinding;
import com.squareup.picasso.Picasso;


public class AddAdvertismentFragment extends Fragment {
    AddAdvertismentFragmentBinding addAdvertismentFragmentBinding;
    private AddAdvertismentViewModel mViewModel;
    int IMG = 1;
    private final String read_permission = Manifest.permission.READ_EXTERNAL_STORAGE;
    Uri filePath;
    String company_name,company_address,company_phone,product_name,product_description,link,language;
    Context context;
    Resources resources;
    public static AddAdvertismentFragment newInstance() {
        return new AddAdvertismentFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        addAdvertismentFragmentBinding = DataBindingUtil.inflate(inflater,R.layout.add_advertisment_fragment,container,false);
        View view = addAdvertismentFragmentBinding.getRoot();
        mViewModel = new AddAdvertismentViewModel(getActivity(),this);
        addAdvertismentFragmentBinding.setAddadvertismentviewmodel(mViewModel);
        addAdvertismentFragmentBinding.backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new AdvertismentFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container,fragment).commit();
            }
        });
        addAdvertismentFragmentBinding.imgAds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Check_ReadPermission(IMG);
            }
        });
        addAdvertismentFragmentBinding.publishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Validation();
            }
        });
        Paper.init(getActivity());
        language = Paper.book().read("language");
        if(language == null){
            Paper.book().write("language","ar");
        }
        updateview(language);
        return  view;
    }

    private void updateview(String language) {
        context = LocaleHelper.setLocale(getActivity(),language);
        resources = context.getResources();
        addAdvertismentFragmentBinding.txtNewAds.setText(resources.getString(R.string.new_ads));
        addAdvertismentFragmentBinding.txtAdsImg.setText(resources.getString(R.string.ads_img));
        addAdvertismentFragmentBinding.etCompanyName.setHint(resources.getString(R.string.company_name));
        addAdvertismentFragmentBinding.etCompanyAddress.setHint(resources.getString(R.string.company_address));
        addAdvertismentFragmentBinding.etCompanyPhone.setHint(resources.getString(R.string.company_phone));
        addAdvertismentFragmentBinding.etProductName.setHint(resources.getString(R.string.product_name));
        addAdvertismentFragmentBinding.etProductDescription.setHint(resources.getString(R.string.product_description));
        addAdvertismentFragmentBinding.etLink.setHint(resources.getString(R.string.vedio_id));
        addAdvertismentFragmentBinding.publishBtn.setText(resources.getString(R.string.publish));
    }

    private void Validation() {
        company_name = addAdvertismentFragmentBinding.etCompanyName.getText().toString();
        company_phone = addAdvertismentFragmentBinding.etCompanyPhone.getText().toString();
        company_address = addAdvertismentFragmentBinding.etCompanyAddress.getText().toString();
        product_name = addAdvertismentFragmentBinding.etProductName.getText().toString();
        product_description = addAdvertismentFragmentBinding.etProductDescription.getText().toString();
        link = addAdvertismentFragmentBinding.etLink.getText().toString();
        if(!TextUtils.isEmpty(company_name)&& !TextUtils.isEmpty(company_address)&& !TextUtils.isEmpty(company_phone)&&
                !TextUtils.isEmpty(product_name)&& !TextUtils.isEmpty(product_description)&&!TextUtils.isEmpty(link)&&
                filePath != null ){
            mViewModel.add_ads(company_name,company_phone,company_address,product_name,product_description,link,filePath);
        }else {
            if(TextUtils.isEmpty(company_name)){
                addAdvertismentFragmentBinding.etCompanyName.setError(resources.getString(R.string.validate_company_name));
            }else {
                addAdvertismentFragmentBinding.etCompanyName.setError(null);
            }
            if(TextUtils.isEmpty(company_phone)){
                addAdvertismentFragmentBinding.etCompanyPhone.setError(resources.getString(R.string.validate_company_phone));
            }else {
                addAdvertismentFragmentBinding.etCompanyPhone.setError(null);
            }
            if(TextUtils.isEmpty(company_address)){
                addAdvertismentFragmentBinding.etCompanyAddress.setError(resources.getString(R.string.validate_company_address));
            }else {
                addAdvertismentFragmentBinding.etCompanyAddress.setError(null);
            }
            if(TextUtils.isEmpty(product_name)){
                addAdvertismentFragmentBinding.etProductName.setError(resources.getString(R.string.validate_product_name));
            }else {
                addAdvertismentFragmentBinding.etProductName.setError(null);
            }
            if(TextUtils.isEmpty(product_description)){
                addAdvertismentFragmentBinding.etProductDescription.setError(resources.getString(R.string.validate_product_description));
            }else {
                addAdvertismentFragmentBinding.etProductDescription.setError(null);
            }
            if(TextUtils.isEmpty(link)){
                addAdvertismentFragmentBinding.etLink.setError(resources.getString(R.string.validate_product_link));
            }else {
                addAdvertismentFragmentBinding.etLink.setError(null);
            }
            if (filePath == null){
                Toast.makeText(getActivity(),resources.getString(R.string.validate_photo), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void Check_ReadPermission(int img) {
        if (ContextCompat.checkSelfPermission(getActivity(), read_permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{read_permission}, img);
        } else {
            select_photo(img);
        }
    }

    private void select_photo(int img) {
        Intent intent;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
        } else {
            intent = new Intent(Intent.ACTION_GET_CONTENT);

        }
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setType("image/*");
        startActivityForResult(intent, img);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMG && resultCode == Activity.RESULT_OK
                && data != null && data.getData() != null) {
            filePath = data.getData();
            Picasso.get().load(filePath).into(addAdvertismentFragmentBinding.imgAds);
            Toast.makeText(getActivity(), "image added successfully", Toast.LENGTH_SHORT).show();
        }
    }
}
