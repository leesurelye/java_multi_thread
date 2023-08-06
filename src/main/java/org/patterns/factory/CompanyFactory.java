package org.patterns.factory;

/**
 * 抽象工厂模式
 */
public abstract class CompanyFactory
{

}
//SELECT `empid`, `deptno`, COUNT(*) AS `c`, SUM(`empid`) AS `s`
//FROM `calcite_inner`.`emps`
//INNER JOIN `calcite_inner`.`depts` USING (`deptno`)
//WHERE `deptno` >= 10
//GROUP BY `empid`, `deptno`