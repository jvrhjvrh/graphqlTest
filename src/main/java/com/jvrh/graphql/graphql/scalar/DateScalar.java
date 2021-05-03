package com.jvrh.graphql.graphql.scalar;

import com.jvrh.graphql.utils.DateUtils;
import graphql.language.StringValue;
import graphql.schema.*;
import org.springframework.stereotype.Component;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.function.Function;

@Component
public class DateScalar extends GraphQLScalarType {
    private final static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    public DateScalar() {
        super("Date", "Date - 'YYYYMMDD'", new Coercing<LocalDate, String>() {
            @Override
            public String serialize(Object input) throws CoercingSerializeException {
                TemporalAccessor temporalAccessor;
                if (input instanceof TemporalAccessor) {
                    temporalAccessor = (TemporalAccessor) input;
                } else if (input instanceof String) {
                    temporalAccessor = parseLocalDate(input.toString(), CoercingSerializeException::new);
                } else {
                    throw new CoercingSerializeException(
                            "Expected a 'String' or 'java.time.temporal.TemporalAccessor' but was '" + typeName(input) + "'."
                    );
                }
                try {
                    return dateFormatter.format(temporalAccessor);
                } catch (DateTimeException e) {
                    throw new CoercingSerializeException(
                            "Unable to turn TemporalAccessor into full date because of : '" + e.getMessage() + "'."
                    );
                }
            }

            @Override
            public LocalDate parseValue(Object input) throws CoercingParseValueException {
                TemporalAccessor temporalAccessor;
                if (input instanceof TemporalAccessor) {
                    temporalAccessor = (TemporalAccessor) input;
                } else if (input instanceof String) {
                    temporalAccessor = parseLocalDate(input.toString(), CoercingParseValueException::new);
                } else {
                    throw new CoercingParseValueException(
                            "Expected a 'String' or 'java.time.temporal.TemporalAccessor' but was '" + typeName(input) + "'."
                    );
                }
                try {
                    return LocalDate.from(temporalAccessor);
                } catch (DateTimeException e) {
                    throw new CoercingParseValueException(
                            "Unable to turn TemporalAccessor into full date because of : '" + e.getMessage() + "'."
                    );
                }
            }

            @Override
            public LocalDate parseLiteral(Object input) throws CoercingParseLiteralException {
                if (!(input instanceof StringValue)) {
                    throw new CoercingParseLiteralException(
                            "Expected AST type 'StringValue' but was '" + typeName(input) + "'."
                    );
                }
                return parseLocalDate(((StringValue) input).getValue(), CoercingParseLiteralException::new);
            }

            private LocalDate parseLocalDate(String s, Function<String, RuntimeException> exceptionMaker) {
                try {
                    TemporalAccessor temporalAccessor = dateFormatter.parse(s);
                    return LocalDate.from(temporalAccessor);
                } catch (DateTimeParseException e) {
                    throw exceptionMaker.apply("Invalid RFC3339 full date value : '" + s + "'. because of : '" + e.getMessage() + "'");
                }
            }

            private String typeName(Object input) {
                if (input == null) {
                    return "null";
                }

                return input.getClass().getSimpleName();
            }
        });
    }
}
