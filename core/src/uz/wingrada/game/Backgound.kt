package uz.wingrada.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2

open class Backgound (private var speed: Int = 4) {

    private val heightScreen = Gdx.graphics.height.toFloat()
    private val widthScreen = Gdx.graphics.width.toFloat()

    private val bgPicture = arrayOf(BGPicture(), BGPicture(position = Vector2(widthScreen, 0f)))
    private val bgpic:BGPicture = BGPicture()

    inner class BGPicture(var texture: Texture = Texture("SkyBackground.png"),
                          var position: Vector2 = Vector2(0f, 0f)
                         )


    public fun render(batch: SpriteBatch){
        loopBackground(batch)
    }

    public fun update(){
        loopMotionBackground()
        moveBackgroundToEndScreen()
    }

    private fun loopMotionBackground(){
        for (i in bgPicture.indices){
            bgPicture[i].position.x -= speed
        }

    }

    private fun moveBackgroundToEndScreen(){
       if (bgPicture[0].position.x < -widthScreen){
           bgPicture[0].position.x = 0f
           bgPicture[1].position.x = widthScreen
       }
    }

    private fun loopBackground(batch: SpriteBatch){
        for (i in bgPicture.indices){
            batch.draw(bgPicture[i].texture, bgPicture[i].position.x, bgPicture[i].position.y, widthScreen, heightScreen)
        }
    }
}