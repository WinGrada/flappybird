package uz.wingrada.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class Main : ApplicationAdapter() {
    private lateinit var batch: SpriteBatch
    private lateinit var backgound: Backgound
    private lateinit var bird: Bird



    override fun create() {
        backgound = Backgound()
        batch = SpriteBatch()
        bird = Bird()
    }

    override fun render() {
        update()
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        batch.begin()
        backgound.render(batch)
        bird.render(batch)
        batch.end()
    }

    fun update(){
        backgound.update()
        bird.update()
    }

    override fun dispose() {
        batch.dispose()
    }
}