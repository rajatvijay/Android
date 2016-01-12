package www.rajat.gnosis.com.gnosis;


import com.firebase.client.Firebase;

/**
 * Created by Owner on 12-01-2016.
 */
public class GnosisApplication extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //Firebase Connect
        Firebase.setAndroidContext(this);
    }
}
