import com.isyscore.kotlin.swing.inline.newClassInstance
import org.junit.Test
import java.awt.Component
import javax.swing.JButton

// test for generic type

class TestGT {

    inline fun<reified T: Component> custom(vararg params: Any, block: T.() -> Unit): T {
        return newClassInstance<T>(*params).apply(block)
    }

    @Test
    fun test() {
        val btn = custom<JButton>("2333") {

        }
        println(btn)
    }

}