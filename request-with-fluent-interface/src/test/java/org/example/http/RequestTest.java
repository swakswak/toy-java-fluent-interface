package org.example.http;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RequestTest {

    @Test
    void should_GetRequest_Not_Null_With_Builder() {
        GetRequest getRequest = Request.get("https://www.google.com/");

        assertNotNull(getRequest);
    }

    @Test
    void should_Executable_GetRequest() {
        ResponseResource<EmployeeList> resource = Request.get("http://localhost:8080/employees")
                .addHeader("x-user", "swakswak")
                .execute(EmployeeList.class);

        assertNotNull(resource.body());
        assertEquals(EmployeeList.class, resource.body().getClass());
    }
}