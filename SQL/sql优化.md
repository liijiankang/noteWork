##### 
    ```
        SQL 作为一项图灵奖级别的发明，其重要意义不单单是发明了一种可以用作数据查询的语言， 更重要的一点是发明了关系代数(Relation Algebra)这一工具， 使得计算机理解和处理查询的语义更加方便。SQL 查询语句的优化也是基于关系代数这一模型。
    ```
    * SQL 优化的本质是对关系代数的优化。
    ```
        select pv.siteid,user.nickname 
        from pv 
        join user
        on pv.siteid = user.siteid and pv.userid = user.id
        where pv.siteid=123;
    ```
![image](https://wx2.sinaimg.cn/mw690/0068lPfLgy1fyc18lutqgj30sh0pw0u9.jpg)

    总结使用关系代数进行查询优化的要点:
    1、SQL 查询可以表示成关系代数
    2、关系代数作为一种树形的结构，实质上也可以表示查询的物理实现方案流程
    3、关系代数可以进行局部的等价变换，变换前后返回的结果不变但执行的成本不同
    4、通过寻找执行成本最低的关系代数表示，我们就可以将一个 SQL 查询优化成更为高效的方案