# TournamentApi

All URIs are relative to *https://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**apiTournamentParticipantGet**](TournamentApi.md#apiTournamentParticipantGet) | **GET** api/tournament/participant | Participant info
[**apiTournamentParticipantTradesPost**](TournamentApi.md#apiTournamentParticipantTradesPost) | **POST** api/tournament/participant/trades | Participant trades history
[**apiTournamentParticipantsCountGet**](TournamentApi.md#apiTournamentParticipantsCountGet) | **GET** api/tournament/participants/count | Participants summary
[**apiTournamentParticipantsPost**](TournamentApi.md#apiTournamentParticipantsPost) | **POST** api/tournament/participants | Participants list
[**apiTournamentRegisterPost**](TournamentApi.md#apiTournamentRegisterPost) | **POST** api/tournament/register | Registration for the tournament


<a name="apiTournamentParticipantGet"></a>
# **apiTournamentParticipantGet**
> ParticipantViewModel apiTournamentParticipantGet(participantId)

Participant info

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.TournamentApi;


TournamentApi apiInstance = new TournamentApi();
UUID participantId = new UUID(); // UUID | 
try {
    ParticipantViewModel result = apiInstance.apiTournamentParticipantGet(participantId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TournamentApi#apiTournamentParticipantGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **participantId** | [**UUID**](.md)|  |

### Return type

[**ParticipantViewModel**](ParticipantViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="apiTournamentParticipantTradesPost"></a>
# **apiTournamentParticipantTradesPost**
> TradesViewModel apiTournamentParticipantTradesPost(filter)

Participant trades history

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.TournamentApi;


TournamentApi apiInstance = new TournamentApi();
TradesFilter filter = new TradesFilter(); // TradesFilter | 
try {
    TradesViewModel result = apiInstance.apiTournamentParticipantTradesPost(filter);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TournamentApi#apiTournamentParticipantTradesPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **filter** | [**TradesFilter**](TradesFilter.md)|  | [optional]

### Return type

[**TradesViewModel**](TradesViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="apiTournamentParticipantsCountGet"></a>
# **apiTournamentParticipantsCountGet**
> ParticipantsSummaryViewModel apiTournamentParticipantsCountGet()

Participants summary

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.TournamentApi;


TournamentApi apiInstance = new TournamentApi();
try {
    ParticipantsSummaryViewModel result = apiInstance.apiTournamentParticipantsCountGet();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TournamentApi#apiTournamentParticipantsCountGet");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**ParticipantsSummaryViewModel**](ParticipantsSummaryViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: text/plain, application/json, text/json

<a name="apiTournamentParticipantsPost"></a>
# **apiTournamentParticipantsPost**
> ParticipantsViewModel apiTournamentParticipantsPost(filter)

Participants list

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.TournamentApi;


TournamentApi apiInstance = new TournamentApi();
ParticipantsFilter filter = new ParticipantsFilter(); // ParticipantsFilter | 
try {
    ParticipantsViewModel result = apiInstance.apiTournamentParticipantsPost(filter);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TournamentApi#apiTournamentParticipantsPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **filter** | [**ParticipantsFilter**](ParticipantsFilter.md)|  | [optional]

### Return type

[**ParticipantsViewModel**](ParticipantsViewModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

<a name="apiTournamentRegisterPost"></a>
# **apiTournamentRegisterPost**
> Void apiTournamentRegisterPost(model)

Registration for the tournament

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.TournamentApi;


TournamentApi apiInstance = new TournamentApi();
NewParticipant model = new NewParticipant(); // NewParticipant | 
try {
    Void result = apiInstance.apiTournamentRegisterPost(model);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TournamentApi#apiTournamentRegisterPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **model** | [**NewParticipant**](NewParticipant.md)|  | [optional]

### Return type

[**Void**](.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json-patch+json, application/json, text/json, application/_*+json
 - **Accept**: text/plain, application/json, text/json

