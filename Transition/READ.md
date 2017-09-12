# Reveal Animation.


ViewAnimationUtils.createCircularReveal(layout, centerX, centerY, startRadius, endRadius); needs to be called to apply circularReveal Animation.

lets take a look into the parameters used 

centerX,CenterY are the cordinates from where the reveal animation starts 
startRadius should be 0.
endRadius you calculate like this. Its a simple Math 
double endRadius = Math.hypot(layout.getWidth(), layout.getHeight());

And you are done 

Just call this method based on your life choices :) 

Bonus
Reverse of the Reveal Animation

just interchange the startRadius and endRadius with each other
ViewAnimationUtils.createCircularReveal(layout, centerX, centerY, startRadius, endRadius); needs to be called to apply circularReveal .


Note 
Since This Transition Api was introduced in API 21
So you will have to add the check condition 
if(Build.VERSION.SDK_INT>20) {
 ViewAnimationUtils.createCircularReveal(layout, centerX, centerY, startRadius, endRadius);
}

You can obviously Add transition time , InterPolator etc You can find it in Full code implementation.


Bonus Again
Adding Listeners to Your Animator 
animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    layout.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });



