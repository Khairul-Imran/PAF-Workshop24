package com.example.day24workshopfinalised.Repositories;

public class Queries {

        public static final String SQL_INSERT_ORDER_DETAILS = """
                        insert into order_details (product, unit_price, discount, quantity, order_id)
                        values(?, ?, ?, ?, ?)
                        """;

        public static final String SQL_INSERT_ORDER = """
                        insert into orders (customer_name, ship_address, notes, tax)
                        values(?, ?, ?, ?)
                        """;

        public static final String SQL_GET_ORDER_BY_ORDER_ID = """
                        select * from orders
                        where order_id = ?
                        """;

        public static final String SQL_GET_ORDER_DETAILS_BY_ORDER_ID = """
                        select * from order_details
                        where order_id = ?
                        """;

        public static final String SQL_GET_LATEST_ORDER_ID = """
                        select * from orders
                        order by order_date desc
                        limit 1
                        """;
}
