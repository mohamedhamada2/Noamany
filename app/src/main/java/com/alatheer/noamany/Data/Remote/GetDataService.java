package com.alatheer.noamany.Data.Remote;

import com.alatheer.noamany.Data.About.About;
import com.alatheer.noamany.Data.Remote.AddUser.AddUser;
import com.alatheer.noamany.Data.Remote.Advertisement.Ads;
import com.alatheer.noamany.Data.Remote.AllSubscription.AddSubscriptionModel;
import com.alatheer.noamany.Data.Remote.AllSubscription.SubscriptionListModel;
import com.alatheer.noamany.Data.Remote.Auth.Auth;
import com.alatheer.noamany.Data.Remote.AuthenticationModels.UserModel;
import com.alatheer.noamany.Data.Remote.ContactUs.Contact;
import com.alatheer.noamany.Data.Remote.Invitation.InvitationModel;
import com.alatheer.noamany.Data.Remote.LogoutModel.UserLogout;
import com.alatheer.noamany.Data.Remote.MemberInbody.MemberInbodyListModel;
import com.alatheer.noamany.Data.Remote.MemberLocker.MemberLocker;
import com.alatheer.noamany.Data.Remote.MemberLocker.MemberLockerListModel;
import com.alatheer.noamany.Data.Remote.MemberSubscription.MemberSubscriptionListModel;
import com.alatheer.noamany.Data.Remote.MemberVedio.MemberVedio;
import com.alatheer.noamany.Data.Remote.News.NewsModel;
import com.alatheer.noamany.Data.Remote.Notification.NotificationModel;
import com.alatheer.noamany.Data.Remote.branches.BranchModel;
import com.alatheer.noamany.Data.Remote.memberdiscount.MemberDiscount;
import com.alatheer.noamany.Data.Remote.updatetoken.UpdateTokenModel;
import com.alatheer.noamany.Data.Remote.wallet.Wallet;
import com.alatheer.noamany.Fragments.Contact_Us;
import com.alatheer.noamany.Points.Points;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetDataService {
  @FormUrlEncoded
  @POST("api/Auth/register")
  Call<AddUser> SignUp(@Field("m_name")String m_name,
                       @Field("m_email")String m_email,
                       @Field("m_tel")String m_tel,
                       @Field("m_birthday")String m_birthday,
                       @Field("m_password")String m_password,
                       @Field("m_card")String m_card);
  @FormUrlEncoded
  @POST("api/Auth/login")
  Call<UserModel> SignIn(@Field("m_email")String m_email,
                         @Field("m_password")String m_password,
                         @Field("notify_token")String notify_token);
  @POST("api/Auth/log_out")
  Call<UserLogout>LogOut(@Query("token")String token);
  //e0bc669e0112e0c7147ff812b8fb54a9de94578c
  @GET("api/Subcriptions/all_subsTyps")
  Call<SubscriptionListModel>getAllSubscriptionList();
  @GET("api/Subcriptions/my_subsTyps")
  Call<MemberSubscriptionListModel> get_member_subscription(@Query("m_code") String mcode,@Query("page")int page);
  @GET("api/Subcriptions/get_member_lockers")
  Call<MemberLockerListModel> get_member_locker(@Query("m_code")String mcode);
  @FormUrlEncoded
  @POST("api/Subcriptions/get_inbody_images")
  Call<MemberInbodyListModel> get_member_inbudyDetails(@Field("member_id") String mcode);
  @FormUrlEncoded
  @POST("api/Contacts/add_contact")
  Call<Contact> add_contact(@Field("m_code")String m_code,@Field("name")String name, @Field("phone")String phone,
                            @Field("email")String email, @Field("msg_type")String msg_type,
                            @Field("content")String content);
  @Multipart
  @POST("api/Ads/add_ads")
  Call<Contact> add_ads(@Part("sender_address")RequestBody sender_address,
                        @Part("sender_company")RequestBody sender_company,
                        @Part("sender_phone")RequestBody sender_phone,
                        @Part MultipartBody.Part ads_image,
                        @Part("youtube_link")RequestBody requestBody,
                        @Part("title")RequestBody title,
                        @Part("description")RequestBody description);
  @GET("api/Ads/all_ads")
  Call<List<Ads>> get_ads();
  @GET("api/News/all_news")
  Call<List<NewsModel>>get_news();
  @GET("api/Membersvideo/allmembersvideos")
  Call<List<MemberVedio>>getvedios(@Query("m_code")String mcode);
  @FormUrlEncoded
  @POST("api/Auth/edit_profile")
  Call<UserModel> edit_user(@Field("m_name")String m_name,
                            @Field("m_email")String m_email,
                            @Field("m_tel")String m_tel,
                            @Field("m_birthday")String m_birthday,
                            @Field("m_password")String m_password,
                            @Field("m_card")String m_card,
                            @Field("id")String id);
  @FormUrlEncoded
  @POST("api/Auth/send_friends_invitations")
  Call<InvitationModel> invite_friend(@Field("m_name")String m_name,
                                      @Field("m_tel")String m_tel,
                                      @Field("m_card")String m_card,
                                      @Field("m_code")String m_code,
                                      @Field("Receiver_name")String Receiver_name,
                                      @Field("Receiver_card")String Receiver_card,
                                      @Field("Receiver_tel")String Receiver_tel,
                                      @Field("age")int age);
  @GET("api/Auth/about_app")
  Call<About> get_about();

  @FormUrlEncoded
  @POST("api/Contacts/get_member_notifiction")
  Call<List<NotificationModel>> get_notification(@Field("member_code")String member_code);

  @FormUrlEncoded
  @POST("api/Auth/send_member_inbody")
  Call<InvitationModel> request_inbody(@Field("m_name")String m_name,
                                       @Field("m_tel")String m_tel,
                                       @Field("m_card")String m_card,
                                       @Field("m_code")String m_code,
                                       @Field("sub_id_fk")String sub_id_fk);

  @FormUrlEncoded
  @POST("api/Subcriptions/get_codes")
  Call<MemberDiscount> get_member_discounts(@Field("member_code")String member_code);

  @FormUrlEncoded
  @POST("api/Points/all_member_points_history")
  Call<List<Points>> get_points(@Field("m_code")String m_code);

  @FormUrlEncoded
  @POST("api/Points/all_member_points_ended_history")
  Call<List<Points>> get_ended_points(@Field("m_code")String m_code);

  @FormUrlEncoded
  @POST("api/Points/all_member_points")
  Call<Wallet> get_points_in_wallet(@Field("m_code")String m_code);

  @FormUrlEncoded
  @POST("api/Auth/update_token")
  Call<UpdateTokenModel> update_token(@Field("member_id")String m_code, @Field("token")String token);


  @POST("api/rest/version/61/merchant/TESTCIB701481/session")
  Call<Auth> auth();

  @FormUrlEncoded
  @POST("api/Subcriptions/get_session")
  Call<Auth> get_session(@Field("order_id")String order_id,
                         @Field("amount")String amount);

  @GET("api/Subcriptions/main_branches")
  Call<BranchModel> get_branches();

  @FormUrlEncoded
  @POST("api/Subcriptions/add_subscription")
  Call<AddSubscriptionModel> add_subscription(@Field("user_id")String user_id,
                                              @Field("branch_id_fk")String branch_id_fk,
                                              @Field("subscription_type")String subscription_type,
                                              @Field("cost")String cost,
                                              @Field("from_date")String from_date,
                                              @Field("to_date")String to_date,
                                              @Field("male_or_female")String male_or_female);

}
