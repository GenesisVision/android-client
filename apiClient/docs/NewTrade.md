
# NewTrade

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**tradeAccountId** | [**UUID**](UUID.md) |  |  [optional]
**ticket** | **Long** |  |  [optional]
**direction** | [**DirectionEnum**](#DirectionEnum) |  |  [optional]
**entry** | [**EntryEnum**](#EntryEnum) |  |  [optional]
**symbol** | **String** |  |  [optional]
**price** | **Double** |  |  [optional]
**profit** | **Double** |  |  [optional]
**volume** | **Double** |  |  [optional]
**date** | [**DateTime**](DateTime.md) |  |  [optional]


<a name="DirectionEnum"></a>
## Enum: DirectionEnum
Name | Value
---- | -----
BUY | &quot;Buy&quot;
SELL | &quot;Sell&quot;


<a name="EntryEnum"></a>
## Enum: EntryEnum
Name | Value
---- | -----
IN | &quot;In&quot;
OUT | &quot;Out&quot;
INOUT | &quot;InOut&quot;
OUTBY | &quot;OutBy&quot;



