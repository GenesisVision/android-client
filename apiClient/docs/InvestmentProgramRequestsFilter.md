
# InvestmentProgramRequestsFilter

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**investmentProgramId** | [**UUID**](UUID.md) |  | 
**dateFrom** | [**DateTime**](DateTime.md) |  |  [optional]
**dateTo** | [**DateTime**](DateTime.md) |  |  [optional]
**status** | [**StatusEnum**](#StatusEnum) |  |  [optional]
**type** | [**TypeEnum**](#TypeEnum) |  |  [optional]
**skip** | **Integer** |  |  [optional]
**take** | **Integer** |  |  [optional]


<a name="StatusEnum"></a>
## Enum: StatusEnum
Name | Value
---- | -----
NEW | &quot;New&quot;
EXECUTED | &quot;Executed&quot;
CANCELLED | &quot;Cancelled&quot;


<a name="TypeEnum"></a>
## Enum: TypeEnum
Name | Value
---- | -----
INVEST | &quot;Invest&quot;
WITHDRAWAL | &quot;Withdrawal&quot;



