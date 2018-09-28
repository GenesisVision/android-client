
# FundDetailsFull

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**entryFee** | **Double** |  |  [optional]
**exitFee** | **Double** |  |  [optional]
**managementFee** | **Double** |  |  [optional]
**currentAssets** | [**List&lt;FundAssetPartWithIcon&gt;**](FundAssetPartWithIcon.md) |  |  [optional]
**statistic** | [**FundStatistic**](FundStatistic.md) |  |  [optional]
**personalFundDetails** | [**PersonalFundDetailsFull**](PersonalFundDetailsFull.md) | Fields for authorized user |  [optional]
**id** | [**UUID**](UUID.md) |  |  [optional]
**logo** | **String** |  |  [optional]
**description** | **String** |  |  [optional]
**title** | **String** |  |  [optional]
**status** | [**StatusEnum**](#StatusEnum) |  |  [optional]
**manager** | [**ProfilePublic**](ProfilePublic.md) |  |  [optional]


<a name="StatusEnum"></a>
## Enum: StatusEnum
Name | Value
---- | -----
NONE | &quot;None&quot;
PENDING | &quot;Pending&quot;
ERRORCREATING | &quot;ErrorCreating&quot;
ACTIVE | &quot;Active&quot;
CLOSED | &quot;Closed&quot;
ARCHIVED | &quot;Archived&quot;
CLOSEDDUETOINACTIVITY | &quot;ClosedDueToInactivity&quot;



