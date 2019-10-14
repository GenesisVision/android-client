# BrokerAccountTypeOld

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | [**UUID**](UUID.md) |  |  [optional]
**name** | **String** |  |  [optional]
**description** | **String** |  |  [optional]
**type** | [**TypeEnum**](#TypeEnum) |  |  [optional]
**leverages** | **List&lt;Integer&gt;** |  |  [optional]
**currencies** | **List&lt;String&gt;** |  |  [optional]
**minimumDepositsAmount** | **Map&lt;String, Double&gt;** |  |  [optional]
**isForex** | **Boolean** |  |  [optional]
**isSignalsAvailable** | **Boolean** |  |  [optional]

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
IDEX | &quot;IDEX&quot;
HUOBI | &quot;Huobi&quot;
EXANTE | &quot;Exante&quot;
BINANCEEXCHANGE | &quot;BinanceExchange&quot;
