package uz.wingrada.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch

var heightScreen: Float = 0.0f
var widthScreen: Float = 0.0f

@ExperimentalStdlibApi
class Main : Game() {
    private lateinit var batch: SpriteBatch
    private lateinit var background: Background
    private lateinit var bird: Bird
    private lateinit var obstacles: Obstacles
    private var gameOver: Boolean = false
    private lateinit var restartTexture: Texture


    private var positionXRestartTexture: Float = 0.0f
    private var positionYRestartTexture: Float = 0.0f


    override fun create() {
        setup()
        background = Background()
        batch = SpriteBatch()
        bird = Bird()
        obstacles = Obstacles()
        gameOver = true
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
        needToComeUpWithName()
        hasBirdLeftBorder()
    }

    override fun dispose() {
        batch.dispose()
    }


    private fun needToComeUpWithName(){
        for (i in 0 until obstacles.amountOfColumns){
            if (bird.position.x > obstacles.columns[i].position.x &&
                bird.position.x < obstacles.columns[i].position.x + obstacles.columns[i].widthColumn){
                if (obstacles.columns[i].emptySpace.contains(bird.position)){
                    gameOver = true
                    return
                }
            }
        }
    }

    private fun hasBirdLeftBorder() {
        if(bird.position.y < 0 || bird.position.y > heightScreen){
            gameOver = true
            return
        }
        gameOver = false
    }

    private fun isGameOver(){
        if (gameOver){
            setGameOver()
            return
        }
        setStartGame()
    }

    private fun setStartGame() {
        background.render(batch)
        bird.render(batch)
        obstacles.render(batch)
    }

    private fun setGameOver() {
        batch.draw(restartTexture, positionXRestartTexture, positionYRestartTexture, 465f, 241f)
    }

    private fun setup(){
        val widthRestartBtn = 465
        val heightRestartBtn = 241
        heightScreen = Gdx.graphics.height.toFloat()
        widthScreen = Gdx.graphics.width.toFloat()
        positionXRestartTexture = widthScreen / 2 - widthRestartBtn / 2
        positionYRestartTexture = heightScreen / 2 - heightRestartBtn / 2
    }


}