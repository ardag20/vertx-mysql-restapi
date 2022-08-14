package com.example.starter;


import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.sqlclient.Row;


public class GenericMuseumHandler {

  private final MuseumRepository repository;

  public GenericMuseumHandler(MuseumRepository repository) {
    this.repository = repository;
  }

  public void readByRating(RoutingContext rc)
  {
      String rating = rc.pathParam("rating");
      repository.readyByRating(rating, (bool,rowset)->{
        if(bool==false)
        {
          System.out.println("ERROR!");
        }
        else
        {
          JsonArray jsonArray = new JsonArray();
          for (Row row: rowset)
          {
            jsonArray.add(
              new JsonObject()
                .put("id",row.getValue("Number"))
                .put("name",row.getValue("Name"))
                .put("location",row.getValue("Location"))
                .put("address",row.getValue("Address"))
                .put("type",row.getValue("Type"))
                .put("rating",row.getValue("Rating"))
            );
          }
          rc.end(jsonArray.encodePrettily());
        }
      });
  }

  public void readByName(RoutingContext rc)
  {
    String name = rc.pathParam("name");
    repository.readByName(name, (bool,rowset)->{
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
              .put("id",row.getValue("Number"))
              .put("name",row.getValue("Name"))
              .put("location",row.getValue("Location"))
              .put("address",row.getValue("Address"))
              .put("type",row.getValue("Type"))
              .put("rating",row.getValue("Rating"))
          );
        }
        rc.end(jsonArray.encodePrettily());
      }
    });
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
              .put("id", row.getValue("Number"))
              .put("name", row.getValue("Name"))
              .put("location", row.getValue("Location"))
              .put("address",row.getValue("Address"))
              .put("type",row.getValue("Type"))
              .put("rating",row.getValue("Rating"))
        );
        }
        rc.end(jsonArray.encodePrettily());

      }
    });
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
              .put("id",row.getValue("Number"))
              .put("name",row.getValue("Name"))
              .put("location",row.getValue("Location"))
              .put("address",row.getValue("Address"))
              .put("type",row.getValue("Type"))
              .put("rating",row.getValue("Rating"))
          );
        }
        rc.end(jsonArray.encodePrettily());
      }
    });
  }

  public void readByType(RoutingContext rc)
  {
    String type = rc.pathParam("type");
    repository.readByType(type, (bool,rowset)->{
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
              .put("id",row.getValue("Number"))
              .put("name",row.getValue("Name"))
              .put("location",row.getValue("Location"))
              .put("address",row.getValue("Address"))
              .put("type",row.getValue("Type"))
              .put("rating",row.getValue("Rating"))
          );
        }
        rc.end(jsonArray.encodePrettily());
      }
  });
  }




}
