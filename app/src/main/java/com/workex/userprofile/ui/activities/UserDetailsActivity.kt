package com.workex.userprofile.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.workex.userprofile.R
import com.workex.userprofile.model.User
import kotlinx.android.synthetic.main.activity_user_details.*

class UserDetailsActivity : AppCompatActivity() {

    //region Variables

    companion object {
        val USER_DETAILS: String = MainActivity::class.java.simpleName
    }

    lateinit var user: User

    //endregion

    //region Override Methods

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        if (intent != null && intent.getParcelableExtra<User>(USER_DETAILS) != null) {
            user = intent.getParcelableExtra<User>(USER_DETAILS)
        }

        initializeViews()

    }

    //endregion

    //region View Initialization Methods

    private fun initializeViews() {
        detailActivityToolbarText.text = "@" + user.userName
        detailActivityUserFullName.text = user.firstName + user.lastName
        Glide.with(detailActivityUserImage.context)
            .load(user.avatar)
            .into(detailActivityUserImage)
    }

    //endregion

}