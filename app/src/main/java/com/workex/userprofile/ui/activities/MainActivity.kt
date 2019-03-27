package com.workex.userprofile.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.workex.userprofile.BuildConfig
import com.workex.userprofile.R
import com.workex.userprofile.model.User
import com.workex.userprofile.network.UserApiService
import com.workex.userprofile.ui.adapters.UsersListAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.user_list_item.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity(), UsersListAdapter.OnListItemClickListener {

    //region Variables

    private val TAG = MainActivity::class.java.simpleName

    private val BASE_URL = "https://us-central1-workex-swiggy-demo.cloudfunctions.net"

    private var compositeDisposable: CompositeDisposable? = null

    private var usersList: ArrayList<User>? = null

    private var userListAdapter: UsersListAdapter? = null

    //endregion

    //region Override Methods

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeViews()
        getUsersList()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable?.clear()
    }

    //endregion

    //region Initialization Methods

    private fun initializeViews() {
        toolbarText.text = getString(R.string.app_name)

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        usersRecyclerView.layoutManager = layoutManager
        progressBar.visibility = View.VISIBLE
    }

    //endregion

    //region API Calls

    private fun getUsersList() {
        val userApiService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHTTPConfigurations())
            .build().create(UserApiService::class.java)

        userApiService.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handleResponse, this::handleError)

    }

    private fun handleResponse(userList: List<User>) {

        usersList = ArrayList(userList)
        userListAdapter = UsersListAdapter(usersList!!, this)

        usersRecyclerView.adapter = userListAdapter
        progressBar.visibility = View.GONE
    }

    private fun handleError(error: Throwable) {
        Log.d(TAG, error.localizedMessage)
        Toast.makeText(this, "Error ${error.localizedMessage}", Toast.LENGTH_SHORT).show()
    }

    //endregion

    //region Implemented Methods

    override fun onItemClick(
        user: User, profileImageView: ImageView,
        userNameTextView: TextView, userFullNameTextView: TextView
    ) {
        val intent = Intent(this, UserDetailsActivity::class.java)
        // Pass data object in the bundle and populate User details activity.
        intent.putExtra(UserDetailsActivity.USER_DETAILS, user)
        intent.putExtra(
            UserDetailsActivity.EXTRA_USER_IMAGE_TRANSITION_NAME,
            ViewCompat.getTransitionName(profileImageView)
        )
        intent.putExtra(
            UserDetailsActivity.EXTRA_USER_NAME_TRANSITION_NAME,
            ViewCompat.getTransitionName(userNameTextView)
        )
        intent.putExtra(
            UserDetailsActivity.EXTRA_USER_FULL_NAME_TRANSITION_NAME,
            ViewCompat.getTransitionName(userFullNameTextView)
        )

        val p1 = Pair.create<View, String>(
            userProfileImage, ViewCompat.getTransitionName(profileImageView)
        )
        val p2 = Pair.create<View, String>(userName, ViewCompat.getTransitionName(userNameTextView))
        val p3 = Pair.create<View, String>(
            userFullName, ViewCompat.getTransitionName(userFullNameTextView)
        )
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this, p1, p2, p3
        )
        startActivity(intent, options.toBundle())
    }

    //endregion

    //region OkHTTP Configuration

    private fun getOkHTTPConfigurations(): OkHttpClient {
        val oKBuilder = OkHttpClient.Builder()
        val logging = HttpLoggingInterceptor { message -> Log.d("OKHTTP", message) }
        logging.level = if (BuildConfig.BUILD_TYPE.equals("debug"))
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
        oKBuilder.addInterceptor(logging)
        oKBuilder.connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
        return oKBuilder.build()
    }

    //endregion

}
