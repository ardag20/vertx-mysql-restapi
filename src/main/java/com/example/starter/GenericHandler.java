package com.example.starter;


import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.sqlclient.Row;


public class GenericHandler {

  private final MuseumRepository repository;

  public GenericHandler(MuseumRepository repository) {
    this.repository = repository;
  }
  public void readByRating(RoutingContext rc)
  {

  }

  public void  readByLocation(RoutingContext rc)    //rating için de yapılır, tum colıumnlar ıcın
  {
    String location = rc.pathParam("locationName");
    repository.readyByLocation(location, (bool,rowset)->{
      if(bool==false)
      {
        System.out.println("ERROR!");
      }
      else
      {
        JsonArray jsonArray = new JsonArray();
        for(Row row: rowset) {
          jsonArray.add(
            new JsonObject()
              .put("id", row.getValue(0))
              .put("name", row.getValue(1))
              .put("location", row.getValue(2))
              .put("address",row.getValue(3))
              .put("type",row.getValue(4))
              .put("rating",row.getValue(5))
        );
        }
        rc.end(jsonArray.encodePrettily());

      }
    });
  }
}
