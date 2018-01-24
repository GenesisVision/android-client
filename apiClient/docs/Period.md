
# Period

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | [**UUID**](UUID.md) |  |  [optional]
**number** | **Integer** |  |  [optional]
**dateFrom** | [**OffsetDateTime**](OffsetDateTime.md) |  |  [optional]
**dateTo** | [**OffsetDateTime**](OffsetDateTime.md) |  |  [optional]
**status** | [**StatusEnum**](#StatusEnum) |  |  [optional]
**startBalance** | **Double** |  |  [optional]
**investmentRequest** | [**List&lt;InvestmentRequest&gt;**](InvestmentRequest.md) |  |  [optional]


<a name="StatusEnum"></a>
## Enum: StatusEnum
Name | Value
---- | -----
PLANNED | &quot;Planned&quot;
INPROCCESS | &quot;InProccess&quot;
CLOSED | &quot;Closed&quot;



