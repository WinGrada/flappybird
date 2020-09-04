package uz.wingrada.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import java.time.ZoneOffset
import kotlin.random.Random


@ExperimentalStdlibApi
class Obstacles(var amountOfColumns: Int = 5) {

    inner class Columns(public var position: Vector2 = Vector2(0f, 0f)) {

        private var speed: Float = 2f
        public val texture: Texture = Texture("column.png")
        public val widthColumn = 144
        private val rangeOffsetFrom = 200
        private val rangeOffsetUntil = 400

        public var offset = (rangeOffsetFrom..rangeOffsetUntil).random().toFloat()

        public var emptySpace: Rectangle = Rectangle(position.x, position.y - offset + 1024, 144f, distanceBetweenColumnsY)


        public fun update(){
            moveColumns()
        }

        private fun moveColumns() { position.x -= speed }

        public fun updateOffset(): Float {
            return (rangeOffsetFrom..rangeOffsetUntil).random().toFloat()
        }

    }

    private var distanceBetweenColumnsY: Float = 280f



    // В массиве экземпляров класса, создаются колоны с разной начальной позицией.
    public val columns= buildList<Columns>{
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
            batch.draw(columns[i].texture, columns[i].position.x, columns[i].position.y - columns[i].offset)

            // Верхняя часть колоны.
            batch.draw(columns[i].texture, columns[i].position.x,
                    columns[i].position.y + columns[i].texture.height + distanceBetweenColumnsY - columns[i].offset)
        }
    }

    private fun loopColumns(){
        for (i in 0 until amountOfColumns ){
            if (columns[i].position.x < -columns[i].widthColumn){
                columns[i].position.x = Gdx.graphics.width.toFloat()
                columns[i].offset = columns[i].updateOffset()
                columns[i].emptySpace.x = columns[i].position.x
            }
            columns[i].update()

        }
    }

}

