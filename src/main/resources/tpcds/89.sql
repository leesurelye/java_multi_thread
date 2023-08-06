SELECT `i_category`, `i_class`, `i_brand`, `s_store_name`, `s_company_name`, `d_moy`, `sum_sales`, `avg_monthly_sales`
FROM (SELECT `i_category`, `i_class`, `i_brand`, `s_store_name`, `s_company_name`, `d_moy`, `sum_sales`, CAST(CAST(`w0$o0` AS DOUBLE PRECISION) / `w0$o1` AS DOUBLE PRECISION) AS `avg_monthly_sales`, `sum_sales` - CAST(`w0$o0` AS DOUBLE PRECISION) / `w0$o1` AS `EXPR_8`
      FROM (SELECT `t5`.`i_category`, `t5`.`i_class`, `t5`.`i_brand`, `t`.`s_store_name`, `t`.`s_company_name`, `t5`.`d_moy`, SUM(`t5`.`ss_sales_price`) AS `sum_sales`, SUM(SUM(`t5`.`ss_sales_price`)) OVER (PARTITION BY `t5`.`i_category`, `t5`.`i_brand`, `t`.`s_store_name`, `t`.`s_company_name` RANGE BETWEEN UNBOUNDED PRECEDING AND UNBOUNDED FOLLOWING) AS `w0$o0`, COUNT(SUM(`t5`.`ss_sales_price`)) OVER (PARTITION BY `t5`.`i_category`, `t5`.`i_brand`, `t`.`s_store_name`, `t`.`s_company_name` RANGE BETWEEN UNBOUNDED PRECEDING AND UNBOUNDED FOLLOWING) AS `w0$o1`
            FROM (SELECT `s_store_sk`, `s_store_name`, `s_company_name`
                  FROM `mysql0718`.`tcpds_2g__store`) AS `t`
                     INNER JOIN (SELECT `t3`.`i_item_sk`, `t3`.`i_brand`, `t3`.`i_class`, `t3`.`i_category`, `t4`.`ss_sold_date_sk`, `t4`.`ss_item_sk`, `t4`.`ss_store_sk`, `t4`.`ss_sales_price`, `t1`.`d_date_sk`, `t1`.`d_year`, `t1`.`d_moy`
                                 FROM (SELECT *
                                       FROM (SELECT `d_date_sk`, `d_year`, `d_moy`
                                             FROM `mysql0718`.`tcpds_2g__date_dim`) AS `t0`
                                       WHERE `d_year` = 1999) AS `t1`
                                          INNER JOIN ((SELECT *
                                                       FROM (SELECT `i_item_sk`, `i_brand`, `i_class`, `i_category`
                                                             FROM `mysql0718`.`tcpds_2g__item`) AS `t2`
                                                       WHERE `i_category` IN ('Books', 'Electronics', 'Sports') AND `i_class` IN ('computers', 'football', 'stereo') OR `i_category` IN ('Jewelry', 'Men', 'Women') AND `i_class` IN ('birdal', 'dresses', 'shirts')) AS `t3` INNER JOIN (SELECT `ss_sold_date_sk`, `ss_item_sk`, `ss_store_sk`, `ss_sales_price`
                                                                                                                                                                                                                                                                                          FROM `mysql0718`.`tcpds_2g__store_sales`) AS `t4` ON `t3`.`i_item_sk` = `t4`.`ss_item_sk`) ON `t1`.`d_date_sk` = `t4`.`ss_sold_date_sk`) AS `t5` ON `t`.`s_store_sk` = `t5`.`ss_store_sk`
            GROUP BY `t5`.`i_category`, `t5`.`i_class`, `t5`.`i_brand`, `t`.`s_store_name`, `t`.`s_company_name`, `t5`.`d_moy`) AS `t9`
      WHERE CASE WHEN CAST(`t9`.`w0$o0` AS DOUBLE PRECISION) / `t9`.`w0$o1` <> 0 THEN ABS(`t9`.`sum_sales` - CAST(`t9`.`w0$o0` AS DOUBLE PRECISION) / `t9`.`w0$o1`) / (CAST(`t9`.`w0$o0` AS DOUBLE PRECISION) / `t9`.`w0$o1`) > 0.1 ELSE FALSE END
      ORDER BY 9, `s_store_name`
          LIMIT 100) AS `t12`; with hints: {odps.sql.allow.fullscan=true, odps.sql.type.system.odps2=true, odps.sql.decimal.odps2=true}