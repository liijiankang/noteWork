##### 闭包
    * Scala中可以在任何作用域中定义函数,在函数体内可以访问到相应作用域内任何变量,但是如果函数不再处于作用域时被调用呢?
     def mulBy(x:double) = (y:double) => x*y
     有如下调用:
     val triple = mulBy(3)
     val half = mulBy(0.5)
     print(triple(14)+" " +half(14))//将打印42 7
    mulBy首次被调用时将3*y函数存入到triple中,接下来mulby再次被调用时将0.5*y存入到half中.
    每一个返回的函数都有自己的x设置,这样的函数被称作闭包.
##### 柯里化
    * 将原来接受两个参数的函数变成新的接受一个参数的函数的过程
    def mulOneAtTime(x:int) = (y:int) => x*y
    mulOneAtTime(6)(7) 结果为42
    mulOneAtTime(6)返回的结果是6*y
##### 辅助构造器
    * 辅助构造器的名字为this
    * 每一个辅助构造器都必须以一个对先前已定义的其他辅助构造器或主构造器的调用开始
    * 一个类如果没有显式定义主构造器则自动拥有一个无参的主构造器
    ```
        class Person {
            private var name = ""
            private var age = 0
            def this(name:string){
                this()//调用主构造器
                this.name=name
            }
            def this(name:string,age:int){
                this(name)//调用前一个辅助构造器
                this.age=age
            }
        }
        
        可以以三种方式构建对象
        val p1 = new Person
        val p2 = new Person("ljk")
        val p3 = new Person("ljk",20)
    ```
##### 主构造器
    * 主构造器不以this方法定义,与类定义交织在一起
    * 主构造器的参数直接放置在类名之后
    class Person(val name:string,val age:int){}
    