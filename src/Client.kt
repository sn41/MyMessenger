import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket

    fun main(args: Array<String>) {
        // Определяем номер порта, на котором нас ожидает сервер для ответа
        val portNumber = 1777
        val adr = "127.0.0.1"
        println("Client is started!")

        val socket = Socket(adr, portNumber)
        val bufferedReader = BufferedReader(InputStreamReader(socket.getInputStream()))
        val printWriter = PrintWriter(socket.getOutputStream(), true)

        while(true){
            //читаем клавиатуры
            val str = readlnOrNull()
            if(str != "fin" && str!=null){
                // Отправляем сообщение на сервер
                printWriter.println(str)
            }else{
                // Завершаем сеанс
                printWriter.println("END")
                break
            }
        }
        //закрываем
        bufferedReader.close()
        printWriter.close()
        socket.close()

        println("Client is finished")
    }
