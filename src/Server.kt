import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket

class Server(val port: Int = 1777, val outMessage:(String)->Unit) : Thread() {

    override fun run() {
        try {
            // Открыть серверный сокет (ServerSocket)
            val servSocket = ServerSocket(port)

            // Входим в цикл - ожидаем соединения
            loop@ while (true) {
                println("Waiting for a connection on $port")
                val fromClientSocket = servSocket.accept()
                fromClientSocket.use { localSocket ->
                    BufferedReader(InputStreamReader(localSocket.getInputStream())).use { bufferedReader ->
                        // Читаем сообщения от клиента
                        while (true) {
                            val line = bufferedReader.readLine()
                            // Ожидаем сообщение от клиента с содержанием "END" для прекращения цикла обмена.
                            if (line != "END") {
                                // Если это не сообщение для завершения сеанса, печатаем сообщение
                                outMessage(line)
                            } else {
                                break@loop
                            }
                        }
                    }
                }
            }

        } catch (ex: IOException) {
            //Вывод трассировки ошибки в поток вывода консоли System.out.
            ex.printStackTrace(System.out)
        }
        println("Server terminated.")
    }
}


