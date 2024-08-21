# 指令集 通用 SWING 基础库

指令集通用的 Kotlin 基础库，适用于 JVM/Swing 平台。

### 使用

```groovy
kotlin_version=1.9.24
java_version=8

dependencies {
    implementation 'com.github.isyscore:common-swing:2.2.4.2'
}
```

### 传递依赖

```groovy
api 'com.github.isyscore:common-jvm:2.2.4.2'
```

详细信息和用法参考本篇，此处不再赘述 [点击进入](https://isyscore.yuque.com/um3ee7/dhbg9u/avg941kdg0gkdkep?singleDoc)

### 基本用法

```kotlin
class MainForm: JFrame("Main") {
    init {
        contentPane = rootVertPanel {
            val tbl = table(arrayRowData = arrayOf(arrayOf("A", true), arrayOf("B", false)), arrayColumnNames = arrayOf("Name", "Checked")) {
                cell<JCheckBox> { cell, value, selected, cellHasFocus, row, col ->
                    // 此处的 this 是 JCheckBox
                    this.isSelected = value as Boolean
                    addActionListener { cell.setEditValue(this.isSelected, row, col) }
                }
            }
            borderPanel {
                button("Check") {
                    addActionListener {
                        val d1 = tbl.model.getValueAt(0, 1)
                        val d2 = tbl.model.getValueAt(1, 1)
                        println("d1 = $d1, d2 = $d2")
                    }
                }
            }
        }
        size { 500 x 300 }
        isVisible = true
    }
}
```

### 详细使用案例

1. 指令集 IIOS HUB 客户端([点击查看](http://10.30.30.3/iios/product-project/hub/hub-native-client))
2. 完整的组件使用演示([点击查看](http://10.30.30.3/hexj/common-swing-sample))