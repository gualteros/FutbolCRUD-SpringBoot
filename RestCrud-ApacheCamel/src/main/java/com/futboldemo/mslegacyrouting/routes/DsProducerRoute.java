
package com.futboldemo.mslegacyrouting.routes;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.futboldemo.mslegacyrouting.configurator.ConfigurationRoute;
import com.futboldemo.mslegacyrouting.exceptions.CustomValidationException;
import com.futboldemo.mslegacyrouting.properties.DatasourceProducer;
import com.futboldemo.mslegacyrouting.util.ErrorsEnum;

import net.minidev.json.JSONObject;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.component.bean.validator.BeanValidationException;
import org.apache.camel.http.common.HttpOperationFailedException;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.http.conn.HttpHostConnectException;
import org.springframework.stereotype.Component;
import org.springframework.http.MediaType;

import javax.annotation.Resource;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.time.format.DateTimeParseException;

@Component
public class DsProducerRoute extends ConfigurationRoute {

	@Resource
	private DatasourceProducer ds;

	@Override
	public void configure() throws Exception {
		super.configure();

		onException(DateTimeParseException.class)
				.handled(true)
				.log(LoggingLevel.ERROR, ErrorsEnum.ERROR_VALIDACION.getMessage())
				.log(LoggingLevel.ERROR, ErrorsEnum.ERROR_EXCEPTION.getMessage())
				.setBody(simple(ErrorsEnum.ERROR_EXCEPTION.getMessage()))
				.setHeader(ErrorsEnum.ERROR_HEADER.getMessage(), simple(ErrorsEnum.ERROR_EXCEPTION.getMessage()))
				.bean(ErrorsEnum.BEAN_TRANSFORMATION.getMessage(), ErrorsEnum.BEAN_CREATE_RSERROR.getMessage())
				.end();

		onException(HttpOperationFailedException.class)
				.handled(true)
				.log(LoggingLevel.WARN, ErrorsEnum.ERROR_EXCEPTIONCLASS.getMessage())
				.log(LoggingLevel.WARN, ErrorsEnum.ERROR_EXCEPTIONCLASS_NAME.getMessage())
				.setBody(simple("${exception.responseBody}"))
				.log(LoggingLevel.WARN, ErrorsEnum.BODY.getMessage())
				.convertBodyTo(String.class)
				.end();

		onException(HttpHostConnectException.class, SocketTimeoutException.class)
				.handled(true)
				.maximumRedeliveries(4)
				.redeliveryDelay(2000)
				.logRetryAttempted(true)
				.retryAttemptedLogLevel(LoggingLevel.WARN)
				.log(LoggingLevel.ERROR,
						"Durante la comunicación por protocolo HTTP al host destino se presentan errores en la ruta ${routeId}")
				.log(LoggingLevel.ERROR, ErrorsEnum.ERROR_EXCEPTIONCLASS.getMessage())
				.log(LoggingLevel.ERROR, ErrorsEnum.ERROR_EXCEPTIONCLASS_NAME.getMessage())
				.log(LoggingLevel.ERROR, ErrorsEnum.ERROR_STACKTRACE.getMessage())
				.setHeader(Exchange.CONTENT_TYPE, simple(MediaType.APPLICATION_JSON.toString()))
				.setHeader(Exchange.HTTP_RESPONSE_CODE, simple("500"))
				.setHeader(ErrorsEnum.ERROR_HEADER.getMessage(), simple(ErrorsEnum.ERROR_EXCEPTION.getMessage()))
				.bean(ErrorsEnum.BEAN_TRANSFORMATION.getMessage(), ErrorsEnum.BEAN_CREATE_RSERROR.getMessage())
				.wireTap(ErrorsEnum.DEFAULT_LOG_ERROR_ROUTE.getMessage())
				.end();

		onException(BeanValidationException.class).handled(true)
				.log(LoggingLevel.ERROR, ErrorsEnum.ERROR_VALIDACION.getMessage())
				.log(LoggingLevel.ERROR, ErrorsEnum.ERROR_EXCEPTION.getMessage())
				.log(LoggingLevel.ERROR, ErrorsEnum.ERROR_EXCEPTIONCLASS.getMessage())
				.log(LoggingLevel.ERROR, ErrorsEnum.ERROR_EXCEPTIONCLASS_NAME.getMessage())
				.log(LoggingLevel.ERROR, ErrorsEnum.ERROR_STACKTRACE.getMessage())
				.setHeader(ErrorsEnum.ERROR_HEADER.getMessage(), simple(ErrorsEnum.ERROR_EXCEPTION.getMessage()))
				.setHeader(Exchange.HTTP_RESPONSE_CODE, simple("500", Integer.class))
				.bean(ErrorsEnum.BEAN_TRANSFORMATION.getMessage(), ErrorsEnum.BEAN_CREATE_RSERROR.getMessage())
				.wireTap(ErrorsEnum.DEFAULT_LOG_ERROR_ROUTE.getMessage())
				.end();

		onException(InvalidFormatException.class, NullPointerException.class)
				.handled(true)
				.log(LoggingLevel.ERROR, ErrorsEnum.ERROR_VALIDACION.getMessage())
				.log(LoggingLevel.ERROR, ErrorsEnum.ERROR_EXCEPTION.getMessage())
				.log(LoggingLevel.ERROR, ErrorsEnum.ERROR_STACKTRACE.getMessage())
				.setBody(simple(ErrorsEnum.ERROR_EXCEPTION.getMessage()))
				.setHeader("mensajePer").simple(ErrorsEnum.BODY.getMessage())
				.setHeader(ErrorsEnum.ERROR_HEADER.getMessage(), simple(ErrorsEnum.ERROR_EXCEPTION.getMessage()))
				.setHeader(Exchange.HTTP_RESPONSE_CODE, simple("500", Integer.class))
				.bean(ErrorsEnum.BEAN_TRANSFORMATION.getMessage(), ErrorsEnum.BEAN_CREATE_RSERROR.getMessage())
				.wireTap(ErrorsEnum.DEFAULT_LOG_ERROR_ROUTE.getMessage())
				.end();

		onException(UnknownHostException.class)
			.handled(true)
			.maximumRedeliveries(4)
			.redeliveryDelay(2000)
			.logRetryAttempted(true)
			.retryAttemptedLogLevel(LoggingLevel.WARN)
			.log(LoggingLevel.ERROR,
					"Durante la comunicación por protocolo HTTP al host destino se presentan errores en la ruta ${routeId}")
			.log(LoggingLevel.ERROR, ErrorsEnum.ERROR_EXCEPTIONCLASS.getMessage())
			.log(LoggingLevel.ERROR, ErrorsEnum.ERROR_EXCEPTIONCLASS_NAME.getMessage())
			.log(LoggingLevel.ERROR, ErrorsEnum.ERROR_STACKTRACE.getMessage())
			.setBody(simple(ErrorsEnum.ERROR_EXCEPTION.getMessage()))
			.setHeader("mensajePer").simple(ErrorsEnum.BODY.getMessage())
			.setHeader(ErrorsEnum.ERROR_HEADER.getMessage(), simple(ErrorsEnum.ERROR_EXCEPTION.getMessage()))
			.setHeader(Exchange.HTTP_RESPONSE_CODE, simple("500", Integer.class))
			.bean(ErrorsEnum.BEAN_TRANSFORMATION.getMessage(), ErrorsEnum.BEAN_CREATE_RSERROR.getMessage())
			.wireTap(ErrorsEnum.DEFAULT_LOG_ERROR_ROUTE.getMessage())
		.end();

		onException(java.lang.Exception.class).handled(true)
			.log(LoggingLevel.ERROR, ErrorsEnum.ERROR_VALIDACION.getMessage())
			.log(LoggingLevel.ERROR, ErrorsEnum.ERROR_EXCEPTION.getMessage())
			.log(LoggingLevel.ERROR, ErrorsEnum.ERROR_STACKTRACE.getMessage())
			.setHeader(ErrorsEnum.ERROR_HEADER.getMessage(), simple(ErrorsEnum.ERROR_EXCEPTION.getMessage()))
			.setHeader(Exchange.HTTP_RESPONSE_CODE, simple("500", Integer.class))
			.setHeader(ErrorsEnum.ERROR_HEADER.getMessage(), simple(ErrorsEnum.ERROR_EXCEPTION.getMessage()))
			.bean(ErrorsEnum.BEAN_TRANSFORMATION.getMessage(), ErrorsEnum.BEAN_CREATE_RSERROR.getMessage())
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

		from("direct:getProducerRoute").routeId("rest_producer_get")
			.log(".:: Producer (Demo Futbol)::.")
			.to("sql:"+ ds.getQuerylist() + "?dataSource=#dataSource")
			.log(LoggingLevel.INFO, "::: Resultado de la consulta: ${body}")
			.to(ErrorsEnum.LOGRESPONSE.getMessage())
		.end();

		from("direct:getByIdProducerRoute").routeId("rest_producer_getbyid")
			.log(".:: Producer (Demo Futbol)::.")
			.log("${headers.id}")
			.to("sql:"+ ds.getQueryById() + "?dataSource=#dataSource")
			.log(LoggingLevel.INFO, "::: Resultado de la consulta: ${body.size()}")
			.choice().when(simple("${body.size()} == 0"))
				.throwException(new CustomValidationException("No se encontró el registro solicitado"))
			.to(ErrorsEnum.LOGRESPONSE.getMessage())
		.end();

		from("direct:postProducerRoute").routeId("rest_producer_Post")
			.log(".:: Producer (Demo Futbol)::.")
			.to("sql:"+ ds.getQueryPost() + "?dataSource=#dataSource")
			.log(LoggingLevel.INFO, "::: Resultado de la consulta: ${body}")
			.to(ErrorsEnum.LOGRESPONSE.getMessage())
		.end();

		from("direct:deleteByIdProducerRoute").routeId("rest_producer_Delete")
			.log(".:: Producer (Demo Futbol)::.")
			.to("sql:"+ ds.getQueryDelete() + "?dataSource=#dataSource")
			.log(LoggingLevel.INFO, "::: Resultado de la consulta: ${body}")
			.to(ErrorsEnum.LOGRESPONSE.getMessage())
		.end();

		from("direct:putProducerRoute").routeId("rest_producer_Put")
			.log(".:: Producer (Demo Futbol)::.")
			.to("sql:"+ ds.getQueryPut() + "?dataSource=#dataSource")
			.log(LoggingLevel.INFO, "::: Resultado de la consulta: ${body}")
			.to(ErrorsEnum.LOGRESPONSE.getMessage())
		.end();

		from("direct:getOrderedProducerRoute").routeId("rest_producer_Ordered")
			.log(".:: Producer (Demo Futbol)::.")
			.to("sql:"+ ds.getQueryOrdered() + "?dataSource=#dataSource")
			.log(LoggingLevel.INFO, "::: Resultado de la consulta: ${body}")
			.to(ErrorsEnum.LOGRESPONSE.getMessage())
		.end();

	}
}