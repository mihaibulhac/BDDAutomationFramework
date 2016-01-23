package tests;

import TADHDB.SPersons;
import com.mysema.commons.lang.Pair;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Query;
import com.querydsl.core.Tuple;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.*;
import com.querydsl.core.types.dsl.NumberOperation;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.sql.*;
import org.hsqldb.Session;

import javax.annotation.Nullable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.function.Function;

/**
 * Created by Mihai on 1/23/2016.
 */



public class queries {



    public static void queryTest1(){
        System.out.println("...enter...");

        try {
            Class.forName("com.mysql.jdbc.Driver");

        String url = "jdbc:mysql://db4free.net:3306/test_automation";
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(url, "mihai", "mypassword");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            SQLTemplates dialect = new MySQLTemplates();
            //SQLTemplates dialect = new HSQLDBTemplates(); // SQL-dialect
            SQLQuery query = new SQLQuery(conn, dialect);
            SQLQuery query2 = new SQLQuery(conn, dialect);
            SQLQuery query3 = new SQLQuery(conn, dialect);

            BooleanBuilder whereClause = new BooleanBuilder();


            //SQLTemplates dialect = new HSQLDBTemplates(); // SQL-dialect
            //SQLQuery query = new SQLQuery(connection, dialect);



            SPersons customer=SPersons.Persons;


            query.select(customer.firstName,customer.lastName).from(customer);

            whereClause.andAnyOf(customer.personID.isNotNull());
            whereClause.and(customer.firstName.like("%Gig%"));
            whereClause.or(customer.personID.like("%11223344%"));

            query.where(whereClause);


            //List<Projection> tuples = query3.from(customer).select(
             //       new QProjection(customer.firstName, customer.lastName,customer.personID)).fetch();

            List<HashMap<String,String>> tuples = query3.from(customer).select(
                    new HashMap<String,String>).fetch();




            System.out.println("SQL query: " + query);

            //List<Tuple> queryResult=query.from(customer).fetchResults(new QTuple(customer.firstName));
            List<Tuple> queryResult=query.fetchResults().getResults();
//            List<Tuple> queryResult2= query2.fetchResults().getResults();




            List<HashMap> list = new ArrayList<HashMap>();
            HashMap hm = new HashMap();
            hm.put(customer.firstName, "Gica");
            hm.put(customer.lastName,"Mihai");
            list.add(hm);





           // System.out.println("Contine qtuple: " + queryResult2.containsAll(queryResult));
            //System.out.println("Contine hmap: " + queryResult2.containsAll(list));

            //queryResult2.removeAll(queryResult);









            System.out.println("query end" + customer.firstName.getMetadata() );
            System.out.println("query end" + customer.firstName.toString() );
            //System.out.println("expr: " + queryResult.co );

           // HashMap<String,String> test=queryResult.get(0);




            for (Tuple row: queryResult) {
                HashMap hp = new HashMap();
                hp.put( row.get(customer.firstName).toString(), row.get(customer.firstName));
                list.add(hp);

                System.out.println("First Name: " + row.get(customer.firstName) );
                System.out.println("Last Name: " + row.get(customer.lastName) );


            }

            System.out.println("query end" );




        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }


    public static void main(String[] args) throws Exception {
        System.out.println("Start...");
        queryTest1();
        System.out.println("End.");

    }


}
