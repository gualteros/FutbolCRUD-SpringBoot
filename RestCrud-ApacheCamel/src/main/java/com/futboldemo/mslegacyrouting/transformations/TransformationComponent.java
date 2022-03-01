
package com.futboldemo.mslegacyrouting.transformations;

import com.futboldemo.mslegacyrouting.exceptions.CustomValidationException;
import com.futboldemo.mslegacyrouting.model.Error;
import com.futboldemo.mslegacyrouting.model.ErrorEstructura;
import com.futboldemo.mslegacyrouting.model.Request;
import com.google.gson.Gson;
import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component("transformationComponent")
public class TransformationComponent  {

    private static final Logger logger = LoggerFactory.getLogger("ms-rest-futboldemo-OC");

    public void createRsError(Exchange exchange) {
        Error errormsg = new Error();
        errormsg.setCode(exchange.getIn().getHeader("CamelHttpResponseCode").toString());
        errormsg.setLogID("-");
        errormsg.setMessage(exchange.getIn().getHeader("error").toString());

        ErrorEstructura error = new ErrorEstructura();
        error.setError(errormsg);

        exchange.getOut().setBody(error);

        logger.info(".:: Error controlado ::.");
    };

    public void validateCapacidad(Exchange exchange) throws CustomValidationException {
        Request body = exchange.getIn().getBody(Request.class);
        switch(body.getDivision()) {
            case 1:
                if (body.getCapacidad() <= 50000){throw new CustomValidationException("La capacidad del estadio no corresponde a la división del equipo");};
                break;
            case 2:
                if (body.getCapacidad() <= 10000){throw new CustomValidationException("La capacidad del estadio no corresponde a la división del equipo");};
                break;
            case 3:
                if (body.getCapacidad() <= 3000){throw new CustomValidationException("La capacidad del estadio no corresponde a la división del equipo");};
                break;                
            default:
                break;
        }

    };

}
