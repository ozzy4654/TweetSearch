package kalan.ozan.tweetsearch;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.*;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;

import butterknife.*;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nobrain.android.permissions.AndroidPermissions;
import kalan.ozan.tweetsearch.api.TweetSearchApi;
import kalan.ozan.tweetsearch.api.model.MyData;
import kalan.ozan.tweetsearch.dialogs.*;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity
        implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final String CONSUMER_KEY = "fx95oKhMHYgytSBmiAqQ";
    private static final String CONSUMER_SEC = "0zfaijLMWMYTwVosdqFTL3k58JhRjZNxd2q0i9cltls";
    private static final String TWITTER_KEY  = "2305278770-GGw8dQQg3o5Vqfx9xHpUgJ0CDUe3BoNmUNeWZBg";
    private static final String TWITTER_SEC  = "iEzxeJjEPnyODVcoDYt5MVvrg90Jx2TOetGdNeol6PeYp";
    private static final String KEYYY        = "fx95oKhMHYgytSBmiAqQ:0zfaijLMWMYTwVosdqFTL3k58JhRjZNxd2q0i9cltls";
    private static final String ENOCDED_KEYY = "Basic Zng5NW9LaE1IWWd5dFNCbWlBcVE6MHpmYWlqTE1XTVlUd1Zvc2RxRlRMM2s1OEpoUmpaTnhkMnEwaTljbHRscw==";
    private static final String ENCODE_TOKEN = "Bearer AAAAAAAAAAAAAAAAAAAAAO8gVwAAAAAAznRZHDqt6%2BqK1RXG%2Bo7kTaf8WJ8%3DcSIDn8O44zxU2DoWv9MCDliX5ConzhinKrhD5k5u23oSl8DNVY";

    private GoogleApiClient mGoogleApiClient;
    private boolean         start;
    private String          querySearch;

    public PackageManager packageManager;
    public Progress       mProgress;
    public static final int REQUEST_CODE = 102;

    boolean askedBefore = false;

    @Bind(R.id.title_text)    TextView mSubTitle;
    @Bind(R.id.search_field) EditText mSearch;
    @Bind(R.id.search_btn)   Button   mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        packageManager = getPackageManager();

        if (packageManager.checkPermission(
                Manifest.permission.ACCESS_FINE_LOCATION,
                getPackageName()) == PackageManager.PERMISSION_DENIED) {

            checkPermission();
        }

        mSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(mSearch.getWindowToken(), 0);
                    searchTwitter();
                    return true;
                } else {
                    return false;
                }
            }
        });

        if (getIntent().hasCategory(Intent.CATEGORY_LAUNCHER)) {
            start = true;
        } else {
            start = false;
        }
    }


    @OnClick(R.id.search_field)
    public void clearSearch() {
        mSearch.setText("");
    }


    @OnClick(R.id.search_btn)
    public void searchTwitter() {
        showProgress();
        mBtn.setClickable(false);
        mSearch.setEnabled(false);

        Gson gson = new GsonBuilder().create();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(getString(R.string.base_url))
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        TweetSearchApi twitterApi = restAdapter.create(TweetSearchApi.class);

        Call<MyData> call = twitterApi.statusList(mSearch.getText().toString(), querySearch);

        call.enqueue(new Callback<MyData>() {

            @Override public void onResponse(Call<MyData> call, Response<MyData> response) {

                if (response.isSuccessful()) {
                    // request successful (status code 200, 201)
                    if(response.body().statuses.isEmpty()) {
                        error(getString(R.string.no_results));
                    }else {
                        String result = gson.toJson(response.body());
                        stIntent(result, querySearch);
                    }

                } else {
                    //request not successful (like 400,401,403 etc)
                    error(response.errorBody().toString());
                }
            }
            @Override public void onFailure(Call<MyData> call, Throwable t) {
                error(t.getMessage());
            }
        });

    }

    public void stIntent(String mdata, String querySearch) {
        mBtn.setClickable(true);
        mSearch.setEnabled(true);
        Intent intent = new Intent(this, TweetResultsActivity.class);
        intent.putExtra(getString(R.string.data), mdata);
        intent.putExtra(getString(R.string.geo), querySearch);
        mProgress.dismiss();
        startActivity(intent);
    }

    public final void error(String msg) {
        mProgress.dismiss();
        SearchError searchError = new SearchError();

        Bundle bundle = new Bundle();
        bundle.putString(getString(R.string.msg_error_key), msg);

        searchError.setArguments(bundle);
        searchError.show(getSupportFragmentManager(), "");
        mBtn.setClickable(true);
        mSearch.setEnabled(true);
    }

    public void showProgress() {
        if (mProgress == null) {
            mProgress = new Progress();
        }
        mProgress.show(getSupportFragmentManager(), "progress");
    }

    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    public void checkPermission() {
        packageManager = getPackageManager();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            boolean showRationale = shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION);

            if (showRationale) {

                RationalPermissionDialog gps = new RationalPermissionDialog();
                gps.show(getSupportFragmentManager(), "");

            } else {
                if (showRationale == false && askedBefore == true && (packageManager.checkPermission(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        getPackageName()) == PackageManager.PERMISSION_DENIED)) {

                    Snackbar snackbar = Snackbar.make(mSubTitle, getResources().getString(
                            R.string.message_no_location_permission_snackbar), Snackbar.LENGTH_INDEFINITE)
                            .setDuration(15000);

                    snackbar.setAction(getResources().getString(R.string.settings), v -> {
                        getSettingsIntent();
                    }).show();
                } else {
                    askedBefore = true;
                    AndroidPermissions.check(this)
                            .permissions(Manifest.permission.ACCESS_FINE_LOCATION)
                            .noPermissions(permissions -> ActivityCompat
                                    .requestPermissions(this,
                                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                                        REQUEST_CODE))
                            .check();
                }
            }
        }
    }

    public static Intent getSettingsIntent() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", BuildConfig.APPLICATION_ID, null);
        intent.setData(uri);
        return intent;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, final String[] permissions, int[] grantResults) {
        AndroidPermissions.result(this)
                .addPermissions(REQUEST_CODE, Manifest.permission.ACCESS_FINE_LOCATION)
                .putActions(REQUEST_CODE, () -> Snackbar.make(mSubTitle, R.string.granted, Snackbar.LENGTH_LONG).show(),
                            (hasPermissions, noPermissions) -> Snackbar.make(mSubTitle, R.string.deny,
                                                                             Snackbar.LENGTH_LONG).show())
                .result(requestCode, permissions, grantResults);
    }

    @Override
    public void onBackPressed() {
        super.finish();
    }

    @Override public void onConnected(Bundle bundle) {
        Location mLastLocation;
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);

        if (mLastLocation != null && start) {
            querySearch = (mLastLocation.getLatitude() + ","
                    + mLastLocation.getLongitude() + "," + "5mi");
            start = false;

        }
    }

    @Override public void onConnectionSuspended(int i) {

    }

    @Override public void onConnectionFailed(ConnectionResult connectionResult) {
    }
}
