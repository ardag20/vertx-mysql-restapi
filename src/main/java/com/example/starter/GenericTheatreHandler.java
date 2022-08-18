package com.example.starter;


import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;

import java.util.ArrayList;
import java.util.List;

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
      rc.response()
        .putHeader(CONTENT_TYPE_HEADER, JSON_CONTENT_TYPE)
        .end(fillJsonArray(bool,rowset).encodePrettily());
    });
  }

  public void readByPlayName(RoutingContext rc)
  {
    String playname = rc.pathParam("playname");
    repository.readByPlayName(playname, (bool, rowset)->{
      rc.response()
        .putHeader(CONTENT_TYPE_HEADER, JSON_CONTENT_TYPE)
        .end(fillJsonArray(bool,rowset).encodePrettily());
    });
  }

  public void readByDate(RoutingContext rc)
  {
    String date = rc.pathParam("date");
    repository.readByDate(date, (bool, rowset)->{
      rc.response()
        .putHeader(CONTENT_TYPE_HEADER, JSON_CONTENT_TYPE)
        .end(fillJsonArray(bool,rowset).encodePrettily());
    });
  }

  public void readByTime(RoutingContext rc)
  {
    String time = rc.pathParam("time");
    repository.readByTime(time, (bool,rowset)->{
      rc.response()
        .putHeader(CONTENT_TYPE_HEADER, JSON_CONTENT_TYPE)
        .end(fillJsonArray(bool,rowset).encodePrettily());
    });
  }

  public void readByTheatreName(RoutingContext rc)
  {
    String theatrename = rc.pathParam("theatrename");
    repository.readByTheatreName(theatrename, (bool,rowset)->{
      rc.response()
        .putHeader(CONTENT_TYPE_HEADER, JSON_CONTENT_TYPE)
        .end(fillJsonArray(bool,rowset).encodePrettily());
    });
  }

  public void readByType(RoutingContext rc)
  {
    String type = rc.pathParam("type");
    repository.readByType(type, (bool,rowset)->{
      rc.response()
        .putHeader(CONTENT_TYPE_HEADER, JSON_CONTENT_TYPE)
        .end(fillJsonArray(bool,rowset).encodePrettily());
    });
  }

  public void readAll(RoutingContext rc)
  {
    String all = rc.pathParam("all");
    repository.readAll(all, (bool,rowset)->{
        rc.response()
        .putHeader(CONTENT_TYPE_HEADER, JSON_CONTENT_TYPE)
        .end(fillJsonArray(bool,rowset).encodePrettily());

    });
  }

  public void readDistinctTypes(RoutingContext rc)
  {
    repository.readDistinctTypes((bool,rowset)->{
      JsonObject jsonObject = new JsonObject();
      List<String> types = new ArrayList<>();
      for(Row row: rowset)
      {
        types.add(row.getValue("Type").toString());
      }
      jsonObject.put("types",types);
      rc.response()
        .putHeader(CONTENT_TYPE_HEADER, JSON_CONTENT_TYPE)
        .end(jsonObject.encodePrettily());
    });
  }

  public void readDistinctTheatreNames(RoutingContext rc)
  {
    repository.readDistinctTheatreNames((bool,rowset)->{
      JsonObject jsonObject = new JsonObject();
      List<String> theatrenames = new ArrayList<>();
      for(Row row: rowset)
      {
        theatrenames.add(row.getValue("Theatre_Name").toString());
      }
      jsonObject.put("theatrename",theatrenames);
      rc.response()
        .putHeader(CONTENT_TYPE_HEADER, JSON_CONTENT_TYPE)
        .end(jsonObject.encodePrettily());
    });
  }

  public void readDistinctTimes(RoutingContext rc)
  {
    repository.readDistinctTimes((bool,rowset)->{
      JsonObject jsonObject = new JsonObject();
      List<String> times = new ArrayList<>();
      for(Row row: rowset)
      {
        times.add(row.getValue("Time").toString());
      }
      jsonObject.put("time",times);
      rc.response()
        .putHeader(CONTENT_TYPE_HEADER, JSON_CONTENT_TYPE)
        .end(jsonObject.encodePrettily());
    });
  }

  public void readDistinctDates(RoutingContext rc)
  {
    repository.readDistinctDates((bool,rowset)->{
      JsonObject jsonObject = new JsonObject();
      List<String> dates = new ArrayList<>();
      for(Row row: rowset)
      {
        dates.add(row.getValue("Date").toString());
      }
      jsonObject.put("date",dates);
      rc.response()
        .putHeader(CONTENT_TYPE_HEADER, JSON_CONTENT_TYPE)
        .end(jsonObject.encodePrettily());
    });
  }

  private JsonArray fillJsonArray(Boolean bool, RowSet<Row> rowset)
  {
    if(bool==false)
    {
      System.out.println("ERROR!");
      return null;
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
      return jsonArray;
    }

  }
}
