package com.qucoon.myhmo.views.UitilityView;


import android.app.Activity;
import android.app.Dialog;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.qucoon.myhmo.R;

public class ViewDialog {

    Activity activity;
    Dialog dialog;

    public ViewDialog(Activity activity) {
        this.activity = activity;

        dialog  = new Dialog(activity,R.style.ThemeOverlay_AppCompat_Light);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.custom_loading_layout);
        dialog.getWindow().setBackgroundDrawableResource(R.color.transparentBlue);


    }





    public void showDialog() {


        ImageView gifImageView = dialog.findViewById(R.id.suntrustgiflogo);

        /*
        it was never easy to load gif into an ImageView before Glide or Others library
        and for doing this we need DrawableImageViewTarget to that ImageView
        */

        // GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(gifImageView);

        //...now load that gif which we put inside the drawble folder here with the help of Glide




        //    fun initView(){
//        //  rotate image view animation
//        val anim =
//            RotateAnimation(0.0f, 360.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
//        anim.interpolator = LinearInterpolator()
//        anim.repeatCount = Animation.INFINITE
//        anim.duration = 700
//        imageView.startAnimation(anim)
//    }

//        Glide.with(activity)
//                .load(R.drawable.keygiflogo)
//           //     .placeholder(R.drawable.suntrustgiflogo)
//            //    .centerCrop()
//            //    .crossFade()
//                .into((new DrawableImageViewTarget(gifImageView)));

        //...finaly show it


        RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(8000);
        rotate.setInterpolator(new LinearInterpolator());
        rotate.setRepeatCount(Animation.INFINITE);
        rotate.setDuration(1700);
        gifImageView.startAnimation(rotate);

        dialog.show();
    }




    //..also create a method which will hide the dialog when some work is done
    public void hideDialog(){
        dialog.dismiss();
    }

}
