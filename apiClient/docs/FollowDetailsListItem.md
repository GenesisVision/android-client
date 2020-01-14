# FollowDetailsListItem

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | [**UUID**](UUID.md) |  |  [optional]
**title** | **String** |  |  [optional]
**description** | **String** |  |  [optional]
**logo** | **String** |  |  [optional]
**creationDate** | [**DateTime**](DateTime.md) |  |  [optional]
**currency** | [**CurrencyEnum**](#CurrencyEnum) |  |  [optional]
**subscribersCount** | **Integer** |  |  [optional]
**tradesCount** | **Integer** |  |  [optional]
**status** | **String** |  |  [optional]
**url** | **String** |  |  [optional]
**color** | **String** |  |  [optional]
**isExternal** | **Boolean** |  |  [optional]
**leverageMin** | **Integer** |  |  [optional]
**leverageMax** | **Integer** |  |  [optional]
**brokerId** | [**UUID**](UUID.md) |  |  [optional]
**brokerType** | [**BrokerTradeServerType**](BrokerTradeServerType.md) |  |  [optional]
**owner** | [**ProfilePublicShort**](ProfilePublicShort.md) |  |  [optional]
**statistic** | [**ProfitChart**](ProfitChart.md) |  |  [optional]
**personalDetails** | [**PersonalFollowDetailsList**](PersonalFollowDetailsList.md) |  |  [optional]
**tags** | [**List&lt;Tag&gt;**](Tag.md) |  |  [optional]

<a name="CurrencyEnum"></a>
## Enum: CurrencyEnum
Name | Value
---- | -----
USD | &quot;USD&quot;
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
EUR | &quot;EUR&quot;
