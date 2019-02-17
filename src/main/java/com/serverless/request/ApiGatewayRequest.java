package com.serverless.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter @NoArgsConstructor
public class ApiGatewayRequest
{
    @JsonProperty("contentType")
    private String contentType;
    @JsonProperty("body")
    private String body;
    @JsonProperty("resource")
    private String resource;
    @JsonProperty("httpMethod")
    private String httpMethod;
    @JsonProperty("path")
    private String path;
    @JsonProperty("isBase64Encoded")
    private boolean isBase64Encoded;
    @JsonProperty("queryStringParameters")
    private Map<String,String> queryStringParameters;
    @JsonProperty("pathParameters")
    private Map<String,String> pathParameters;
    @JsonProperty("stageVariables")
    private Map<String,String> stageVariables;
    @JsonProperty("headers")
    private Map<String,String> headers;
    @JsonProperty("additionalProperties")
    private Map<String,String> additionalProperties=new HashMap<>();
}
