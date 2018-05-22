
# InvestmentProgramsFilter

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**managerId** | [**UUID**](UUID.md) |  |  [optional]
**brokerId** | [**UUID**](UUID.md) |  |  [optional]
**brokerTradeServerId** | [**UUID**](UUID.md) |  |  [optional]
**investMaxAmountFrom** | **Double** |  |  [optional]
**investMaxAmountTo** | **Double** |  |  [optional]
**sorting** | [**SortingEnum**](#SortingEnum) |  |  [optional]
**name** | **String** |  |  [optional]
**levelMin** | **Integer** |  |  [optional]
**levelMax** | **Integer** |  |  [optional]
**balanceUsdMin** | **Double** |  |  [optional]
**balanceUsdMax** | **Double** |  |  [optional]
**profitAvgMin** | **Double** |  |  [optional]
**profitAvgMax** | **Double** |  |  [optional]
**profitTotalMin** | **Double** |  |  [optional]
**profitTotalMax** | **Double** |  |  [optional]
**profitTotalPercentMin** | **Double** |  |  [optional]
**profitTotalPercentMax** | **Double** |  |  [optional]
**profitAvgPercentMin** | **Double** |  |  [optional]
**profitAvgPercentMax** | **Double** |  |  [optional]
**profitTotalChange** | [**ProfitTotalChangeEnum**](#ProfitTotalChangeEnum) |  |  [optional]
**periodMin** | **Integer** |  |  [optional]
**periodMax** | **Integer** |  |  [optional]
**showActivePrograms** | **Boolean** |  |  [optional]
**equityChartLength** | **Integer** |  |  [optional]
**showMyFavorites** | **Boolean** |  |  [optional]
**roundNumber** | **Integer** |  |  [optional]
**skip** | **Integer** |  |  [optional]
**take** | **Integer** |  |  [optional]


<a name="SortingEnum"></a>
## Enum: SortingEnum
Name | Value
---- | -----
BYLEVELASC | &quot;ByLevelAsc&quot;
BYLEVELDESC | &quot;ByLevelDesc&quot;
BYPROFITASC | &quot;ByProfitAsc&quot;
BYPROFITDESC | &quot;ByProfitDesc&quot;
BYORDERSASC | &quot;ByOrdersAsc&quot;
BYORDERSDESC | &quot;ByOrdersDesc&quot;
BYENDOFPERIODASC | &quot;ByEndOfPeriodAsc&quot;
BYENDOFPERIODDESC | &quot;ByEndOfPeriodDesc&quot;
BYTITLEASC | &quot;ByTitleAsc&quot;
BYTITLEDESC | &quot;ByTitleDesc&quot;
BYBALANCEASK | &quot;ByBalanceAsk&quot;
BYBALANCEDESC | &quot;ByBalanceDesc&quot;


<a name="ProfitTotalChangeEnum"></a>
## Enum: ProfitTotalChangeEnum
Name | Value
---- | -----
UNCHANGED | &quot;Unchanged&quot;
UP | &quot;Up&quot;
DOWN | &quot;Down&quot;



