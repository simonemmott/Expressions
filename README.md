# Expressions - v0.0.1
The Expressions project provides an implementation of Expressions and Predicates suitable for implementation as javax persistence 
Expressions and Predicates.  The Expressions and Predicates themselves do not implement the javax persistence API Expression and Predicate 
interfaces entirely though could be extended to support them. In essence they provide the same functionality without being tied to 
an Entity instance or criteria query.

Consequently they can be used to inject values in a template without being tied to the source of those values as well as providing 
logical control again without being tied to the source of data for the logical control.

Beyond supporting most of the javax persistence API methods of Expression and Predicate they also implement an `T evaluate(Evaluator)`
method. Calling this method returns the value of expression or predicate. The Evaluator is a simple Interface that is extended and implemented
to provide values for the expressions that require values from an external value source. e.g. ParameterExpressions and GetterExpressions

On its own the Expression project doesn't do anything particularly useful, however the it provides support services to any project that
needs to decouple the assembly of data from the source of the data e.g. the javax persistence API or a proforma style project.

Javadoc documentation of this project can be found [here](https://simonemmott.github.io/Proforma/index.html)


### License

[GNU GENERAL PUBLIC LICENSE v3](http://fsf.org/)

## Basic Example

The following Expression example code

```java
ExpressionBuilder xb = new ExpressionBuilder();

Expression<Integer> expr = xb.abs(xb.param(String.class, "p1"));

SimpleParameterEvaluator spe = new SimpleParameterEvaluator();

spe.set(String.class, "p1", "Hello World!");

System.out.println(expr.evaluate(spe));
```

Produces the following output

```text
Hello World!
```

The above example is trivial, however the Expression `expr` could be assembled into a more complex assembly of data completely independently 
from the implementation of the Evaluator interface used to provide values for the expressions in the assembly.

The following Predicate example code

```java
PredicateBuilder pb = new PredicateBuilder();
ExpressionBuilder xb = new ExpressionBuilder()

Predicate equals10 = pb.equals(xb.param(Integer.class, "p1"), 10);

SimpleParameterEvaluator spe = new SimpleParameterEvaluator();

spe.set(Integer.class, "p1", 5)

if (equals10.evaluate(spe)) System.out.println("5 == 10");
if (equals10.not().evaluate(spe)) System.out.println("5 != 10");
```

Produces the following output

```text
5 != 10
```




## Getting Started

Download a jar file containing the latest version or fork this project and install in your IDE

Maven users can add this project using the following additions to the pom.xml file.
```maven
<dependencies>
    ...
    <dependency>
        <groupId>com.k2</groupId>
        <artifactId>Expressions</artifactId>
        <version>0.0.1</version>
    </dependency>
    ...
</dependencies>
```

## Working With Expressions

All expressions are generated though the ExpressionBuilder. The `ExpressionBuiler` provides the following families of methods.

| Method             | Returns                    | Expression description |
|====================|============================|========================|
| param(...)         | Typed parameter expression | Returns the parameter value extracted from the `Evaluator` |
| get(...)           | Typed getter expression    | Returns the value generated by a `Getter` implementation through the `Evaluator` |
| abs(...)           | Number                     | Returns the absolute value of the given number |
| concatenate(...)   | String                     | Concatenates the string value of the given arguments together |
| currentDate()      | Date                       | Returns the current date as supplied by the `Evaluator`. Repeated calls to the the `current*()` methods return the same date |
| currentTime()      | Time                       | Returns the current time as supplied by the `Evaluator`. Repeated calls to the the `current*()` methods return the same time |
| currentTimestamp() | Timestamp                  | Returns the current timestamp as supplied by the `Evaluator`. Repeated calls to the the `current*()` methods return the same timestamp |
| diff(...)          | Number                     | Returns the difference between the given arguments |
| length(...)        | Integer                    | Returns the length of the given String expression |
| literal(...)       | Typed literal value        | Returns the given literal value |
| locate(...)        | Integer                    | Returns the start of one string within another |
| mod(...)           | Integer                    | Returns the modulus of the two arguments |
| neg(...)           | Number                     | Returns the negative value of the given argument |
| nullIf(...)        | Typed value                | Returns null if the first argument is null or equal to the second argument, otherwise it returns the first argument |
| nullLiteral(...)   | Typed null value           | Returns null |
| prod(...)          | Number                     | Returns the product of the two arguments |
| quot(...)          | Double                     | Returns the quotient of the two arguments rounded to 12 decimal places |
| sqrt(...)          | Double                     | Returns the square root of the given argument rounded to 12 decimal places |
| substring(...)     | String                     | Returns a portion of the given string argument |
| toBigDecimal(...)  | BigDecimal                 | Converts the given number into a big decimal |
| toBigInteger(...)  | BigInteger                 | Converts the given number into a big integer |
| toBoolean(...)     | Boolean                    | Converts the given argument into a boolean value using the methods from BooleanUtil |
| toDate(...)        | Date                       | Converts the given argument into a date using the methods from DateUtil |
| toDouble(...)      | Double                     | Converts the given argument into a Double value using the methods from DoubleUtil |
| toFloat(...)       | Float                      | Converts the given argument into a Float value using the methods from the FloatUtil |
| toInteger(...)     | Integer                    | Converts the given argument into an Integer value using the methods from IntegerUtil |
| toLong(...)        | Long                       | Converts the given argument into a Long value using the methods from LongUtil |
| toString(...)      | String                     | Converts the given argument into a String value using the methods from StringUtil |
| trim(...)          | String                     | Trims leading and/or trailing characters from the given string |
| upper(...)         | String                     | Converts the given string to upper case |

Expressions define the following predicate methods

| Method      | Description |
|=============|=============|
| isNull()    | Provides a predicate that returns true if the expression returns null |
| isNotNull() | Provides a predicate that returns true if the expression does not return null |
| in (...)    | Provides a predicate that returns true is the expression is in the given arguments |

## Working With Predicates

All predicates are generated though the PredicateBuilder. The `PredicateBuilder` provides the following families of methods

| Method                    | Predicate description |
|===========================|=======================|
| and(...)                  | True if all arguemnts are true |
| between(...)              | True if the first argument is between the subsequent two arguments |
| equals(...)               | True if the two arguments are equal |
| ge(...)                   | True if the first numeric argument is greater than or equal to the second argument |
| greaterThan(...)          | True is the first comparable argument is greater than the second argument |
| greaterThanOrEqualTo(...) | True if the first comparable argument is greater than or equal to the second argument |
| gt(...)                   | True if first numerical argument is greater than the second numerical argument |
| in(...)                   | True if the first argument is in the subsequent arguments |
| isFalse()                 | Returns false |
| isFalse(...)              | Returns true if the argument is false |
| isTrue()                  | Returns true |
| isTrue(...)               | Returns true is the argument is true |
| le(...)                   | True if the first numerical argument is less than or equal to the second argument |
| lessThan(...)             | True if the first comparable argument is less that the second argument |
| lessThanOrEqualTo(...)    | True if the first comparable argument is less than or equal to the second argument |
| like (...)                | True if the string matches the pattern. For simplicity of use and SQL compatibility the character `%` is replaced with the regex (.*) |
| lt(...)                   | True if the first numerical argument is less than the second arguemnt |
| not(...)                  | True if the given argument is false |
| isNotNull(...)            | True if the given argument is not null |
| isNull(...)               | True if the given argument is null |
| or(...)                   | True if any of the arguments are true |















