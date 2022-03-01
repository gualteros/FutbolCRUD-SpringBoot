
package com.futboldemo.mslegacyrouting.routes;

import javax.annotation.Resource;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.MarshalException;

import org.apache.camel.ValidationException;
import org.apache.camel.http.common.HttpOperationFailedException;

import javax.xml.xpath.XPathExpressionException;

import org.apache.camel.Exchange;
import org.apache.camel.component.bean.validator.BeanValidationException;
import org.springframework.stereotype.Component;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.futboldemo.mslegacyrouting.configurator.ConfigurationRoute;
import com.futboldemo.mslegacyrouting.exceptions.CustomValidationException;
import com.futboldemo.mslegacyrouting.util.ErrorsEnum;

import org.apache.camel.LoggingLevel;

@Component
public class TransformationRoute extends ConfigurationRoute {

        @Override
        public void configure() throws Exception {
                super.configure();

                onException(HttpOperationFailedException.class).handled(true)
                        .log(LoggingLevel.ERROR,
                        "La ruta no logra resolver el endpoint externo de validacion, presenta el codigo de error 404 en la ruta ${routeId}")
                        .log(LoggingLevel.ERROR, ErrorsEnum.ERROR_EXCEPTIONCLASS.getMessage())
                        .log(LoggingLevel.ERROR, ErrorsEnum.ERROR_EXCEPTIONCLASS_NAME.getMessage())
                        .log(LoggingLevel.ERROR, ErrorsEnum.ERROR_STACKTRACE.getMessage())
                        .setHeader(Exchange.CONTENT_TYPE, simple(MediaType.APPLICATION_JSON.toString()))
                        .setHeader(Exchange.HTTP_RESPONSE_CODE, simple("500"))
                        .setHeader(ErrorsEnum.ERROR_HEADER.getMessage(),
                                        simple(ErrorsEnum.ERROR_EXCEPTION.getMessage()))
                        .bean(ErrorsEnum.BEAN_TRANSFORMATION.getMessage(),
                                        ErrorsEnum.BEAN_CREATE_RSERROR.getMessage())
                        .wireTap(ErrorsEnum.DEFAULT_LOG_ERROR_ROUTE.getMessage())
                .end();

                onException(CustomValidationException.class).handled(true)
                        .log(LoggingLevel.ERROR,
                        "La ruta no logra resolver el endpoint externo de validacion, presenta el codigo de error 404 en la ruta ${routeId}")
                        .log(LoggingLevel.ERROR, ErrorsEnum.ERROR_EXCEPTIONCLASS.getMessage())
                        .log(LoggingLevel.ERROR, ErrorsEnum.ERROR_EXCEPTIONCLASS_NAME.getMessage())
                        .log(LoggingLevel.ERROR, ErrorsEnum.ERROR_STACKTRACE.getMessage())
                        .setHeader(Exchange.CONTENT_TYPE, simple(MediaType.APPLICATION_JSON.toString()))
                        .setHeader(Exchange.HTTP_RESPONSE_CODE, simple("500"))
                        .setHeader(ErrorsEnum.ERROR_HEADER.getMessage(),
                                        simple(ErrorsEnum.ERROR_EXCEPTION.getMessage()))
                        .bean(ErrorsEnum.BEAN_TRANSFORMATION.getMessage(),
                                        ErrorsEnum.BEAN_CREATE_RSERROR.getMessage())
                        .wireTap(ErrorsEnum.DEFAULT_LOG_ERROR_ROUTE.getMessage())
                .end();

                onException(NullPointerException.class).handled(true)
                                .log(LoggingLevel.ERROR, ErrorsEnum.ERROR_ESTRUCTURA.getMessage())
                                .log(LoggingLevel.ERROR, ErrorsEnum.ERROR_EXCEPTIONCLASS.getMessage())
                                .log(LoggingLevel.ERROR, ErrorsEnum.ERROR_EXCEPTIONCLASS_NAME.getMessage())
                                .log(LoggingLevel.ERROR, ErrorsEnum.ERROR_STACKTRACE.getMessage())
                                .setHeader(Exchange.HTTP_RESPONSE_CODE, simple("500"))
                                .setHeader(Exchange.CONTENT_TYPE, simple(MediaType.APPLICATION_JSON.toString()))
                                .setHeader(ErrorsEnum.ERROR_HEADER.getMessage(),
                                                simple(ErrorsEnum.ERROR_EXCEPTION.getMessage()))
                                .bean(ErrorsEnum.BEAN_TRANSFORMATION.getMessage(),
                                                ErrorsEnum.BEAN_CREATE_RSERROR.getMessage())
                                .wireTap(ErrorsEnum.DEFAULT_LOG_ERROR_ROUTE.getMessage())
                                .end();

                onException(InvalidFormatException.class).handled(true)
                                .log(LoggingLevel.ERROR,
                                                "Exception de formato, error de serializacion o deserializacion en la ruta ${routeId}")
                                .log(LoggingLevel.ERROR, ErrorsEnum.ERROR_EXCEPTIONCLASS.getMessage())
                                .log(LoggingLevel.ERROR, ErrorsEnum.ERROR_EXCEPTIONCLASS_NAME.getMessage())
                                .log(LoggingLevel.ERROR, ErrorsEnum.ERROR_STACKTRACE.getMessage())
                                .setHeader(Exchange.CONTENT_TYPE, simple(MediaType.APPLICATION_JSON.toString()))
                                .setHeader(Exchange.HTTP_RESPONSE_CODE, simple("400"))
                                .setHeader(ErrorsEnum.ERROR_HEADER.getMessage(),
                                                simple(ErrorsEnum.ERROR_EXCEPTION.getMessage()))
                                .bean(ErrorsEnum.BEAN_TRANSFORMATION.getMessage(),
                                                ErrorsEnum.BEAN_CREATE_RSERROR.getMessage())
                                .wireTap(ErrorsEnum.DEFAULT_LOG_ERROR_ROUTE.getMessage())
                                .end();

                onException(UnmarshalException.class).handled(true)
                                .log(LoggingLevel.ERROR, ErrorsEnum.ERROR_ESTRUCTURA.getMessage())
                                .log(LoggingLevel.ERROR, ErrorsEnum.ERROR_EXCEPTIONCLASS.getMessage())
                                .log(LoggingLevel.ERROR, ErrorsEnum.ERROR_EXCEPTIONCLASS_NAME.getMessage())
                                .log(LoggingLevel.ERROR, ErrorsEnum.ERROR_STACKTRACE.getMessage())
                                .setHeader(Exchange.CONTENT_TYPE, simple(MediaType.APPLICATION_JSON.toString()))
                                .setHeader(Exchange.HTTP_RESPONSE_CODE, simple("400"))
                                .setHeader(ErrorsEnum.ERROR_HEADER.getMessage(),
                                                simple(ErrorsEnum.ERROR_EXCEPTION.getMessage()))
                                .bean(ErrorsEnum.BEAN_TRANSFORMATION.getMessage(),
                                                ErrorsEnum.BEAN_CREATE_RSERROR.getMessage())
                                .wireTap(ErrorsEnum.DEFAULT_LOG_ERROR_ROUTE.getMessage())
                                .end();

                onException(JsonParseException.class).handled(true)
                                .log(LoggingLevel.ERROR,
                                                "El contenido del mensaje no se ajusta a la sintaxis json, error en la ruta ${routeId}")
                                .log(LoggingLevel.ERROR, ErrorsEnum.ERROR_EXCEPTIONCLASS.getMessage())
                                .log(LoggingLevel.ERROR, ErrorsEnum.ERROR_EXCEPTIONCLASS_NAME.getMessage())
                                .log(LoggingLevel.ERROR, ErrorsEnum.ERROR_STACKTRACE.getMessage())
                                .setHeader(Exchange.CONTENT_TYPE, simple(MediaType.APPLICATION_JSON.toString()))
                                .setHeader(Exchange.HTTP_RESPONSE_CODE, simple("400"))
                                .setHeader(ErrorsEnum.ERROR_HEADER.getMessage(),
                                                simple(ErrorsEnum.ERROR_EXCEPTION.getMessage()))
                                .bean(ErrorsEnum.BEAN_TRANSFORMATION.getMessage(),
                                                ErrorsEnum.BEAN_CREATE_RSERROR.getMessage())
                                .wireTap(ErrorsEnum.DEFAULT_LOG_ERROR_ROUTE.getMessage())
                                .end();

                onException(MarshalException.class).handled(true)
                                .log(LoggingLevel.ERROR, ErrorsEnum.ERROR_ESTRUCTURA.getMessage())
                                .log(LoggingLevel.ERROR, ErrorsEnum.ERROR_EXCEPTIONCLASS.getMessage())
                                .log(LoggingLevel.ERROR, ErrorsEnum.ERROR_EXCEPTIONCLASS_NAME.getMessage())
                                .log(LoggingLevel.ERROR, ErrorsEnum.ERROR_STACKTRACE.getMessage())
                                .setHeader(Exchange.CONTENT_TYPE, simple(MediaType.APPLICATION_JSON.toString()))
                                .setHeader(Exchange.HTTP_RESPONSE_CODE, simple("400"))
                                .setHeader(ErrorsEnum.ERROR_HEADER.getMessage(),
                                                simple(ErrorsEnum.ERROR_EXCEPTION.getMessage()))
                                .bean(ErrorsEnum.BEAN_TRANSFORMATION.getMessage(),
                                                ErrorsEnum.BEAN_CREATE_RSERROR.getMessage())
                                .wireTap(ErrorsEnum.DEFAULT_LOG_ERROR_ROUTE.getMessage())
                                .end();

                onException(BeanValidationException.class).handled(true)
                        .log(LoggingLevel.ERROR,
                                "La estructura del mensaje entrante presenta errores en la ruta ${routeId}")
                        .log(LoggingLevel.ERROR, ErrorsEnum.ERROR_EXCEPTIONCLASS.getMessage())
                        .log(LoggingLevel.ERROR, ErrorsEnum.ERROR_EXCEPTIONCLASS_NAME.getMessage())
                        .log(LoggingLevel.ERROR, ErrorsEnum.ERROR_STACKTRACE.getMessage())
                        .setHeader(Exchange.HTTP_RESPONSE_CODE, simple("400"))
                        .setHeader(Exchange.CONTENT_TYPE, simple(MediaType.APPLICATION_JSON.toString()))
                        .setHeader(ErrorsEnum.ERROR_HEADER.getMessage(),
                                        simple(ErrorsEnum.ERROR_EXCEPTION.getMessage()))
                        .bean(ErrorsEnum.BEAN_TRANSFORMATION.getMessage(),
                                        ErrorsEnum.BEAN_CREATE_RSERROR.getMessage())
                        .wireTap(ErrorsEnum.DEFAULT_LOG_ERROR_ROUTE.getMessage())
                .end();

                onException(ValidationException.class).handled(true)
                        .log(LoggingLevel.ERROR,
                                "La estructura del mensaje entrante presenta errores en la ruta ${routeId}")
                        .log(LoggingLevel.ERROR, ErrorsEnum.ERROR_EXCEPTIONCLASS.getMessage())
                        .log(LoggingLevel.ERROR, ErrorsEnum.ERROR_EXCEPTIONCLASS_NAME.getMessage())
                        .log(LoggingLevel.ERROR, ErrorsEnum.ERROR_STACKTRACE.getMessage())
                        .setHeader(Exchange.HTTP_RESPONSE_CODE, simple("400"))
                        .setHeader(Exchange.CONTENT_TYPE, simple(MediaType.APPLICATION_JSON.toString()))
                        .setHeader(ErrorsEnum.ERROR_HEADER.getMessage(),
                                simple(ErrorsEnum.ERROR_EXCEPTION.getMessage()))
                        .bean(ErrorsEnum.BEAN_TRANSFORMATION.getMessage(),
                                ErrorsEnum.BEAN_CREATE_RSERROR.getMessage())
                        .wireTap(ErrorsEnum.DEFAULT_LOG_ERROR_ROUTE.getMessage())
                .end();

                onException(XPathExpressionException.class).handled(true)
                        .log(LoggingLevel.ERROR,
                                        "La expresion entregada no se pudo aplicar al mensaje y presenta errores en la ruta ${routeId}")
                        .log(LoggingLevel.ERROR, ErrorsEnum.ERROR_EXCEPTIONCLASS.getMessage())
                        .log(LoggingLevel.ERROR, ErrorsEnum.ERROR_EXCEPTIONCLASS_NAME.getMessage())
                        .log(LoggingLevel.ERROR, ErrorsEnum.ERROR_STACKTRACE.getMessage())
                        .setHeader(Exchange.HTTP_RESPONSE_CODE, simple("400"))
                        .setHeader(Exchange.CONTENT_TYPE, simple(MediaType.APPLICATION_JSON.toString()))
                        .setHeader(ErrorsEnum.ERROR_HEADER.getMessage(),
                                        simple(ErrorsEnum.ERROR_EXCEPTION.getMessage()))
                        .bean(ErrorsEnum.BEAN_TRANSFORMATION.getMessage(),
                                        ErrorsEnum.BEAN_CREATE_RSERROR.getMessage())
                        .wireTap(ErrorsEnum.DEFAULT_LOG_ERROR_ROUTE.getMessage())
                .end();

                // Here is where you need make all the process in the route.
                from("direct:postTransformationRoute").routeId("mslegacyrouting_transformation")
                        .log(".:: Inicio del Servicio ::.")
                        .to("bean-validator://validateMessage")
                        .log("${body.toString()}")
                        .log("${body.getClass()}")
                        .bean("transformationComponent","validateCapacidad")
                        .setProperty("Nombre", simple("${body.getNombre()}"))
                        .setProperty("Ciudad", simple("${body.getCiudad()}"))
                        .setProperty("Propietario", simple("${body.getPropietario()}"))
                        .setProperty("Capacidad", simple("${body.getCapacidad()}"))
                        .setProperty("Division", simple("${body.getDivision()}"))
                        .setProperty("Competicion", simple("${body.getCompeticion()}"))	
                        .setProperty("Numero_Jugadores", simple("${body.getNumeroJugadores()}"))	
                        .setProperty("Fecha_Fundacion", simple("${body.getFechaFundacion()}"))	
                        .to("direct:postProducerRoute")
                .end();

                from("direct:putByIdTransformationRoute").routeId("mslegacyrouting_puttransformation")
                        .log(".:: Inicio del Servicio ::.")
                        .to("bean-validator://validateMessage")
                        .log("${body.toString()}")
                        .log("${body.getClass()}")
                        .bean("transformationComponent","validateCapacidad")
                        .setProperty("Nombre", simple("${body.getNombre()}"))
                        .setProperty("Ciudad", simple("${body.getCiudad()}"))
                        .setProperty("Propietario", simple("${body.getPropietario()}"))
                        .setProperty("Capacidad", simple("${body.getCapacidad()}"))
                        .setProperty("Division", simple("${body.getDivision()}"))
                        .setProperty("Competicion", simple("${body.getCompeticion()}"))	
                        .setProperty("Numero_Jugadores", simple("${body.getNumeroJugadores()}"))	
                        .setProperty("Fecha_Fundacion", simple("${body.getFechaFundacion()}"))	
                        .to("direct:putProducerRoute")
                .end();

        }
}