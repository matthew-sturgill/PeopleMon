package mattsturgill.peoplemongo.Network;

import mattsturgill.peoplemongo.Models.CatchUser;
import mattsturgill.peoplemongo.Models.CheckIn;
import mattsturgill.peoplemongo.Models.Message;
import mattsturgill.peoplemongo.Models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by matthewsturgill on 11/7/16.
 */

public interface ApiService {

    @FormUrlEncoded
    @POST("token")
    Call<User> login(@Field("username") String email,
                     @Field("password") String password,
                     @Field("grant_type") String grantType);

    @POST("/api/Account/Register")
    Call<Void> register(@Body User user);

    @POST("/v1/User/CheckIn")
    Call<Void> checkIn(@Body CheckIn checkIn);

    @POST ("/v1/User/Catch")
    Call <Void> catchUser(@Body CatchUser catchUser);

    @POST ("/v1/User/Conversation")
    Call<Void> Conversation(@Body Message message);



  //  @POST("api/Account/ChangePassword")
  //  Call<User> changePassword (@Field("OldPassword") String oldpassword,
    //                           @Field("NewPassword") String newpassword,
      //                         @Field("ConfirmPassword") String confirmpassword);


 //   @POST("api/Account/UserInfo")
   // Call<Void> changenameavatar (@Field("FullName") String fullname,
     //                            @Field("AvatarBase64") String avatar);

  //  @GET("api/Account/UserInfo")
 //   Call<User> changenameavatar (@Field("Id") String id,
   //                              @Field("Email") String email,
     //                            @Field("HasRegister") Boolean isRegistered,
       //                          @Field("LoginProvider") String loginprovider,
         //                        @Field("FullName") String fullname,
           //                      @Field("AvatarBase64") String avatar,
             //                    @Field("LastCheckInLongitude") Double checkInLongitude,
               //                  @Field("LastCheckInLatitude") Double checkInLatitude
                                 //need date
                 //                );

}
