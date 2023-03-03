package com.example.movementplayer.ImageSnippingTool;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.movementplayer.Sprite.PlatformSprite;

public class PlatformView {
        private final PlatformSprite Platformsprite;
        
    public PlatformView(PlatformSprite  playerPicture) {

            this.Platformsprite=playerPicture;
        }
        public void draw(Canvas canvas, double positionX, double positionY) {
            canvas.drawBitmap(Platformsprite.getBitmap(), null,
                    new Rect((int)positionX, (int)positionY, (int)positionX + Platformsprite.getBitmap().getWidth(), (int)positionY +Platformsprite.getBitmap().getHeight()), null);
        }


    public PlatformSprite getPlatformsprite() {
        return Platformsprite;
    }
}

