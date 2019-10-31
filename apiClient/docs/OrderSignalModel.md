# OrderSignalModel

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**providers** | [**List&lt;OrderSignalProgramInfo&gt;**](OrderSignalProgramInfo.md) |  |  [optional]
**totalCommission** | **Double** |  |  [optional]
**totalCommissionByType** | [**List&lt;FeeDetails&gt;**](FeeDetails.md) |  |  [optional]
**tradingAccountId** | [**UUID**](UUID.md) |  |  [optional]
**currency** | [**Currency**](Currency.md) |  |  [optional]
**id** | [**UUID**](UUID.md) |  |  [optional]
**login** | **String** |  |  [optional]
**ticket** | **String** |  |  [optional]
**symbol** | **String** |  |  [optional]
**volume** | **Double** |  |  [optional]
**profit** | **Double** |  |  [optional]
**direction** | [**TradeDirectionType**](TradeDirectionType.md) |  |  [optional]
**date** | [**DateTime**](DateTime.md) |  |  [optional]
**price** | **Double** |  |  [optional]
**priceCurrent** | **Double** |  |  [optional]
**entry** | [**TradeEntryType**](TradeEntryType.md) |  |  [optional]
**baseVolume** | **Double** | Volume in account currency. Only filled when trade have zero commission (for paying fees with GVT) |  [optional]
**originalCommission** | **Double** | Huobi: sell - quote currency (right), buy - base symbol currency (left) |  [optional]
**originalCommissionCurrency** | **String** |  |  [optional]
**commission** | **Double** | In account currency |  [optional]
**swap** | **Double** |  |  [optional]
**showOriginalCommission** | **Boolean** |  |  [optional]
**signalData** | [**OrderModelSignalData**](OrderModelSignalData.md) |  |  [optional]
