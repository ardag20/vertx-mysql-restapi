package com.example.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;
import io.vertx.sqlclient.SqlClient;

public class MainVerticle extends AbstractVerticle {
  final String getAll = "select * from Muze";
  final String getInBesiktas = "select * from Muze where Konum='Besiktas'";
  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    MySQLConnectOptions connectOptions = new MySQLConnectOptions()
      .setPort(8889)
      .setHost("localhost")
      .setDatabase("staj")
      .setUser("root")
      .setPassword("root");

// Pool options
    PoolOptions poolOptions = new PoolOptions()
      .setMaxSize(5);

// Create the client pool
    SqlClient client = MySQLPool.pool(vertx, connectOptions, poolOptions);


    MuseumRepository repository = new MuseumRepository(client);
    GenericHandler handler = new GenericHandler(repository);
    Router router = Router.router(vertx);

    router.route("/api/museums*").handler(BodyHandler.create());
    router.get("/api/museums/location/:locationName").handler(handler::readByLocation);
                ///api/museums/rating/:rating  hepsı ıcın yapılıcak.


// A simple query
    /*client
      .query(getInBesiktas)
      .execute(ar -> {
        if (ar.succeeded()) {
          RowSet<Row> result = ar.result();
          for(Row museum: result)
          {
            System.out.println(museum.getString("İsim"));
          }
          System.out.println("Got " + result.size() + " rows ");
        } else {
          System.out.println("Failure: " + ar.cause().getMessage());
        }

        // Now close the pool
        client.close();
      });*/

    vertx.createHttpServer().requestHandler(router).listen(8080, http -> {
      if (http.succeeded()) {
        startPromise.complete();
        System.out.println("HTTP server started on port 8080");
      } else {
        startPromise.fail(http.cause());
      }
    });


  }


}
