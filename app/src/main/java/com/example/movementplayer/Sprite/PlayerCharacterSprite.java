package com.example.movementplayer.Sprite;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.example.movementplayer.ImageSnippingTool.PlayerCharacter;
import com.example.movementplayer.R;
/**Snipping the statuplayerfour image  to Sprites*/
public class PlayerCharacterSprite  {
    Bitmap bitmap;
    private int PlayerSize;
    public PlayerCharacterSprite(Context context,int sWidht,int sHeight) {

        BitmapFactory.Options bitmapPlayerOptions = new BitmapFactory.Options();
        bitmapPlayerOptions.inScaled=false;

        this.bitmap=BitmapFactory.decodeResource(context.getResources(), R.drawable.playerstategame,bitmapPlayerOptions);
        double Ratio=(double) sWidht/bitmap.getWidth()*3;
        this.PlayerSize= (int) (this.bitmap.getHeight()*Ratio);
        this.bitmap=Bitmap.createScaledBitmap(this.bitmap,PlayerSize*52,PlayerSize,true);
    }
    //set different image to the array
    public PlayerCharacter[] getPlayerSprite(){
        /**Snipping the image and place them in a array*/
        PlayerCharacter[] playerCharactersArray= new PlayerCharacter[53];
        playerCharactersArray[0]=new PlayerCharacter(this,new Rect(0,0, this.PlayerSize,this.PlayerSize));
        for(int i=1; i< playerCharactersArray.length;i++){
            playerCharactersArray[i]=new PlayerCharacter(this,new Rect(i*this.PlayerSize,0,(i+1)*this.PlayerSize,this.PlayerSize));
        }
        return playerCharactersArray;
    }

    public Bitmap getBitmap() {
        return  bitmap;
    }
}
