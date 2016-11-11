package mattsturgill.peoplemongo.Network;

import mattsturgill.peoplemongo.Models.OtherUsers;
import mattsturgill.peoplemongo.Models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
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
    Call<User> checkIn(@Body User user);

    @GET("/v1/User/Caught")
    Call<OtherUsers> listCaught (@Body OtherUsers otherUsers);

    @GET ("/v1/User/Nearby")
    Call<OtherUsers> userNearbyQuery (@Body OtherUsers otherUsers);

}
