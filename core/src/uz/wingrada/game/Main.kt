package uz.wingrada.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch

val heightScreen = Gdx.graphics.height.toFloat()
val widthScreen = Gdx.graphics.width.toFloat()

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
        background = Background()
        batch = SpriteBatch()
        bird = Bird()
        obstacles = Obstacles()
        gameOver = false
        restartTexture = Texture("restart_btn.png")
    }

    override fun render() {
        update()
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        batch.begin()
        if(gameOver){
            batch.draw(restartTexture, positionXRestartTexture, positionYRestartTexture)
        }
        else {
            background.render(batch)
            bird.render(batch)
            obstacles.render(batch)
        }
        batch.end()
    }

    fun update(){
        background.update()
        bird.update()
        obstacles.update()
    }

    override fun dispose() {
        batch.dispose()
    }
}