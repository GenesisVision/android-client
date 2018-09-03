
# DashboardProgramDetails

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | [**UUID**](UUID.md) |  |  [optional]
**logo** | **String** |  |  [optional]
**level** | **Integer** |  |  [optional]
**title** | **String** |  |  [optional]
**description** | **String** |  |  [optional]
**currency** | [**CurrencyEnum**](#CurrencyEnum) |  |  [optional]
**manager** | [**ProfilePublic**](ProfilePublic.md) |  |  [optional]
**share** | **Double** |  |  [optional]
**currentValue** | **Double** |  |  [optional]
**profitPercent** | **Double** |  |  [optional]
**profitValue** | **Double** |  |  [optional]
**status** | [**StatusEnum**](#StatusEnum) |  |  [optional]
**chart** | [**List&lt;ChartSimple&gt;**](ChartSimple.md) |  |  [optional]
**periodStarts** | [**DateTime**](DateTime.md) |  |  [optional]
**periodEnds** | [**DateTime**](DateTime.md) |  |  [optional]
**isReinvesting** | **Boolean** |  |  [optional]
**profit** | **Double** |  |  [optional]
**timeLeft** | **Double** |  |  [optional]
**value** | **Double** |  |  [optional]


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


<a name="StatusEnum"></a>
## Enum: StatusEnum
Name | Value
---- | -----
UNDEFINED | &quot;Undefined&quot;
PENDING | &quot;Pending&quot;
ACTIVE | &quot;Active&quot;
WITHDRAWING | &quot;Withdrawing&quot;
ENDED | &quot;Ended&quot;



