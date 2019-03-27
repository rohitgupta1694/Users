package com.workex.userprofile.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.workex.userprofile.R

class UserDetailsActivity : AppCompatActivity() {

    //region Override Methods

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)
    }

    //endregion

}