package com.serverless.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.serverless.models.Detail;
import com.serverless.models.Headers;

import java.io.Serializable;
import java.util.List;

public class CreateShipmentRequest implements Serializable
{
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("header")
    private Headers header;
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("details")
    private List<Detail> details;
}
