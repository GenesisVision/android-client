
# BrokerTradeServer

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | [**UUID**](UUID.md) |  |  [optional]
**brokerId** | [**UUID**](UUID.md) |  |  [optional]
**name** | **String** |  |  [optional]
**host** | **String** |  |  [optional]
**type** | [**TypeEnum**](#TypeEnum) |  |  [optional]
**broker** | [**Broker**](Broker.md) |  |  [optional]
**registrationDate** | [**OffsetDateTime**](OffsetDateTime.md) |  |  [optional]


<a name="TypeEnum"></a>
## Enum: TypeEnum
Name | Value
---- | -----
UNDEFINED | &quot;Undefined&quot;
METATRADER4 | &quot;MetaTrader4&quot;
METATRADER5 | &quot;MetaTrader5&quot;
NINJATRADER | &quot;NinjaTrader&quot;
CTRADER | &quot;cTrader&quot;
RUMUS | &quot;Rumus&quot;
METASTOCK | &quot;Metastock&quot;



