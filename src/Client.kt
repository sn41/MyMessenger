import java.io.PrintWriter
import java.net.Socket
class Client(val adr: String = "127.0.0.1", val portNumber: Int = 1777):Thread()  {
    override fun run() {
        // Определяем номер порта, на котором нас ожидает сервер для ответа
        println("Client is started!")

        val clientSocket = Socket(adr, portNumber)

        clientSocket.use { socket ->
            PrintWriter(socket.getOutputStream(), true).use { printWriter ->
                while (true) {
                    //читаем клавиатуру
                    val str = readlnOrNull()
                    if (str != "fin" && str != null) {
                        // Отправляем сообщение на сервер
                        printWriter.println(str)
                    } else {
                        // Завершаем сеанс
                        printWriter.println("END")
                        break
                    }
                }
            }
        }
        println("Client is finished")
    }
}