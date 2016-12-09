# JLinq
[![Build Status](https://travis-ci.org/claassen/jlinq.svg?branch=master)](https://travis-ci.org/claassen/jlinq) [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

A collection querying library alternative to Java 8 Streams with an API similar to Linq in C#. JLinq operates on Iterables and performs lazy evaluation wherever possible (for example lazy evaluation is not possible when using `join` or 'groupBy').

## Getting started
### Dependency
**Include as maven dependency**

```
<dependency>
    <groupId>com.github.claassen</groupId>
    <artifactId>jlinq</artifactId>
    <version>0.0.1</version>
</dependency>
```

**Include as gradle dependency**

```
compile 'com.github.claassen:jlinq:0.0.1'
```

### Examples
**Map/Reduce**

```java
int sum = query(Arrays.asList("1", "2", "3"))
    .map(x -> Integer.parseInt(x))
    .reduce(0, (acc, i) -> acc + i);
```

**Where**

```java
List<Integer> even = query(Arrays.asList(1, 2, 3, 4, 5))
    .where(x -> x % 2 == 0)
    .toList();
```

**Group By**

```java
List<MyClass> items = ...

//Groups items by field 'x' and sums values of field 'y' in each group
List<Integer> groupSums = query(items)
    .groupBy(x -> x.getX())
    .map(x -> x
        .reduce(0, (a, i) -> a + i.getY())
    ).toList();
```

**Join**

```java
List<MyClass> items1 = ...
List<MyOtherClass> items2 = ...

List<Tuple<MyClass, MyOtherClass>> = query(items1)
    .join(items2, (x, y) -> x.getX().equals(y.getY()))
    .toList();
```

**Union**

```java
List<Integer> union = query(Arrays.asList(1, 2, 3))
    .union(Arrays.asList(4, 5, 6))
    .union(Arrays.asList(7, 8, 9))
    .toList();
```

**Take**

```java
List<Integer> three = query(Arrays.asList(1, 2, 3, 4, 5))
    .take(3)
    .toList();
```

**Skip**

```java
List<Integer> fourFive = query(Arrays.asList(1, 2, 3, 4, 5))
    .skip(3)
    .toList();
```

**Sum/Avg/Min/Max**

It necessary to use the numeric version of `query`: `queryn` in order to perform numerical aggregation functions. Alternatively and already non-numeric query object can be converted into a numeric query object using `mapn`. Numeric aggregation functions always return a `double`.

```java
double sum = queryn(Arrays.asList(1, 2, 3)).sum();
double avg = queryn(Arrays.asList(1, 2, 3)).avg();
int min    = (int)queryn(Arrays.asList(1, 2, 3)).min();
int max    = (int)queryn(Arrays.asList(1, 2, 3)).max();

//Converting non numeric query to a numeric query
double sum = query(Arrays.asList("1", "2", "3"))
    .mapn(x -> Integer.parseInt(x))
    .sum();
```



