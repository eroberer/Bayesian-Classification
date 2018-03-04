# Bayesian-Classification
Living species classification with Bayesian

# Example
 Give birth, Can fly, Live in water, Have legs
 0: no, 1: yes, 2: sometimes
```java
Bayesian bayesian = new Bayesian("File", "Positive Class Name", "Negative Class Name");
String unknownType = "1,0,1,0";
String result = bayesian.getResult(unknownType);
System.out.println(result);
```
# Result
```
Positive Rate -> 0.020991253644314863
Negative Rate -> 0.0027309968138370514
mammals
```
