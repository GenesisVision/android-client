
# BrokerInvestmentProgram

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | [**UUID**](UUID.md) |  |  [optional]
**managerAccountId** | [**UUID**](UUID.md) |  |  [optional]
**dateFrom** | [**DateTime**](DateTime.md) |  |  [optional]
**dateTo** | [**DateTime**](DateTime.md) |  |  [optional]
**description** | **String** |  |  [optional]
**isEnabled** | **Boolean** |  |  [optional]
**period** | **Integer** |  |  [optional]
**feeSuccess** | **Double** |  |  [optional]
**feeManagement** | **Double** |  |  [optional]
**feeEntrance** | **Double** |  |  [optional]
**investMinAmount** | **Double** |  |  [optional]
**investMaxAmount** | **Double** |  |  [optional]
**lastPeriod** | [**Period**](Period.md) |  |  [optional]
**login** | **String** |  |  [optional]
**ipfsHash** | **String** |  |  [optional]
**tradeIpfsHash** | **String** |  |  [optional]
**balance** | **Double** |  |  [optional]
**status** | [**StatusEnum**](#StatusEnum) |  |  [optional]


<a name="StatusEnum"></a>
## Enum: StatusEnum
Name | Value
---- | -----
NONE | &quot;None&quot;
FORCLOSINGDUETOINACTIVITY | &quot;ForClosingDueToInactivity&quot;



