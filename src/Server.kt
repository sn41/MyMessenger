import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket

fun main(args: Array<String>) {
    // Определяем номер порта, который будет "слушать" сервер
    val port = 1777

    try {
        // Открыть серверный сокет (ServerSocket)
        val servSocket = ServerSocket(port)

        // Входим в бесконечный цикл - ожидаем соединения
        while (true) {
            println("Waiting for a connection on $port")
            // Получив соединение начинаем работать с сокетом
            val fromClientSocket = servSocket.accept()
            fromClientSocket
                .use { localSocket ->
                    PrintWriter(localSocket.getOutputStream(), true)
                        .use { printWriter ->
                            BufferedReader(InputStreamReader(localSocket.getInputStream()))
                                .use { bufferedReader ->

                                    // Читаем сообщения от клиента
                                    while (true) {
                                        val line = bufferedReader.readLine()

                                        // Ожидаем сообщение от клиента с содержанием "END" для прекращения цикла обмена.
                                        if (line != "END") {
                                            // Если это не сообщение для завершения сеанса, печатаем сообщение
                                            println(line)
                                        } else {
                                            // Если получено END - завершаем цикл обмена.
                                            // Отправляем клиенту подтверждение окончания сеанса "END".
                                            printWriter.println("END")
                                            // Завершаем цикл
                                            break
                                        }
                                    }
                                }
                        }
                }
        }
    } catch (ex: IOException) {
        //Вывод трассировки ошибки в поток вывода консоли System.out.
        ex.printStackTrace(System.out)
    }
}

