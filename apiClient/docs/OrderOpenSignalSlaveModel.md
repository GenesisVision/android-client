
# OrderOpenSignalSlaveModel

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**manager** | [**ProfilePublic**](ProfilePublic.md) |  |  [optional]
**program** | [**OrderProgramData**](OrderProgramData.md) |  |  [optional]
**programId** | [**UUID**](UUID.md) |  |  [optional]
**id** | [**UUID**](UUID.md) |  |  [optional]
**login** | **String** |  |  [optional]
**ticket** | **String** |  |  [optional]
**symbol** | **String** |  |  [optional]
**volume** | **Double** |  |  [optional]
**profit** | **Double** |  |  [optional]
**direction** | [**DirectionEnum**](#DirectionEnum) |  |  [optional]
**date** | [**DateTime**](DateTime.md) |  |  [optional]
**price** | **Double** |  |  [optional]
**priceCurrent** | **Double** |  |  [optional]
**entry** | [**EntryEnum**](#EntryEnum) |  |  [optional]


<a name="DirectionEnum"></a>
## Enum: DirectionEnum
Name | Value
---- | -----
BUY | &quot;Buy&quot;
SELL | &quot;Sell&quot;
BALANCE | &quot;Balance&quot;
CREDIT | &quot;Credit&quot;
UNDEFINED | &quot;Undefined&quot;


<a name="EntryEnum"></a>
## Enum: EntryEnum
Name | Value
---- | -----
IN | &quot;In&quot;
OUT | &quot;Out&quot;
INOUT | &quot;InOut&quot;
OUTBY | &quot;OutBy&quot;



