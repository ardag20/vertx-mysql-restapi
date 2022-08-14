package com.example.starter;

import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.SqlClient;

import io.vertx.sqlclient.RowSet;
import java.util.function.BiConsumer;

public class MuseumRepository {

  public MuseumRepository(SqlClient client) {
    this.client = client;
  }

  private SqlClient client;

  public void readyByLocation(String location, BiConsumer<Boolean, RowSet<Row>> consumer)
  {
    String query = "select * from Muze where Location='"+location+"'";
    getRowSet(query,consumer);
  }

  public void readyByRating(String rating, BiConsumer<Boolean, RowSet<Row>> consumer)
  {
    String query = "select * from Muze where Rating>="+rating;
    getRowSet(query,consumer);
  }

  public void readByName(String name, BiConsumer<Boolean, RowSet<Row>> consumer)
  {
    String query = "select * from Muze where Name='"+name+"'";
    getRowSet(query,consumer);
  }

  public void readByNumber(String number, BiConsumer<Boolean, RowSet<Row>> consumer)
  {
    String query = "select * from Muze where Number="+number;
    getRowSet(query,consumer);
  }

  public void readByType(String type, BiConsumer<Boolean, RowSet<Row>> consumer)
  {
    String query = "select * from Muze where Type='"+type+"'";
    getRowSet(query,consumer);
  }

  public void readAll(String all, BiConsumer<Boolean, RowSet<Row>> consumer)
  {
    String query = "select * from Muze";
    getRowSet(query,consumer);
  }

  public void getRowSet(String query, BiConsumer<Boolean, RowSet<Row>> consumer)
  {
    client
      .query(query)
      .execute(ar -> {
        if (ar.succeeded()) {
          io.vertx.sqlclient.RowSet<Row> result = ar.result();
          if(result.size()<0)
          {
            consumer.accept(false,null);
            System.out.println("Failure: " + ar.cause().getMessage());
          }
          else
          {
            for(Row museum: result)
            {
              System.out.println(museum.getString("Name"));
            }
            System.out.println("Got " + result.size() + " rows ");
            consumer.accept(true,result);
          }
        } else {
          System.out.println("Failure: " + ar.cause().getMessage());
        }

        // Now close the pool
        //client.close();
      });

  }


}
