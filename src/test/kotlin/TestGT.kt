import com.isyscore.kotlin.swing.inline.newClassInstance
import com.isyscore.kotlin.swing.runOnMainThread
import org.junit.Test
import java.awt.Color
import java.awt.Component
import javax.swing.DefaultComboBoxModel
import javax.swing.JButton
import javax.swing.JComboBox

// test for generic type

class TestGT {

    inline fun<reified T: Component> custom(vararg params: Any, block: T.() -> Unit): T {
        return newClassInstance<T>(*params).apply(block)
    }

    class MyCombo(data: List<String>): JComboBox<String>() {
        init {
            background = Color.WHITE
            model = DefaultComboBoxModel(data.toTypedArray())
            runOnMainThread { updateUI() }
        }
    }

    @Test
    fun test() {
        val list: List<String> = listOf("a", "b")
        val btn = custom<MyCombo>(list) {

        }
        println(btn)
    }

}