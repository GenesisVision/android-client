
# DashboardProgramDetails

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | [**UUID**](UUID.md) |  |  [optional]
**avatar** | **String** |  |  [optional]
**level** | **Integer** |  |  [optional]
**title** | **String** |  |  [optional]
**description** | **String** |  |  [optional]
**currency** | [**CurrencyEnum**](#CurrencyEnum) |  |  [optional]
**manager** | [**ManagerProfile**](ManagerProfile.md) |  |  [optional]
**share** | **Double** |  |  [optional]
**timeLeft** | **Integer** |  |  [optional]
**value** | **Double** |  |  [optional]
**profit** | **Double** |  |  [optional]
**status** | [**StatusEnum**](#StatusEnum) |  |  [optional]
**chart** | [**List&lt;ChartSimple&gt;**](ChartSimple.md) |  |  [optional]


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



