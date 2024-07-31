package com.rgarage.employeeportal.config;

import com.rgarage.employeeportal.employee.resolvers.EmployeeResolver;
import com.rgarage.employeeportal.user.UserResolver;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import io.leangen.graphql.GraphQLSchemaGenerator;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class GraphqlConfig {

    private final EmployeeResolver employeeResolver;
    private final UserResolver userResolver;

    @Bean
    GraphQL getGraphQL() {

        return new GraphQL.Builder(schema()).build();
    }
    @Bean
    GraphQLSchema schema() {
        return new GraphQLSchemaGenerator()
                .withBasePackages("com.rgarage.employeeportal")
                .withOperationsFromSingleton(this.employeeResolver)
                .withOperationsFromSingleton(this.userResolver)
                .generate();
    }
}
