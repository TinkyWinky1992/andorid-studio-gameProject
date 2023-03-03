package com.example.movementplayer.Sprite;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.example.movementplayer.ImageSnippingTool.HealingBarView;
import com.example.movementplayer.R;

public class HealingBarSprite {
    Bitmap bitmap;
    private int HealthBarSizeHeight;
    private int HealthBarSizeWidth;

    public HealingBarSprite(Context context,int sWidht,int sHeight) {
        BitmapFactory.Options bitmapPlayerOptions = new BitmapFactory.Options();
        bitmapPlayerOptions.inScaled=false;
        this.bitmap=BitmapFactory.decodeResource(context.getResources(), R.drawable.barhealth,bitmapPlayerOptions);
        double RatioHeight=(double) sHeight/bitmap.getHeight()*0.050;
        this.HealthBarSizeHeight= (int) (this.bitmap.getHeight()*RatioHeight);
        double RatioWidth=(double) sWidht/bitmap.getWidth()*0.1;
        this.HealthBarSizeWidth=(int)(this.bitmap.getWidth()*RatioWidth);
        this.bitmap=Bitmap.createScaledBitmap(this.bitmap,this.HealthBarSizeWidth*5,this.HealthBarSizeHeight,true);




    }
    //set different image to the array
    public HealingBarView[] getHealingBarView(){
        /**Snipping the image and place them in a array*/
        HealingBarView[] HealingBarArray= new HealingBarView[5];
        HealingBarArray[0]=new HealingBarView(this,new Rect(0,0, HealthBarSizeWidth,HealthBarSizeHeight));
        for(int i=1; i< HealingBarArray.length;i++){
            HealingBarArray[i]=new HealingBarView(this,new Rect(i*HealthBarSizeWidth,0,(i+1)*HealthBarSizeWidth,HealthBarSizeHeight));
        }
        return HealingBarArray;
    }

    public Bitmap getBitmap() {
        return  bitmap;
    }
}

