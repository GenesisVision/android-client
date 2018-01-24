
# InvestmentRequest

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | [**UUID**](UUID.md) |  |  [optional]
**date** | [**OffsetDateTime**](OffsetDateTime.md) |  |  [optional]
**amount** | **Double** |  |  [optional]
**type** | [**TypeEnum**](#TypeEnum) |  |  [optional]
**status** | [**StatusEnum**](#StatusEnum) |  |  [optional]


<a name="TypeEnum"></a>
## Enum: TypeEnum
Name | Value
---- | -----
INVEST | &quot;Invest&quot;
WITHDRAWAL | &quot;Withdrawal&quot;


<a name="StatusEnum"></a>
## Enum: StatusEnum
Name | Value
---- | -----
NEW | &quot;New&quot;
EXECUTED | &quot;Executed&quot;



