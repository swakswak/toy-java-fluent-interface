# toy-java-fluent-interface

다음과 같은 퍼블릭 인터페이스를 가진 REST API 클라이언트 구현

```java
Request.get("http://hostnameexample.com/products/1")
        .addHeader("x-user","홍길동")
        .execute(Product.class);
```