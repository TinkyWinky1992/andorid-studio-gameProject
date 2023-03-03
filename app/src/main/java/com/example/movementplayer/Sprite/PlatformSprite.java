package com.example.movementplayer.Sprite;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.movementplayer.R;
/**Snipping the tilemapObjet image  to Sprites*/
public class  PlatformSprite   {
    Bitmap bitmap;
    public PlatformSprite(Context context,int sWidht,int sHeight){
    BitmapFactory.Options bitmapPlayerOptions = new BitmapFactory.Options();
    bitmapPlayerOptions.inScaled=false;
    this.bitmap=BitmapFactory.decodeResource(context.getResources(), R.drawable.maptile,bitmapPlayerOptions);
    this.bitmap=Bitmap.createScaledBitmap(this.bitmap,sWidht,sHeight,true);

    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
