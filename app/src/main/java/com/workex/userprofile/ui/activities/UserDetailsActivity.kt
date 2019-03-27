package com.workex.userprofile.ui.activities

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener
import com.workex.userprofile.R
import com.workex.userprofile.model.User
import kotlinx.android.synthetic.main.activity_user_details.*


class UserDetailsActivity : AppCompatActivity() {

    //region Variables

    companion object {
        val USER_DETAILS: String = MainActivity::class.java.simpleName
        const val EXTRA_USER_IMAGE_TRANSITION_NAME: String = "USER_IMAGE_TRANSITION_NAME"
        const val EXTRA_USER_FULL_NAME_TRANSITION_NAME: String = "USER_FULL_NAME_TRANSITION_NAME"
        const val EXTRA_USER_NAME_TRANSITION_NAME: String = "USER_NAME_TRANSITION_NAME"
    }

    lateinit var user: User
    lateinit var imageTransitionName: String
    lateinit var userNameTrasitionName: String
    lateinit var userFullNameTransitionName: String

    //endregion

    //region Override Methods

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        if (intent != null && intent.getParcelableExtra<User>(USER_DETAILS) != null) {
            user = intent.getParcelableExtra(USER_DETAILS)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                imageTransitionName =
                    intent.getStringExtra(EXTRA_USER_IMAGE_TRANSITION_NAME)
                userNameTrasitionName =
                    intent.getStringExtra(EXTRA_USER_NAME_TRANSITION_NAME)
                userFullNameTransitionName =
                    intent.getStringExtra(EXTRA_USER_FULL_NAME_TRANSITION_NAME)
            }
        }

        initializeViews()

    }

    //endregion

    //region View Initialization Methods

    private fun initializeViews() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            detailActivityUserImage.transitionName = imageTransitionName
            detailActivityToolbarText.transitionName = userNameTrasitionName
            detailActivityUserFullName.transitionName = userFullNameTransitionName
        }

        detailActivityToolbarText.text = "@" + user.userName
        detailActivityUserFullName.text = user.firstName + " " + user.lastName
        Glide.with(detailActivityUserImage.context)
            .load(user.avatar)
            .listener(object : RequestListener<String, GlideDrawable> {
                override fun onException(
                    e: Exception?,
                    model: String?,
                    target: com.bumptech.glide.request.target.Target<GlideDrawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    supportStartPostponedEnterTransition()
                    return false
                }

                override fun onResourceReady(
                    resource: GlideDrawable?,
                    model: String?,
                    target: com.bumptech.glide.request.target.Target<GlideDrawable>?,
                    isFromMemoryCache: Boolean,
                    isFirstResource: Boolean
                ): Boolean {
                    supportStartPostponedEnterTransition()
                    return false
                }
            })
            .into(detailActivityUserImage)
    }

    //endregion

}