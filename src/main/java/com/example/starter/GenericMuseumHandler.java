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

  protected static final String CONTENT_TYPE_HEADER = "Content-Type";

  protected static final String JSON_CONTENT_TYPE = "application/json; charset=UTF-8";

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
                .put("Number",row.getValue("Number"))
                .put("Name",row.getValue("Name"))
                .put("Location",row.getValue("Location"))
                .put("Address",row.getValue("Address"))
                .put("Type",row.getValue("Type"))
                .put("Rating",row.getValue("Rating"))
            );
          }

          rc.response()
            .putHeader(CONTENT_TYPE_HEADER, JSON_CONTENT_TYPE)
            .end(jsonArray.encodePrettily());                         //json olarak dondum, response olarak donuyorum.
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
              .put("Number",row.getValue("Number"))
              .put("Name",row.getValue("Name"))
              .put("Location",row.getValue("Location"))
              .put("Address",row.getValue("Address"))
              .put("Type",row.getValue("Type"))
              .put("Rating",row.getValue("Rating"))
          );
        }
        rc.response()
          .putHeader(CONTENT_TYPE_HEADER, JSON_CONTENT_TYPE)
          .end(jsonArray.encodePrettily());             }
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
              .put("Number", row.getValue("Number"))
              .put("Name", row.getValue("Name"))
              .put("Location", row.getValue("Location"))
              .put("Address",row.getValue("Address"))
              .put("Type",row.getValue("Type"))
              .put("Rating",row.getValue("Rating"))
        );
        }
        rc.response()
          .putHeader(CONTENT_TYPE_HEADER, JSON_CONTENT_TYPE)
          .end(jsonArray.encodePrettily());
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
              .put("Number",row.getValue("Number"))
              .put("Name",row.getValue("Name"))
              .put("Location",row.getValue("Location"))
              .put("Address",row.getValue("Address"))
              .put("Type",row.getValue("Type"))
              .put("Rating",row.getValue("Rating"))
          );
        }
        rc.response()
          .putHeader(CONTENT_TYPE_HEADER, JSON_CONTENT_TYPE)
          .end(jsonArray.encodePrettily());             }
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
              .put("Number",row.getValue("Number"))
              .put("Name",row.getValue("Name"))
              .put("Location",row.getValue("Location"))
              .put("Address",row.getValue("Address"))
              .put("Type",row.getValue("Type"))
              .put("Rating",row.getValue("Rating"))
          );
        }
        rc.response()
          .putHeader(CONTENT_TYPE_HEADER, JSON_CONTENT_TYPE)
          .end(jsonArray.encodePrettily());             }
  });
  }

  public void readAll(RoutingContext rc)
  {
    String all = rc.pathParam("all");
    repository.readAll(all, (bool,rowset)->{
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
              .put("Number",row.getValue("Number"))
              .put("Name",row.getValue("Name"))
              .put("Location",row.getValue("Location"))
              .put("Address",row.getValue("Address"))
              .put("Type",row.getValue("Type"))
              .put("Rating",row.getValue("Rating"))
          );
        }
        rc.response()
          .putHeader(CONTENT_TYPE_HEADER, JSON_CONTENT_TYPE)
          .end(jsonArray.encodePrettily());             }
    });
  }


}
