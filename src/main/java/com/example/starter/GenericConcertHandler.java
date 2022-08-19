package com.example.starter;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;

import java.util.ArrayList;
import java.util.List;

public class GenericConcertHandler {

  private final ConcertRepository repository;

  public GenericConcertHandler(ConcertRepository repository) {this.repository = repository; }

  protected static final String CONTENT_TYPE_HEADER = "Content-Type";

  protected static final String JSON_CONTENT_TYPE = "application/json; charset=UTF-8";

  public void readByNumber(RoutingContext rc)
  {
    String number = rc.pathParam("number");
    repository.readByNumber(number, (bool,rowset)->{
      rc.response()
        .putHeader(CONTENT_TYPE_HEADER, JSON_CONTENT_TYPE)
        .end(fillJsonArray(bool,rowset).encodePrettily());
    });
  }

  public void readByName(RoutingContext rc)
  {
    String name = rc.pathParam("name");
    repository.readByName(name, (bool,rowset)->{
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

  public void readDistinctLocations(RoutingContext rc)
  {
    repository.readDistinctLocations((bool,rowset)->{
      JsonObject jsonObject = new JsonObject();
      List<String> locations = new ArrayList<>();
      for(Row row: rowset)
      {
        locations.add(row.getValue("Location").toString());
      }
      jsonObject.put("location",locations);
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
            .put("Name",row.getValue("Name"))
            .put("Date",row.getValue("Date"))
            .put("Time",row.getValue("Time"))
            .put("Location",row.getValue("Location"))
        );
      }
      return jsonArray;
    }
  }

}
