package com.caiqueponjjar.cuco

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.caiqueponjjar.cuco.R.*
import com.caiqueponjjar.cuco.helper.usuario
import com.caiqueponjjar.cuco.ui.login.LoginActivity
import android.R
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction


class SelectLogin : AppCompatActivity() {

    private lateinit var videoview : VideoView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_select_login)
        videoview = findViewById<View>(id.backgroundVideo) as VideoView
        val uri: Uri = Uri.parse("android.resource://" + packageName + "/" + raw.cuckoofootage)
        videoview.setVideoURI(uri)
        val metrics = DisplayMetrics()
        this.getWindowManager().getDefaultDisplay().getRealMetrics(metrics)

        // (getContext() as Activity).windowManager.defaultDisplay.getRealMetrics(metrics)
        //windowManager.defaultDisplay.getMetrics(metrics)
        val params = videoview.getLayoutParams()
        //params.leftMargin = 0
        videoview.setOnPreparedListener { mp: MediaPlayer ->

            val mVideoHeight = 720

            val mVideoWidth = 1280

            mp.setLooping(true)
            mp.setVolume(0.0F ,0.0F)
            if(metrics.heightPixels > metrics.widthPixels) {
                params.height =metrics.heightPixels
                params.width = metrics.heightPixels * ( metrics.heightPixels/ mVideoHeight)
            }else{
                params.height = metrics.widthPixels * metrics.widthPixels / mVideoHeight
                params.width =  metrics.widthPixels
            }
            videoview.setLayoutParams(params)

        }

        videoview.start()
        val myFadeInAnimation: Animation = AnimationUtils.loadAnimation(this, anim.fadein)
        findViewById<ImageView>(id.LogoImage).startAnimation(myFadeInAnimation) //Set animation to your ImageView

        val WithGoogleButton = findViewById<Button>(id.withGoogle)
        WithGoogleButton.setOnClickListener {
            startActivity(Intent(this, withGoogle::class.java))

        }
        val LoginButton = findViewById<Button>(id.login)
            LoginButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        //Reproduz animação
        val fadeInDuration = 500 // Configure time values here

    }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = usuario().getUser()
        if(currentUser != null || usuario().getAccount(this) != null){
            
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
    override fun onResume() {
        super.onResume()
        videoview.start()
    }

}