package com.example.starter;

import io.vertx.ext.web.RoutingContext;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.SqlClient;

import io.vertx.sqlclient.RowSet;
import java.util.function.BiConsumer;

public class ConcertRepository {

  public ConcertRepository(SqlClient client) {this.client = client; }

  private SqlClient client;

  public void readByNumber(String number, BiConsumer<Boolean, RowSet<Row>> consumer)
  {
    String query = "select * from Konser where Number="+number;
    getRowSet(query,consumer);
  }

  public void readByName(String name, BiConsumer<Boolean, RowSet<Row>> consumer)
  {
    String query = "select * from Konser where Name='"+name+"'";
    getRowSet(query,consumer);
  }

  public void readByDate(String date, BiConsumer<Boolean, RowSet<Row>> consumer)
  {
    String query = "select * from Konser where Date='"+date+"'";
    getRowSet(query, consumer);
  }

  public void readByTime(String time, BiConsumer<Boolean, RowSet<Row>> consumer)
  {
    String query = "select * from Konser where Time='"+time+"'";
    getRowSet(query, consumer);
  }

  public void readDistinctLocations(BiConsumer<Boolean, RowSet<Row>> consumer)
  {
    String query = "select distinct Location from Konser";
    getRowSet(query,consumer);
  }

  public void readDistinctTimes(BiConsumer<Boolean, RowSet<Row>> consumer)
  {
    String query = "select distinct Time from Tiyatro";
    getRowSet(query,consumer);
  }

  public void readDistinctDates(BiConsumer<Boolean, RowSet<Row>> consumer)
  {
    String query = "select distinct Date from Tiyatro";
    getRowSet(query,consumer);
  }

  public void getRowSet(String query, BiConsumer<Boolean, RowSet<Row>> consumer)
  {
    client
      .query(query)
      .execute(ar -> {
        if(ar.succeeded())
        {
          io.vertx.sqlclient.RowSet<Row> result = ar.result();
          if(result.size()<0)
          {
            consumer.accept(false,null);
            System.out.println("Failure: "+ar.cause().getMessage());
          }
          else
          {
            for(Row row: result)
            {
              System.out.println("row requested: "+row);
            }
            System.out.println("Got "+result.size()+" rows");
            consumer.accept(true,result);
          }
        }else
        {
          System.out.println("Failure: " + ar.cause().getMessage());
        }
        //client.close();
      } );
  }

}
