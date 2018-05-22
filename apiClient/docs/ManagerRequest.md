
# ManagerRequest

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**userId** | [**UUID**](UUID.md) |  |  [optional]
**requestId** | [**UUID**](UUID.md) |  |  [optional]
**name** | **String** |  |  [optional]
**email** | **String** |  |  [optional]
**currency** | [**CurrencyEnum**](#CurrencyEnum) |  |  [optional]
**password** | **String** |  |  [optional]
**depositAmount** | **Double** |  |  [optional]
**leverage** | **Integer** |  |  [optional]
**programDateFrom** | [**DateTime**](DateTime.md) |  |  [optional]


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



