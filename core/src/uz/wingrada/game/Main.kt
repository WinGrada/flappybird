package uz.wingrada.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch

var heightScreen: Float = 0.0f
var widthScreen: Float = 0.0f

@ExperimentalStdlibApi
class Main : ApplicationAdapter() {
    private lateinit var batch: SpriteBatch
    private lateinit var background: Background
    private lateinit var bird: Bird
    private lateinit var obstacles: Obstacles
    private var gameOver: Boolean = true
    private lateinit var restartTexture: Texture



    private val positionXRestartTexture: Float = widthScreen / 2 - 465 / 2
    private val positionYRestartTexture: Float = heightScreen / 2 - 241 / 2



    override fun create() {
        setup()
        background = Background()
        batch = SpriteBatch()
        bird = Bird()
        obstacles = Obstacles()
        gameOver = false
        restartTexture = Texture("restart_btn.png")
    }

    override fun render() {
        update()
        batch.begin()
        isGameOver()
        batch.end()
    }

    private fun update(){
        background.update()
        bird.update()
        obstacles.update()
    }

    override fun dispose() {
        batch.dispose()
    }

    private fun isGameOver(){
        if (gameOver){
            setGameOver()
        }
        setStartGame()
    }

    private fun setStartGame() {
        background.render(batch)
        bird.render(batch)
        obstacles.render(batch)
    }

    private fun setGameOver() {
        batch.draw(restartTexture, positionXRestartTexture, positionYRestartTexture)
    }

    private fun setup(){
        heightScreen = Gdx.graphics.height.toFloat()
        widthScreen = Gdx.graphics.width.toFloat()
    }


}