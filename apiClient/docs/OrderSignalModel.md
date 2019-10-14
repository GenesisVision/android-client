# OrderSignalModel

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**providers** | [**List&lt;OrderSignalProgramInfo&gt;**](OrderSignalProgramInfo.md) |  |  [optional]
**totalCommission** | **Double** |  |  [optional]
**totalCommissionByType** | [**List&lt;FeeDetails&gt;**](FeeDetails.md) |  |  [optional]
**tradingAccountId** | [**UUID**](UUID.md) |  |  [optional]
**currency** | [**CurrencyEnum**](#CurrencyEnum) |  |  [optional]
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
**baseVolume** | **Double** | Volume in account currency. Only filled when trade have zero commission (for paying fees with GVT) |  [optional]
**originalCommission** | **Double** | Huobi: sell - quote currency (right), buy - base symbol currency (left) |  [optional]
**originalCommissionCurrency** | **String** |  |  [optional]
**commission** | **Double** | In account currency |  [optional]
**swap** | **Double** |  |  [optional]
**showOriginalCommission** | **Boolean** |  |  [optional]
**signalData** | [**OrderModelSignalData**](OrderModelSignalData.md) |  |  [optional]

<a name="CurrencyEnum"></a>
## Enum: CurrencyEnum
Name | Value
---- | -----
UNDEFINED | &quot;Undefined&quot;
GVT | &quot;GVT&quot;
ETH | &quot;ETH&quot;
BTC | &quot;BTC&quot;
ADA | &quot;ADA&quot;
USDT | &quot;USDT&quot;
XRP | &quot;XRP&quot;
BCH | &quot;BCH&quot;
LTC | &quot;LTC&quot;
DOGE | &quot;DOGE&quot;
BNB | &quot;BNB&quot;
USD | &quot;USD&quot;
EUR | &quot;EUR&quot;

<a name="DirectionEnum"></a>
## Enum: DirectionEnum
Name | Value
---- | -----
BUY | &quot;Buy&quot;
SELL | &quot;Sell&quot;
BALANCE | &quot;Balance&quot;
CREDIT | &quot;Credit&quot;
UNDEFINED | &quot;Undefined&quot;
MANUALBALANCING | &quot;ManualBalancing&quot;

<a name="EntryEnum"></a>
## Enum: EntryEnum
Name | Value
---- | -----
IN | &quot;In&quot;
OUT | &quot;Out&quot;
INOUT | &quot;InOut&quot;
OUTBY | &quot;OutBy&quot;
