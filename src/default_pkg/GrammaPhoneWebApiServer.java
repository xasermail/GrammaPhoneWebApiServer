package default_pkg;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class GrammaPhoneWebApiServer {


  // точка входа в программу
  public static void main(String[] args) throws IOException {
    // создаём экземпляр класса и выполняем произвольный метод для запуска,
    // пусть по привычке это будет execute()
    GrammaPhoneWebApiServer grammaPhoneWebApiServer = new GrammaPhoneWebApiServer();
    grammaPhoneWebApiServer.execute();
  }


  // основной метод
  public void execute() throws  IOException {
    // создаём HttpServer, эта штука умеет открыть некоторый порт (в данном
    // случае 8000, но можно поменять при желании), что значит второй параметр
    // 0 - не знаю, может таймаут или вроде того, не искал
    HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
    // теперь делаем обработчик обращений к нашему HTTP-серверу, допустим при обращении
    // на страницу /message мы будем выдавать сообщения, доступные пользователю,
    // для этого нам надо создать контекст и привязать адрес страницы к классу, который
    // будет выполнять обработку обращения на эту страницу
    server.createContext("/message", new MyHttpHandler());
    // не знаю что это, оставим как есть
    server.setExecutor(null); // creates a default executor
    // запускаем
    server.start();
    // сообщаем, что всё запустилось
    System.out.println("started");
  }

  // это класс, который будет обрабатывать обращения пользователя к странице /message
  class MyHttpHandler implements HttpHandler {

    // собственно сам метод обработки обращения
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
      // выводим в консоль, просто чтобы видеть. что всё работает
      System.out.println("hi from handle");
      // это то, что мы ответим пользователю при обращении, по идее
      // тут должна быть какая-то логика получения сообщений, но
      // допустим пока возвращаем неизменное сообщение
      // по идее тут должно быть обращение к БД
      String response = "This works correctly";
      httpExchange.sendResponseHeaders(200, response.length());
      OutputStream os = httpExchange.getResponseBody();
      os.write(response.getBytes());
      os.close();
      // всё, теперь можно обращаться к странице http://localhost:8000/message
      // и мы получим сообщение This works correctly
    }
  }



}
