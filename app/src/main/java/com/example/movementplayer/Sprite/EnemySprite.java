package com.example.movementplayer.Sprite;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.example.movementplayer.ImageSnippingTool.EnemyView;
import com.example.movementplayer.ImageSnippingTool.PlayerCharacter;
import com.example.movementplayer.R;

public class EnemySprite {
    Bitmap bitmap;
    private int EnemySize;
    public EnemySprite(Context context, int sWidht, int sHeight) {

        BitmapFactory.Options bitmapPlayerOptions = new BitmapFactory.Options();
        bitmapPlayerOptions.inScaled=false;

        this.bitmap=BitmapFactory.decodeResource(context.getResources(), R.drawable.enemystate,bitmapPlayerOptions);
        double Ratio=(double) sWidht/bitmap.getWidth()*2;
        this.EnemySize= (int) (this.bitmap.getHeight()*Ratio);
        this.bitmap=Bitmap.createScaledBitmap(this.bitmap,EnemySize*38,EnemySize,true);
    }
    //set different image to the array
    public EnemyView[] getEnemySprite(){
        /**Snipping the image and place them in a array*/
        EnemyView[] EnemyArray= new EnemyView[39];
        EnemyArray[0]=new EnemyView(this,new Rect(0,0, this.EnemySize,this.EnemySize));
        for(int i=1; i< EnemyArray.length;i++){
            EnemyArray[i]=new EnemyView(this,new Rect(i*this.EnemySize,0,(i+1)*this.EnemySize,this.EnemySize));
        }
        return EnemyArray;
    }

    public Bitmap getBitmap() {
        return  bitmap;
    }
}
