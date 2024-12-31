fun main(args: Array<String>) {
    //  Получить IP адрес сервера
    val address = try {
        args[0]
    } catch (e: Exception) {
        "127.0.0.1"
    }

    //  Получить номер порта
    val port = try {
        args[1].toInt()
    } catch (e: Exception) {
        1777
    }

    if (address == "Help" || address == "h") {
        println("формат вызова программы: Messenger A P, где А - адрес компьютера собеседника, P - номер порта")
        return
    }

    val client = Client(address, port) {
        print(">")
        readlnOrNull() ?: ""
    }

    val server = Server(port){
        println("#$it")
    }

    //Запуск
    server.start()
    client.start()
    //ожидаем завершения
    client.join()
}
