package com.rgarage.employeeportal.config;

import com.rgarage.employeeportal.employee.resolvers.EmployeeResolver;
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


    @Bean
    GraphQL getGraphQL() {

        return new GraphQL.Builder(schema()).build();
    }
    @Bean
    GraphQLSchema schema() {
        return new GraphQLSchemaGenerator()
                .withBasePackages("com.rgarage.employeeportal")
                .withOperationsFromSingleton(this.employeeResolver)

                .generate();
    }
}
