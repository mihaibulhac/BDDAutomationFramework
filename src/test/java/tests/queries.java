package tests;

import TADHDB.SPersons;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.sql.MySQLTemplates;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLTemplates;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

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
            SQLQuery query = new SQLQuery(conn, dialect);

            BooleanBuilder whereClause = new BooleanBuilder();



            SPersons customer=SPersons.Persons;
            query.select(customer.all()).from(customer);

            whereClause.andAnyOf(customer.personID.isNotNull());

            whereClause.and(customer.firstName.like("%Gig%"));
            whereClause.or(customer.personID.like("%11223344%"));



            query.where(whereClause);


            System.out.println("SQL query: " + query);

            List<Tuple> queryResult=query.fetchResults().getResults();
            System.out.println("query end");

            for (Tuple row: queryResult) {
                System.out.println("First Name: " + row.get(customer.firstName) );
                System.out.println("Last Name: " + row.get(customer.lastName) );


            }




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
