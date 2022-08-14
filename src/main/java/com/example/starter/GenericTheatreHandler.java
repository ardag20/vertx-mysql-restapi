package com.example.starter;


import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.sqlclient.Row;

public class GenericTheatreHandler {

  private final TheatreRepository repository;

  public GenericTheatreHandler(TheatreRepository repository) {
    this.repository = repository;
  }

  public void readByNumber(RoutingContext rc)
  {
    String number = rc.pathParam("number");
    repository.readByNumber(number, (bool,rowset)->{
      if(bool==false)
      {
        System.out.println("ERROR!");
      }
      else
      {
        JsonArray jsonArray = new JsonArray();
        for(Row row: rowset)
        {
          jsonArray.add(
            new JsonObject()
              .put("number",row.getValue("Number"))
              .put("playname",row.getValue("Play_Name"))
              .put("date",row.getValue("Date"))
              .put("time",row.getValue("Time"))
              .put("theatrename",row.getValue("Theatre_Name"))
              .put("type",row.getValue("Type"))
          );
        }
        rc.end(jsonArray.encodePrettily());
      }
    });
  }

}
