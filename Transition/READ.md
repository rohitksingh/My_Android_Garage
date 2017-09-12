# Reveal Animation.

![687474703a2f2f692e696d6775722e636f6d2f5a4e6c344642322e676966](https://user-images.githubusercontent.com/11274840/30338466-a2801128-97a0-11e7-988d-137ca0557a1c.gif)


The only thing you are going to use is this method.   

**ViewAnimationUtils.createCircularReveal(layout, centerX, centerY, startRadius, endRadius);**

Lets break down this method into bits and pieces. The parameter used are 
layout : which will be animated

- **centerX** : x- coordinate on the layout
- **centerY** : y- coordinate on the layout
- `**startRadius** : Should be 0.
- **endRadius** :  Calculate like this. Its simple math 
                  
`double endRadius = Math.hypot(layout.getWidth(), layout.getHeight());`

And you are done 

Just call this method based on your life choices :) 

### **Bonus**

**Reverse of the Reveal Animation**

Just interchange the startRadius and endRadius with each other
`ViewAnimationUtils.createCircularReveal(layout, centerX, centerY, startRadius, endRadius);`

###Note 
Since This Transition Api was introduced in API 21.   
So you will have to add the check condition 

`if(Build.VERSION.SDK_INT>20) {
 ViewAnimationUtils.createCircularReveal(layout, centerX, centerY, startRadius, endRadius);
}`

You can obviously add transition time , interPolator etc based on your reqiurement. You can see full [code](https://github.com/rohitksingh/My_Android_Garage/blob/master/Transition/app/src/main/java/com/omdb/rohksin/transitionapi/MainActivity.java) implementation.

