
# NotificationViewModel

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | [**UUID**](UUID.md) |  |  [optional]
**text** | **String** |  |  [optional]
**date** | [**DateTime**](DateTime.md) |  |  [optional]
**type** | [**TypeEnum**](#TypeEnum) |  |  [optional]
**assetId** | [**UUID**](UUID.md) |  |  [optional]
**managerId** | [**UUID**](UUID.md) |  |  [optional]
**logo** | **String** |  |  [optional]
**url** | **String** |  |  [optional]
**color** | **String** |  |  [optional]
**isUnread** | **Boolean** |  |  [optional]
**assetType** | [**AssetTypeEnum**](#AssetTypeEnum) |  |  [optional]


<a name="TypeEnum"></a>
## Enum: TypeEnum
Name | Value
---- | -----
PLATFORMNEWSANDUPDATES | &quot;PlatformNewsAndUpdates&quot;
PLATFORMEMERGENCY | &quot;PlatformEmergency&quot;
PLATFORMOTHER | &quot;PlatformOther&quot;
PROFILEUPDATED | &quot;ProfileUpdated&quot;
PROFILEPWDUPDATED | &quot;ProfilePwdUpdated&quot;
PROFILEVERIFICATION | &quot;ProfileVerification&quot;
PROFILE2FA | &quot;Profile2FA&quot;
PROFILESECURITY | &quot;ProfileSecurity&quot;
TRADINGACCOUNTPWDUPDATED | &quot;TradingAccountPwdUpdated&quot;
PROGRAMNEWSANDUPDATES | &quot;ProgramNewsAndUpdates&quot;
PROGRAMENDOFPERIOD | &quot;ProgramEndOfPeriod&quot;
PROGRAMCONDITION | &quot;ProgramCondition&quot;
PROGRAMEXCEEDINVESTMENTLIMIT | &quot;ProgramExceedInvestmentLimit&quot;
FUNDNEWSANDUPDATES | &quot;FundNewsAndUpdates&quot;
FUNDENDOFPERIOD | &quot;FundEndOfPeriod&quot;
FUNDREBALANCING | &quot;FundRebalancing&quot;
MANAGERNEWPROGRAM | &quot;ManagerNewProgram&quot;
MANAGERNEWFUND | &quot;ManagerNewFund&quot;
MANAGERNEWEXTERNALSIGNALACCOUNT | &quot;ManagerNewExternalSignalAccount&quot;
SIGNALS | &quot;Signals&quot;
EXTERNALSIGNALS | &quot;ExternalSignals&quot;


<a name="AssetTypeEnum"></a>
## Enum: AssetTypeEnum
Name | Value
---- | -----
PROGRAM | &quot;Program&quot;
FUND | &quot;Fund&quot;



