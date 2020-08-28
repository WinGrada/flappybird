package uz.wingrada.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2

class Bird(private val img: Texture = Texture("bird.png")) {

    private val startPositionBirdX: Float = (Gdx.graphics.height / 2).toFloat()
    private val startPositionBirdY: Float = (Gdx.graphics.width / 4.5).toFloat()

    private val position: Vector2 = Vector2(startPositionBirdX, startPositionBirdY)

    var vectorY = 0f
    val gravity = -0.7f

    public fun render(batch: SpriteBatch){
        batch.draw(img, position.x, position.y)
    }

    public fun update(){
        applyGravity()
    }

    private fun applyGravity(){
        if(Gdx.input.isTouched){
            vectorY = 10f
        }
        vectorY += gravity
        position.y += vectorY
    }
}