package com.example.galdino.filmespopulares.dominio;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.galdino.filmespopulares.R;


/**
 * Created by Galdino on 06/06/2017.
 */

public class AnimationControler
{
    // To reveal a previously invisible view using this effect:
    public static void CircularRevelShow(final View botao, final View layout) {
        botao.setEnabled(false);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            // get the center for the clipping circle
            int cx = (botao.getLeft() + botao.getRight()) / 2;
            int cy = (botao.getTop() + botao.getBottom()) / 2;

            // get the final radius for the clipping circle
            int finalRadius = Math.max(layout.getWidth(), layout.getHeight());

            // create the animator for this view (the start radius is zero)
            Animator anim = ViewAnimationUtils.createCircularReveal(layout, cx, cy, 0, finalRadius);
//            anim.setInterpolator(new LinearOutSlowInInterpolator());
            anim.setDuration(250);

            // make the view visible and start the animation
            layout.setVisibility(View.VISIBLE);
            anim.start();
            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    botao.setEnabled(true);
                }
            });
        }
        else
        {
            layout.setVisibility(View.VISIBLE);
            botao.setEnabled(true);
        }
    }

    // To CircularRevelHide a previously visible view using this effect:
    public static void CircularRevelHide(final View botao, final View layout) {
        botao.setEnabled(false);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            // get the center for the clipping circle
            int cx = (botao.getLeft() + botao.getRight()) / 2;
            int cy = (botao.getTop() + botao.getBottom()) / 2;

            // get the initial radius for the clipping circle
            int initialRadius = layout.getWidth();

            // create the animation (the final radius is zero)
            Animator anim = ViewAnimationUtils.createCircularReveal(layout, cx, cy, initialRadius, 0);
//            anim.setInterpolator(new LinearOutSlowInInterpolator());
            anim.setDuration(250);

            // make the view invisible when the animation is done
            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    layout.setVisibility(View.INVISIBLE);
                    botao.setEnabled(true);
                }
            });

            // start the animation
            anim.start();
        }
        else
        {
            layout.setVisibility(View.INVISIBLE);
            botao.setEnabled(true);
        }
    }

    public static void translateShow(View view, Context context)
    {
        Animation animationTranslate = AnimationUtils.loadAnimation(context, R.anim.anim_translate_in);
        view.setAnimation(animationTranslate);
        view.setVisibility(View.VISIBLE);
        animationTranslate.start();
    }

    public static void translateHide(View view, Context context)
    {
        Animation animationTranslate = AnimationUtils.loadAnimation(context, R.anim.anim_translate_out);
        view.setAnimation(animationTranslate);
        view.setVisibility(View.INVISIBLE);
        animationTranslate.start();
    }

    public static void upShowView(View view, Context context)
    {
        Animation animacaoIn = AnimationUtils.loadAnimation(context, R.anim.anim_in_up);
        view.setAnimation(animacaoIn);
        view.setVisibility(View.VISIBLE);
        animacaoIn.start();
    }

    public static void downHideView(View view, Context context)
    {
        Animation animacaoIn = AnimationUtils.loadAnimation(context, R.anim.anim_out_down);
        view.setAnimation(animacaoIn);
        view.setVisibility(View.GONE);
        animacaoIn.start();
    }
}
