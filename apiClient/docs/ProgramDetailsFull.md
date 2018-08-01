
# ProgramDetailsFull

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | [**UUID**](UUID.md) |  |  [optional]
**avatar** | **String** |  |  [optional]
**description** | **String** |  |  [optional]
**title** | **String** |  |  [optional]
**currency** | [**CurrencyEnum**](#CurrencyEnum) |  |  [optional]
**level** | **Integer** |  |  [optional]
**periodDuration** | **Integer** |  |  [optional]
**periodDateStart** | [**DateTime**](DateTime.md) |  |  [optional]
**periodDateEnd** | [**DateTime**](DateTime.md) |  |  [optional]
**successFee** | **Double** |  |  [optional]
**availableForInvestment** | **Double** |  |  [optional]
**manager** | [**ProfilePublicViewModel**](ProfilePublicViewModel.md) |  |  [optional]
**statistic** | [**ProgramStatistic**](ProgramStatistic.md) |  |  [optional]
**personalProgramDetails** | [**PersonalProgramDetailsFull**](PersonalProgramDetailsFull.md) | Fields for authorized user |  [optional]


<a name="CurrencyEnum"></a>
## Enum: CurrencyEnum
Name | Value
---- | -----
UNDEFINED | &quot;Undefined&quot;
GVT | &quot;GVT&quot;
ETH | &quot;ETH&quot;
BTC | &quot;BTC&quot;
ADA | &quot;ADA&quot;
USD | &quot;USD&quot;
EUR | &quot;EUR&quot;



