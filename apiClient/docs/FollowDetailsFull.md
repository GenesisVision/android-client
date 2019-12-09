# FollowDetailsFull

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | [**UUID**](UUID.md) |  |  [optional]
**tradingAccountId** | [**UUID**](UUID.md) |  |  [optional]
**title** | **String** |  |  [optional]
**description** | **String** |  |  [optional]
**logo** | **String** |  |  [optional]
**creationDate** | [**DateTime**](DateTime.md) |  |  [optional]
**currency** | [**CurrencyEnum**](#CurrencyEnum) |  |  [optional]
**tradesCount** | **Integer** |  |  [optional]
**status** | **String** |  |  [optional]
**url** | **String** |  |  [optional]
**color** | **String** |  |  [optional]
**leverage** | **Integer** |  |  [optional]
**signalSettings** | [**AssetSignalSettings**](AssetSignalSettings.md) |  |  [optional]
**subscribersCount** | **Integer** |  |  [optional]
**brokerDetails** | [**BrokerDetails**](BrokerDetails.md) |  |  [optional]
**owner** | [**ProfilePublic**](ProfilePublic.md) |  |  [optional]
**personalDetails** | [**PersonalFollowDetailsFull**](PersonalFollowDetailsFull.md) |  |  [optional]
**tags** | [**List&lt;Tag&gt;**](Tag.md) |  |  [optional]

<a name="CurrencyEnum"></a>
## Enum: CurrencyEnum
Name | Value
---- | -----
USD | &quot;USD&quot;
BTC | &quot;BTC&quot;
ETH | &quot;ETH&quot;
USDT | &quot;USDT&quot;
GVT | &quot;GVT&quot;
UNDEFINED | &quot;Undefined&quot;
ADA | &quot;ADA&quot;
XRP | &quot;XRP&quot;
BCH | &quot;BCH&quot;
LTC | &quot;LTC&quot;
DOGE | &quot;DOGE&quot;
BNB | &quot;BNB&quot;
EUR | &quot;EUR&quot;
