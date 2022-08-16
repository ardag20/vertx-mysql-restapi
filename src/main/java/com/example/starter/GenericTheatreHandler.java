package com.example.starter;


import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;

public class GenericTheatreHandler {

  private final TheatreRepository repository;

  protected static final String CONTENT_TYPE_HEADER = "Content-Type";

  protected static final String JSON_CONTENT_TYPE = "application/json; charset=UTF-8";

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
              .put("Number",row.getValue("Number"))
              .put("Play name",row.getValue("Play_Name"))
              .put("Date",row.getValue("Date"))
              .put("Time",row.getValue("Time"))
              .put("Theatre Name",row.getValue("Theatre_Name"))
              .put("Type",row.getValue("Type"))
          );
        }
        rc.response()
          .putHeader(CONTENT_TYPE_HEADER, JSON_CONTENT_TYPE)
          .end(jsonArray.encodePrettily());             }
    });
  }

  public void readByPlayName(RoutingContext rc)
  {
    String playname = rc.pathParam("playname");
    repository.readByPlayName(playname, (bool, rowset)->{
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
              .put("Play Name",row.getValue("Play_Name"))
              .put("Date",row.getValue("Date"))
              .put("Time",row.getValue("Time"))
              .put("Theatre Name",row.getValue("Theatre_Name"))
              .put("Type",row.getValue("Type"))
          );
        }
        rc.response()
          .putHeader(CONTENT_TYPE_HEADER, JSON_CONTENT_TYPE)
          .end(jsonArray.encodePrettily());
      }
    });
  }

  public void readByDate(RoutingContext rc)
  {
    String date = rc.pathParam("date");
    repository.readByDate(date, (bool, rowset)->{
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
              .put("Play Name",row.getValue("Play_Name"))
              .put("Date",row.getValue("Date"))
              .put("Time",row.getValue("Time"))
              .put("Theatre Name",row.getValue("Theatre_Name"))
              .put("Type",row.getValue("Type"))
          );
        }
        rc.response()
          .putHeader(CONTENT_TYPE_HEADER, JSON_CONTENT_TYPE)
          .end(jsonArray.encodePrettily());
      }
    });
  }

  public void readByTime(RoutingContext rc)
  {
    String time = rc.pathParam("time");
    repository.readByTime(time, (bool,rowset)->{
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
              .put("Play Name",row.getValue("Play_Name"))
              .put("Date",row.getValue("Date"))
              .put("Time",row.getValue("Time"))
              .put("Theatre Name",row.getValue("Theatre_Name"))
              .put("Type",row.getValue("Type"))
          );
        }
        rc.response()
          .putHeader(CONTENT_TYPE_HEADER, JSON_CONTENT_TYPE)
          .end(jsonArray.encodePrettily());
      }
    });
  }

  public void readByTheatreName(RoutingContext rc)
  {
    String theatrename = rc.pathParam("theatrename");
    repository.readByTheatreName(theatrename, (bool,rowset)->{
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
              .put("Name",row.getValue("Number"))
              .put("Play Name",row.getValue("Play_Name"))
              .put("Date",row.getValue("Date"))
              .put("Time",row.getValue("Time"))
              .put("Theatre Name",row.getValue("Theatre_Name"))
              .put("Type",row.getValue("Type"))
          );
        }
        rc.response()
          .putHeader(CONTENT_TYPE_HEADER, JSON_CONTENT_TYPE)
          .end(jsonArray.encodePrettily());
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
              .put("Name",row.getValue("Number"))
              .put("Play Name",row.getValue("Play_Name"))
              .put("Date",row.getValue("Date"))
              .put("Time",row.getValue("Time"))
              .put("Theatre Name",row.getValue("Theatre_Name"))
              .put("Type",row.getValue("Type"))
          );
        }
        rc.response()
          .putHeader(CONTENT_TYPE_HEADER, JSON_CONTENT_TYPE)
          .end(jsonArray.encodePrettily());
      }
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
              .put("Name",row.getValue("Number"))
              .put("Play Name",row.getValue("Play_Name"))
              .put("Date",row.getValue("Date"))
              .put("Time",row.getValue("Time"))
              .put("Theatre Name",row.getValue("Theatre_Name"))
              .put("Type",row.getValue("Type"))
          );
        }
        rc.response()
          .putHeader(CONTENT_TYPE_HEADER, JSON_CONTENT_TYPE)
          .end(jsonArray.encodePrettily());
      }
    });
  }

}
