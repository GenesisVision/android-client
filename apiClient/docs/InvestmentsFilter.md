
# InvestmentsFilter

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**managerId** | [**UUID**](UUID.md) |  |  [optional]
**brokerId** | [**UUID**](UUID.md) |  |  [optional]
**brokerTradeServerId** | [**UUID**](UUID.md) |  |  [optional]
**investMaxAmountFrom** | **Double** |  |  [optional]
**investMaxAmountTo** | **Double** |  |  [optional]
**sorting** | [**SortingEnum**](#SortingEnum) |  |  [optional]
**skip** | **Integer** |  |  [optional]
**take** | **Integer** |  |  [optional]


<a name="SortingEnum"></a>
## Enum: SortingEnum
Name | Value
---- | -----
BYRATINGASC | &quot;ByRatingAsc&quot;
BYRATINGDESC | &quot;ByRatingDesc&quot;
BYPROFITASC | &quot;ByProfitAsc&quot;
BYPROFITDESC | &quot;ByProfitDesc&quot;
BYORDERSASC | &quot;ByOrdersAsc&quot;
BYORDERSDESC | &quot;ByOrdersDesc&quot;



