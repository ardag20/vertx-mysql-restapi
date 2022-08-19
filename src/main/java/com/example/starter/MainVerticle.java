package com.example.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.SqlClient;

public class MainVerticle extends AbstractVerticle {



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
    TheatreRepository repository1 = new TheatreRepository(client);
    ConcertRepository repository2 = new ConcertRepository(client);

    GenericMuseumHandler handler = new GenericMuseumHandler(repository);
    GenericTheatreHandler handler1 = new GenericTheatreHandler(repository1);
    GenericConcertHandler handler2 = new GenericConcertHandler(repository2);

    Router router = Router.router(vertx);

    router.route("/api/museums*").handler(BodyHandler.create());
    router.get("/api/museums/location/:locationName").handler(handler::readByLocation);
    router.get("/api/museums/rating/:rating").handler(handler::readByRating);
    router.get("/api/museums/name/:name").handler(handler::readByName);
    router.get("/api/museums/number/:number").handler(handler::readByNumber);
    router.get("/api/museums/type/:type").handler(handler::readByType);
    router.get("/api/museums/all").handler(handler::readAll);

    router.get("/api/museums/distinctLocations").handler(handler::readDistinctLocations);
    router.get("/api/museums/distinctTypes").handler(handler::readDistinctTypes);


    router.route("/api/theatres*").handler(BodyHandler.create());
    router.get("/api/theatres/number/:number").handler(handler1::readByNumber);
    router.get("/api/theatres/playname/:playname").handler(handler1::readByPlayName);
    router.get("/api/theatres/date/:date").handler(handler1::readByDate);
    router.get("/api/theatres/time/:time").handler(handler1::readByTime);
    router.get("/api/theatres/theatrename/:theatrename").handler(handler1::readByTheatreName);
    router.get("/api/theatres/type/:type").handler(handler1::readByType);
    router.get("/api/theatres/all").handler(handler1::readAll);

    router.get("/api/theatres/distinctTypes").handler(handler1::readDistinctTypes);
    router.get("/api/theatres/distinctTheatreNames").handler(handler1::readDistinctTheatreNames);
    router.get("/api/theatres/distinctTimes").handler(handler1::readDistinctTimes);
    router.get("/api/theatres/distinctDates").handler(handler1::readDistinctDates);

    router.route("/api/concerts*").handler(BodyHandler.create());
    router.get("/api/concerts/number/:number").handler(handler2::readByNumber);
    router.get("/api/concerts/name/:name").handler(handler2::readByName);
    router.get("/api/concerts/date/:date").handler(handler2::readByDate);
    router.get("/api/concerts/time/:time").handler(handler2::readByTime);

    router.get("/api/concerts/distinctLocations").handler(handler2::readDistinctLocations);
    router.get("/api/concerts/distinctTimes").handler(handler2::readDistinctTimes);
    router.get("/api/concerts/distinctDates").handler(handler2::readDistinctDates);


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
