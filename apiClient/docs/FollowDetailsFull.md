# FollowDetailsFull

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
**signalSettings** | [**AssetSignalSettings**](AssetSignalSettings.md) |  |  [optional]
**brokerDetails** | [**BrokerDetails**](BrokerDetails.md) |  |  [optional]
**owner** | [**ProfilePublic**](ProfilePublic.md) |  |  [optional]
**personalDetails** | [**PersonalFollowDetailsFull**](PersonalFollowDetailsFull.md) |  |  [optional]
**tags** | [**List&lt;ProgramTag&gt;**](ProgramTag.md) |  |  [optional]

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
