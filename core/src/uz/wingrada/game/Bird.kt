package uz.wingrada.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2

class Bird {

    private val startPositionBirdX: Float = 1197f//(Gdx.graphics.height / 2).toFloat()
    private val startPositionBirdY: Float = (Gdx.graphics.width / 4.5).toFloat()

    public val position: Vector2 = Vector2(startPositionBirdX, startPositionBirdY)
    private val img: Texture = Texture("bird.png")

    private var vectorY = 0f
    private val gravity = -0.7f

    public fun render(batch: SpriteBatch){
        batch.draw(img, position.x, position.y)
    }

    public fun update(){
        applyGravity()
    }

    private fun applyGravity(){
        if(Gdx.input.isTouched){
            vectorY = 9f
        }
        vectorY += gravity
        position.y += vectorY
    }
}