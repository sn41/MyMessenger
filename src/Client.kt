import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket

    fun main(args: Array<String>) {
        // Определяем номер порта, на котором нас ожидает сервер для ответа
        val portNumber = 1777
        val adr = "127.0.0.1"
        println("Start!")

        val socket = Socket(adr, portNumber)
        val bufferedReader = BufferedReader(InputStreamReader(socket.getInputStream()))
        val printWriter = PrintWriter(socket.getOutputStream(), true)

        while(true){
            //чтение клавиатуры
            val str = readLine()

            if(str != "fin"){
                // Отправляем сообщение на сервер
                printWriter.println(str)
            }else{
                break
            }
        }

        //закрываем
        bufferedReader.close()
        printWriter.close()
        socket.close()

        println("Client is finished")
    }
