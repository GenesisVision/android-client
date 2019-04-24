
# InternalTransferRequest

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**sourceId** | [**UUID**](UUID.md) |  |  [optional]
**sourceType** | [**SourceTypeEnum**](#SourceTypeEnum) |  |  [optional]
**destinationId** | [**UUID**](UUID.md) |  |  [optional]
**destinationType** | [**DestinationTypeEnum**](#DestinationTypeEnum) |  |  [optional]
**amount** | **Double** |  |  [optional]
**transferAll** | **Boolean** |  |  [optional]
**walletToWallet** | **Boolean** |  |  [optional]
**walletToCopyTradingAccount** | **Boolean** |  |  [optional]
**copyTradingAccountToWallet** | **Boolean** |  |  [optional]


<a name="SourceTypeEnum"></a>
## Enum: SourceTypeEnum
Name | Value
---- | -----
UNDEFINED | &quot;Undefined&quot;
WALLET | &quot;Wallet&quot;
COPYTRADINGACCOUNT | &quot;CopyTradingAccount&quot;


<a name="DestinationTypeEnum"></a>
## Enum: DestinationTypeEnum
Name | Value
---- | -----
UNDEFINED | &quot;Undefined&quot;
WALLET | &quot;Wallet&quot;
COPYTRADINGACCOUNT | &quot;CopyTradingAccount&quot;



