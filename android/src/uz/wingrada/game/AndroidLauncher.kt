package uz.wingrada.game

import android.os.Bundle
import android.util.DisplayMetrics
import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration

class AndroidLauncher : AndroidApplication() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val config = AndroidApplicationConfiguration()
        initialize(Main(), config)
    }

    public fun getWidth(): Int{
        return DisplayMetrics().widthPixels
    }

    public fun getHeight(): Int{
        return DisplayMetrics().heightPixels
    }
}