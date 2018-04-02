
# InvestmentProgramRequest

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | [**UUID**](UUID.md) |  |  [optional]
**date** | [**DateTime**](DateTime.md) |  |  [optional]
**amount** | **Double** |  |  [optional]
**type** | [**TypeEnum**](#TypeEnum) |  |  [optional]
**status** | [**StatusEnum**](#StatusEnum) |  |  [optional]
**canCancelRequest** | **Boolean** |  |  [optional]


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
CANCELLED | &quot;Cancelled&quot;



