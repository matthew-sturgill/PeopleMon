package mattsturgill.peoplemongo.Network;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by matthewsturgill on 11/7/16.
 */

public class SessionRequestInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        if (UserStore.getInstance().getToken() != null) {
            Request.Builder builder = request.newBuilder();
            builder.header("Authorization", "bearer " + UserStore.getInstance().getToken());
            request = builder.build();

        }
        return chain.proceed(request);
    }
}
