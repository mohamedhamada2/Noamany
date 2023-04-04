package com.alatheer.noamany.receipts;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import io.paperdb.Paper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alatheer.noamany.Adapters.MemberSupscriptionsAdapter;
import com.alatheer.noamany.Data.Local.Govern;
import com.alatheer.noamany.Data.Local.SharedPreference2;
import com.alatheer.noamany.Data.Local.UserSharedPreference;
import com.alatheer.noamany.Data.Remote.AuthenticationModels.UserModel;
import com.alatheer.noamany.Data.Remote.MemberSubscription.MemberSubscription;
import com.alatheer.noamany.Fragments.SettingsFragment;
import com.alatheer.noamany.Helper.LocaleHelper;
import com.alatheer.noamany.MemberSubscriptionViewModel;
import com.alatheer.noamany.R;
import com.alatheer.noamany.ReceiptsViewModel;
import com.alatheer.noamany.databinding.FragmentMySubscriptionsBinding;
import com.alatheer.noamany.databinding.FragmentReceiptsBinding;

import java.util.List;


public class ReceiptsFragment extends Fragment {
    ReceiptsViewModel receiptsViewModel;
    FragmentReceiptsBinding fragmentReceiptsBinding;
    MemberSupscriptionsAdapter memberSupscriptionsAdapter;
    GridLayoutManager layoutManager;
    Resources resources;
    Context context;
    String language;
    private boolean isloading;
    private int pastvisibleitem,visibleitemcount,totalitemcount,previous_total=0;
    int view_threshold = 10;
    int page =1 ;
    UserSharedPreference userSharedPreference;
    UserModel userModel;
    String mcode,id;
    List<MemberSubscription> memberSubscriptionList;
    SharedPreference2 sharedPreference2;
    Govern govern;
    String govern_id;
    Integer flag;
    String subscription_id,session_id,subscription_value;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentReceiptsBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_receipts,container,false);
        View view = fragmentReceiptsBinding.getRoot();
        receiptsViewModel = new ReceiptsViewModel(getActivity(),this);
        //here data must be an instance of the class MarsDataProvider
        fragmentReceiptsBinding.setReceiptsviewmodel(receiptsViewModel);
        sharedPreference2 = SharedPreference2.getInstance();
        govern = sharedPreference2.Get_UserData(getActivity());
        govern_id = govern.getId();
        Paper.init(getActivity());
        language = Paper.book().read("language");
        if(language == null){
            Paper.book().write("language","ar");
        }
        updateview(language);
        userSharedPreference = UserSharedPreference.getInstance();
        userModel = userSharedPreference.Get_UserData(getActivity());
        mcode = userModel.getMember().getMCode();
        id = userModel.getMember().getId();
        receiptsViewModel.getmemebersupscription(page,id,govern_id);
        fragmentReceiptsBinding.backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new SettingsFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container,fragment).commit();
            }
        });
        fragmentReceiptsBinding.memberSubcriptionsRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleitemcount = layoutManager.getChildCount();
                totalitemcount = layoutManager.getItemCount();
                pastvisibleitem = layoutManager.findFirstVisibleItemPosition();
                if(dy>0){
                    if(isloading){
                        if(totalitemcount>previous_total){
                            isloading = false;
                            previous_total = totalitemcount;
                        }
                    }
                    if(!isloading &&(totalitemcount-visibleitemcount)<= pastvisibleitem+view_threshold){
                        page++;
                        receiptsViewModel.PerformPagination(id,page,govern_id);
                        isloading = true;
                    }

                }
            }
        });
        return  view;
    }
    private void updateview(String language) {
        context = LocaleHelper.setLocale(getActivity(),language);
        resources = context.getResources();
        fragmentReceiptsBinding.txtMemberSubscription.setText(resources.getString(R.string.receipts));
    }

    public void initrecycler(ReceiptsAdapter memberSupscriptionsAdapter, List<MemberSubscription> memberSubscriptionList) {
        this.memberSubscriptionList = memberSubscriptionList;
        fragmentReceiptsBinding.memberSubcriptionsRecycler.setHasFixedSize(true);
        fragmentReceiptsBinding.memberSubcriptionsRecycler.setAdapter(memberSupscriptionsAdapter);
        layoutManager = new GridLayoutManager(getActivity(),1);
        fragmentReceiptsBinding.memberSubcriptionsRecycler.setLayoutManager(layoutManager);

    }

    public void setprogressbar() {
        fragmentReceiptsBinding.progreesbar.setVisibility(View.GONE);
    }
}