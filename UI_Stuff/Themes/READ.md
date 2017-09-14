# **Decorate Your Text with Custom Font**

![4564android-custom-fonts](https://user-images.githubusercontent.com/11274840/30446253-6ce568a8-993d-11e7-82db-97fd7ca6bdf7.jpg)




**Follow these simple steps**

### **Download .ttf file** 
Download .ttf file of  font style you want from internet (Just google it there are plenty of sites which will give you tons of fonts free of cost eg Oswald-Stencbab.ttf ).

### **Upload your font file in your local**
Now this is the part you need to be extra careful. You should upload your .ttf file in this folder
**/app/src/main/assets/fonts/**
if you don't see fonts folder under assets then make a new folder and name it fonts and then paste or drag and drop your .ttf file 

![androidcustomtextview4](https://user-images.githubusercontent.com/11274840/30446197-42af3d02-993d-11e7-9994-50e550c70c31.png)

**Now the custom font is available now in your local you can use it anytime.** 

But how ?
Like this 
### **Set typeface to your textView**
  
    TextView text;     
    Typeface customTypeFace=Typeface.createFromAsset(getResources().getAssets(),"fonts/Oswald-Stencbab.ttf");
    text.setTypeface(customTypeFace);`

That is all you need to do.
