package ch.zli.m223;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.inject.Stereotype;
import javax.transaction.Transactional;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusIntegrationTest
public class UserResourceIT extends UserResourceTest {
    // Execute the same tests but in packaged mode.

    @QuarkusTest
    @Stereotype
    @Transactional
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)

    public @interface TransactionalQuarkusTest {
}
}   
