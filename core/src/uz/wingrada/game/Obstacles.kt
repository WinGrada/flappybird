package uz.wingrada.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import java.time.ZoneOffset
import kotlin.random.Random


@ExperimentalStdlibApi
class Obstacles(private var amountOfColumns: Int = 5) {

    inner class Columns(public var position: Vector2 = Vector2(0f, 0f)) {

        private var speed: Float = 2f
        public val texture: Texture = Texture("column.png")

        private val rangeOffsetFrom = 200
        private val rangeOffsetUntil = 400

        public var offset = (rangeOffsetFrom..rangeOffsetUntil).random().toFloat()

        public fun update(){
            moveColumns()
        }

        private fun moveColumns() { position.x -= speed }

        public fun updateOffset(): Float {
            return (rangeOffsetFrom..rangeOffsetUntil).random().toFloat()
        }

    }

    private var distanceBetweenColumnsY: Float = 280f


    // В массиве экземпляра класса, создаются колоны с разной начальной позицией.
    private val obstacles= buildList<Columns>{
        val startPositionColumnsX: Float = 1200f
        val distanceBetweenColumnsX: Float = 500f
        val randomOffset = (200..400).random().toFloat()

        repeat(amountOfColumns){
            add(Columns(Vector2(startPositionColumnsX + it * distanceBetweenColumnsX, -randomOffset)))
        }
    }

    public fun render(batch: SpriteBatch){
        renderColumns(batch)
    }

    public fun update(){
        loopColumns()
    }



    private fun renderColumns(batch: SpriteBatch){
        for ( i in 0 until amountOfColumns){
            // Нижняя часть колоны.
            batch.draw(obstacles[i].texture, obstacles[i].position.x, obstacles[i].position.y - obstacles[i].offset)

            // Верхняя часть колоны.
            batch.draw(obstacles[i].texture, obstacles[i].position.x,
                    obstacles[i].position.y + obstacles[i].texture.height + distanceBetweenColumnsY - obstacles[i].offset)
        }
    }

    private fun loopColumns(){
        val widthColumn = 144
        for (i in 0 until amountOfColumns ){
            if (obstacles[i].position.x < -widthColumn){
                obstacles[i].position.x = Gdx.graphics.width.toFloat()
                obstacles[i].offset = obstacles[i].updateOffset()
            }
            obstacles[i].update()

        }
    }

}

