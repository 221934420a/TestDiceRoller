package com.example.diceroller

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    var textView: TextView? = null  // 把視圖的元件宣告成全域變數
    var button: Button? = null
    var result // 儲存資料用的字串
            : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rollButton: Button = findViewById(R.id.button)

        rollButton.setOnClickListener {
            /*val toast = Toast.makeText(this, "Dice Rolled!", Toast.LENGTH_SHORT)
            toast.show()
            val resultTextView: TextView = findViewById(R.id.textView)
            resultTextView.text = Dice(6).roll().toString()
             */
            textView = findViewById(R.id.textView)
            val thread = Thread(mutiThread)
            thread.start() // 開始執行
        }
    }

    private val mutiThread = Runnable {
        try {
            val url = URL("http://192.168.0.11//GetData.php")
            // 開始宣告 HTTP 連線需要的物件，這邊通常都是一綑的
            val connection = url.openConnection() as HttpURLConnection
            // 建立 Google 比較挺的 HttpURLConnection 物件
            connection.requestMethod = "POST"
            // 設定連線方式為 POST
            connection.doOutput = true // 允許輸出
            connection.doInput = true // 允許讀入
            connection.useCaches = false // 不使用快取
            connection.connect() // 開始連線
            val responseCode = connection.responseCode
            // 建立取得回應的物件
            if (responseCode ==
                HttpURLConnection.HTTP_OK
            ) {
                // 如果 HTTP 回傳狀態是 OK ，而不是 Error
                val inputStream = connection.inputStream
                // 取得輸入串流
                val bufReader = BufferedReader(InputStreamReader(inputStream, "utf-8"), 8)
                // 讀取輸入串流的資料
                var box = "" // 宣告存放用字串
                var line: String? = null // 宣告讀取用的字串
                while (bufReader.readLine().also { line = it } != null) {
                    box += """
                        $line
                        
                        """.trimIndent()
                    // 每當讀取出一列，就加到存放字串後面
                }
                inputStream.close() // 關閉輸入串流
                result = box // 把存放用字串放到全域變數
            }
            // 讀取輸入串流並存到字串的部分
            // 取得資料後想用不同的格式
            // 例如 Json 等等，都是在這一段做處理
        } catch (e: Exception) {
            result = e.toString() // 如果出事，回傳錯誤訊息
        }

        // 當這個執行緒完全跑完後執行
        runOnUiThread {
            textView!!.text = result // 更改顯示文字
        }
    }
}