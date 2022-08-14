package com.example.starter;

import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.SqlClient;

import io.vertx.sqlclient.RowSet;
import java.util.function.BiConsumer;



public class TheatreRepository {

  public TheatreRepository(SqlClient client) {
    this.client = client;
  }

  private SqlClient client;

  public void readByNumber(String number, BiConsumer<Boolean, RowSet<Row>> consumer)
  {
    String query = "select * from Tiyatro where Number="+number;
    getRowSet(query,consumer);
  }

  public void readByPlayName(String playname, BiConsumer<Boolean, RowSet<Row>> consumer)
  {
    String query = "select * from Tiyatro where Play_Name='"+playname+"'";
    getRowSet(query,consumer);
  }

  public void readByDate(String date, BiConsumer<Boolean, RowSet<Row>> consumer)
  {
    String query = "select * from Tiyatro where Date='"+date+"'";
    getRowSet(query, consumer);
  }

  public void readByTime(String time, BiConsumer<Boolean, RowSet<Row>> consumer)
  {
    String query = "select * from Tiyatro where Time='"+time+"'";
    getRowSet(query, consumer);
  }

  public void readByTheatreName(String theatrename, BiConsumer<Boolean, RowSet<Row>> consumer)
  {
      String query = "select * from Tiyatro where Theatre_Name='"+theatrename+"'";
      getRowSet(query,consumer);
  }

  public void readByType(String type, BiConsumer<Boolean, RowSet<Row>> consumer)
  {
    String query = "select * from Tiyatro where Type='"+type+"'";
    getRowSet(query,consumer);
  }

  public void readAll(String all, BiConsumer<Boolean, RowSet<Row>> consumer)
  {
    String query = "select * from Tiyatro";
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
            for(Row theatre: result)
            {
              System.out.println(theatre.getString("Play_Name"));
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
